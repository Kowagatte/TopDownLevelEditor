package ca.damocles.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import ca.damocles.sprites.Sprite.Walls;
import javax.imageio.ImageIO;

public class ImageUtil {
	
	public static ImageUtil instance = new ImageUtil();
	
	public static ImageUtil getInstance() {
		return instance;
	}
	
	public ImageUtil() { }
	
	public BufferedImage getImage(String path, String name) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(path+"/"+name+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedImage getWall(int id) {
		try {
			return ImageIO.read(getClass().getResourceAsStream("/Walls/"+Walls.getFromId(id).toString()+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public enum ColorCodes{
		GREEN_SPAWN(100, 100, 100),
		PURPLE_SPAWN(255, 0, 255),
		WALLS(189, 189, 189),
		BACKGROUND(34, 32, 52),
		COINS(255, 255, 0);
		
		int r, g, b;
		ColorCodes(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		public int getR() { return r; }
		public int getG() { return g; }
		public int getB() { return b; }
	}
	
}
