package it.unibo.mobilesensingframework.mux.disruptor;

import android.util.Log;

import com.lmax.disruptor.EventHandler;

/**
 * The Class DisruptorBundlePerformanceHandler.
 * 
 * @author "Andrea Cirri"
 */
public class DisruptorBundlePerformanceHandler implements EventHandler<DisruptorBundlePerformance>{
	

	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;

	/** The Constant TAG. */
	private final static String TAG = DisruptorBundlePerformanceHandler.class
			.getCanonicalName();

	/* (non-Javadoc)
	 * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long, boolean)
	 */
	public void onEvent(DisruptorBundlePerformance event, long sequence,
			boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub
		if (DEBUG)
			Log.i(TAG,
					"Ricevuto messaggio del sensore: "
							+ event._name+" timestamp: "+event._timestamp
							+ " X: "
							+ event._values[0]
							+ " Y: " + event._values[1] + " Z: "
							+ event._values[2]);
	}

}
