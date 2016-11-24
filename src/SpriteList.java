/***************************
 * Purpose: SpriteList class to contain the master list
 * of all sprites in the game. It is recommended that
 * classes adding to the sprite list keep their own list
 * of sprites that have been added for easier removal
 * later. This class is used by the view components to
 * draw the contents of the sprite list to the screen.
 * 
 * Adding/removing sprites to/from the master list does
 * not change any local sprite lists. It is the
 * responsibility of the CALLER to update any local sprite
 * lists accordingly.
 * 
 * All operations except getList() are thread-safe. Use
 * the public lock (spriteListLock) before calling
 * getList() to make it thread-safe.
 *
 * Contributors:
 * - Zachary Johnson
 ***************************/

import java.util.ArrayList;

public class SpriteList
{
	public static Object spriteListLock = new Object();
	private static PlayerShip playerShip = new PlayerShip(new Vector2D(50.0, 50.0));
	private static ArrayList<Sprite> masterSpriteList = new ArrayList<Sprite>();
	private static ArrayList<Sprite> backgroundsFar = new ArrayList<Sprite>();
	private static GameGUI gui;
	
	public SpriteList() { }
	
	//Add Sprite to the list
	//Returns true if successful, false if not added to list
	// (Usually due to sprite already present in the list)
	public static void addSprite(Sprite s)
	{
		synchronized(spriteListLock)
		{	
			if (s instanceof GameGUI)
				gui = (GameGUI) s;
			else if (s instanceof BackgroundSprite)
				backgroundsFar.add(s);
			else
				masterSpriteList.add(s);
		}
	}
	
	//Remove Sprite from the list.
	//Returns true if element was found in the list and removed
	public static void removeSprite(Sprite s)
	{
		synchronized (spriteListLock)
		{
			if (s == gui)
				gui = null;
			else if (s instanceof BackgroundSprite)
				backgroundsFar.remove(s);
			else
				masterSpriteList.remove(s);
//			return masterSpriteList.remove(s);
		}
	}
	
	//This method is intended only for drawing the sprites.
	//Modifying the contents of the list should not be done
	//through this method.
	//It is recommended to synchronize on the public lock before
	//calling this method
	//Returned in order they should be drawn
	public static ArrayList<Sprite> getList()
	{
		ArrayList<Sprite> drawList;
		synchronized(spriteListLock)
		{
			//Far backgrounds drawn first
			drawList = new ArrayList<Sprite>(backgroundsFar);
			
			//Next, draw primary sprites
			drawList.addAll(masterSpriteList);
			
			//Add GUI last (on top of everything
			drawList.add(drawList.size(), gui);
		}
		
		return drawList;
	}
	
	public static void clearPlayerShip()
	{
		if (playerShip == null)
			return;
		else
		{
			synchronized(spriteListLock)
			{
				masterSpriteList.remove(playerShip);
				
				playerShip = null;
			}
		}
	}
	
	public static void setPlayerShip(PlayerShip newShip)
	{
		if (newShip == null)
			throw new IllegalArgumentException("Error: Attempted to set SpriteList playerShip to null!");
		else
			synchronized(spriteListLock)
			{
				masterSpriteList.remove(playerShip);
				
				playerShip = newShip;
				
				masterSpriteList.add(newShip);
			}
	}
	
	public static PlayerShip getPlayerShip()
	{
		if (playerShip == null)
		{
			synchronized(spriteListLock)
			{
				playerShip = new PlayerShip(new Vector2D(50.0, 50.0));
				
				masterSpriteList.add(playerShip);
			}
		}
		
		return playerShip;
	}
}