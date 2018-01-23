import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Point[] points;

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter inputs");
        int dot = scanner.nextInt();
        scanner.nextLine();
        String x = scanner.nextLine();
        String y = scanner.nextLine();
        int recs = scanner.nextInt();
        scanner.nextLine();
        Rectangle[] rectangles = new Rectangle[recs];
        int k = 0;
        while (recs > 0){
            System.out.println("enter rectangle:");
            String rec1 = scanner.nextLine();
            String[] h = rec1.split(" ");
            int uX = Integer.parseInt(h[0]);
            int uY = Integer.parseInt(h[1]);
            int dx = Integer.parseInt(h[2]);
            int dY = Integer.parseInt(h[3]);
            rectangles[k] = new Rectangle(new Point(uX , uY) , new Point(dx , dY));
            recs--;
            k++;
        }

        points = new Point[dot];
        String[] s = x.split(" ");
        String[] sy = y.split(" ");
        for (int i = 0 ; i< dot ; i ++){
            int sX = Integer.parseInt(s[i]);
            int sY = Integer.parseInt(sy[i]);
            points[i] = new Point(sX , sY);

        }



        RangeTree r = new RangeTree();

       for (int i=0 ; i<points.length ; i++){
           r.insert(points[i]);
       }

       for (int i =0 ; i <rectangles.length; i++){
           r.search(rectangles[i]);
           r.show();
       }

    }
}
