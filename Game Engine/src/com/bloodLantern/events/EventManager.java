package com.bloodLantern.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.annotations.Nullable;

/**
 * Class used to manage Events. By 'managing', we mean registering listeners and
 * calling their listening methods when the Event type attached to them has been
 * fired/raised. It also contains some useful static methods when working with
 * {@link Event} objects.
 *
 * @author BloodLantern
 */
public final class EventManager {

	/**
	 * List containing all the classes listening to Events.
	 */
	@NotNull
	private static final Map<Listener, List<Class<? extends Event>>> LISTENERS = new HashMap<>();

	/**
	 * Comparator used for EventListener annotated methods priority.
	 */
	@NotNull
	private static final Comparator<Method> EVENT_LISTENER_COMPARATOR = new Comparator<>() {
		@Override
		public int compare(Method o1, Method o2) {
			int p1 = o1.getAnnotation(EventListener.class).value(), p2 = o2.getAnnotation(EventListener.class).value();
			return p1 > p2 ? 1 : p1 == p2 ? 0 : -1;
		}
	};

	/**
	 * Cannot be instantiated.
	 */
	private EventManager() {
	}

	/**
	 * Used to fire an Event for every registered Listener.
	 *
	 * @param event The Event to fire/raise.
	 * @return True if the Event should be continued. False if it is an instance of
	 *         Cancellable and that it has been cancelled.
	 */
	public static boolean fireEvent(@NotNull Event event) {
		if (event == null)
			throw new NullPointerException("Cannot fire a null Event!");
		Map<Method, Object> methodList = new HashMap<>();
		// Iterating over every registered Listener to find those that listen to the
		// parameterized Event type
		for (Entry<Listener, List<Class<? extends Event>>> entry : LISTENERS.entrySet())
			// If it found one then fire the event on this Listener
			// TODO Invoke Event methods listeners when any Event is fired/raised.
			if (entry.getValue().contains(event.getClass()))
				for (Method method : entry.getKey().getClass().getMethods())
					if (method.isAnnotationPresent(EventListener.class))
						if (method.canAccess(entry.getKey()))
							if (method.getParameterCount() == 1)
								if (method.getParameterTypes()[0].equals(event.getClass()))
									methodList.put(method, entry.getKey());
		List<Method> methods = new ArrayList<>(methodList.keySet());
		Collections.sort(methods, EVENT_LISTENER_COMPARATOR);
		for (Method method : methods)
			try {
				method.invoke(methodList.get(method), event);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception while parsing Event " + event + " in " + methodList.get(method));
				e.printStackTrace();
			}
		if (event instanceof Cancellable)
			if (((Cancellable) event).isCancelled())
				return false;
		return true;
	}

	/**
	 * Adds this Listener to the listeners list. Note that a same Listener may be
	 * added more than once to the list with different Event types. However, it
	 * won't do anything if a Listener has already been added to the list with the
	 * same Event type.
	 *
	 * @param listener  The Listener to append to the list.
	 * @param eventType The type of Event to listen.
	 * @return The updated listeners list, for chaining.
	 */
	@NotNull
	public static Map<Listener, List<Class<? extends Event>>> addListener(@NotNull Listener listener,
			@NotNull Class<? extends Event> eventType) {
		if (listener == null || eventType == null)
			throw new NullPointerException("Cannot add a null Listener or event type to the listeners list!");
		if (!LISTENERS.containsKey(listener))
			LISTENERS.put(listener, new ArrayList<Class<? extends Event>>());
		if (!LISTENERS.get(listener).contains(eventType))
			LISTENERS.get(listener).add(eventType);
		return LISTENERS;
	}

	/**
	 * Removes this Listener from the listeners list.
	 *
	 * @param listener The Listener to remove from to the list. Because no entry can
	 *                 be null, a null value won't do anything.
	 * @return The updated listeners list, for chaining.
	 */
	@NotNull
	public static Map<Listener, List<Class<? extends Event>>> removeListener(@Nullable Listener listener) {
		if (LISTENERS.containsKey(listener))
			LISTENERS.remove(listener);
		return LISTENERS;
	}

	/**
	 * Used to remove every Listener associated with the selected Event type.
	 *
	 * @param eventType The type of Event to remove from the listeners list.
	 * @return The updated listeners list, for chaining.
	 */
	@NotNull
	public static Map<Listener, List<Class<? extends Event>>> removeEventType(
			@NotNull Class<? extends Event> eventType) {
		if (eventType == null)
			throw new NullPointerException("Cannot remove a null EventType from the listeners list!");
		for (Entry<Listener, List<Class<? extends Event>>> entry : LISTENERS.entrySet())
			if (entry.getValue().contains(eventType))
				entry.getValue().remove(eventType);
		return LISTENERS;
	}

	/**
	 * Getter for the listeners list.
	 *
	 * @return The current listeners list.
	 */
	@NotNull
	public static Map<Listener, List<Class<? extends Event>>> getListeners() {
		return LISTENERS;
	}

}
