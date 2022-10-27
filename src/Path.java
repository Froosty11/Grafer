import java.util.ArrayList;

public class Path implements Comparable<Path>{
    Integer length;
    ArrayList<City> connections;
    public Path(){
        connections = new ArrayList<>();
        length = 0;
    }

    public Path addToNewPath(Connection c){
        Path n = new Path();
        n.connections.addAll(connections);
        n.connections.add(c.destination);
        n.length += c.minutes + this.length;
        return n;
    }

    @Override
    public int compareTo(Path path) {
        return this.length.compareTo(path.length);
    }
}
