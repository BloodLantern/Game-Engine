package com.bloodLantern.renderer.renderables.movements;

import penner.easing.*;

/**
 * Enum containing different movement types.
 * 
 * @author BloodLantern
 */
public enum Movements {

	/**
	 * Move without animation.
	 */
	NONE(null),

	/**
	 * Starts by moving backwards and then go forward. Same when arriving on its
	 * destination position.
	 */
	BACK(Back.class),

	/**
	 * Bounces 4 times when arriving on its destination position.
	 */
	BOUNCE(Bounce.class),

	/**
	 * 
	 */
	CIRC(Circ.class),

	/**
	 * 
	 */
	CUBIC(Cubic.class),

	/**
	 * idk
	 */
	ELASTIC(Elastic.class),

	/**
	 * Exponentially increase/decrease speed.
	 */
	EXPO(Expo.class),

	/**
	 * 
	 */
	LINEAR(Linear.class),

	/**
	 * 
	 */
	QUAD(Quad.class),

	/**
	 * 
	 */
	QUART(Quart.class),

	/**
	 * 
	 */
	QUINT(Quint.class),

	/**
	 * 
	 */
	SINE(Sine.class);
	
	private final Class<?> clazz;
	
	Movements(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Used to get an array of Class objects with every single penner.easing package
	 * classes.
	 * 
	 * @return a Class object array with every penner.easing classes.
	 */
	public Class<?>[] getMovementClasses() {
		return new Class<?>[] { Back.class, Bounce.class, Circ.class, Cubic.class, Elastic.class, Expo.class,
				Linear.class, Quad.class, Quart.class, Quint.class, Sine.class };
	}

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	public enum Types {

		/**
		 * Acceleration.
		 */
		IN("easeIn"),

		/**
		 * Deceleration.
		 */
		OUT("easeOut"),

		/**
		 * Acceleration AND deceleration.
		 */
		IN_OUT("easeInOut");
		
		private final String methodName;
		
		Types(String methodName) {
			this.methodName = methodName;
		}

		/**
		 * @return the methodName
		 */
		public String getMethodName() {
			return methodName;
		}
	}

}
