import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

public class AnimCanvas extends Canvas implements Runnable {

	private int w, h;
	
	// start(1), stop(0)
	public int status = 0;
	
	// 位置、速度
	int x, y, y0;
	float vy, vy0=70;
	final float gravity = 9.8f;
	
	public AnimCanvas(int w, int h) {
		this.w = w;
		this.h = h;
		
		x = w / 2;
		y = y0 = h / 2;
		
		setBackground(Color.WHITE);
	}

	public AnimCanvas(GraphicsConfiguration config) {
		super(config);
	}
	

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
	}
	
	public void update(Graphics g) {		
		if (status == 1) {
			
			if (y >= y0) {
				vy = vy0;
			} else {
				vy -= gravity;
			}
			y = (int) Math.min(y - vy, y0);
		}
		super.update(g);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			
			repaint();
		}
	}
}
