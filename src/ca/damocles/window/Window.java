package ca.damocles.window;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ca.damocles.sprites.Sprite;
import ca.damocles.utils.ImageUtil;

public class Window extends JPanel implements Runnable{

	/** Generated serialVersionID */
	private static final long serialVersionUID = -6235998664465645887L;
	private InputHandler inputHandler;
	private final int WIDTH = 27, HEIGHT = 17, SCALE = 32;
	public List<Sprite> level = new ArrayList<>();
	private Thread thread;
	public int[] mouseLocation;
	public JFrame frame;
	public boolean grid = true;
	
	public Window() {
		frame = new JFrame("TopDownShooter: Level Designer");
		frame.setIconImage(ImageUtil.getInstance().getImage("", "Icon"));
		frame.setSize((WIDTH*SCALE)+7, (HEIGHT*SCALE)+35);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	stop();
            }
        });
		frame.getContentPane().add(this);
		frame.setVisible(true);
		
		inputHandler = new InputHandler(this);

		addFocusListener(inputHandler);
		addMouseListener(inputHandler);
		addMouseMotionListener(inputHandler);
		addMouseWheelListener(inputHandler);
		addKeyListener(inputHandler);
		
		start();
	}
	
	public static void main(String[] args) {
		new Window();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(Sprite sprite : level) {
			if(sprite.image != null)
				g.drawImage(sprite.image, sprite.getX()*SCALE, sprite.getY()*SCALE, null);
		}
		
		if(grid)
			paintGrid(g);
		
		g.drawImage(ImageUtil.getInstance().getWall(InputHandler.currentWall), mouseLocation[0], mouseLocation[1], null);
		
	}
	
	public void paintGrid(Graphics g) {
		int x, y;
		for(x=0;x<WIDTH;x++){
		    for(y=0;y<HEIGHT;y++){
		        g.drawRect(x*SCALE,y*SCALE,SCALE,SCALE);
		    }
		}
	}
	
	public Sprite getTileFromLocation(int x, int y) {
		for(Sprite sprite : level) {
			if(sprite.getX() == x && sprite.getY() == y) {
				return sprite;
			}
		}
		return null;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double secondsPerTicks = 1000000000 / 60;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / secondsPerTicks;
			lastTime = now;
			if(delta >= 1) {
				requestFocus();
				repaint();
				delta--;
			}
		}
	}
	
	public void start() { 
		int x, y;
		for(x=0;x < WIDTH;x++){
		    for(y=0;y < HEIGHT;y++){
		    	level.add(new Sprite(x,y));
		    }
		}
		loadStartingLevel();
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		System.exit(0);
	}
	
	public void loadStartingLevel() {
		InputStream in = Window.class.getResourceAsStream("/level.tds");
		BufferedReader r = new BufferedReader(new InputStreamReader(in));
		try{
			while (r.ready()) {
				String line = r.readLine();
				String[] l = line.split("/");
				int x = Integer.valueOf(l[0]);
				int y = Integer.valueOf(l[1]);
				int wall = Integer.valueOf(l[2]);
				Sprite sprite = getTileFromLocation(x, y);
				if(sprite != null)
					sprite.setWall(wall);
			}
			r.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void unFormat(File file) {
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(file));
			try{
				while (r.ready()) {
					String line = r.readLine();
					String[] l = line.split("/");
					int x = Integer.valueOf(l[0]);
					int y = Integer.valueOf(l[1]);
					int wall = Integer.valueOf(l[2]);
					Sprite sprite = getTileFromLocation(x, y);
					if(sprite != null)
						sprite.setWall(wall);
				}
				r.close();
			} catch (IOException e) {e.printStackTrace();}
		} catch (FileNotFoundException e1) {e1.printStackTrace();}
	}
	
	public List<String> format() {
		List<String> s = new ArrayList<>();
		for(Sprite sprite : level) {
			s.add(sprite.getX()+"/"+sprite.getY()+"/"+sprite.getWall());
		}
		return s;
	}
	
}
