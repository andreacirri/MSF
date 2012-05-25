package it.unibo.mobilesensingframework.mux.disruptor;

import android.os.Bundle;

import com.lmax.disruptor.EventFactory;


/**
 * The Class DisruptorValueEvent.
 * 
 * @author "Andrea Cirri"
 */
public final class DisruptorBundleEvent {
	
	/** The _value. */
	private Bundle _value=null;
	
	/**
	 * Instantiates a new value event.
	 */
	public DisruptorBundleEvent(){
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
	 * @param value
	 *            the new bundle
	 */
	public void setBundle(Bundle value) {
		_value = value;
	}

	/** The Constant EVENT_FACTORY. */
	public final static EventFactory<DisruptorBundleEvent> EVENT_FACTORY = new EventFactory<DisruptorBundleEvent>() {
		public DisruptorBundleEvent newInstance() {
			return new DisruptorBundleEvent();
		}
	};
}