package zero_one;

public class sort {
	//public class sort {
		public static void main(String[] args) {
			// ��λ������ֵ�ֱ�Ϊ:10 5 7 6 3 8 90 100
			double w[] = { 508, 1021, 1321, 111, 1098, 1196, 204, 939, 1107, 399, 474, 719, 803, 1054, 1781, 525, 1050,
					1362, 530, 641, 903, 432, 583, 894, 754, 806, 1241, 1056, 1092, 1545 };// ���������

			double v[] = { 408, 921, 1329, 11, 998, 1009, 104, 839, 943, 299, 374, 673, 703, 954, 1657, 425, 950, 1375, 430,
					541, 971, 332, 483, 815, 654, 706, 1360, 956, 992, 1948 };// ����ļ�ֵ

			double M = 10149;// �����������ɵ�����

			int n = w.length - 1;// ����ĸ���

			double[] x = new double[n + 1];// ÿ������װ���ı���,���ڵ���0����С�ڵ���1

			// f(w, v, M, n, x);//����̰���㷨����

			double[] t = new double[n + 1];// ����һ�������ʾ��λ��������ļ�ֵ
			for (int i = 1; i <= n; i++) {
				t[i] = v[i] / w[i];
			}
			// ��ð�������double[]t��������(�����ǰ)
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n - i; j++) {
					if (t[j] < t[j + 1]) {
						double temp = t[j];
						t[j] = t[j + 1];
						t[j + 1] = temp;
					}
				}
			}
			System.out.println("�������ļ�ֵ:�����Ƚ��зǵ�������: ");
			for (int i = 1; i <= n; i++) {
				System.out.print(t[i] + "\n");
			}

			double maxValueSum = 0; // ������ű�����װ�µ����������ֵ�ܺ�
			for (int i = 1; i < x.length; i++) {
				maxValueSum += x[i] * v[i];
			}
			System.out.println();

		}
	}
