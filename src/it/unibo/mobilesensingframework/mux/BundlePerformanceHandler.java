package it.unibo.mobilesensingframework.mux;

import android.util.Log;

import com.lmax.disruptor.EventHandler;

public class BundlePerformanceHandler implements EventHandler<BundlePerformance>{
	

	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;

	/** The Constant TAG. */
	private final static String TAG = BundlePerformanceHandler.class
			.getCanonicalName();

	public void onEvent(BundlePerformance event, long sequence,
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
