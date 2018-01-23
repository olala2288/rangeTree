import java.awt.*;

public class Rectangle {

     Point up;
     Point down;
     double upX;
     double upY;
     double downX;
     double downY;

    public Rectangle( double upX , double upY ,  double downX , double downY){
        this.upX = upX;
        this.upY = upY;
        this.downX = downX;
        this.downY = downY;
    }

    public double maxY(){
        if (upY >= downY){
            return upY;
        }
        else return downY;
    }

    public double maxX(){
        if (upX >= downX){
            return upX;
        }
        else return downX;
    }

    public double minX(){
        if (upX <= downX){
            return upX;
        }
        else return down.x;
    }

    public double minY(){
        if (upY <= downY){
            return upY;
        }
        else return downY;
    }

    public boolean inXLimit(double x){
        return x >= minX() && x<=maxX();
    }

    public boolean inYLimit(double y){
        return y >= minY() && y <= maxY();
    }

    public boolean inRectangle(double x , double y){

        return x >= minX() && y >= minY() && x <= maxX() && y <= maxY();
    }








}
