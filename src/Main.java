import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int[] nList = {72};
        for (int n :
                nList) {
            int iterations = 1;
            long time = 0;
            int j = 0;

            for(int i = 0; i < iterations; i++) {


                Map m = new Map("src/trains.csv", n);
                for (City c :
                        m.cities) {
                    if (c != null) ;
                }
                String s = null;
                while (s == null) {
                    int f = r.nextInt(m.cities.length);
                    if (m.cities[f] != null) s = m.cities[f].name;
                }
                City c1 = m.lookup("Stockholm");
                City c2 = m.lookup("Umeå");
                long t_Start = System.nanoTime();
                j = m.shortestTime(c1, c2);
                time += System.nanoTime() - t_Start;
            }
            System.out.println(j);
            System.out.printf("%d\t%d\n",n,time/iterations);
        }
    }

}