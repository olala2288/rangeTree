import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RangeTree {

   private Node root;
   int[] sortArray;
   String result = null;
   String firstLine = "";
   String secondLine = "";

   public RangeTree(){
       root = null;
    }

    private int height(Node node){
       return node == null ? -1 : node.height;
    }

    private int max(int lhs, int rhs)

    {

        return lhs > rhs ? lhs : rhs;

    }

    public void insert(double x , double y){
       root = insert(x , y , root);

    }


    private Node insert(double x , double y, Node t)

    {

        if (t == null)
            t = new Node(x , y);

        t.findRange.put(y , x);

        if (x < t.x)

        {

            t.setLeft(insert(x , y , t.getLeft()));

            if( height( t.getLeft() ) - height( t.getRight() ) == 2 )

                if( x < t.getLeft().x )

                    t = rotateWithLeftChild( t );

                else

                    t = doubleWithLeftChild( t );

        }

        else if( x > t.x )

        {

            t.setRight(insert(x , y , t.getRight()));

            if( height( t.getRight() ) - height( t.getLeft() ) == 2 )

                if( x > t.getRight().x)

                    t = rotateWithRightChild( t );

                else

                    t = doubleWithRightChild( t );

        }

        else

            ;  // Duplicate; do nothing

        t.height = max( height( t.getLeft() ), height( t.getRight() ) ) + 1;


        return t;

    }


    private Node rotateWithLeftChild(Node k2)

    {

        Node k1 = k2.getLeft();

        k2.setLeft(k1.getRight());

        k1.setRight(k2);

        k2.height = max( height( k2.getLeft() ), height( k2.getRight() ) ) + 1;

        k1.height = max( height( k1.getLeft() ), k2.height ) + 1;

        return k1;

    }


    private Node rotateWithRightChild(Node k1)

    {

        Node k2 = k1.getRight();

        k1.setRight(k2.getLeft());

        k2.setLeft(k1);

        k1.height = max( height( k1.getLeft() ), height( k1.getRight() ) ) + 1;

        k2.height = max( height( k2.getRight() ), k1.height ) + 1;

        return k2;

    }



    private Node doubleWithLeftChild(Node k3)

    {

        k3.setLeft(rotateWithRightChild( k3.getLeft() ));

        return rotateWithLeftChild( k3 );

    }



    private Node doubleWithRightChild(Node k1)

    {

        k1.setRight(rotateWithLeftChild( k1.getRight() ));

        return rotateWithRightChild( k1 );

    }

    public void search(Rectangle rect){
       double maxX = rect.maxX();
       double maxY = rect.maxY();
       double minX = rect.minX();
       double minY = rect.minY();

       Node help = root;

       while (help!=null && !rect.inXLimit(help.x)){
           if (maxX < help.x) help = help.getLeft();
           else if (help.x < minX) help = help.getRight();

       }
       if (help==null) return;

       if (rect.inRectangle(help.x , help.y)){
           firstLine += help.x + " ";
           secondLine += help.y + " ";
          // System.out.println("("+help.getPoint().x+","+help.getPoint().y + ")");
       }

       searchOnLeft(help.getLeft() , rect);
       searchOnRight(help.getRight() , rect);
    }

    private void searchOnLeft(Node help , Rectangle rectangle){
       if (help == null) return;
        if (rectangle.inRectangle(help.x , help.y)){
            firstLine += help.x + " ";
            secondLine += help.y + " ";
           // System.out.println("B: (" + help.getPoint().x + ", " + help.getPoint().y+")");
        }
        if (help.x > rectangle.minX()) {

            subTree(help.getRight(), rectangle);
            searchOnLeft(help.getLeft(), rectangle);
        }
        else {
            searchOnLeft(help.getRight(), rectangle);
        }

    }

    private void searchOnRight(Node help , Rectangle rectangle){
       if (help == null) return;
        if (rectangle.inRectangle(help.x , help.y)){
            firstLine += help.x + " ";
            secondLine += help.y + " ";
            //System.out.println("C: (" + help.getPoint().x + ", " + help.getPoint().y + ")");
        }
        if (help.x > rectangle.maxX()) {

            subTree(help.getLeft(), rectangle);
            searchOnLeft(help.getRight(), rectangle);

        }
        else {
            searchOnLeft(help.getLeft(), rectangle);

        }
    }


    private void subTree(Node h , Rectangle rectangle){
        if (h == null) return;

        ArrayList<Double> list = h.findRange.range(rectangle.minY() , rectangle.maxY());
        for (double y : list) {
            double x = h.findRange.getX(y);
            firstLine += h.getPoint().x + " ";
            secondLine += h.getPoint().y + " ";
           // System.out.println("D: (" + x + ", " + y + ")");
        }

    }

    public void show(){
       result = firstLine + "\n" + secondLine;
       if (firstLine.equals("") || secondLine.equals("")){
           result = "none";
       }
       show(result);
    }

    private void show(String string){
       System.out.println(string);
    }


}
