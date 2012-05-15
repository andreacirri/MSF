/**
 * 
 */
package it.unibo.mobilesensingframework.pool;

import android.os.Bundle;

/**
 * @author "Andrea Cirri"
 *
 */
public interface IBundlePool {

	Bundle borrowBundle();
	void returnBundle(Bundle b);
	
}
