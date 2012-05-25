package it.unibo.mobilesensingframework.naming;

import android.app.Application;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import it.unibo.mobilesensingframework.mux.IMux;
import it.unibo.mobilesensingframework.mux.disruptor.DisruptorBundlePerformanceHandler;
import it.unibo.mobilesensingframework.mux.disruptor.DisruptorMux;

// TODO: Auto-generated Javadoc
/**
 * The Class NamingService.
 */
public class NamingService extends Application{
	
	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;

	
	/** The _imux. */
	private IMux _imux=null;
	
	/** The pm. */
	PowerManager _pm = null;
	
	/** The _wake lock. */
	private WakeLock _wakeLock=null;

	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		_pm=(PowerManager) getSystemService(Context.POWER_SERVICE);
		_wakeLock = _pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG");
		_wakeLock.acquire();
		_imux=new DisruptorMux();
		_imux.registryHandler(new DisruptorBundlePerformanceHandler());
		_imux.registryHandler(new DisruptorBundlePerformanceHandler());
		_imux.registryHandler(new DisruptorBundlePerformanceHandler());
	}
	
	/* (non-Javadoc)
	 * @see android.app.Application#onTerminate()
	 */
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		_wakeLock.release();
		super.onTerminate();
		
	}

	/**
	 * Gets the Imux instance.
	 * 
	 * @return the _imux
	 */
	public IMux get_imux() {
		return _imux;
	}

	/**
	 * Sets the Imux instance.
	 * 
	 * @param iMux
	 *            the new _imux
	 */
	public void set_imux(IMux iMux) {
		this._imux = iMux;
	}
	
	
	/**
	 * Gets the _wake lock.
	 * 
	 * @return the _wake lock
	 */
	public WakeLock get_wakeLock() {
		return _wakeLock;
	}

	/**
	 * Sets the _wake lock.
	 * 
	 * @param _wakeLock
	 *            the new _wake lock
	 */
	public void set_wakeLock(WakeLock _wakeLock) {
		this._wakeLock = _wakeLock;
	}

	

	
}
