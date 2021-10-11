package com.bloodLantern;

import com.bloodLantern.events.CollisionEvent;
import com.bloodLantern.events.Event;
import com.bloodLantern.events.EventListener;
import com.bloodLantern.events.EventManager;
import com.bloodLantern.events.EventPriority;
import com.bloodLantern.events.Listener;
import com.bloodLantern.events.TriggerEvent;

/**
 * Simple class for tester Event handling implementation.
 *
 * @author BloodLantern
 */
public class EventTester implements Listener {

	/**
	 * Default constructor.
	 */
	public EventTester() {
		// Register this listener with the wanted Event
		EventManager.addListener(this, CollisionEvent.class);
		// EventManager.addListener(this, TriggerEvent.class);
		EventManager.addListener(this, Event.class);
	}

	@EventListener(EventPriority.IMPORTANT)
	public void onCollision(CollisionEvent event) {
		System.out.println("CollisionEvent called!");
		System.out.println(event.getObject1() + "\n" + event.getObject2());
	}

	@EventListener
	public void hey(Event event) {
		System.out.println("Hey, " + event);
	}

	@EventListener
	public void onTrigger(TriggerEvent event) {
		System.out.println("TriggerEvent called!");
		System.out.println(event.getObject1() + "\n" + event.getObject2());
	}

}
