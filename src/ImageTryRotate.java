import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

public class ImageTryRotate {

	static StartingPoint sp;
	private Graphics2D g2;
	private URL url;
	private Image img;

	private double angle;

	public ImageTryRotate(StartingPoint a) {
		// TODO Auto-generated constructor stub
		angle = 0;
		sp = a;
		try {
			url = sp.getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		img = sp.getImage(url, "images/character.png");
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		g2 = (Graphics2D) g;
		g2.rotate(Math.toRadians(angle));
		g2.drawImage(img,
				(int) (50 * Math.cos(Math.toRadians(angle)) + 50 * Math
						.sin(Math.toRadians(angle))), (int) (-1 * (50 * Math
						.sin(Math.toRadians(angle)) - 50 * Math.cos(Math
						.toRadians(angle)))), sp);
		g2.fillRect((int) (50 * Math.cos(Math.toRadians(angle)) + 50 * Math
				.sin(Math.toRadians(angle))), (int) (-1 * (50 * Math.sin(Math
				.toRadians(angle)) - 50 * Math.cos(Math.toRadians(angle)))),
				50, 50);
		g2.rotate(Math.toRadians(-1 * angle));

	}

	public void update(Graphics g) {
		// TODO Auto-generated method stub
		angle += 1;
		if (angle == 360)
			angle = 0;
	}
}
