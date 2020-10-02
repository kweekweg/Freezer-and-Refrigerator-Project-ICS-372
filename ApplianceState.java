/**
 * Super class for all states.
 * 
 *
 */
public abstract class ApplianceState {
	protected static ApplianceContext context;
	protected static ApplianceDisplay display;
	
	/**
	 * Initialzies the context and display
	 */
	protected ApplianceState() {
		context = ApplianceContext.instance();
		display = context.getDisplay();
	}
	
	/**
	 * Processes a clock tick
	 */
	public abstract void processClockTick();
	
	/**
	 * Initializes the state
	 */
	public abstract void run();
	
	/**
	 * Handles an event
	 * 
	 * @param event
	 */
	public abstract void handle(Object event);
}
