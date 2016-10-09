/***************************
 * Purpose: Controller class, takes input from the user
 * and calls functions to interface with the program
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

class Controller implements MouseListener, MouseWheelListener, MouseMotionListener, KeyListener
{
	Model model;
	public static Point2D mousePos = new Point2D(0,0);
	boolean draggingMap = false, draggingWindow = false;
	boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false;

	Controller(Model m)
	{
		this.model = m;
	}

	public void mousePressed(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseDown(e);
				break;
			case GAME:
				Point2D position = new Point2D(e.getX(), e.getY());
		
				if (e.getButton() == MouseEvent.BUTTON1)
					this.model.onLeftClick(position);
				if (e.getButton() == MouseEvent.BUTTON3)
					this.model.onRightClick(position);
				break;
			default:
				break;
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseUp(e);
				break;
			case GAME:
				if (e.getButton() == MouseEvent.BUTTON1)
					this.model.onLeftClickRelease(new Point2D(e.getX(), e.getY()));
				if (e.getButton() == MouseEvent.BUTTON3)
					this.model.onRightClickRelease(new Point2D(e.getX(), e.getY()));
				
				System.out.println("Click released");
				draggingMap = false;
				draggingWindow = false;
				break;
			default:
				break;
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseScroll(e);
				break;
			case GAME:
				break;
			default:
				break;
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseDrag(e);
				break;
			case GAME:
				this.mousePos = new Point2D(e.getX(), e.getY());
				break;
			default:
				break;
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseMove(e);
				break;
			case GAME:
				this.mousePos = new Point2D(e.getX(), e.getY());
				break;
			default:
				break;
		}
	}
	
	public void mouseClicked(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void keyPressed(KeyEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.keyPress(e);
				break;
			case GAME:
				int keyCode = e.getKeyCode();
				
				switch (keyCode)
				{
					case KeyEvent.VK_UP:
						upPressed = true;
						model.mv.playerShip.forward = true;
						break;
					case KeyEvent.VK_DOWN:
						downPressed = true;
						model.mv.playerShip.backward = true;
						break;
					case KeyEvent.VK_LEFT:
						leftPressed = true;
						model.mv.playerShip.left = true;
						break;
					case KeyEvent.VK_RIGHT:
						rightPressed = true;
						model.mv.playerShip.right = true;
						break;
				}
				break;
			default:
				break;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		
		switch (keyCode)
		{
			case KeyEvent.VK_UP:
				upPressed = false;
				model.mv.playerShip.forward = false;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = false;
				model.mv.playerShip.backward = false;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				model.mv.playerShip.left = false;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				model.mv.playerShip.right = false;
				break;
		}
	}
	public void keyTyped(KeyEvent k) {    }

}
















