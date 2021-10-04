package com.bloodLantern.renderer.renderables;

/**
 * Basically an animation, a Texture array.
 * 
 * @author BloodLantern
 */
public class Animation implements Renderable2D {

	/**
	 * The disbled texture is the one rendered when the animation is not running but still visible.
	 */
	private final Texture disabledTexture;
	private final Texture[] textures;
	private double x = 0;
	private double y = 0;
	private int roundedX = 0;
	private int roundedY = 0;

	/**
	 * Default constructor.
	 * 
	 * @param length the animation length. The Texture count of this animation.
	 */
	public Animation(int length, Texture disabledTexture) {
		this.disabledTexture = disabledTexture;
		textures = new Texture[length];
	}
	
	public Animation(int length) {
		textures = new Texture[length];
		disabledTexture = textures[0];
	}

	/**
	 * @return the textures
	 */
	public Texture[] getTextures() {
		return textures;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public Texture getTexture() {
		return disabledTexture;
	}

	@Override
	public int getRoundedX() {
		return roundedX;
	}

	@Override
	public void setRoundedX(int x) {
		roundedX = x;
	}

	@Override
	public int getRoundedY() {
		return roundedY;
	}

	@Override
	public void setRoundedY(int y) {
		roundedY = y;
	}

}
