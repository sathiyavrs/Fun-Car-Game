import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

public class Line {

	private double x;
	private double y;
	private double y0;
	double xAng;
	double yAng;

	double rectWidth;
	double rectHeight;

	StartingPoint sp;
	private Graphics2D g2;
	private URL url;
	private Image img;

	private double xOfCenter = 200;

	private double dy;

	private double angle;
	double varAng;

	private boolean moving = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean continueOn = false;
	private boolean continueOff = false;

	boolean check = false;

	public Line(StartingPoint a) {
		// TODO Auto-generated constructor stub
		x = 225;
		sp = a;
		y = 100;
		y0 = y;
		dy = 15;
		angle = 90;
		// y = Math.acos(1 / Math.tan(Math.toRadians(angle)));
		y = Math.PI * 50;
		x = 200 + 1 * (Math.sin(y / 100) * 50);
		x = 237.5;
		y0 = y + 380;
		/*
		 * Top Left: 30,159
		 * 
		 * Bounding Rectangle: 53*112
		 * 
		 * Area: 5936 px^2
		 */

		rectWidth = 53;
		rectHeight = 112;

		try {
			url = sp.getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		img = sp.getImage(url, "Images/Duplicate of Vehicles expansion.jpg");
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);

		g.setColor(Color.white);
		g.drawLine(200, 0, 200, sp.getHeight());
		g.drawLine(200 + 1, 0, 200 + 1, sp.getHeight());
		g.drawLine(200 - 1, 0, 200 - 1, sp.getHeight());

		/*
		 * xmin = 162.5026752309779 xmax = 237.4922337553501
		 */

		g.drawLine((int) (153 - rectWidth / 2), 0, (int) (153 - rectWidth / 2),
				sp.getHeight());
		g.drawLine((int) (246 + rectWidth / 2), 0, (int) (246 + rectWidth / 2),
				sp.getHeight());
		g.drawLine((int) (245 + rectWidth / 2), 0, (int) (245 + rectWidth / 2),
				sp.getHeight());
		g.drawLine((int) (247 + rectWidth / 2), 0, (int) (247 + rectWidth / 2),
				sp.getHeight());
		g.drawLine((int) (320 + rectWidth / 2), 0, (int) (320 + rectWidth / 2),
				sp.getHeight());

		g.setColor(Color.BLACK);

		g2 = (Graphics2D) g;
		if (angle > 0)
			varAng = 180 + angle;
		else
			varAng = angle + 360;

		xAng = (x * Math.sin(Math.toRadians(varAng)) + y0
				* Math.cos(Math.toRadians(varAng)));
		yAng = (y0 * Math.sin(Math.toRadians(varAng)) - x
				* Math.cos(Math.toRadians(varAng)));

		g2.rotate(Math.toRadians(90 - varAng));

		g2.setColor(Color.black);
		// g2.fillRect((int) (xAng - rectWidth / 2),
		// (int) (yAng - rectHeight / 2), (int) (rectWidth),
		// (int) (rectHeight));

		g2.drawImage(img, (int) (xAng - rectWidth / 2),
				(int) (yAng - rectHeight / 2), (int) (xAng + rectWidth / 2),
				(int) (yAng + rectHeight / 2), 30, 159, 83, 271, sp);

		g2.rotate(Math.toRadians(-1 * (90 - varAng)));

		double a1, a2, b1, b2;

		a1 = xAng - rectWidth / 2;
		a2 = xAng - rectWidth / 2;
		b1 = yAng + rectHeight / 2;
		b2 = yAng + rectHeight / 2;

		double p1, p2, q1, q2;

		p1 = a1 * Math.cos(Math.toRadians(varAng - 90)) + b1
				* Math.sin(Math.toRadians(varAng - 90));
		p2 = b1 * Math.cos(Math.toRadians(varAng - 90)) - a1
				* Math.sin(Math.toRadians(varAng - 90));
		q1 = a2 * Math.cos(Math.toRadians(varAng - 90)) + b2
				* Math.sin(Math.toRadians(varAng - 90));
		q2 = b2 * Math.cos(Math.toRadians(varAng - 90)) - a2
				* Math.sin(Math.toRadians(varAng - 90));

		g.drawLine((int) p1, (int) p2, (int) q1, (int) q2);

		g.setColor(Color.BLACK);

		// --------------- One Line To Rule Them All-------------------------

		// g.setColor(Color.black);
		// g.drawLine((int) (x - (length / 2 *
		// Math.cos(Math.toRadians(angle)))),
		// (int) (y0 - (length / 2 * Math.sin(Math.toRadians(angle)))),
		// (int) (x + (length / 2 * Math.cos(Math.toRadians(angle)))),
		// (int) (y0 + (length / 2 * Math.sin(Math.toRadians(angle)))));

	}

	public void update(Graphics g) {
		// TODO Auto-generated method stub

		if (moving) {
			y += dy;

			if ((x < xOfCenter - 37.4) && movingLeft) {
				moving = false;
				x = xOfCenter - 37.5;
				movingLeft = false;
				angle = -90;
				y = 487.0796326794897;
				if (continueOn) {
					if (x == 237.5) {
						moving = true;
						movingRight = false;
						movingLeft = true;
						xOfCenter = 200;
						angle = 90;
						y = 802.0796326794897;
					}
					continueOn = false;
				}

				if (continueOff) {
					if (x == 237.5) {
						moving = true;
						movingRight = true;
						movingLeft = false;
						xOfCenter = 275;
						y = 487.0796326794897;
						angle = -90;

					}
					if (x == 162.5) {
						moving = true;
						movingLeft = false;
						xOfCenter = 200;
						y = 487.0796326794897;
						angle = -90;
						movingRight = true;

					}
					continueOff = false;
				}
			}

			if ((x > xOfCenter + 37.4) && movingRight) {
				moving = false;
				x = xOfCenter + 37.5;
				movingRight = false;
				angle = 90;
				y = 802.0796326794897;
				if (continueOn) {
					if (x == 237.5) {
						moving = true;
						movingRight = true;
						movingLeft = false;
						xOfCenter = 275;
						y = 487.0796326794897;
						angle = -90;
					}
					continueOn = false;
				}

				if (continueOff) {
					if (x == 237.5) {
						moving = true;
						movingRight = false;
						movingLeft = true;
						xOfCenter = 200;
						angle = 90;
						y = 802.0796326794897;
					}
					if (x == 312.5) {
						moving = true;
						movingLeft = true;

						movingRight = false;
					}
					continueOff = false;
				}

			}

			if (moving)
				x = xOfCenter + 0.75 * (Math.sin(y / 100) * 50);

			/*
			 * xmin = 162.5026752309779 xmax = 237.4922337553501
			 */

			if (moving)
				angle = Math.toDegrees(Math.atan(10 / (Math.cos(y / 100))));
		}
	}

	public void goLeft() {

		if (xOfCenter == 200 && x == 237.5) {
			moving = true;
			movingLeft = true;
			if (movingRight) {
				movingLeft = false;
				return;
			}
			movingRight = false;

		} else if (xOfCenter == 275 && x == 312.5) {
			moving = true;
			movingLeft = true;
			if (movingRight) {
				movingLeft = false;
				return;
			}
			movingRight = false;

		} else if (xOfCenter == 275 && x == 237.5) {
			moving = true;
			movingLeft = true;
			if (movingRight) {
				movingLeft = false;
				return;
			}
			xOfCenter = 200;
			angle = 90;
			y = 802.0796326794897;
			movingRight = false;

		} else if (!movingRight && movingLeft) {
			continueOn = true;

		} else if (movingRight && !movingLeft) {
			continueOff = true;

		}
	}

	public void goRight() {
		if (xOfCenter == 200 && x == 162.5) {
			moving = true;
			if (movingLeft) {
				return;
			}
			movingLeft = false;

			movingRight = true;
		}

		else if (xOfCenter == 200 && x == 237.5) {

			moving = true;
			if (movingLeft) {
				return;
			}
			movingLeft = false;
			xOfCenter = 275;
			y = 487.0796326794897;
			angle = -90;
			movingRight = true;
		} else if (xOfCenter == 275 && x == 237.5) {

			moving = true;
			if (movingLeft) {
				return;
			}
			movingLeft = false;

			movingRight = true;
		} else if (movingRight && !movingLeft) {
			continueOn = true;
		} else if (!movingRight && movingLeft) {
			continueOff = true;

		}

	}

}
