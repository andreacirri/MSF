/**
 * 
 */
package it.unibo.mobilesensingframework.mux.disruptor;

import it.unibo.mobilesensingframework.mux.IMux;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.dsl.Disruptor;

import android.hardware.SensorEvent;
import android.util.Log;

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
	private static final int NUM_EVENT_PROCESSORS = 4;

	/** The Constant RING_SIZE. */
	private static final int RING_SIZE = 1024;

	/** The _disruptor. */
	private Disruptor<DisruptorBundlePerformance> _disruptor = null;

	/** The _executor. */
	private ExecutorService _executor = null;

	/** The _ring buffer. */
	private RingBuffer<DisruptorBundlePerformance> _ringBuffer = null;

	/** The _start. */
	private long _start = 0;

	/** The _handlers. */
	private Vector<EventHandler<DisruptorBundlePerformance>> _handlers = null;

	/** The _is started. */
	private boolean _isStarted = false;

	/**
	 * Instantiates a new Mux.
	 */
	public DisruptorMux() {

		_executor = Executors.newCachedThreadPool();//newFixedThreadPool(NUM_EVENT_PROCESSORS);

		_disruptor = new Disruptor<DisruptorBundlePerformance>(DisruptorBundlePerformance.EVENT_FACTORY,
				_executor, new SingleThreadedClaimStrategy(RING_SIZE),
				new BlockingWaitStrategy());

		_handlers = new Vector<EventHandler<DisruptorBundlePerformance>>();

	}

	/**
	 * Start.
	 */
	public void start() {
		_ringBuffer=null;
		_ringBuffer = _disruptor.start();
		_isStarted = true;
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
		DisruptorBundlePerformance b=_ringBuffer.get(sequence);
		
		
		b._accuracy=event.accuracy;
		b._mindeley=event.sensor.getMinDelay();
		b._name=event.sensor.getName();
		b._power=event.sensor.getPower();
		b._range=event.sensor.getMaximumRange();
		b._timestamp=event.timestamp;
		b._resolution=event.sensor.getResolution();
		b._type=event.sensor.getType();
		b._values=event.values;
		b._vendor=event.sensor.getVendor();
		b._version=event.sensor.getVersion();
		
//		BundleEvent _event = _ringBuffer.get(sequence);
//
//		Bundle bundle = _event.getBundle();
//
//		bundle.clear();
//		bundle.putInt(SensorEventToBundle.ACCURACY_INT, event.accuracy);
//		bundle.putLong(SensorEventToBundle.TIMESTAMP_LONG, event.timestamp);
//		bundle.putFloatArray(SensorEventToBundle.VALUES_FLOAT_ARRAY,
//				event.values);
//		bundle.putFloat(SensorEventToBundle.SENSOR_MAXIMUM_RANGE_FLOAT,
//				event.sensor.getMaximumRange());
//		bundle.putInt(SensorEventToBundle.SENSOR_MINDELEY_INT,
//				event.sensor.getMinDelay());
//		bundle.putString(SensorEventToBundle.SENSOR_NAME_STRING,
//				event.sensor.getName());
//		bundle.putFloat(SensorEventToBundle.SENSOR_POWER_FLOAT,
//				event.sensor.getPower());
//		bundle.putFloat(SensorEventToBundle.SENSOR_RESOLUTION_FLOAT,
//				event.sensor.getResolution());
//		bundle.putInt(SensorEventToBundle.SENSOR_TYPE_INT,
//				event.sensor.getType());
//		bundle.putString(SensorEventToBundle.SENSOR_VENDOR_STRING,
//				event.sensor.getVendor());
//		bundle.putInt(SensorEventToBundle.SENSOR_VERSION_INT,
//				event.sensor.getVersion());

		// _event.getBundle().clear();
		// _event.getBundle().putInt(SensorEventToBundle.ACCURACY_INT,
		// event.accuracy);
		// _event.getBundle().putLong(SensorEventToBundle.TIMESTAMP_LONG,
		// event.timestamp);
		// _event.getBundle().putFloatArray(SensorEventToBundle.VALUES_FLOAT_ARRAY,
		// event.values);
		// _event.getBundle().putFloat(SensorEventToBundle.SENSOR_MAXIMUM_RANGE_FLOAT,
		// event.sensor.getMaximumRange());
		// _event.getBundle().putInt(SensorEventToBundle.SENSOR_MINDELEY_INT,
		// event.sensor.getMinDelay());
		// _event.getBundle().putString(SensorEventToBundle.SENSOR_NAME_STRING,
		// event.sensor.getName());
		// _event.getBundle().putFloat(SensorEventToBundle.SENSOR_POWER_FLOAT,
		// event.sensor.getPower());
		// _event.getBundle().putFloat(SensorEventToBundle.SENSOR_RESOLUTION_FLOAT,
		// event.sensor.getResolution());
		// _event.getBundle().putInt(SensorEventToBundle.SENSOR_TYPE_INT,
		// event.sensor.getType());
		// _event.getBundle().putString(SensorEventToBundle.SENSOR_VENDOR_STRING,
		// event.sensor.getVendor());
		// _event.getBundle().putInt(SensorEventToBundle.SENSOR_VERSION_INT,
		// event.sensor.getVersion());

		_ringBuffer.publish(sequence);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unibo.mobilesensingframework.mux.IMux#registryHandler(com.lmax.disruptor
	 * .EventHandler)
	 */
//	public void registryHandler(EventHandler<BundleEvent> eventHandler) {
//		// TODO Auto-generated method stub
//		_handlers.add(eventHandler);
//		_disruptor.handleEventsWith(eventHandler);
//		if (DEBUG)
//			Log.i(TAG, "Aggiunto handler");
//		start();
//	}

	public void registryHandler(EventHandler<DisruptorBundlePerformance> eventHandler) {

		_handlers.add(eventHandler);
		if (!_isStarted) {
			_disruptor.halt();
			_disruptor.shutdown();
			_disruptor = null;
		}

		_disruptor = new Disruptor<DisruptorBundlePerformance>(DisruptorBundlePerformance.EVENT_FACTORY,
				_executor, new SingleThreadedClaimStrategy(RING_SIZE),
				new BlockingWaitStrategy());

		for (EventHandler<DisruptorBundlePerformance> c : _handlers) {
			_disruptor.handleEventsWith(c);
		}
		start();
		if (DEBUG)
			Log.i(TAG, "Aggiunto handler");
	}

}
