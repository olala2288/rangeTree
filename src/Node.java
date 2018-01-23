import java.awt.*;

public class Node {

    private Node left;
    private Node right;
    private Point point;
    FindRange findRange = new FindRange();
     int height;


    public Node(Point point){
        setPoint(point);
       // findRange = new FindRange(point.y , point.x);
        findRange.put(point.y , point.x);
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
