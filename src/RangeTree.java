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
       System.out.println(point.x);
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


}
