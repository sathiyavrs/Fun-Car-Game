import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener {

	Image img;
	Graphics doubleG;

	Line line;
	ImageTryRotate image;
	ArrayList<DottedLines> dots;
	NegativeObstacle obs1, obs2, obs3;

	boolean gameOver = true;
	private int score = 0;

	private boolean allThreeDown = false;
	private int designChooser = 0;
	private Random randObj;
	// private int[] obstacleArr;
	private int obstacleY = 0;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		setSize(400, 600);
		line = new Line(this);
		image = new ImageTryRotate(this);
		dots = new ArrayList<DottedLines>();
		dots.add(new DottedLines((int) 237.5, 0));
		dots.add(new DottedLines((int) 237.5, 200));
		dots.add(new DottedLines((int) 237.5, 400));
		dots.add(new DottedLines((int) 162.5, 0 + 100));
		dots.add(new DottedLines((int) 162.5, 200 + 100));
		dots.add(new DottedLines((int) 162.5, 400 + 100));
		dots.add(new DottedLines((int) 312.5, 0 - 100));
		dots.add(new DottedLines((int) 312.5, 200 - 100));
		dots.add(new DottedLines((int) 312.5, 400 - 100));

		obs1 = new NegativeObstacle(line);
		obs2 = new NegativeObstacle(line, 237.5, -100);
		obs3 = new NegativeObstacle(line, 312.5, -200);
		addKeyListener(this);

		randObj = new Random();
		// obstacleArr = new int[3];
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		dots.get(0).paint(g);
		dots.get(1).paint(g);
		dots.get(2).paint(g);
		dots.get(3).paint(g);
		dots.get(4).paint(g);
		dots.get(5).paint(g);
		dots.get(6).paint(g);
		dots.get(7).paint(g);
		dots.get(8).paint(g);

		obs3.paint(g);
		obs2.paint(g);
		obs1.paint(g);
		line.paint(g);
		image.paint(g);

		Font font = new Font("Times New Roman", Font.BOLD, 32);
		g.setFont(font);

		g.drawString("" + score, 60 + 1, 60 + 1);
		g.setColor(Color.MAGENTA);
		g.drawString("" + score, 60, 60);

		if (gameOver) {
			dots.get(0).dy = 0;
			dots.get(1).dy = 0;
			dots.get(2).dy = 0;
			dots.get(3).dy = 0;
			dots.get(4).dy = 0;
			dots.get(5).dy = 0;
			dots.get(6).dy = 0;
			dots.get(7).dy = 0;
			dots.get(8).dy = 0;

			obs3.dy = 0;
			obs3.y = -100;
			obs2.dy = 0;
			obs2.y = -100;
			obs1.dy = 0;
			obs1.y = -100;

			font = new Font("Bradley Hand ITC", Font.BOLD, 32);
			g.setFont(font);
			g.setColor(Color.YELLOW);

			g.drawString("Hit Enter to play", 19, 200 + 1);
			g.setColor(Color.MAGENTA);

			font = new Font("Times New Roman", Font.BOLD, 32);
			g.setFont(font);
			g.drawString("" + score, 60, 60);

		} else {
			score++;
		}
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		dots.get(0).update(g);
		dots.get(1).update(g);
		dots.get(2).update(g);
		dots.get(3).update(g);
		dots.get(4).update(g);
		dots.get(5).update(g);
		dots.get(6).update(g);
		dots.get(7).update(g);
		dots.get(8).update(g);

		obs3.update(g);
		obs2.update(g);
		obs1.update(g);
		line.update(g);
		image.update(g);

		if (gameOver) {
			dots.get(0).dy = 0;
			dots.get(1).dy = 0;
			dots.get(2).dy = 0;
			dots.get(3).dy = 0;
			dots.get(4).dy = 0;
			dots.get(5).dy = 0;
			dots.get(6).dy = 0;
			dots.get(7).dy = 0;
			dots.get(8).dy = 0;

			obs3.dy = 0;
			obs3.y = -100;
			obs2.dy = 0;
			obs2.y = -100;
			obs1.dy = 0;
			obs1.y = -100;

			Font font = new Font("Bradley Hand ITC", Font.BOLD, 32);
			g.setFont(font);
			g.setColor(Color.YELLOW);

			g.drawString("Hit Enter to play", 19, 200 + 1);
			g.setColor(Color.MAGENTA);

			font = new Font("Times New Roman", Font.BOLD, 32);
			g.setFont(font);
			g.drawString("" + score, 60, 60);

		} else {
			score++;

			// The Obstacles are called obs1, obs2, and obs3, in left to right
			// order.

			allThreeDown = (obs1.y - 50 > this.getHeight())
					&& (obs2.y - 50 > this.getHeight())
					&& (obs3.y - 50 > this.getHeight());

			// if (obs3.isHasCollided())
			// System.out.println("obs3!!");

			designChooser = randObj.nextInt(8 + 3 + 4 + 2) + 1;

			// designChooser = randObj.nextInt(2) + 16;
			obstacleY = -10;
			if (allThreeDown) {
				switch (designChooser) {
				case 1:
					// obstacleArr[0] =
					updateObstacle(false, obstacleY, obs3);
					obstacleY -= 200;
					updateObstacle(false, obstacleY - 10 + randObj.nextInt(20),
							obs2);
					obstacleY -= 200;
					updateObstacle(false, obstacleY, obs1);
					obstacleY = -100;
					break;

				case 2:
					updateObstacle(false, obstacleY, obs1);
					obstacleY -= 200;
					updateObstacle(false, obstacleY - 10 + randObj.nextInt(20),
							obs2);
					obstacleY -= 200;
					updateObstacle(false, obstacleY, obs3);
					obstacleY = -100;
					break;

				case 3:
					updateObstacle(false, obstacleY + randObj.nextInt(20), obs1);
					obstacleY -= 200;
					updateObstacle(false, obstacleY, obs2);
					obstacleY += 200;
					updateObstacle(false, obstacleY, obs3);
					obstacleY = -100;
					break;

				case 4:
					obstacleY = -300;
					updateObstacle(false, obstacleY + randObj.nextInt(20), obs1);
					obstacleY += 200;
					updateObstacle(false, obstacleY, obs2);
					obstacleY -= 200;
					updateObstacle(false, obstacleY, obs3);
					obstacleY = -100;
					break;

				case 5:
					obstacleY = -300;
					updateObstacle(false, obstacleY, obs1);
					updateObstacle(false, obstacleY - randObj.nextInt(20), obs2);
					obstacleY += 200;
					updateObstacle(false, obstacleY, obs3);
					obstacleY = -100;
					break;

				case 6:
					obstacleY = -100;
					updateObstacle(false, obstacleY, obs1);
					obstacleY -= 200;
					updateObstacle(false, obstacleY - randObj.nextInt(20), obs2);
					updateObstacle(false, obstacleY, obs3);
					obstacleY = -100;
					break;

				case 7:
					obstacleY = -100;
					updateObstacle(false, obstacleY, obs1);
					updateObstacle(false, obstacleY + randObj.nextInt(20), obs2);
					obstacleY -= 200;
					updateObstacle(false, obstacleY, obs3);
					obstacleY = -100;
					break;

				case 8:
					obstacleY = -300;
					updateObstacle(false, obstacleY, obs1);
					obstacleY += 200;
					updateObstacle(false, obstacleY + randObj.nextInt(20), obs2);
					updateObstacle(false, obstacleY, obs3);
					obstacleY = -100;
					break;

				case 9:
					obstacleY = -200;
					updateObstacle(false, obstacleY, obs1);
					obstacleY += randObj.nextInt(100);
					updateObstacle(true, obstacleY, obs2);
					obstacleY += randObj.nextInt(70);
					updateObstacle(false, obstacleY, obs3);

					break;

				case 10:
					obstacleY = -200;
					updateObstacle(true, obstacleY, obs1);
					obstacleY += randObj.nextInt(100);
					updateObstacle(false, obstacleY, obs2);
					obstacleY += randObj.nextInt(70);
					updateObstacle(false, obstacleY, obs3);

					break;

				case 11:
					obstacleY = -200;
					updateObstacle(false, obstacleY, obs1);
					obstacleY += randObj.nextInt(100);
					updateObstacle(false, obstacleY, obs2);
					obstacleY += randObj.nextInt(70);
					updateObstacle(true, obstacleY, obs3);
					break;

				case 12:
					obstacleY = -300;
					updateObstacle(true, obstacleY, obs1);
					obstacleY += 150;
					updateObstacle(false, obstacleY + randObj.nextInt(30), obs2);
					updateObstacle(true, obstacleY, obs3);
					break;

				case 13:
					obstacleY = -100;
					updateObstacle(true, obstacleY, obs1);
					obstacleY -= 150;
					updateObstacle(false, obstacleY - randObj.nextInt(30), obs2);
					updateObstacle(true, obstacleY, obs3);
					break;

				case 14:
					obstacleY = -200;
					updateObstacle(true, obstacleY, obs1);
					obstacleY += randObj.nextInt(100);
					updateObstacle(true, obstacleY, obs2);
					obstacleY += randObj.nextInt(70);
					updateObstacle(false, obstacleY, obs3);
					break;

				case 15:
					obstacleY = -200;
					updateObstacle(false, obstacleY, obs1);
					obstacleY += randObj.nextInt(100);
					updateObstacle(true, obstacleY, obs2);
					obstacleY += randObj.nextInt(70);
					updateObstacle(true, obstacleY, obs3);
					break;

				case 16:
					obstacleY = -200;
					updateObstacle(true, obstacleY, obs1);
					obstacleY += randObj.nextInt(100 - 20) + 20;
					updateObstacle(true, obstacleY, obs2);
					obstacleY += randObj.nextInt(70 - 20) + 20;
					updateObstacle(true, obstacleY, obs3);
					break;

				case 17:
					obstacleY = -50;
					updateObstacle(true, obstacleY, obs1);
					obstacleY -= randObj.nextInt(70 - 20) + 20;
					updateObstacle(true, obstacleY, obs2);
					obstacleY -= randObj.nextInt(100 - 20) + 20;
					updateObstacle(true, obstacleY, obs3);
					break;

				default:
					break;
				}
				allThreeDown = false;

			}

		}

		if (img == null) {
			img = createImage(this.getWidth(), this.getHeight());
			doubleG = img.getGraphics();
		}

		doubleG.setColor(Color.BLACK);
		doubleG.fillRect(0, 0, this.getWidth(), this.getHeight());

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(img, 0, 0, this);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyId = e.getKeyCode();
		switch (keyId) {
		case KeyEvent.VK_LEFT:
			line.goLeft();
			break;
		case KeyEvent.VK_RIGHT:
			line.goRight();
			break;
		case KeyEvent.VK_ENTER:
			if (gameOver) {
				gameOver = false;
				score = 0;
				line = new Line(this);
				image = new ImageTryRotate(this);
				dots = new ArrayList<DottedLines>();
				dots.clear();
				dots.add(new DottedLines((int) 237.5, 0));
				dots.add(new DottedLines((int) 237.5, 200));
				dots.add(new DottedLines((int) 237.5, 400));
				dots.add(new DottedLines((int) 162.5, 0 + 100));
				dots.add(new DottedLines((int) 162.5, 200 + 100));
				dots.add(new DottedLines((int) 162.5, 400 + 100));
				dots.add(new DottedLines((int) 312.5, 0 - 100));
				dots.add(new DottedLines((int) 312.5, 200 - 100));
				dots.add(new DottedLines((int) 312.5, 400 - 100));
				obs1 = new NegativeObstacle(line);
				obs2 = new NegativeObstacle(line, 237.5, -200);
				obs3 = new NegativeObstacle(line, 312.5, -400);
			}
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void updateObstacle(boolean isGreen, double y,
			NegativeObstacle obstacle) {
		obstacle.setHasCollided(false);
		obstacle.setColor(isGreen);
		obstacle.y = y;
	}
}