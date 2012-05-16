package it.unibo.mobilesensingframework.mux;

import android.util.Log;

import com.lmax.disruptor.EventHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class MyEventHandler.
 */
public class BundleEventHandler implements EventHandler<BundleEvent> {

	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;

	/** The Constant TAG. */
	private final static String TAG = BundleEventHandler.class
			.getCanonicalName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long,
	 * boolean)
	 */
	public void onEvent(BundleEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		// TODO Auto-generated method stub
		if (DEBUG)
			Log.i(TAG,
					"Ricevuto messaggio del sensore: "
							+ event.getBundle().getString(
									SensorEventToBundle.SENSOR_NAME_STRING)
							+ " X: "
							+ event.getBundle().getFloatArray(
									SensorEventToBundle.VALUES_FLOAT_ARRAY)[0]
							+ " Y: " + event.getBundle().getFloatArray(
									SensorEventToBundle.VALUES_FLOAT_ARRAY)[1] + " Z: "
							+ event.getBundle().getFloatArray(
									SensorEventToBundle.VALUES_FLOAT_ARRAY)[2]);
	}

}