package ca.damocles.window;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import ca.damocles.sprites.Sprite;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener, MouseWheelListener{

	public static int currentWall = 0;
	public Window window;
	
	public InputHandler(Window window) {
		this.window = window;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		int nextwall = currentWall + ((arg0.getWheelRotation()) * -1);
		if(nextwall >= 0 && nextwall <= (Sprite.Walls.values().length - 1)) {
			currentWall = nextwall;
		}
		System.out.println(currentWall+"");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			int x = (int)Math.floor(e.getX()/32.0);
			int y = (int)Math.floor(e.getY()/32.0);
			System.out.println("x:"+x+"; y:"+y);
			Sprite sprite = window.getTileFromLocation(x, y);
			if(sprite != null)
				sprite.setWall(currentWall);
		}
		
		if(SwingUtilities.isRightMouseButton(e)) {
			int x = (int)Math.floor(e.getX()/32.0);
			int y = (int)Math.floor(e.getY()/32.0);
			System.out.println("x:"+x+"; y:"+y);
			Sprite sprite = window.getTileFromLocation(x, y);
			if(sprite != null)
				sprite.setWall(0);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			int x = (int)Math.floor(e.getX()/32.0);
			int y = (int)Math.floor(e.getY()/32.0);
			System.out.println("x:"+x+"; y:"+y);
			Sprite sprite = window.getTileFromLocation(x, y);
			if(sprite != null)
				sprite.setWall(currentWall);
		}
		
		if(SwingUtilities.isMiddleMouseButton(e)) {
			int x = (int)Math.floor(e.getX()/32.0);
			int y = (int)Math.floor(e.getY()/32.0);
			Sprite sprite = window.getTileFromLocation(x, y);
			currentWall = sprite.getWall();
		}
		
		if(SwingUtilities.isRightMouseButton(e)) {
			int x = (int)Math.floor(e.getX()/32.0);
			int y = (int)Math.floor(e.getY()/32.0);
			System.out.println("x:"+x+"; y:"+y);
			Sprite sprite = window.getTileFromLocation(x, y);
			if(sprite != null)
				sprite.setWall(0);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		int[] loc = {arg0.getX(), arg0.getY()};
		window.mouseLocation = loc;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.print(arg0.getKeyCode());
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		    
		    JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("TDS Files", "tds");
			    chooser.setFileFilter(filter);
		    int retrival = chooser.showSaveDialog(window.frame);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            PrintWriter writer = new PrintWriter(chooser.getSelectedFile()+".tds");
					for(String s : window.format()) {
						writer.println(s);
				}
				writer.close();
		        } catch (Exception ex) { ex.printStackTrace(); }
		    }
			
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_G) {
			window.grid = !window.grid;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_BACK_SLASH){
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("TDS Files", "tds");
		    chooser.setFileFilter(filter);
		    int retrival = chooser.showOpenDialog(window.frame);
		    if(retrival == JFileChooser.APPROVE_OPTION) {
		    	window.unFormat(chooser.getSelectedFile());
		    }
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			window.loadStartingLevel();
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
