
public class FridgeDoorClosedState extends ApplianceState {
	private static FridgeDoorClosedState instance;
	static {
		instance = new FridgeDoorClosedState();
	}
	
	public static FridgeDoorClosedState instance() {
		return instance;
	}

	@Override
	public void handle(Object event) {
		if (event.equals(ApplianceContext.Events.FRIDGE_DOOR_OPEN_EVENT)) {
			processDoorOpen();
		} else if (event.equals(ApplianceContext.Events.FRIDGE_COOLING_EVENT)) {
			processTemperatureRangeExceeded();
		} else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}
	
	public void processDoorOpen() {
		context.changeCurrentFridgeState(FridgeDoorOpenedState.instance());
	}
	
	public void processTemperatureRangeExceeded() {
		context.changeCurrentFridgeState(FridgeCoolingState.instance());
	}
	
	@Override
	public void processClockTick() {
		if(context.getFridgeCurrentTemp() > (context.getFridgeDesiredTemp() + context.getFridgeTempDifferential())) {
			processTemperatureRangeExceeded();
		}
		context.setFridgeTimer(context.getFridgeTimer() + 1);
		int tempTimer = context.getFridgeTimer();
		if (tempTimer % (context.getFridgeClosedRate()) <= 0) {
			context.setFridgeCurrentTemp(context.getFridgeCurrentTemp() + 1);
			display.displayFridgeTemp(context.getFridgeCurrentTemp());
			/*if(context.getFridgeCurrentTemp() > (context.getFridgeDesiredTemp() + context.getFridgeTempDifferential()))
				processTemperatureRangeExceeded();*/
		}
	}
	
	@Override
	public void run() {
		display.turnFridgeLightOff();
		display.fridgeCoolingOff();
	}
}