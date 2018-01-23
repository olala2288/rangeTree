import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class FindRange {

    private double x;
    private double y;
    HashMap<Double , Double> point = new HashMap<>();
    ArrayList<Double> yAxis = new ArrayList<>();

    public FindRange(double y , double x){
        this . x = x;
        this. y = y;
    }
    public FindRange(){

    }

    public void put(double y , double x){
        point.put(y , x);
        yAxis.add(y);
    }

    public ArrayList<Double> range(double minY , double maxY){

        ArrayList<Double> list = new ArrayList<>();

        for (int i = 0 ; i < yAxis.size() ; i++){
            if (yAxis.get(i) <= maxY && yAxis.get(i) >= minY){
                list.add(yAxis.get(i));
            }
        }


        return list;
    }

    public double getX(double y){
        return point.get(y);
    }



}
