import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

public class RangeTree {

   private Node root;
   int[] sortArray;
   String result = null;
   String firstLine = "";
   String secondLine = "";
   DecimalFormat df = new DecimalFormat("0.#");
   FindRange findRange = new FindRange();
   int minus = 0;
   MakeTree makeTree = new MakeTree();

   public RangeTree(){
       root = null;
    }

    private int height(Node node){
       return node == null ? -1 : node.height;
    }

    private int max(int lhs, int rhs)

    {

        return lhs > rhs ? lhs : rhs;

    }

    public void insert(double x , double y){

       root = makeTree.insert(x , y , root);

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

       ArrayList<Double> answers = new ArrayList<>();

       if (minus != 0){
           for (int i = 0 ; i < h ; i ++){
               int index = minus - (h - i);
               double y = findRange.getY(findRange.xAxis.get(index));
               if (rect.inYLimit(y)){
                   answers.add(y);
               }
           }
       }
       else {
           for (int i = 0 ; i < h ; i ++){
               int index = i;
               double y = findRange.getY(findRange.xAxis.get(index));
               if (rect.inYLimit(y)){
                   answers.add(y);
               }
           }
       }

       findAnswer(answers , rect);

    }

    public void findAnswer(ArrayList<Double> answer , Rectangle rectangle){
       for (double d:answer){
           double x = findRange.getX(d);
           if (rectangle.inXLimit(x)){
               firstLine += df.format(x) + " ";
               secondLine += df.format(d) + " ";
           }
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
       if (firstLine.equals("") || secondLine.equals("")){
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
