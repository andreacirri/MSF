package it.unibo.mobilesensingframework.input;

import android.os.Bundle;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IInput objects.
 */
public interface IInputFactory {

	/**
	 * Gets the single instance of IInputFactory.
	 *
	 * @param type the type of Sensor which are your needs
	 * @param parameters the parameters needed for the Sensor
	 * @return single instance of request Sensor type 
	 */
	IInput getInstance(int type, Bundle parameters);
	
}
