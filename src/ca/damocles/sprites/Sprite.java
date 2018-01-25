package ca.damocles.sprites;

import java.awt.image.BufferedImage;
import ca.damocles.utils.ImageUtil;

public class Sprite {
	
	private int x , y;
	private int wall = 0;
	public BufferedImage image;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		loadImage();
	}
	
	public int getWall() {
		return wall;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void loadImage() {
		image = ImageUtil.getInstance().getWall(wall);
	}
	
	public void setWall(int wall) {
		this.wall = wall;
		loadImage();
	}
	
	
	public enum Walls{
		NoWall(0),
		TopRightCorner(1),
		TopRightCenterWall(2),
		TopLeftRightDown(3),
		TopLeftCorner(4),
		TopLeftCenterWall(5),
		TopHorizontal(6),
		RightWallVertical(7),
		RightUpDownLeft(8),
		LeftWallVertical(9),
		LeftUpDownRight(10), 
		CenterWallVertical(11),
		CenterWallHorizontal(12),
		CenterUpDownRight(13),
		CenterUpDownLeft(14),
		CenterRightLeftUp(15),
		CenterLeftRightDown(16),
		CenterCross(17),
		BottomRightCorner(18),
		BottomRightCenterWall(19),
		BottomLeftRightUp(20),
		BottomLeftCorner(21),
		BottomLeftCenterWall(22),
		BottomHorizontal(23),
		Coin(24),
		SpawnPoint(25);
		
		private int id;
		Walls(int id){ this.id = id; }
		public int getID() { return this.id; }
		public static Walls getFromId(int id) {
			for(Walls i : Walls.values()) {
				if(i.getID() == id) {
					return i;
				}
			}
			return null;
		}
	}
	
}
