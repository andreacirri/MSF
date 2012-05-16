package it.unibo.mobilesensingframework.mux;

import android.hardware.SensorEvent;

import com.lmax.disruptor.EventFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class ValueEvent.
 */
public final class ValueEvent {
	
	/** The _value. */
	private SensorEvent _value=null;
	
	/**
	 * Instantiates a new value event.
	 */
	public ValueEvent(){
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
	public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
		public ValueEvent newInstance() {
			return new ValueEvent();
		}
	};
}
