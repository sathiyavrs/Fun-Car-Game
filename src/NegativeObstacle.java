import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class NegativeObstacle {

	private double x;
	double y;
	private double ddy = 0.003;
	private double leftW;
	private double rightW;

	private Line line;
	double dy = 4;

	double xAng;
	double yAng;
	double varAng;

	private double rectWidth;
	private double rectHeight;

	private int effect; // Takes care of the Colors of the Obstacle

	Color a;
	Random rand = new Random();

	private boolean changeColor = true;
	private boolean hasCollided = false;

	double a1, a2, b1, b2;
	double p1, p2, q1, q2;

	double yComp0, xComp0, yComp1, xComp1;

	public NegativeObstacle(Line line) {
		// TODO Auto-generated constructor stub
		x = 162.5;
		y = 0;
		leftW = 20;
		rightW = 20;
		this.line = line;
		effect = rand.nextInt(2);

		xAng = line.xAng;
		yAng = line.yAng;
		varAng = line.varAng;
		rectWidth = line.rectWidth;
		rectHeight = line.rectHeight;
	}

	public NegativeObstacle(Line line, double x, double y) {
		this.x = x;
		this.y = y;
		this.line = line;
		leftW = 20;
		rightW = 20;

		xAng = line.xAng;
		yAng = line.yAng;
		varAng = line.varAng;
		rectWidth = line.rectWidth;
		rectHeight = line.rectHeight;
	}

	public void paint(Graphics g) {

		if (changeColor) {
			if (effect == 0) {
				a = Color.RED;
			} else
				a = Color.GREEN;
		} else
			a = Color.ORANGE;
		g.setColor(a);

		g.fillRect((int) (x - leftW), (int) (y - 10), (int) (leftW + rightW),
				20);

		g.setColor(Color.WHITE);
		g.drawRect((int) (x - leftW), (int) (y - 10),
				(int) (leftW + rightW - 0), 20 - 0);
		g.drawRect((int) (x - leftW + 1), (int) (y - 10 + 1), (int) (leftW
				+ rightW - 0 - 2), 20 - 2);

	}

	public void update(Graphics g) {
		if (!line.sp.gameOver)
			dy += ddy;

		if (dy > 6) {
			dy = 6;
			ddy = 0;
			System.out.println("INNN");
		}

		if (dy < 4) {
			dy = 4;
			ddy = -ddy;
		}
		checkForCollision(line);

		// if (hasCollided)
		// changeColor = true;
		// else
		y += dy;

		if (hasCollided) {
			if (effect == 0)
				line.sp.gameOver = true;
			else {
				hasCollided = true;
				// effect = rand.nextInt(2);
				// y = -200 - rand.nextInt(200);
				y = line.sp.getHeight() + 200;
			}
		}

		if (y > 640) {
			if (!hasCollided) {
				if (effect == 1)
					line.sp.gameOver = true;
			}

			if (hasCollided) {
				if (effect == 1) {
					y = line.sp.getHeight() + 200;
				}
			}

			// if (effect == 0) {
			// effect = rand.nextInt(2);
			// y = -200 - rand.nextInt(100);
			// }
		}
	}

	public void checkForCollision(Line line) {

		xAng = line.xAng;
		yAng = line.yAng;
		varAng = line.varAng;
		rectWidth = line.rectWidth;
		rectHeight = line.rectHeight;

		// Top Line First
		a1 = xAng + rectWidth / 2;
		a2 = xAng - rectWidth / 2;
		b1 = yAng + rectHeight / 2;
		b2 = yAng + rectHeight / 2;

		p1 = a1 * Math.cos(Math.toRadians(varAng - 90)) + b1
				* Math.sin(Math.toRadians(varAng - 90));
		p2 = b1 * Math.cos(Math.toRadians(varAng - 90)) - a1
				* Math.sin(Math.toRadians(varAng - 90));
		q1 = a2 * Math.cos(Math.toRadians(varAng - 90)) + b2
				* Math.sin(Math.toRadians(varAng - 90));
		q2 = b2 * Math.cos(Math.toRadians(varAng - 90)) - a2
				* Math.sin(Math.toRadians(varAng - 90));

		yComp0 = p2 + ((q2 - p2) / (q1 - p1)) * ((x - leftW) - p1);
		yComp1 = p2 + ((q2 - p2) / (q1 - p1)) * ((x + rightW) - p1);

		if ((x - leftW) >= p1 && (x - leftW) <= q1) {
			if ((yComp0 >= (y - 10)) && (yComp0 <= (y + 10))) {
				hasCollided = true;
			}
		}

		if ((x + rightW) >= p1 && (x + rightW) <= q1) {
			if ((yComp1 >= (y - 10)) && (yComp1 <= (y + 10))) {
				hasCollided = true;
			}
		}

		// Bottom Line
		a1 = xAng + rectWidth / 2;
		a2 = xAng - rectWidth / 2;
		b1 = yAng - rectHeight / 2;
		b2 = yAng - rectHeight / 2;

		p1 = a1 * Math.cos(Math.toRadians(varAng - 90)) + b1
				* Math.sin(Math.toRadians(varAng - 90));
		p2 = b1 * Math.cos(Math.toRadians(varAng - 90)) - a1
				* Math.sin(Math.toRadians(varAng - 90));
		q1 = a2 * Math.cos(Math.toRadians(varAng - 90)) + b2
				* Math.sin(Math.toRadians(varAng - 90));
		q2 = b2 * Math.cos(Math.toRadians(varAng - 90)) - a2
				* Math.sin(Math.toRadians(varAng - 90));

		yComp0 = p2 + ((q2 - p2) / (q1 - p1)) * ((x - leftW) - p1);
		yComp1 = p2 + ((q2 - p2) / (q1 - p1)) * ((x + rightW) - p1);

		if ((x - leftW) >= p1 && (x - leftW) <= q1) {
			if ((yComp0 >= (y - 10)) && (yComp0 <= (y + 10))) {
				hasCollided = true;
			}
		}

		if ((x + rightW) >= p1 && (x + rightW) <= q1) {
			if ((yComp1 >= (y - 10)) && (yComp1 <= (y + 10))) {
				hasCollided = true;
			}
		}

		// Left Line
		a1 = xAng + rectWidth / 2;
		a2 = xAng + rectWidth / 2;
		b1 = yAng + rectHeight / 2;
		b2 = yAng - rectHeight / 2;

		p1 = a1 * Math.cos(Math.toRadians(varAng - 90)) + b1
				* Math.sin(Math.toRadians(varAng - 90));
		p2 = b1 * Math.cos(Math.toRadians(varAng - 90)) - a1
				* Math.sin(Math.toRadians(varAng - 90));
		q1 = a2 * Math.cos(Math.toRadians(varAng - 90)) + b2
				* Math.sin(Math.toRadians(varAng - 90));
		q2 = b2 * Math.cos(Math.toRadians(varAng - 90)) - a2
				* Math.sin(Math.toRadians(varAng - 90));

		xComp0 = p1 + ((q1 - p1) / (q2 - p2)) * ((y - 10) - p2);
		xComp1 = p1 + ((q1 - p1) / (q2 - p2)) * ((y + 10) - p2);

		if ((y + 10) >= p2 && (y + 10) <= q2) {
			if (xComp1 >= (x - leftW) && xComp1 <= (x + rightW)) {
				hasCollided = true;
			}
		}

		if ((y - 10) >= p2 && (y + 10) <= q2) {
			if (xComp0 >= (x - leftW) && xComp0 <= (x + rightW)) {
				hasCollided = true;

			}
		}

		// Right Line
		a1 = xAng - rectWidth / 2;
		a2 = xAng - rectWidth / 2;
		b1 = yAng + rectHeight / 2;
		b2 = yAng - rectHeight / 2;

		p1 = a1 * Math.cos(Math.toRadians(varAng - 90)) + b1
				* Math.sin(Math.toRadians(varAng - 90));
		p2 = b1 * Math.cos(Math.toRadians(varAng - 90)) - a1
				* Math.sin(Math.toRadians(varAng - 90));
		q1 = a2 * Math.cos(Math.toRadians(varAng - 90)) + b2
				* Math.sin(Math.toRadians(varAng - 90));
		q2 = b2 * Math.cos(Math.toRadians(varAng - 90)) - a2
				* Math.sin(Math.toRadians(varAng - 90));

		xComp0 = p1 + ((q1 - p1) / (q2 - p2)) * ((y - 10) - p2);
		xComp1 = p1 + ((q1 - p1) / (q2 - p2)) * ((y + 10) - p2);

		if ((y + 10) >= p2 && (y + 10) <= q2) {
			if (xComp1 >= (x - leftW) && xComp1 <= (x + rightW)) {
				hasCollided = true;
			}
		}

		if ((y - 10) >= p2 && (y + 10) <= q2) {
			if (xComp0 >= (x - leftW) && xComp0 <= (x + rightW)) {
				hasCollided = true;

			}
		}

	}

	public void setColor(boolean isGreen) {
		if (isGreen) {
			a = Color.GREEN;
			effect = 1;
		} else {
			a = Color.RED;
			effect = 0;
		}
	}

	public boolean isHasCollided() {
		return hasCollided;
	}

	public void setHasCollided(boolean hasCollided) {
		this.hasCollided = hasCollided;
	}
}