import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

public class RangeTree {

   private Node root;
   int[] sortArray;
   String result = null;
   String firstLine = "";
   String secondLine = "";
   DecimalFormat df = new DecimalFormat("0.#");
   FindRange findRange = new FindRange();

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

    public void inorder(Node root){
       if (root == null)
           return;
       inorder(root.getLeft());
       findRange.put(root.y , root.x);
       inorder(root.getRight());
    }

    public void search(Rectangle rect){

       firstLine = "";
       secondLine = "";
       inorder(root);
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

           firstLine += df.format(help.x) + " ";
           secondLine += df.format(help.y) + " ";
          // System.out.println("("+help.getPoint().x+","+help.getPoint().y + ")");
       }

       searchOnLeft(help.getLeft() , rect);
       searchOnRight(help.getRight() , rect);
    }

    private void searchOnLeft(Node help , Rectangle rectangle){
       if (help == null) return;
        if (rectangle.inRectangle(help.x , help.y)){
            firstLine += df.format(help.x) + " ";
            secondLine += df.format(help.y) + " ";
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
            firstLine += df.format(help.x) + " ";
            secondLine += df.format(help.y) + " ";
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

        ArrayList<Double> list = findRange.range(rectangle.minY() , rectangle.maxY());

        for (double y : list) {
            double x = findRange.getX(y);
            if (x <= rectangle.maxX()){
                firstLine += df.format(x) + " ";
                secondLine += df.format(y) + " ";
            }

        }

    }

    public void show(){
       HashSet<Double> set = new HashSet<>();
       HashSet<Double> sety = new HashSet<>();
       HashMap<Double , Double> coordinates = new HashMap<>();
       String[] s = firstLine.split(" ");
       String[] s2 = secondLine.split(" ");
       Set<Double> key;
       for (int i = 0 ; i < s.length ; i++){
           coordinates.put(Double.parseDouble(s[i]) , Double.parseDouble(s2[i]));
       }
       key = coordinates.keySet();

       String first = "";
       String second = "";
        for (double d:key){
            first += df.format(d) + " ";
            second += df.format(coordinates.get(d)) + " ";
        }
       result = first + "\n" + second;
       if (firstLine.equals("") || secondLine.equals("")){
           result = "none";
       }
       show(result);
    }

    private void show(String string){
       System.out.println(string);
    }


}
