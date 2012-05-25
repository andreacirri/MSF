/**
 * 
 */
package it.unibo.mobilesensingframework.mux;

import it.unibo.mobilesensingframework.mux.disruptor.DisruptorBundlePerformance;

import com.lmax.disruptor.EventHandler;

import android.hardware.SensorEvent;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMux.
 * 
 * @author "Andrea Cirri"
 */
public interface IMux {

	/**
	 * Send event.
	 * 
	 * @param event
	 *            the event
	 */
	void publicSensorEvent(SensorEvent event);
	
	/**
	 * Registry handler.
	 * 
	 * @param eventHandler
	 *            the event handler
	 */
	void registryHandler(EventHandler<DisruptorBundlePerformance> eventHandler);
	
}
