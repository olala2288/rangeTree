import java.awt.*;

public class Node {

    private Node left;
    private Node right;
    private Point point;


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
