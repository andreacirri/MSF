package it.unibo.mobilesensingframework.mux.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * The Class DisruptorBundlePerformance. Similar Bundle class designed to sending SensorEvent which improves Android CPU usage.
 * 
 * @author "Andrea Cirri"
 */
public class DisruptorBundlePerformance {
	
	/** The _accuracy. */
	public int _accuracy;
	
	/** The _timestamp. */
	public long _timestamp;
	
	/** The _values. */
	public float[] _values;
	
	/** The _range. */
	public float _range;
	
	/** The _mindeley. */
	public int _mindeley;
	
	/** The _name. */
	public String _name;
	
	/** The _power. */
	public float _power;
	
	/** The _resolution. */
	public float _resolution;
	
	/** The _type. */
	public int _type;
	
	/** The _vendor. */
	public String _vendor;
	
	/** The _version. */
	public int _version;
	

	/**
	 * Instantiates a new disruptor bundle performance.
	 */
	public DisruptorBundlePerformance(){
	}
	 
	
	/** The Constant EVENT_FACTORY. */
	public final static EventFactory<DisruptorBundlePerformance> EVENT_FACTORY = new EventFactory<DisruptorBundlePerformance>() {
		public DisruptorBundlePerformance newInstance() {
			return new DisruptorBundlePerformance();
		}
	};
}
