package zero_one;

import java.util.Arrays;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {508, 1021, 1321, 111, 1098, 1196, 204, 939, 1107, 399, 474, 719, 803, 1054, 1781, 525, 1050, 1362,
                530, 641, 903, 432, 583, 894, 754, 806, 1241, 1056, 1092, 1545,};//物品的重量
        
        int[] value = {408, 921, 1329, 11, 998, 1009, 104, 839, 943, 299, 374, 673, 703, 954, 1657, 425, 950, 1375, 430,
                541, 971, 332, 483, 815, 654, 706, 1360, 956, 992, 1948,};//物品的价值
        int m = 10149;//背包的容量
        int n = value.length;//物品的个数

        int[][] v = new int[n + 1][m + 1];//v[i][j]表示前i个物品中，能够装入容量为j的背包中的最大价值
        int[][] path = new int[n + 1][m + 1];//记录放入物品的情况

        long startTime = System.currentTimeMillis();//
        
        //初始化i = 0 和 j = 0 的值为0，可省略
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        Arrays.fill(v[0], 0);

        //动态规划进行解决
		for (int i = 1; i < v.length; i++) {// 从i=1开始
			for (int j = 1; j < v[0].length; j++) {// 从j=1开始
				if (weight[i - 1] > j) {
					v[i][j] = v[i - 1][j];
				} else {
//                    v[i][j] = Math.max(v[i - 1][j], val[i-1] + v[i - 1][j - w[i-1]]);
					if (v[i - 1][j] < value[i - 1] + v[i - 1][j - weight[i - 1]]) {
						v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
            }
        }
        //输出价值矩阵
       // System.out.printf("输出价值矩阵:");
        for (int[] a : v) {
        	
            System.out.println(Arrays.toString(a));
        }

        //从path最后开始，输出放入的路径
        int i = v.length - 1;
        int j = v[0].length - 1;
        
        System.out.printf("放入背包的商品:");
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("%d,", i);
                j -= weight[i - 1];
            }
            i--;
        }
        System.out.printf("\n");
        
        long endTime = System.currentTimeMillis();
        //  PrintStream ps = new PrintStream(“C:\Users\Administrator\Desktop\source.txt”);
          System.out.println("最优解时间：" + (double) (endTime - startTime) / 1000 + "s");
    }
}

