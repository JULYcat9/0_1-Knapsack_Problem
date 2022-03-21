package main.java.blog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class M {
    private static String[] cubage=new String[12];
    private static int profits[][] = new int[12][4000];
    private static int weights[][] = new int[12][4000];
    private static int choosezu;

    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        do {
            System.out.println("|*************************************************************************");
            System.out.println("|**************************输入1：绘制散点图********************************");
            System.out.println("|**************************输入2：数据项集排序******************************");
            System.out.println("|**************************输入3：动态规划法D{0-1}问题求解*******************");
            System.out.println("|**************************输入4：回溯法D{0-1}问题求解***********************");
            System.out.println("|**************************输入5：D{0-1}问题求解并保存文件*******************");
            System.out.println("|**************************输入6：退出程序**********************************");
            System.out.println("|**************************************************************************");
            System.out.println("请输入你的选择：");
            choice=scanner.nextInt();
            if (choice==1) {
                readFile();
                draw();
            }else if(choice==2){
                readFile();
                dataOrder(scanner);
            }else if(choice==3){
                readFile();
                System.out.println("输入你想要选择的组数：");
                choosezu = scanner.nextInt();
                System.out.println();
                int C = Integer.parseInt(cubage[choosezu-1]);
                //System.out.println(C);
                int count = profits[choosezu-1].length;
                knaspace(count,C);
            }else if(choice==4){
                readFile();
                System.out.println("输入你想要选择的组数：");
                int groups = scanner.nextInt();
                int c = Integer.parseInt(cubage[groups-1]);
                int count=0;
                for (int i : profits[groups - 1]) {
                    if(i!=0){
                        count++;
                    }
                }
                int[][] p=new int[count/10+1][3];
                int[][] w=new int[count/10+1][3];

                for (int i = 0; i <3 ; i++) {
                    p[0][i]=0;
                    w[0][i]=0;
                }
                int j=1;
                int total=0;
                for (int i = 0; i < count; i++) {
                    if(total==3){
                        total=0;
                        j++;
                    }
                    p[j][total]=profits[groups - 1][i];
                    total++;
                }
            }
        }while(choice!=6);
    }

    /**
     * 对项集排序
     * @param scanner
     */
    private static void dataOrder(Scanner scanner) {
        System.out.println("输入将要排序的组数:");
        int group=scanner.nextInt();
        List<item> items = new ArrayList<>();
        for (int i1 = 0; i1 < profits[group - 1].length; i1++) {
            if(profits[group - 1][i1]!=0){
                item item = new item();
                item.setProfit(profits[group - 1][i1]);
                item.setWeight(weights[group - 1][i1]);
                items.add(item);
            }
        }
        ArrayList<sortList> sortLists = new ArrayList<>();
        for (int k = 0; k < items.size(); k=k+3) {
            List<item> items1 = items.subList(k, k + 3);
            sortList sortList = new sortList();
            sortList.setData(items1);
            item item = items1.get(items1.size() - 1);
            float v = item.getProfit() / new Float(item.getWeight());
            sortList.setRate(v);
            sortLists.add(sortList);
        }

        Collections.sort(sortLists);
        for (sortList sortList : sortLists) {
            System.out.println(sortList.toString());
        }
    }

    /**
     * 画散点图
     * @throws IOException
     */
    private static void drawScatter(int[] profitList,int[] weightList) throws IOException {
        //设置散点图
        XYSeries firefox = new XYSeries("背包问题");
        for (int i = 0,j=0; i < profitList.length; i++,j++) {
            firefox.add(weightList[i],profitList[j]);
        }
        //添加到数据集
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(firefox);
        //实现简单的散点图，设置基本的数据
        JFreeChart freeChart = ChartFactory.createScatterPlot(
                "数据散点图",// 图表标题
                "重量",//x轴方向数据标签
                "价值",//y轴方向数据标签
                dataset,//数据集，即要显示在图表上的数据
                PlotOrientation.VERTICAL,//设置方向
                true,//是否显示图例
                true,//是否显示提示
                false//是否生成URL连接
        );
        //以面板显示
        ChartPanel chartPanel = new ChartPanel(freeChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 400));
        //创建一个主窗口来显示面板
        JFrame frame = new JFrame("散点图");
        frame.setLocation(500, 200);
        frame.setSize(1000, 800);

        //将主窗口的内容面板设置为图表面板
        frame.setContentPane(chartPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void draw() throws IOException {
        //绘制散点图
        System.out.println("请输入所要绘制的散点图的组数： ");
        Scanner scanner = new Scanner(System.in);
        int group=scanner.nextInt();
        drawScatter(profits[group-1],weights[group-1]);
    }

    /**
     * 读取文件
     * @throws IOException
     */
    private static void readFile() throws IOException {
        String rootPath="C:\\Users\\ASUS\\Desktop\\demo\\data_set";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件名 (1、idkp1-10；  2、sdkp1-10；  3、udkp1-10；  4、wdkp1-10)");
        int fileName=scanner.nextInt();
        switch (fileName){
            case 1:
                rootPath="C:\\Users\\ASUS\\Desktop\\demo\\data_set\\idkp1-10.txt";
                break;
            case 2:
                rootPath="C:\\Users\\ASUS\\Desktop\\demo\\data_set\\sdkp1-10.txt";
                break;
            case 3:
                rootPath="C:\\Users\\ASUS\\Desktop\\demo\\data_set\\udkp1-10.txt";
                break;
            case 4:
                rootPath="C:\\Users\\ASUS\\Desktop\\demo\\data_set\\wdkp1-10.txt";
                break;
        }
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(rootPath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        //读取文件中的一行数据

        //存放物品价值和质量的列表
        ArrayList<String> profitList = new ArrayList<>();
        ArrayList<String> weightList = new ArrayList<>();
        int packnum=0;
        while((str = bufferedReader.readLine()) != null)
        {
            if(str.contains("cubage of knapsack")){
                String replace = str.replace(".", "");
                String[] s = replace.split(" ");
                cubage[packnum]=s[s.length-1];
                //System.out.println("==========请输出背包的容量："+cubage[packnum]);
                packnum++;
            }
            if(str.contains("The profit of")){
                profitList.add(bufferedReader.readLine());
            }
            if(str.contains("The weight of")){
                weightList.add(bufferedReader.readLine());
            }
        }
        //遍历物品重量，将其分割出来
        //System.out.println("输出所有物品价值：");
        int i=0,j=0;
        for (String s : profitList) {
            j=0;
            String replace = s.replace(".", "");
            String[] split = replace.split(",");
            for (String s1 : split) {
                //字符型转整形
                profits[i][j] =  Integer.parseInt(s1);
                //System.out.println("输出第"+i+"组第"+j+"个物品的价值："+profits[i][j]);
                j++;
            }
            i++;
        }
        //遍历物品重量，将其分割出来
        //System.out.println("\n输出所有物品重量：\n");
        int m=0,n;
        for (String s : weightList) {
            n=0;
            String replace = s.replace(".", "");
            String[] split = replace.split(",");
            for (String s1 : split){
                //字符型转整形
                weights[m][n] = Integer.parseInt(s1);
                //System.out.println("输出第"+m+"组第"+n+"个物品的重量："+weights[m][n]);
                n++;
            }
            m++;
        }
    }
    /**
     *回溯算法
     */
    public void  recursion(ArrayList<Integer> ret, int volume, int[][] p, int[][] w, int totalProfit, int totalWeight, int i, int j){
        if(j!=3){
//          相当于不选当前的项集
            totalProfit=totalProfit+p[i][j];
            totalWeight=totalWeight+w[i][j];
        }
//      如果加上当前物品时总重量超过了背包容量则返回上一级
        if(totalWeight>volume){
            ret.add(totalProfit);
            return;
        }
        if(i==p.length-1){
            ret.add(totalProfit);
            return;
        }
        for (int k = 0; k <4 ; k++) {
            recursion(ret,volume,p,w,totalProfit,totalWeight,i+1,k);
        }
    }

    /**
     * 动态规划算法
     * @throws IOException
     */
    public static int knaspace(int count,int maxweight) throws IOException {
        long start = Calendar.getInstance().getTimeInMillis();
        int n = weights[choosezu].length;
        int[][] maxvalue = new int[n + 1][maxweight + 1];
        for (int i = 0; i < maxweight + 1; i++) {
            maxvalue[0][i] = 0;
            //System.out.print(maxvalue[0][i]);
        }
        for (int i = 0; i < n + 1; i++) {
            maxvalue[i][0] = 0;
           // System.out.print(maxvalue[i][0]);
        }
        for (int i = 1; i <= count; i++) {
            for (int j = 1; j <= maxweight; j++) {
                maxvalue[i][j] = maxvalue[i - 1][j];
                if (weights[choosezu-1][i-1] <= j) {
                    if (maxvalue[i - 1][j - weights[choosezu-1][i - 1]] + profits[choosezu-1][i - 1] > maxvalue[i - 1][j]) {
                        maxvalue[i][j] = maxvalue[i - 1][j - weights[choosezu-1][i - 1]] + profits[choosezu-1][i - 1];
                    }
                   // System.out.println("=========== weight="+weights[choosezu-1][i - 1]+"  value="+profits[choosezu-1][i - 1]+"  "+maxvalue[i][j]);
                }
            }
        }
        int resule = maxvalue[count][maxweight];
        long end = Calendar.getInstance().getTimeInMillis();
        double spentTime = (double) end - start;
        System.out.println("得到背包的最大价值为： "+resule);
        System.out.println("程序运行时间：" + spentTime/1000 + "s");
        write(resule,spentTime);
        return 0;
    }
    /**
     * 结果写入文件
     * @throws IOException
     */
    private static void write(int results,double time) throws IOException {
        File file =new File("C:\\Users\\ASUS\\Desktop\\demo\\source.txt");
        Writer out =new FileWriter(file);
        out.write("得到背包的最大价值为: "+results);
        out.write("      程序运行时间: "+time);
        out.close();
    }
}


