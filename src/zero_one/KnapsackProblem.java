package zero_one;

import java.util.Arrays;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {508, 1021, 1321, 111, 1098, 1196, 204, 939, 1107, 399, 474, 719, 803, 1054, 1781, 525, 1050, 1362,
                530, 641, 903, 432, 583, 894, 754, 806, 1241, 1056, 1092, 1545,};//��Ʒ������
        
        int[] value = {408, 921, 1329, 11, 998, 1009, 104, 839, 943, 299, 374, 673, 703, 954, 1657, 425, 950, 1375, 430,
                541, 971, 332, 483, 815, 654, 706, 1360, 956, 992, 1948,};//��Ʒ�ļ�ֵ
        int m = 10149;//����������
        int n = value.length;//��Ʒ�ĸ���

        int[][] v = new int[n + 1][m + 1];//v[i][j]��ʾǰi����Ʒ�У��ܹ�װ������Ϊj�ı����е�����ֵ
        int[][] path = new int[n + 1][m + 1];//��¼������Ʒ�����

        long startTime = System.currentTimeMillis();//
        
        //��ʼ��i = 0 �� j = 0 ��ֵΪ0����ʡ��
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        Arrays.fill(v[0], 0);

        //��̬�滮���н��
		for (int i = 1; i < v.length; i++) {// ��i=1��ʼ
			for (int j = 1; j < v[0].length; j++) {// ��j=1��ʼ
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
        //�����ֵ����
       // System.out.printf("�����ֵ����:");
        for (int[] a : v) {
        	
            System.out.println(Arrays.toString(a));
        }

        //��path���ʼ����������·��
        int i = v.length - 1;
        int j = v[0].length - 1;
        
        System.out.printf("���뱳������Ʒ:");
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("%d,", i);
                j -= weight[i - 1];
            }
            i--;
        }
        System.out.printf("\n");
        
        long endTime = System.currentTimeMillis();
        //  PrintStream ps = new PrintStream(��C:\Users\Administrator\Desktop\source.txt��);
          System.out.println("���Ž�ʱ�䣺" + (double) (endTime - startTime) / 1000 + "s");
    }
}

