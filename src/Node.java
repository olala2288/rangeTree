import java.awt.*;

public class Node {

    private Node left;
    private Node right;
    private Point point;
     double x;
     double y;
     int index;
   // FindRange findRange = new FindRange();
     int height;



    public Node(double x , double y){
        this.x = x;
        this.y = y;
        //findRange.put(y , x);
    }


    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

}
