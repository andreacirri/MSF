package it.unibo.mobilesensingframework;

import java.io.IOException;
import java.io.RandomAccessFile;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * The Class MobileSensingFrameworkActivity.
 * 
 * @author "Andrea Cirri"
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
		
		Button stats = (Button) findViewById(R.id.button3);

		stats.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView textStats=(TextView)findViewById(R.id.textView1);
				textStats.setText("Cpu Usage: "+readUsage());
			}
		});

	}
	
	
	/**
	 * Read usage.
	 * 
	 * @return the float
	 */
	private float readUsage() {
	    try {
	        RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
	        String load = reader.readLine();

	        String[] toks = load.split(" ");

	        long idle1 = Long.parseLong(toks[5]);
	        long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	              + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        try {
	            Thread.sleep(360);
	        } catch (Exception e) {}

	        reader.seek(0);
	        load = reader.readLine();
	        reader.close();

	        toks = load.split(" ");

	        long idle2 = Long.parseLong(toks[5]);
	        long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	            + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }

	    return 0;
	} 
}