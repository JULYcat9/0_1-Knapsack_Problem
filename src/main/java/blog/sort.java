package main.java.blog;

public class sort {
    public static void sorts(int[] unitvalue){
        //冒泡排序
        int temp;
        for(int i=0;i<unitvalue.length;i++){
            for(int j=0;j<unitvalue.length-i-1;j++){
                if(unitvalue[j]<unitvalue[j+1]){
                    temp = unitvalue[j];
                    unitvalue[j] = unitvalue[j+1];
                    unitvalue[j+1] = temp;
                }
            }
        }
        for(int i:unitvalue){
            System.out.println(i);
        }
    }

    public static void main(String[] args){
        int[] a={25,3,56,8,24,1,7,2,9,12,85,48,68,10};
        sorts(a);
    }
}
