package it.unibo.mobilesensingframework.mux;

import android.util.Log;

import com.lmax.disruptor.EventHandler;

public class MyEventHandler implements EventHandler<ValueEvent>{
	
	/** The Constant DEBUG. */
	private final static boolean DEBUG = true;
	
	/** The Constant TAG. */
	private final static String TAG = MyEventHandler.class.getCanonicalName();

	public void onEvent(ValueEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		// TODO Auto-generated method stub
		if(DEBUG)Log.i(TAG, "Ricevuto messaggio del sensore: "+ event.getValue().sensor + " X: "+event.getValue().values[0]+ " Y: "+event.getValue().values[1]+ " Z: "+event.getValue().values[2]);
	}

}
