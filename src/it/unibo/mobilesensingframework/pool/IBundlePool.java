/**
 * 
 */
package it.unibo.mobilesensingframework.pool;

import android.os.Bundle;

// TODO: Auto-generated Javadoc
/**
 * The Interface IBundlePool.
 * 
 * @author "Andrea Cirri"
 */
public interface IBundlePool {

	/**
	 * Borrow bundle.
	 * 
	 * @return the bundle
	 */
	Bundle borrowBundle();
	
	/**
	 * Return bundle.
	 * 
	 * @param b
	 *            the b
	 */
	void returnBundle(Bundle b);
	
}
