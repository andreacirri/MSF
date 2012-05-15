package it.unibo.mobilesensingframework.naming;

import android.app.Application;
import it.unibo.mobilesensingframework.mux.IMux;
import it.unibo.mobilesensingframework.mux.Mux;
import it.unibo.mobilesensingframework.mux.MyEventHandler;

public class NamingService extends Application{
	
	private IMux _imux=null;

	@Override
	public void onCreate() {
		super.onCreate();
		
		_imux=new Mux();
		_imux.registryHandler(new MyEventHandler());
	}

	public IMux get_imux() {
		return _imux;
	}

	public void set_imux(IMux iMux) {
		this._imux = iMux;
	}
	
	

	
}
