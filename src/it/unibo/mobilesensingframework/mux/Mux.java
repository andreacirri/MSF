/**
 * 
 */
package it.unibo.mobilesensingframework.mux;

import it.unibo.mobilesensingframework.input.AccelerometerInput;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * @author "Andrea Cirri"
 *
 */
public class Mux extends Handler implements IMux{
	
	/** The Constant DEBUG. */
	private final static boolean DEBUG = true;
	
	/** The Constant TAG. */
	private final static String TAG = Mux.class.getCanonicalName();

	@Override
	public void handleMessage(Message msg) {

		if(DEBUG) Log.i(TAG, "Ricevuto messaggio");
		//super.handleMessage(msg);
	}

	public Mux(){
	}

	/* (non-Javadoc)
	 * @see it.unibo.mobilesensingframework.mux.IMux#getHandler()
	 */
	public Handler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
