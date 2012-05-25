package it.unibo.mobilesensingframework.input;


/**
 * The Interface IInput.
 * 
 * Interface that realizes an input component.
 * 
 * @author "Andrea Cirri"
 */
public interface IInput {

	/**
	 * Method used to start an IInput instance.
	 */
	public void start();
	
	/**
	 * Method used to stop an IInput instance.
	 */
	public void stop();
	
	/**
	 * Method used to check IInput start/stop state.
	 * 
	 * @return true, if is started
	 */
	public boolean isStarted();
	
}
