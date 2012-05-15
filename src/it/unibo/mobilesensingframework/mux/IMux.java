/**
 * 
 */
package it.unibo.mobilesensingframework.mux;

import com.lmax.disruptor.EventHandler;

import android.hardware.SensorEvent;

/**
 * @author "Andrea Cirri"
 *
 */
public interface IMux {

	void sendEvent(SensorEvent event);
	void registryHandler(EventHandler<ValueEvent> eventHandler);
	
}
