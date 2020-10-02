import java.util.Observable;
import java.util.Observer;

public class ApplianceContext implements Observer{

	private static ApplianceDisplay applianceDisplay;
	private static ApplianceState fridgeState;
	private static ApplianceState freezerState;
	private int fridgeTimer;
	private int fridgeMaxTemp = 41;
	private int fridgeMinTemp = 37;
	private int fridgeTempDifferential = 2;
	private int fridgeOpenRate = 2;
	private int fridgeClosedRate = 30;
	private int fridgeCoolingRate = 20;
	private int fridgeCurrentTemp;
	private int fridgeDesiredTemp;
	private boolean fridgeCoolingOn;
	private int freezerTimer;
	private int freezerMaxTemp = 0;
	private int freezerMinTemp = -9;
	private int freezerTempDifferential = 1;
	private int freezerOpenRate = 1;
	private int freezerClosedRate = 10;
	private int freezerCoolingRate = 30;
	private int freezerCurrentTemp;
	private int freezerDesiredTemp;
	private boolean freezerCoolingOn;
	private int roomTemperature;
	private static ApplianceContext applianceContext;
	static {
		applianceContext = new ApplianceContext();
		applianceDisplay = ApplianceDisplay.instance();
	}
	
	public static enum Events {
		FRIDGE_DOOR_OPEN_EVENT, FRIDGE_DOOR_CLOSE_EVENT, FRIDGE_COOLING_EVENT, FREEZER_DOOR_OPEN_EVENT, FREEZER_DOOR_CLOSE_EVENT, FREEZER_COOLING_EVENT
	}
	/**
	 * Make it a singleton
	 */
	private ApplianceContext() {
	}
	
	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static ApplianceContext instance() {
		if (applianceContext == null) {
			applianceContext = new ApplianceContext();
		}
		return applianceContext;
	}

	/**
	 * For observer
	 * 
	 * @param observable
	 *            will be the clock
	 * @param arg
	 *            the event that clock has ticked
	 */
	@Override
	public void update(Observable o, Object arg) {
		fridgeState.handle(arg);
		freezerState.handle(arg);
	}

	/**
	 * handle one of the several other events such as door close
	 * 
	 * @param arg
	 *            the event from the GUI
	 */
	public void processEvent(Object arg) {
		fridgeState.handle(arg);
		freezerState.handle(arg);
	}
	/**
	 * lets door closed state be the starting state for both fridge and freezer 
	 * adds the object as an observable for clock
	 */
	public void initialize() {
		applianceContext.changeCurrentFridgeState(FridgeDoorClosedState.instance());
		applianceContext.changeCurrentFreezerState(FreezerDoorClosedState.instance());
		Clock.instance().addObserver(applianceContext);
	}
	
	public void changeCurrentFridgeState(ApplianceState nextState) {
		fridgeState = nextState;
		fridgeTimer = 0;
		nextState.run();
	}
	
	public void changeCurrentFreezerState(ApplianceState nextState) {
		freezerState = nextState;
		freezerTimer = 0;
		nextState.run();
	}
	
	/**
	 * Gets the display
	 * 
	 * @return the display
	 */
	public ApplianceDisplay getDisplay() {
		return applianceDisplay;
	}
	
	/**
	 * Various getters and setters
	 */
	public int getFridgeTimer() {
		return fridgeTimer;
	}
	public void setFridgeTimer(int fridgeTimer) {
		this.fridgeTimer = fridgeTimer;
	}
	public int getFridgeMaxTemp() {
		return fridgeMaxTemp;
	}
	public void setFridgeMaxTemp(int fridgeMaxTemp) {
		this.fridgeMaxTemp = fridgeMaxTemp;
	}
	public int getFridgeMinTemp() {
		return fridgeMinTemp;
	}
	public void setFridgeMinTemp(int fridgeMinTemp) {
		this.fridgeMinTemp = fridgeMinTemp;
	}
	public int getFridgeTempDifferential() {
		return fridgeTempDifferential;
	}
	public void setFridgeTempDifferential(int fridgeTempDifferential) {
		this.fridgeTempDifferential = fridgeTempDifferential;
	}
	public int getFridgeOpenRate() {
		return fridgeOpenRate;
	}
	public void setFridgeOpenRate(int fridgeOpenRate) {
		this.fridgeOpenRate = fridgeOpenRate;
	}
	public int getFridgeClosedRate() {
		return fridgeClosedRate;
	}
	public void setFridgeClosedRate(int fridgeClosedRate) {
		this.fridgeClosedRate = fridgeClosedRate;
	}
	public int getFridgeCoolingRate() {
		return fridgeCoolingRate;
	}
	public void setFridgeCoolingRate(int fridgeCoolingRate) {
		this.fridgeCoolingRate = fridgeCoolingRate;
	}
	public int getFridgeCurrentTemp() {
		return fridgeCurrentTemp;
	}
	public void setFridgeCurrentTemp(int fridgeCurrentTemp) {
		this.fridgeCurrentTemp = fridgeCurrentTemp;
	}
	public int getFridgeDesiredTemp() {
		return fridgeDesiredTemp;
	}
	public void setFridgeDesiredTemp(int fridgeDesiredTemp) {
		this.fridgeDesiredTemp = fridgeDesiredTemp;
	}
	public boolean isFridgeCoolingOn() {
		return fridgeCoolingOn;
	}
	public void setFridgeCoolingOn(boolean fridgeCoolingOn) {
		this.fridgeCoolingOn = fridgeCoolingOn;
	}
	public int getFreezerTimer() {
		return freezerTimer;
	}
	public void setFreezerTimer(int freezerTimer) {
		this.freezerTimer = freezerTimer;
	}
	public int getFreezerMaxTemp() {
		return freezerMaxTemp;
	}
	public void setFreezerMaxTemp(int freezerMaxTemp) {
		this.freezerMaxTemp = freezerMaxTemp;
	}
	public int getFreezerMinTemp() {
		return freezerMinTemp;
	}
	public void setFreezerMinTemp(int freezerMinTemp) {
		this.freezerMinTemp = freezerMinTemp;
	}
	public int getFreezerTempDifferential() {
		return freezerTempDifferential;
	}
	public void setFreezerTempDifferential(int freezerTempDifferential) {
		this.freezerTempDifferential = freezerTempDifferential;
	}
	public int getFreezerOpenRate() {
		return freezerOpenRate;
	}
	public void setFreezerOpenRate(int freezerOpenRate) {
		this.freezerOpenRate = freezerOpenRate;
	}
	public int getFreezerClosedRate() {
		return freezerClosedRate;
	}
	public void setFreezerClosedRate(int freezerClosedRate) {
		this.freezerClosedRate = freezerClosedRate;
	}
	public int getFreezerCoolingRate() {
		return freezerCoolingRate;
	}
	public void setFreezerCoolingRate(int freezerCoolingRate) {
		this.freezerCoolingRate = freezerCoolingRate;
	}
	public int getFreezerCurrentTemp() {
		return freezerCurrentTemp;
	}
	public void setFreezerCurrentTemp(int freezerCurrentTemp) {
		this.freezerCurrentTemp = freezerCurrentTemp;
	}
	public int getFreezerDesiredTemp() {
		return freezerDesiredTemp;
	}
	public void setFreezerDesiredTemp(int freezerDesiredTemp) {
		this.freezerDesiredTemp = freezerDesiredTemp;
	}
	public boolean isFreezerCoolingOn() {
		return freezerCoolingOn;
	}
	public void setFreezerCoolingOn(boolean freezerCoolingOn) {
		this.freezerCoolingOn = freezerCoolingOn;
	}
	public int getRoomTemperature() {
		return roomTemperature;
	}
	public void setRoomTemperature(int roomTemperature) {
		this.roomTemperature = roomTemperature;
	}
	public static ApplianceContext getApplianceContext() {
		return applianceContext;
	}
	public static ApplianceState getFridgeState() {
		return fridgeState;
	}
	public static ApplianceState getFreezerState() {
		return freezerState;
	}
	public ApplianceDisplay getApplianceDisplay() {
		return applianceDisplay;
	}
}
