import java.awt.Color;
import java.awt.Graphics;

public class DottedLines {

	private int x;
	private int y;
	private int height;

	double dy = 4;
	private double ddy = 0.003;

	public DottedLines(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		height = 80;
	}

	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(x - 5, y, 10, height);
	}

	public void update(Graphics g) {
		// TODO Auto-generated method stub
		y += dy;
		if (!ImageTryRotate.sp.gameOver)
			dy += ddy;

		if (dy > 6) {
			dy = 6;
			ddy = 0;
		}

		if (dy < 4) {
			dy = 4;
			ddy = -ddy;
		}
		if (y > ImageTryRotate.sp.getHeight()) {
			y = -1 * height;
		}
	}
}
