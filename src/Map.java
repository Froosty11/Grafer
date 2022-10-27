import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Map {
    City[] cities;
    int depth;
    ArrayList<Path> paths;
    public Map(String file, int maxNodes) {
         int i= 0;
        paths = new ArrayList<>();
        cities = new City[541];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null && i++ < maxNodes) {
                line = line.replaceAll(",", " ");
                String[] array = line.split(" ");
                this.addLine(array[0],array[1], Integer.parseInt(array[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addLine(String s){
        String[] temp = s.replaceAll(",", " ").split(",");
        addLine(temp[0],temp[1],Integer.parseInt(temp[2]));
    }

    public void addLine(String t1, String t2, int distance){
        //city 1
        int hashed1 = Helper.hash(t1);
        City location;
        location = findOrAddCity(t1, hashed1);
        //city 2
        int hashed2 = Helper.hash(t2);
        City location2;
        location2 = findOrAddCity(t2, hashed2);
        location.connections.add(new Connection(location2, distance));
        location2.connections.add(new Connection(location, distance));

    }
    private City findOrAddCity(String name, int hashed){
        City location = null;
        if(cities[hashed] == null){
            location = new City(name);
            cities[hashed] = location;
        }
        else {
            City c = lookup(hashed);
            if (name.equals(c.name)) {
                location = c;
            } else System.out.println("Collision! at " + name + " " + hashed);
        }
        return location;
    }
    public Integer shortestTime(City c1, City c2){
        paths = new ArrayList<>();
        if(c2 == null  || c1 == null) return -1;
        lookThrough(c1, c2.name, new Path());
        Collections.sort(paths);
        if(paths.isEmpty()) return -1;
        for(City c : paths.get(0).connections);/* System.out.println(c.name);*/
        return paths.get(0).length;
    }
    private void lookThrough(City c, String lookingFor, Path currentPath){
        //if(depth++ > 1000) return;
        if(paths.size() > 1000000) return;
        for (Connection conn :
                c.connections) {
            if (conn == null) continue;
            if(conn.destination.name.equals(lookingFor)){
                paths.add(currentPath.addToNewPath(conn));
                //System.out.println("Found slut");
                return;
            }
        }
        for(Connection conn: c.connections){ // very bad to loop through twice but whatever lol
            if(!currentPath.connections.contains(conn.destination)){ //if that path isn't travelled
                //System.out.println(c);
                lookThrough(conn.destination, lookingFor, currentPath.addToNewPath(conn));
            }
        }
    }
    public City lookup(int hash){return cities[hash];}
    public City lookup(String s){ return cities[Helper.hash(s)];}

}
