import java.util.ArrayList;

public class City {
    ArrayList<Connection> connections;
    String name;
    int hashedName;
    public City(String name){
        connections = new ArrayList<>();
        this.name = name;
        this.hashedName = Helper.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.name).append("\n");
        for (Connection con : connections)
            if(con != null)
            s.append(" -> ").append(con.destination.name).append(" - ").append(con.minutes).append(" min \n");
        return s.toString();
    }
}
