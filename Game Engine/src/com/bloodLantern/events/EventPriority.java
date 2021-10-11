package com.bloodLantern.events;

/**
 * Class defining common Event priorities. Although, these priorities does only
 * correspond to an integer value and because of that, you can use the integer
 * value of your choice for the Event priority.
 * <p>
 * You may extends this class to create another with more priority levels.
 *
 * @author BloodLantern
 */
public class EventPriority {

	/**
	 * The lowest Event priority. Equal to {@code Integer.MIN_VALUE}. It means that
	 * the annotated method will be invoked at last. This priority sould be used
	 * either to force cancelling a Cancellable Event or the opposite, or to do some
	 * modifications about the Event with no great matter.
	 */
	public static final int LOWEST = Integer.MIN_VALUE;

	/**
	 * The low priority is equal to -1000.
	 */
	public static final int LOW = -1000;

	/**
	 * The not important priority is equal to -100.
	 */
	public static final int NOT_IMPORTANT = -100;

	/**
	 * The normal priority is the default priority for the EventListener annotation.
	 * It is equal to 0.
	 */
	public static final int NORMAL = 0;

	/**
	 * The important priority is equal to 100.
	 */
	public static final int IMPORTANT = 100;

	/**
	 * The high priority is equal to 1000.
	 */
	public static final int HIGH = 1000;

	/**
	 * The highest Event priority. Equal to {@code Integer.MAX_VALUE}. This priority
	 * should be only used for necessary Event managing. Remember that a high Event
	 * priority means that the annotated method will be invoked first when the
	 * selected Event will be fired/raised.
	 */
	public static final int HIGHEST = Integer.MAX_VALUE;

	/**
	 * Cannot be instantiated.
	 */
	private EventPriority() {
	}
}
