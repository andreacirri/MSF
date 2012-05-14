package it.unibo.mobilesensingframework.input.inputmanager;

import it.unibo.mobilesensingframework.input.IInputFactory;
import it.unibo.mobilesensingframework.input.InputFactory;
import it.unibo.mobilesensingframework.input.IInput;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class InputManager extends Service {

	private final static boolean DEBUG = true;
	private final static String TAG = InputManager.class.getCanonicalName();

	private final static String INTENT_START_ACTION = "it.unibo.mobilesensingframework.inputmanager.START_INPUT";
	private final static String INTENT_STOP_ACTION = "it.unibo.mobilesensingframework.inputmanager.STOP_INPUT";
	private final static String INTENT_SENSOR_TYPE="SensorType";
	private final static String INTENT_SENSOR_PARAM="SensorParameters";

	private IInputFactory _inputFactory = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		_inputFactory = new InputFactory(getApplicationContext());
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

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
						if(!inputsensor.isStarted())
						inputsensor.start();
						
						if(DEBUG)Log.i(TAG, "Start a sensor. Type: "+intent.getIntExtra(INTENT_SENSOR_TYPE, -1));
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
						if(inputsensor.isStarted())
						inputsensor.stop();
						if(DEBUG)Log.i(TAG, "Stop a sensor. Type: "+intent.getIntExtra(INTENT_SENSOR_TYPE, -1));

					}
				}
			}	
		}
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
