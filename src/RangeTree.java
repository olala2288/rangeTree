import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

public class RangeTree {

   private Node root;
   String result = null;
   String firstLine = "";
   String secondLine = "";
   DecimalFormat df = new DecimalFormat("0.#");
   FindRange findRange = new FindRange();
   int minus = 0;
   MakeTree makeTree = new MakeTree();
   ArrayList<MyPoint> points = new ArrayList<>();

    /**
     * should make primary tree right so you can get the right answer
     *
     */

   public RangeTree(){
       root = null;
    }


    public void insert(double x , double y){

       root = makeTree.insert(x , y , root);
    }

    public void setPoints(ArrayList<MyPoint> points){
       this.points = points;
    }



    public void inorder(Node root){
       if (root == null)
           return;
       inorder(root.getLeft());
       findRange.put(root.y , root.x);
       inorder(root.getRight());
    }

    public void search(Rectangle rect){
       firstLine = "";
       secondLine = "";
       findRange.xAxis.clear();
       findRange.yAxis.clear();
       findRange.point.clear();
       findRange.point2.clear();
       inorder(root);
       double maxX = rect.maxX();
       double maxY = rect.maxY();
       double minX = rect.minX();
       double minY = rect.minY();

       Node help = root;

       while (help!=null && !rect.inXLimit(help.x)){
           if (maxX < help.x){
               help = help.getLeft();
               minus = 0;
           }
           else if (help.x < minX){
               help = help.getRight();
               minus = findRange.xAxis.size();
           }

       }

       if (help==null) return;


       int h = countSpot(help);

       if (minus != 0){
           for (int i = 0 ; i < h ; i ++){
               int index = minus - (h - i);
               double y = findRange.getY(findRange.xAxis.get(index));
               //double y = findRange.yAxis.get(index);
               if (rect.inYLimit(y)){
                   double x = findRange.getX(y);
                   if (rect.inXLimit(x)){
                       firstLine += df.format(x) + " ";
                       secondLine += df.format(y) + " ";
                   }
               }
           }
       }
       else {
           for (int i = 0 ; i < h ; i ++){
               int index = i;
               double y = findRange.getY(findRange.xAxis.get(index));
               if (rect.inYLimit(y)){
                   double x = findRange.getX(y);
                   if (rect.inXLimit(x)){
                       firstLine += df.format(x) + " ";
                       secondLine += df.format(y) + " ";
                   }
               }
           }
       }

       if (h == 1 && minus == findRange.xAxis.size()){

       }
       if (h == 1 && minus == 0){

       }



    }




    public void show(){

       //String[] f = firstLine.split(" ");
        String sec = "";
        String fir = "";
       String[] s = secondLine.split(" ");
       if (!secondLine.equals("")){
           MergeSort sort = new MergeSort();
           double[] dd = new double[s.length];
           for (int i = 0 ; i< s.length ; i++){
               dd[i] = Double.parseDouble(s[i]);
           }

           dd = sort.sort(dd);

           for (double d : dd){
               sec += df.format(d) + " ";
               fir += df.format(findRange.getX(d))+ " ";
           }
       }


       result = fir + "\n" + sec;
       if (firstLine.equals("") && secondLine.equals("")){
           result = "None";
       }
       show(result);
    }

    private void show(String string){

       System.out.println(string);
    }


    public int countSpot(Node node){
       if (node == null) return 0;
       return 1 + countSpot(node.getLeft()) + countSpot(node.getRight());
    }

    public boolean isLeaf(Node node){
        if (node.getLeft()==null && node.getRight() == null){
            return true;
        }
        else return false;
    }




}
