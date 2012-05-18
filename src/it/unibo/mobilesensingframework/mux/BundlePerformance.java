package it.unibo.mobilesensingframework.mux;

import com.lmax.disruptor.EventFactory;

// A similar Bundle class designed for best Android performance
public class BundlePerformance {
	
	public int _accuracy;
	public long _timestamp;
	public float[] _values;
	
	public float _range;
	public int _mindeley;
	public String _name;
	public float _power;
	public float _resolution;
	public int _type;
	public String _vendor;
	public int _version;
	

	public BundlePerformance(){
	}
	 
	
	public final static EventFactory<BundlePerformance> EVENT_FACTORY = new EventFactory<BundlePerformance>() {
		public BundlePerformance newInstance() {
			return new BundlePerformance();
		}
	};
}
