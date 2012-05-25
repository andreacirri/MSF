package it.unibo.mobilesensingframework.mux.disruptor;

import com.lmax.disruptor.EventHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class MyEventHandler.
 */
public class DisruptorValueEventHandler implements EventHandler<DisruptorValueEvent>{
	
	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;
	
	/** The Constant TAG. */
	private final static String TAG = DisruptorValueEventHandler.class.getCanonicalName();

	/* (non-Javadoc)
	 * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long, boolean)
	 */
	public void onEvent(DisruptorValueEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		// TODO Auto-generated method stub
		//if(DEBUG)Log.i(TAG, "Ricevuto messaggio del sensore: "+ event.getValue().sensor + " X: "+event.getValue().values[0]+ " Y: "+event.getValue().values[1]+ " Z: "+event.getValue().values[2]);
	}

}
