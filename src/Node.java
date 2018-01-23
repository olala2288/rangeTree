import java.awt.*;

public class Node {

    private Node left;
    private Node right;
    private Point point;
     double x;
     double y;
    FindRange findRange = new FindRange();
     int height;


    public Node(Point point){
        setPoint(point);
       // findRange = new FindRange(point.y , point.x);
        findRange.put(point.y , point.x);
    }

    public Node(double x , double y){
        this.x = x;
        this.y = y;
        findRange.put(y , x);
    }


    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Point getPoint() {
        return point;
    }
}
