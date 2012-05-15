///**
// * 
// */
//package it.unibo.mobilesensingframework.pool;
//
//import it.unibo.mobilesensingframework.input.inputmanager.InputManager;
//import org.apache.commons.pool.impl.GenericObjectPool;
//
//import android.os.Bundle;
//import android.util.Log;
//
///**
// * The Class BundlePool.
// * 
// * @author "Andrea Cirri"
// */
//public class BundlePool extends GenericObjectPool<Bundle> implements IBundlePool{
//
//	
//	/** The Constant DEBUG. */
//	private final static boolean DEBUG = true;
//	
//	/** The Constant TAG. */
//	private final static String TAG = InputManager.class.getCanonicalName();
//	
//	/**
//	 * Instantiates a new BundlePool.
//	 */
//	public BundlePool(){
//		this.setWhenExhaustedAction(WHEN_EXHAUSTED_GROW);
//	}
//
//	/* (non-Javadoc)
//	 * @see it.unibo.mobilesensingframework.pool.IBundlePool#borrowBundle()
//	 */
//	public Bundle borrowBundle() {
//		if(DEBUG)Log.i(TAG, "Prelevato Bundle");
//		
//		return this.borrowBundle();
//	}
//
//	/* (non-Javadoc)
//	 * @see it.unibo.mobilesensingframework.pool.IBundlePool#returnBundle(android.os.Bundle)
//	 */
//	public void returnBundle(Bundle b) {
//		if(DEBUG)Log.i(TAG, "Restituito Bundle");
//		this.returnBundle(b);
//	}
//	
//	
//}
