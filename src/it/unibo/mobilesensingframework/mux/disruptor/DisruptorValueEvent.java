package it.unibo.mobilesensingframework.mux.disruptor;

import android.hardware.SensorEvent;

import com.lmax.disruptor.EventFactory;


/**
 * The Class DisruptorValueEvent.
 * 
 * @author "Andrea Cirri"
 */
public final class DisruptorValueEvent {
	
	/** The _value. */
	private SensorEvent _value=null;
	
	/**
	 * Instantiates a new value event.
	 */
	public DisruptorValueEvent(){
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public SensorEvent getValue() {
		return _value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param sensorEvent
	 *            the new value
	 */
	public void setValue(SensorEvent sensorEvent) {
		_value = sensorEvent;
	}

	/** The Constant EVENT_FACTORY. */
	public final static EventFactory<DisruptorValueEvent> EVENT_FACTORY = new EventFactory<DisruptorValueEvent>() {
		public DisruptorValueEvent newInstance() {
			return new DisruptorValueEvent();
		}
	};
}
