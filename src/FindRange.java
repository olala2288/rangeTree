import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class FindRange {

    private int x;
    private int y;
    TreeMap<Integer , Integer> point = new TreeMap<>();

    public FindRange(int y , int x){
        this . x = x;
        this. y = y;
    }

    public void put(int y , int x){
        point.put(y , x);
    }

    public ArrayList<Integer> range(int y){
        Set<Integer> set = point.keySet();
        ArrayList<Integer> list = new ArrayList<>();
        Integer[] ySet = set.toArray(new Integer[point.size()]);
        for (int i = 0 ; i < ySet.length ; i++){
            if (ySet[i] <= y){
                list.add(ySet[i]);
            }
        }

        return list;
    }

    public int getX(int y){
        return point.get(y);
    }




}
