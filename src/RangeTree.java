import java.awt.*;
import java.util.ArrayList;

public class RangeTree {

   private Node root;

   public RangeTree(){
       root = null;
    }


    public void insert(Point point){
       root = insert(root , point);
    }

    private Node insert(Node h , Point point){
       if (h == null) return new Node(point);
       h.findRange.put(point.y , point.x);
       if (point.x <= h.getPoint().x){
           h.setLeft(insert(h.getLeft() , point));
       }
       else {
           h.setRight(insert(h.getRight() , point));
       }

       return h;
    }

    private void inorder(Node r)

    {

        if (r != null)

        {

            inorder(r.getLeft());

            System.out.print(r.getPoint().x + " ");

            inorder(r.getRight());

        }

    }

    public void inorder(){
       inorder(root);
    }


    public void show(){
       ArrayList<Integer> list = root.findRange.range(5);
       System.out.println("\n"+list.size() + "->" + list.get(2));
       System.out.println("root ->" + root.getPoint().x);

    }

    public void search(Rectangle rect){
       int maxX = rect.maxX();
       int maxY = rect.maxY();
       int minX = rect.minX();
       int minY = rect.minY();
       Node help = root;

       while (help!=null && !rect.inXLimit(help.getPoint().x)){
           if (maxX < help.getPoint().x) help = help.getLeft();
           else if (help.getPoint().x < minX) help = help.getRight();

       }
       if (help==null) return;

       if (rect.inRectangle(help.getPoint())) System.out.println("("+help.getPoint().x+","+help.getPoint().y + ")");

       searchOnLeft(help.getLeft() , rect);
       searchOnRight(help.getRight() , rect);
    }

    private void searchOnLeft(Node help , Rectangle rectangle){
       if (help == null) return;
        if (rectangle.inRectangle(help.getPoint())) System.out.println("B: " + help.getPoint().x + ", " + help.getPoint().y);
        if (!(help.getPoint().x < rectangle.minX())) {

            subTree(help.getRight(), rectangle);
            searchOnLeft(help.getLeft(), rectangle);
        }
        else {
            searchOnLeft(help.getRight(), rectangle);
        }

    }

    private void searchOnRight(Node help , Rectangle rectangle){
       if (help == null) return;
        if (rectangle.inRectangle(help.getPoint())) System.out.println("C: " + help.getPoint().x + ", " + help.getPoint().y);
        if (!(help.getPoint().x < rectangle.maxX())) {

            subTree(help.getLeft(), rectangle);
            searchOnLeft(help.getRight(), rectangle);
        }
        else {
            searchOnLeft(help.getLeft(), rectangle);
        }
    }


    private void subTree(Node h , Rectangle rectangle){
        if (h == null) return;

        ArrayList<Integer> list = h.findRange.range(rectangle.maxY());
        for (int y : list) {
            int x = h.findRange.getX(y);
            System.out.println("D: " + x + ", " + y);
        }
        System.out.println("-");

    }


}
