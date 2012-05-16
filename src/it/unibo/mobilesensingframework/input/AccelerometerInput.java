package it.unibo.mobilesensingframework.input;

import it.unibo.mobilesensingframework.naming.NamingService;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class AccelerometerInput.
 */
public class AccelerometerInput implements IInput, SensorEventListener {

	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;
	
	/** The Constant TAG. */
	private final static String TAG = AccelerometerInput.class.getCanonicalName();
	
	/** The Constant SENSOR_TYPE. */
	private final static int SENSOR_TYPE = Sensor.TYPE_ACCELEROMETER;

	/** The _sensor manager. */
	private SensorManager _sensorManager = null;
	
	/** The _sensor. */
	private Sensor _sensor = null;
	
	/** The _context. */
	private Context _context = null;
	
	/** The _sensor rate. */
	private int _sensorRate = 0;
	
	/** The _is started. */
	private boolean _isStarted = false;
	
	/** The _naming service. */
	private NamingService _namingService=null;

	
	/**
	 * Return a new instance of AccelerometerInput.
	 * 
	 * @param context Context Android 
	 * @param sensorRate Sample rate value for the sensor. The value must be one of android.hardware.Sensor.SENSOR_DELAY_NORMAL, android.hardware.Sensor.SENSOR_DELAY_UI, android.hardware.Sensor.SENSOR_DELAY_GAME, or android.hardware.Sensor.SENSOR_DELAY_FASTEST or, the desired delay between events in microsecond.
	 */
	public AccelerometerInput(Context context, int sensorRate) {
		_context = context;
		_sensorRate = sensorRate;
	}

	
	/* (non-Javadoc)
	 * @see it.unibo.mobilesensingframework.input.IInput#start()
	 */
	public void start() {

		if (_sensorManager == null) {
			_sensorManager = (SensorManager) _context
					.getSystemService(Context.SENSOR_SERVICE);
		}
		if (_sensor == null) {
			_sensor = _sensorManager.getDefaultSensor(SENSOR_TYPE);
		}

		_sensorManager.registerListener(this, _sensor, _sensorRate);
		_isStarted = true;
		if (DEBUG)
			Log.i(TAG, "Start()");
	}

	
	/* (non-Javadoc)
	 * @see it.unibo.mobilesensingframework.input.IInput#stop()
	 */
	public void stop() {

		_sensorManager.unregisterListener(this, _sensor);
		_isStarted = false;

		if (_sensorManager != null) {
			_sensorManager = null;
		}
		if (_sensor != null) {
			_sensor = null;
		}

		if (DEBUG)
			Log.i(TAG, "Stop()");
	}

	/* (non-Javadoc)
	 * @see it.unibo.mobilesensingframework.input.IInput#isStarted()
	 */
	public boolean isStarted() {
		// TODO Auto-generated method stub
		return _isStarted;
	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
	 */
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
	 */
	public void onSensorChanged(SensorEvent event) {
		if(_namingService==null)_namingService=(NamingService)_context.getApplicationContext();
		_namingService.get_imux().publicSensorEvent(event);
		
	}

	// Metodi getter e setter
	/**
	 * Gets the sensor rate.
	 * 
	 * @return the _sensor rate
	 */
	public int get_sensorRate() {
		return _sensorRate;
	}

	/**
	 * Sets the sensor rate.
	 * 
	 * @param sensorRate
	 *            the new _sensor rate
	 */
	public void set_sensorRate(int sensorRate) {
		this._sensorRate = sensorRate;
		_sensorManager.unregisterListener(this, _sensor);
		_sensorManager.registerListener(this, _sensor, _sensorRate);
	}

}
