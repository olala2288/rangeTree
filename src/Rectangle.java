import java.awt.*;

public class Rectangle {

     Point up;
     Point down;

    public Rectangle(Point up , Point down){
        this.up = up;
        this.down = down;
    }

    public int maxY(){
        if (up.y >= down.y){
            return up.y;
        }
        else return down.y;
    }

    public int maxX(){
        if (up.x >= down.x){
            return up.x;
        }
        else return down.x;
    }

    public int minX(){
        if (up.x <= down.x){
            return up.x;
        }
        else return down.x;
    }

    public int minY(){
        if (up.y <= down.y){
            return up.y;
        }
        else return down.y;
    }

    public boolean inXLimit(int x){
        return x >= minX() && x<=maxX();
    }

    public boolean inYLimit(int y){
        return y >= minY() && y <= maxY();
    }

    public boolean inRectangle(Point point){

        return point.x >= minX() && point.y >= minY() && point.x < maxX() && point.y < maxY();
    }








}
