package zero_one;

import java.awt.*;

import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.*;

public class scatterdiagram extends JPanel {

	int[] data = { 408, 921, 1329, 11, 998, 1009, 104, 839, 943, 299, 374, 673, 703, 954, 1657, 425, 950, 1375, 430,
			541, 971, 332, 483, 815, 654, 706, 1360, 956, 992, 1948}; // 输入对应的数据，是integer

	final int PAD = 20;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,

				RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();

		int h = getHeight();

// Draw ordinate. 

		g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));

// Draw abcissa.横坐标

		g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));

		double xInc = (double) (w - 2 * PAD) / (data.length - 1);

		double scale = (double) (h - 2 * PAD) / getMax();

// Mark data points.

		g2.setPaint(Color.red);

		for (int i = 0; i < data.length; i++) {

			double x = PAD + i * xInc;

			double y = h - PAD - scale * data[i];

			g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));

		}

	}

	private int getMax() {
		int max = -Integer.MAX_VALUE;

		for (int i = 0; i < data.length; i++) {
			if (data[i] > max)

				max = data[i];

		}

		return max;

	}

	public static void main(String[] args) throws Exception {

		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.add(new scatterdiagram());

		f.setSize(300, 300);

		f.setLocation(200, 200);

		f.setVisible(true);

		String path = "C:\\Users\\Administrator\\Desktop\\idkp1-10.txt";
//win系统
//String path = "c:\\hello.txt";
		FileInputStream fileInputStream = new FileInputStream(path);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}

		fileInputStream.close();

	}

}
