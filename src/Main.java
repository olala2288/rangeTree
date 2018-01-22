import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Point[] p = new Point[7];
        p[0] = new Point(1 , 1);
        p[1] = new Point(2 , 7);
        p[2] = new Point(3 , 5);
        p[3] = new Point(5 , 8);
        p[4] = new Point(6 , 6);
        p[5] = new Point(7 , 2);
        p[6] = new Point(9 , 3);

        RangeTree r = new RangeTree();
        for (int i = 0 ; i < p.length ; i++){
            r.insert(p[i]);
        }
        r.inorder();
        r.show();
    }
}
