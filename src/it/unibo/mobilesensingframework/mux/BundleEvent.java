package it.unibo.mobilesensingframework.mux;

import android.os.Bundle;

import com.lmax.disruptor.EventFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class ValueEvent.
 */
public final class BundleEvent {
	
	/** The _value. */
	private Bundle _value=null;
	
	/**
	 * Instantiates a new value event.
	 */
	public BundleEvent(){
		_value=new Bundle();
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Bundle getBundle() {
		return _value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param sensorEvent
	 *            the new value
	 */
	public void setBundle(Bundle value) {
		_value = value;
	}

	/** The Constant EVENT_FACTORY. */
	public final static EventFactory<BundleEvent> EVENT_FACTORY = new EventFactory<BundleEvent>() {
		public BundleEvent newInstance() {
			return new BundleEvent();
		}
	};
}