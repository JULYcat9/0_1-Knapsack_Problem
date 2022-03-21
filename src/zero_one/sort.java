package zero_one;

public class sort {
	//public class sort {
		public static void main(String[] args) {
			// 单位重量价值分别为:10 5 7 6 3 8 90 100
			double w[] = { 508, 1021, 1321, 111, 1098, 1196, 204, 939, 1107, 399, 474, 719, 803, 1054, 1781, 525, 1050,
					1362, 530, 641, 903, 432, 583, 894, 754, 806, 1241, 1056, 1092, 1545 };// 物体的重量

			double v[] = { 408, 921, 1329, 11, 998, 1009, 104, 839, 943, 299, 374, 673, 703, 954, 1657, 425, 950, 1375, 430,
					541, 971, 332, 483, 815, 654, 706, 1360, 956, 992, 1948 };// 物体的价值

			double M = 10149;// 背包所能容纳的重量

			int n = w.length - 1;// 物体的个数

			double[] x = new double[n + 1];// 每个物体装进的比例,大于等于0并且小于等于1

			// f(w, v, M, n, x);//调用贪心算法函数

			double[] t = new double[n + 1];// 定义一个数组表示单位重量物体的价值
			for (int i = 1; i <= n; i++) {
				t[i] = v[i] / w[i];
			}
			// 用冒泡排序对double[]t进行排序(大的在前)
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n - i; j++) {
					if (t[j] < t[j + 1]) {
						double temp = t[j];
						t[j] = t[j + 1];
						t[j + 1] = temp;
					}
				}
			}
			System.out.println("项集第三项的价值:重量比进行非递增排序: ");
			for (int i = 1; i <= n; i++) {
				System.out.print(t[i] + "\n");
			}

			double maxValueSum = 0; // 用来存放背包能装下的物体的最大价值总和
			for (int i = 1; i < x.length; i++) {
				maxValueSum += x[i] * v[i];
			}
			System.out.println();

		}
	}
