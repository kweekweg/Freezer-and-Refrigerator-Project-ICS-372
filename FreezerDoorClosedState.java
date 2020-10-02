
public class FreezerDoorClosedState extends ApplianceState {
	private static FreezerDoorClosedState instance;
	static {
		instance = new FreezerDoorClosedState();
	}
	
	public static FreezerDoorClosedState instance() {
		return instance;
	}

	@Override
	public void handle(Object event) {
		if (event.equals(ApplianceContext.Events.FREEZER_DOOR_OPEN_EVENT)) {
			processDoorOpen();
		} else if (event.equals(ApplianceContext.Events.FREEZER_COOLING_EVENT)) {
			processTemperatureRangeExceeded();
		} else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}
	
	public void processDoorOpen() {
		context.changeCurrentFreezerState(FreezerDoorOpenedState.instance());
	}
	
	public void processTemperatureRangeExceeded() {
		context.changeCurrentFreezerState(FreezerCoolingState.instance());
	}
	
	@Override
	public void processClockTick() {
		if(context.getFreezerCurrentTemp() > (context.getFreezerDesiredTemp() + context.getFreezerTempDifferential()))
			processTemperatureRangeExceeded();
		context.setFreezerTimer(context.getFreezerTimer() + 1);
		if (context.getFreezerTimer() % (context.getFreezerClosedRate()) <= 0) {
			context.setFreezerCurrentTemp(context.getFreezerCurrentTemp() + 1);
			display.displayFreezerTemp(context.getFreezerCurrentTemp());
			/*if(context.getFreezerCurrentTemp() > (context.getFreezerDesiredTemp() + context.getFreezerTempDifferential()))
				processTemperatureRangeExceeded(); */
		}
	}
	
	@Override
	public void run() {
		display.turnFreezerLightOff();
		display.freezerCoolingOff();
	}
}