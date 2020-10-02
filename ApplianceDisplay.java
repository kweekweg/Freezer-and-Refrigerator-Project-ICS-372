import java.util.Observable;

/**
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 */
public abstract class ApplianceDisplay extends Observable {
    protected static ApplianceContext context;
	protected static ApplianceDisplay instance;

	/**
	 * Initializes the context and instance
	 */
	protected ApplianceDisplay() {
		instance = this;
		context = ApplianceContext.instance();
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static ApplianceDisplay instance() {
		return instance;
	}

	/**
	 * Do the initializations to make the context an observer
	 */
	public void initialize() {
		instance().addObserver(context);
		context.initialize();
	}


	/**
	 * Indicate that the Fridge light is on
	 */
    public abstract void turnFridgeLightOn();
    
    /**
	 * Indicate that the Fridge light is off
	 */
	public abstract void turnFridgeLightOff();
    
    /**
	 * Indicate that the Freezer light is on
	 */
	public abstract void turnFreezerLightOn();

	/**
	 * Indicate that the Freezer light is off
	 */
	public abstract void turnFreezerLightOff();

	/**
	 * Indicate that the fridge door is now closed
	 */
	public abstract void fridgeDoorClosed();

    /**
	 * Indicate that the fridge door is now open
	 */
    public abstract void fridgeDoorOpened();
    
    /**
	 * Indicate that the freezer door is now open
	 */
    public abstract void freezerDoorOpened();
    
    /**
	 * Indicate that the freezer door is now closed
	 */
	public abstract void freezerDoorClosed();

	/**
	 * indicate that fridge cooling has begun
	 */
    public abstract void fridgeCoolingOn();
    
    /**
	 * indicate that freezer cooling has begun
	 */
	public abstract void freezerCoolingOn();

	/**
	 * indicate that fridge cooling has ended
	 */
    public abstract void fridgeCoolingOff();
    
    /**
	 * indicate that freezer cooling has ended
	 */
	public abstract void freezerCoolingOff();

    /**
	 * indicate that freezer temperature
	 */
	public abstract void displayFreezerTemp(int temp);

	/**
	 * indicate that fridge temperature
	 */
	public abstract void displayFridgeTemp(int temp);

	
}