import java.awt.Color;
import java.awt.Graphics;

public class Car {

	private double x;
	private double y;
	private double y0;
	private double dx;
	private double edge;

	public Car() {
		// TODO Auto-generated constructor stub
		x = 120;
		y0 = 400;
		y = y0;
		dx = 1;
		edge = 20;
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.fillRect((int) (x - edge / 2), (int) (y - edge / 2), (int) edge,
				(int) edge);
		g.setColor(Color.GRAY);
		g.fillRect((int) (x - 10 / 2), (int) (y - 10 / 2), (int) 10,
				(int) 10);
	}

	public void update(Graphics g) {
		// TODO Auto-generated method stub
		y += dx;
		x = y0 + (Math.sin(y / 10) * 50);
	}

}
