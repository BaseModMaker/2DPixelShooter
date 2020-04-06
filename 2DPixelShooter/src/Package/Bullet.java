package Package;

import java.awt.Graphics;

public class Bullet {
	String Hi;
	int SniperBulletShotShowTime = 10;
	int X;
	int Y;
	int Radius;
	int RadialSpeed;
	int Time = 0;
	double Angle;
	int BeginPositionX;
	int BeginPositionY;
	Main main;
	String GunCategory;

	public Bullet(Main main, String GunCategory, int BeginPositionX, int BeginPositionY, double Angle, int RadialSpeed) {
		this.main = main;
		X = BeginPositionX;
		Y = BeginPositionY;
		this.RadialSpeed = RadialSpeed;
		this.Angle = Angle;
		this.BeginPositionX = BeginPositionX;
		this.BeginPositionY = BeginPositionY;
		this.GunCategory = GunCategory;
	}

	public void draw(Graphics g, Main main) {
		// Test what type of gun it is
		if (GunCategory.equals("Category=Rifle") == true) {
			g.fillOval(X, Y, 3, 3);
		} else {
			g.drawLine(BeginPositionX, BeginPositionY, (int) (Math.cos(Angle) * 1000) + BeginPositionX, (int) (Math.sin(Angle) * 1000) + BeginPositionY);
		}

	}

	public void update() {
		if (GunCategory.equals("Category=Rifle") == true) {
			// Change X and Y according to the speed
			X = (int) (Math.cos(Angle) * RadialSpeed * Time) + BeginPositionX;
			Y = (int) (Math.sin(Angle) * RadialSpeed * Time) + BeginPositionY;

			// Make time move forward
			Time += 1;

			// Remove the bullet if it's off screen
			if (X < 0 || X > main.getWidth() || Y < 0 || Y > main.getHeight()) {
				main.bullet.remove(this);
			}

		} else {
			// Make the sniper bullet show time deplete
			SniperBulletShotShowTime -= 1;

			// Remove the bullet if it's shot
			if (SniperBulletShotShowTime < 0) {
				main.bullet.remove(this);
			}
		}

	}

}