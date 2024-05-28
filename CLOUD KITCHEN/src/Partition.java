import java.util.*;
public class Partition {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5};
        int k = 3;
        ArrayList <ArrayList<Integer>> res = new ArrayList <> ();
        printAllPartitions(arr , k , res , 0);


    }

    public static void printAllPartitions(int [] arr , int k , ArrayList <ArrayList<Integer>> res , int index){
        ArrayList<Integer> chain = new ArrayList <> ();
        int i = index;
        for(i = index ; i < arr.length ; i++){
            chain.add(arr[i]);
            res.add(new ArrayList <> (chain));
            if(k > 0) printAllPartitions(arr , k-1 , res , i+1);
            res.remove(res.size()-1);
        }
        if(i == arr.length-1){
            for(ArrayList<Integer> a : res) System.out.print(a + " ");
            System.out.println();
        }

    }
}
