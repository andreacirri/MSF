package it.unibo.mobilesensingframework.mux;

import android.hardware.SensorEvent;

import com.lmax.disruptor.EventFactory;


public final class ValueEvent {
	private SensorEvent _value=null;
	
	public ValueEvent(){
	}

	public SensorEvent getValue() {
		return _value;
	}

	public void setValue(SensorEvent sensorEvent) {
		_value = sensorEvent;
	}

	public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
		public ValueEvent newInstance() {
			return new ValueEvent();
		}
	};
}
