import java.io.IOException;

public class PlayerShip extends Sprite
{
	public boolean left, right, forward, backward, firing;
	private double turnRate = 5, thrustPower = 0.2;
	
	public PlayerShip(Point2D position)
	{
		super(position);
		
		left = false;
		right = false;
		forward = false;
		backward = false;
		firing = false;
		
		try
		{
			this.currentImage = GameFunction.loadBufferedImage("/resources/ships/player_test_ship.png");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void forward() { this.forward = true; }
	public void backward() { this.backward = true; }
	public void left() { this.left = true; }
	public void right() { this.right = true; }
	
	public void update()
	{
		if (forward)
		{
			this.velocity.addX(Math.cos(this.rotation.getRadians() - Math.PI / 2.0) * this.thrustPower);
			this.velocity.addY(Math.sin(this.rotation.getRadians() - Math.PI / 2.0) * this.thrustPower);
		}
		if (backward)
		{
			this.velocity.addX(-Math.cos(this.rotation.getRadians() - Math.PI / 2.0) * this.thrustPower);
			this.velocity.addY(-Math.sin(this.rotation.getRadians() - Math.PI / 2.0) * this.thrustPower);
		}
		if (left)
		{
			this.rotation.addAmount(-this.turnRate);
		}
		if (right)
		{
			this.rotation.addAmount(this.turnRate);
		}
		
		this.mapPos.addX(this.velocity.x);
		this.mapPos.addY(this.velocity.y);
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	