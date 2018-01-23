import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class FindRange {

    private int x;
    private int y;
    HashMap<Integer , Integer> point = new HashMap<>();
    ArrayList<Integer> yAxis = new ArrayList<>();

    public FindRange(int y , int x){
        this . x = x;
        this. y = y;
    }
    public FindRange(){

    }

    public void put(int y , int x){
        point.put(y , x);
        yAxis.add(y);
    }

    public ArrayList<Integer> range(int minY , int maxY){

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0 ; i < yAxis.size() ; i++){
            if (yAxis.get(i) <= maxY && yAxis.get(i) >= minY){
                list.add(yAxis.get(i));
            }
        }


        return list;
    }

    public int getX(int y){
        return point.get(y);
    }



}
