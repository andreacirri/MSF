package it.unibo.mobilesensingframework;

import it.unibo.mobilesensingframework.input.IInputFactory;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class MobileSensingFrameworkActivity.
 */
public class MobileSensingFrameworkActivity extends Activity {
	
	/** The STAR t_ input. */
	private static String START_INPUT="it.unibo.mobilesensingframework.inputmanager.START_INPUT";
	
	/** The STO p_ input. */
	private static String STOP_INPUT="it.unibo.mobilesensingframework.inputmanager.STOP_INPUT";
	
	/** The CATEGORY. */
	private static String CATEGORY="it.unibo.mobilesensingframework.INPUT_MANAGER";
	
	/** The Constant INTENT_SENSOR_TYPE. */
	private final static String INTENT_SENSOR_TYPE="SensorType";
	
	/** The Constant INTENT_SENSOR_PARAM. */
	private final static String INTENT_SENSOR_PARAM="SensorParameters";
	
	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            the saved instance state
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button b = (Button) findViewById(R.id.button1);

		b.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setAction(START_INPUT);
				i.addCategory(CATEGORY);
				i.putExtra(INTENT_SENSOR_TYPE, Sensor.TYPE_MAGNETIC_FIELD);
				startService(i);
				i.putExtra(INTENT_SENSOR_TYPE, Sensor.TYPE_ACCELEROMETER);
				startService(i);
			}
		});

		Button b2 = (Button) findViewById(R.id.button2);

		b2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setAction(STOP_INPUT);
				i.addCategory(CATEGORY);
				i.putExtra(INTENT_SENSOR_TYPE, Sensor.TYPE_MAGNETIC_FIELD);
				startService(i);
				
				i.putExtra(INTENT_SENSOR_TYPE, Sensor.TYPE_ACCELEROMETER);
				startService(i);
			}
		});

	}
}