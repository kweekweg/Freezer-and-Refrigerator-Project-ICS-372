
public class FridgeCoolingState extends ApplianceState {
	private static FridgeCoolingState instance;
	static {
		instance = new FridgeCoolingState();
	}
	
	public static FridgeCoolingState instance() {
		return instance;
	}

	@Override
	public void handle(Object event) {
		if (event.equals(ApplianceContext.Events.FRIDGE_DOOR_OPEN_EVENT)) {
			processDoorOpen();
		} else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}
	
	public void processDoorOpen() {
		context.changeCurrentFridgeState(FridgeDoorOpenedState.instance());
	}
	
	public void processTemperatureRangeMet() {
		context.changeCurrentFridgeState(FridgeDoorClosedState.instance());
	}
	
	@Override
	public void processClockTick() {
		context.setFridgeTimer(context.getFridgeTimer() + 1);
		if (context.getFridgeTimer() % (context.getFridgeCoolingRate()) <= 0) {
			context.setFridgeCurrentTemp(context.getFridgeCurrentTemp() - 1);
			display.displayFridgeTemp(context.getFridgeCurrentTemp());
			int tempTemp = context.getFridgeCurrentTemp();
			if (tempTemp == context.getFridgeDesiredTemp()) {
				processTemperatureRangeMet();
			}
		}
	}
	
	@Override
	public void run() {
		display.turnFridgeLightOff();
		display.fridgeCoolingOn();
	}
}
