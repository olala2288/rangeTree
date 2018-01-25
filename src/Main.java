import java.awt.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<MyPoint> points = new ArrayList<>();
        Double[] doublesX;
        Double[] doublesY;
        int dot = 0;
        Scanner scanner = new Scanner(System.in);
        int lines = 0;
        while (scanner.hasNext()){
            dot = scanner.nextInt();
            doublesX = new Double[dot];
            doublesY = new Double[dot];
            if (lines>10000) break;
            scanner.nextLine();
            lines++;
            String x = scanner.nextLine();
            lines++;
            String y = scanner.nextLine();
            lines++;
            int recs = scanner.nextInt();
            scanner.nextLine();
            lines++;
            Rectangle[] rectangles = new Rectangle[recs];
            int k = 0;
            while (recs > 0){
                String rec1 = scanner.nextLine();
                lines++;
                String[] h = rec1.split(" ");
                double uX = Double.parseDouble(h[0]);
                double uY = Double.parseDouble(h[1]);
                double dx = Double.parseDouble(h[2]);
                double dY = Double.parseDouble(h[3]);
                rectangles[k] = new Rectangle(uX , uY , dx , dY);
                recs--;
                k++;
            }

            String[] s = x.split(" ");
            String[] sy = y.split(" ");
            for (int i = 0 ; i< dot ; i ++){
                double sX = Double.parseDouble(s[i]);
                double sY = Double.parseDouble(sy[i]);
                doublesX[i] = sX;
                doublesY[i] = sY;
                points.add(new MyPoint(sX , sY));
            }

            Collections.sort(points, new Comparator<MyPoint>() {
                @Override
                public int compare(MyPoint p1, MyPoint p2) {
                    return Double.compare(p1.x , p2.x);
                }
            });




            RangeTree r = new RangeTree();
            r.setPoints(points);

            for (int i=0 ; i<dot ; i++){
                r.insert(points.get(i).x , points.get(i).y);
            }

            for (int i =0 ; i <rectangles.length; i++){
                r.search(rectangles[i]);
                r.show();
            }
        }


    }
}
