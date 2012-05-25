package it.unibo.mobilesensingframework.input;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * The Class InputManager.
 * 
 * @author "Andrea Cirri"
 */
public class InputManager extends Service {

	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;
	
	/** The Constant TAG. */
	private final static String TAG = InputManager.class.getCanonicalName();

	/** The Constant INTENT_START_ACTION. */
	private final static String INTENT_START_ACTION = "it.unibo.mobilesensingframework.inputmanager.START_INPUT";
	
	/** The Constant INTENT_STOP_ACTION. */
	private final static String INTENT_STOP_ACTION = "it.unibo.mobilesensingframework.inputmanager.STOP_INPUT";
	
	/** The Constant INTENT_SENSOR_TYPE. */
	private final static String INTENT_SENSOR_TYPE="SensorType";
	
	/** The Constant INTENT_SENSOR_PARAM. */
	private final static String INTENT_SENSOR_PARAM="SensorParameters";

	/** The _input factory. */
	private IInputFactory _inputFactory = null;

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		_inputFactory = new InputFactory(getApplicationContext());
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		if(intent!=null){
			
			Toast.makeText(getApplicationContext(), "Ricevuto Intent "+intent.getAction(), 1000).show();
			
			//richiesta di start sensore
			if(intent.getAction()==INTENT_START_ACTION){
				//verifico che l'intent contenga un tipo di sensore valido
				if(intent.getIntExtra(INTENT_SENSOR_TYPE, -1)!=-1){
				
					//recupero istanza del sensore e se non è null verifico stato del sensore. Se non è già avviato start.
					IInput inputsensor = _inputFactory.getInstance(intent.getIntExtra(INTENT_SENSOR_TYPE, -1), intent.getBundleExtra(INTENT_SENSOR_PARAM));
					if(inputsensor!=null){
						if(!inputsensor.isStarted()){
							inputsensor.start();
							if(DEBUG)Log.i(TAG, "Start a sensor. Type: "+intent.getIntExtra(INTENT_SENSOR_TYPE, -1));
						}
					}
				}
			}
			
			//richiesta di stop sensore
			if(intent.getAction()==INTENT_STOP_ACTION){
				//verifico che l'intent contenga un tipo di sensore valido
				if(intent.getIntExtra(INTENT_SENSOR_TYPE, -1)!=-1){
					
					//recupero istanza del sensore e se non è null verifico stato del sensore. Se è avviato stop.
					IInput inputsensor = _inputFactory.getInstance(intent.getIntExtra(INTENT_SENSOR_TYPE, -1), intent.getBundleExtra(INTENT_SENSOR_PARAM));
					if(inputsensor!=null){
						if(inputsensor.isStarted()){
							inputsensor.stop();
							if(DEBUG)Log.i(TAG, "Stop a sensor. Type: "+intent.getIntExtra(INTENT_SENSOR_TYPE, -1));
						}
					}
				}
			}	
		}
		
		return super.onStartCommand(intent, flags, startId);
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
