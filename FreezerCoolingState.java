
public class FreezerCoolingState extends ApplianceState {
	private static FreezerCoolingState instance;
	static {
		instance = new FreezerCoolingState();
	}
	
	public static FreezerCoolingState instance() {
		return instance;
	}

	@Override
	public void handle(Object event) {
		if (event.equals(ApplianceContext.Events.FREEZER_DOOR_OPEN_EVENT)) {
			processFreezerDoorOpen();
		} else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}
	
	public void processFreezerDoorOpen() {
		context.changeCurrentFreezerState(FreezerDoorOpenedState.instance());
	}
	
	public void processTemperatureRangeMet() {
		context.changeCurrentFreezerState(FreezerDoorClosedState.instance());
	}
	
	@Override
	public void processClockTick() {
		context.setFreezerTimer(context.getFreezerTimer() + 1);
		if (context.getFreezerTimer() % (context.getFreezerCoolingRate()) <= 0) {
			context.setFreezerCurrentTemp(context.getFreezerCurrentTemp() - 1);
			display.displayFreezerTemp(context.getFreezerCurrentTemp());
			if(context.getFreezerCurrentTemp() <= (context.getFreezerDesiredTemp()))
				processTemperatureRangeMet();
		}
	}
	
	@Override
	public void run() {
		display.turnFreezerLightOff();
		display.freezerCoolingOn();
	}
}