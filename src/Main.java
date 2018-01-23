import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Point[] points;
        Double[] doublesX;
        Double[] doublesY;

        Scanner scanner = new Scanner(System.in);
        int dot = scanner.nextInt();
        doublesX = new Double[dot];
        doublesY = new Double[dot];
        scanner.nextLine();
        String x = scanner.nextLine();
        String y = scanner.nextLine();
        int recs = scanner.nextInt();
        scanner.nextLine();
        Rectangle[] rectangles = new Rectangle[recs];
        int k = 0;
        while (recs > 0){
            String rec1 = scanner.nextLine();
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

        }



        RangeTree r = new RangeTree();

       for (int i=0 ; i<dot ; i++){
           r.insert(doublesX[i] , doublesY[i]);
       }

       for (int i =0 ; i <rectangles.length; i++){
           r.search(rectangles[i]);
           r.show();
       }

    }
}
