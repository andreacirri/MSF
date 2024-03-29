/**
 * 
 */
package it.unibo.mobilesensingframework.input;

import java.util.HashMap;

import android.content.Context;
import android.hardware.Sensor;
import android.os.Bundle;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Input objects.
 * 
 * @author "Andrea Cirri"
 */
public class InputFactory implements IInputFactory{
	
	/** The _inputs. */
	private HashMap<Integer, IInput> _inputs=null;
	
	/** The _context. */
	private Context _context;
	

	/**
	 * Instantiates a new InputFactory.
	 * 
	 * @param context
	 *            the Android context
	 */
	public InputFactory(Context context) {
		_inputs=new HashMap<Integer, IInput>();
		_context=context;
	}




	/* (non-Javadoc)
	 * @see it.unibo.mobilesensingframework.input.IInputFactory#getInstance(int, android.os.Bundle)
	 */
	public IInput getInstance(int type, Bundle parameters) {

		
		if(_inputs.get(type)==null) {
			
			switch(type){
			case Sensor.TYPE_ACCELEROMETER : 
				_inputs.put(Sensor.TYPE_ACCELEROMETER, new AccelerometerInput(_context, android.hardware.SensorManager.SENSOR_DELAY_GAME));
				break;	
				
			case Sensor.TYPE_MAGNETIC_FIELD : 
				_inputs.put(Sensor.TYPE_MAGNETIC_FIELD, new MagneticFieldInput(_context, android.hardware.SensorManager.SENSOR_DELAY_GAME));
				break;
			}
			
						
		}
			
		return _inputs.get(type);
	}

	
}
