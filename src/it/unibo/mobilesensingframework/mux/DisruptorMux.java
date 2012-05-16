/**
 * 
 */
package it.unibo.mobilesensingframework.mux;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequencer;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;

import android.hardware.SensorEvent;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class Mux. This version of IMux uses Disruptor bus.
 * 
 * @author "Andrea Cirri"
 */
public class DisruptorMux implements IMux {

	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;

	/** The Constant TAG. */
	private final static String TAG = DisruptorMux.class.getCanonicalName();

	/** The Constant NUM_EVENT_PROCESSORS. */
	private static final int NUM_EVENT_PROCESSORS = 2;

	/** The Constant RING_SIZE. */
	private static final int RING_SIZE = 16;

	/** The _disruptor. */
	private Disruptor<BundleEvent> _disruptor = null;

	/** The _executor. */
	private ExecutorService _executor = null;

	/** The _ring buffer. */
	private RingBuffer<BundleEvent> _ringBuffer = null;

	/** The _start. */
	private long _start = 0;
	
	

	/**
	 * Instantiates a new Mux.
	 */
	public DisruptorMux() {

		_executor = Executors.newFixedThreadPool(NUM_EVENT_PROCESSORS);

		_disruptor = new Disruptor<BundleEvent>(BundleEvent.EVENT_FACTORY,
				_executor, new SingleThreadedClaimStrategy(RING_SIZE),
				new BlockingWaitStrategy());

	}

	/**
	 * Start.
	 */
	public void start() {

		_ringBuffer = _disruptor.start();
		_start = System.currentTimeMillis();
		if (DEBUG)
			Log.i(TAG, "Buffer avviato at " + _start);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unibo.mobilesensingframework.mux.IMux#sendEvent(android.hardware.
	 * SensorEvent)
	 */
	public void publicSensorEvent(SensorEvent event) {

		long sequence = _ringBuffer.next();
		BundleEvent _event = _ringBuffer.get(sequence);
		
		_event.getBundle().clear();
		_event.getBundle().putInt(SensorEventToBundle.ACCURACY_INT, event.accuracy);
		_event.getBundle().putLong(SensorEventToBundle.TIMESTAMP_LONG, event.timestamp);
		_event.getBundle().putFloatArray(SensorEventToBundle.VALUES_FLOAT_ARRAY, event.values);
		_event.getBundle().putFloat(SensorEventToBundle.SENSOR_MAXIMUM_RANGE_FLOAT, event.sensor.getMaximumRange());
		_event.getBundle().putInt(SensorEventToBundle.SENSOR_MINDELEY_INT, event.sensor.getMinDelay());
		_event.getBundle().putString(SensorEventToBundle.SENSOR_NAME_STRING, event.sensor.getName());
		_event.getBundle().putFloat(SensorEventToBundle.SENSOR_POWER_FLOAT, event.sensor.getPower());
		_event.getBundle().putFloat(SensorEventToBundle.SENSOR_RESOLUTION_FLOAT, event.sensor.getResolution());
		_event.getBundle().putInt(SensorEventToBundle.SENSOR_TYPE_INT, event.sensor.getType());
		_event.getBundle().putString(SensorEventToBundle.SENSOR_VENDOR_STRING, event.sensor.getVendor());
		_event.getBundle().putInt(SensorEventToBundle.SENSOR_VERSION_INT, event.sensor.getVersion());

		_ringBuffer.publish(sequence);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unibo.mobilesensingframework.mux.IMux#registryHandler(com.lmax.disruptor
	 * .EventHandler)
	 */
	public void registryHandler(EventHandler<BundleEvent> eventHandler) {
		// TODO Auto-generated method stub
		_disruptor.handleEventsWith(eventHandler);
		if (DEBUG)
			Log.i(TAG, "Aggiunto handler");
		start();
	}

}
