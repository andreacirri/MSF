package it.unibo.mobilesensingframework.pipelinemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.util.Log;

public class PipelineManager extends Service {

	/** The Constant TAG. */
	private final static String TAG = PipelineManager.class.getCanonicalName();

	/** The Constant DEBUG. */
	private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;

	public static final String ACTION_PICK_PLUGIN = "it.unibo.mobilesensingframework.ACTION_PIPELINE";
	static final String KEY_PKG = "pkg";
	static final String KEY_SERVICENAME = "servicename";
	static final String KEY_ACTIONS = "actions";
	static final String KEY_CATEGORIES = "categories";
	static final String BUNDLE_EXTRAS_CATEGORY = "category";

	private PluginReceiver _pluginReceiver = null;
	private IntentFilter _pluginIntentFilter = null;
	private boolean _isStarted = false;
	private ArrayList<HashMap<String, String>> _services = null;
	private ArrayList<String> _categories = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate() {
		super.onCreate();

		_services = new ArrayList<HashMap<String, String>>();
		_categories = new ArrayList<String>();

		refreshPluginList();

		// creo un broadcast receiver e imposto l'intent filter per gli eventi
		// di sistema legati all'installazione, l'upgrade e la rimozione di
		// package
		_pluginReceiver = new PluginReceiver();
		_pluginIntentFilter = new IntentFilter();
		_pluginIntentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
		_pluginIntentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		_pluginIntentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
		_pluginIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
		_pluginIntentFilter.addDataScheme("package");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// Al primo avvio registro broadcast receiver
		if (_isStarted == false) {
			registerReceiver(_pluginReceiver, _pluginIntentFilter);
			_isStarted = true;
			if(DEBUG)Log.i(TAG, "PipelineManager Started");
		}
		if(DEBUG)Log.i(TAG, "PipelineManager Started");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {

		// Al termine del servizio rimuovo registrazione broadcast receiver
		if (_isStarted == true) {
			unregisterReceiver(_pluginReceiver);
			_isStarted = false;
			if(DEBUG)Log.i(TAG, "PipelineManager Stopped");

		}

		// Imposto a null variabili per favorire gc
		_pluginIntentFilter = null;
		_pluginReceiver = null;

		super.onDestroy();
	}

	public void refreshPluginList() {
		//cancello lista 
		_services.clear();
		_categories.clear();
		PackageManager packageManager = getPackageManager();
		Intent baseIntent = new Intent(ACTION_PICK_PLUGIN);
		baseIntent.setFlags(Intent.FLAG_DEBUG_LOG_RESOLUTION);
		List<ResolveInfo> list = packageManager.queryIntentServices(baseIntent,
				PackageManager.GET_RESOLVED_FILTER);
		if (DEBUG)
			Log.i(TAG, "refreshPluginList: " + list);
		for (int i = 0; i < list.size(); ++i) {
			ResolveInfo info = list.get(i);
			ServiceInfo sinfo = info.serviceInfo;
			IntentFilter filter = info.filter;
			if (DEBUG)
				Log.i(TAG, "refreshPluginList: i: " + i + "; sinfo: " + sinfo
						+ ";filter: " + filter);
			if (sinfo != null) {
				HashMap<String, String> item = new HashMap<String, String>();
				item.put(KEY_PKG, sinfo.packageName);
				item.put(KEY_SERVICENAME, sinfo.name);
				String firstCategory = null;
				if (filter != null) {
					StringBuilder actions = new StringBuilder();
					for (Iterator<String> actionIterator = filter
							.actionsIterator(); actionIterator.hasNext();) {
						String action = actionIterator.next();
						if (actions.length() > 0)
							actions.append(",");
						actions.append(action);
					}
					StringBuilder categories = new StringBuilder();
					for (Iterator<String> categoryIterator = filter
							.categoriesIterator(); categoryIterator.hasNext();) {
						String category = categoryIterator.next();
						if (firstCategory == null)
							firstCategory = category;
						if (categories.length() > 0)
							categories.append(",");
						categories.append(category);
					}
					item.put(KEY_ACTIONS, new String(actions));
					item.put(KEY_CATEGORIES, new String(categories));
				} else {
					item.put(KEY_ACTIONS, "<null>");
					item.put(KEY_CATEGORIES, "<null>");
				}
				if (firstCategory == null)
					firstCategory = "";
				_categories.add(firstCategory);
				_services.add(item);
			}
		}
		if (DEBUG)
			Log.i(TAG, "services: " + _services);
		if (DEBUG)
			Log.i(TAG, "categories: " + _categories);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	class PluginReceiver extends BroadcastReceiver {

		/** The Constant TAG. */
		private final String TAG = PluginReceiver.class.getCanonicalName();

		/** The Constant DEBUG. */
		private final static boolean DEBUG = true && it.unibo.mobilesensingframework.debug.Debug.DEBUG_SYSTEM;

		public void onReceive(Context context, Intent intent) {
			if (DEBUG)Log.d(TAG, "onReceive: " + intent);
			refreshPluginList();
		}
	}

}
