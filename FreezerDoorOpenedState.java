
public class FreezerDoorOpenedState extends ApplianceState {
	private static FreezerDoorOpenedState instance;
	static {
		instance = new FreezerDoorOpenedState();
	}
	
	public static FreezerDoorOpenedState instance() {
		return instance;
	}

	@Override
	public void handle(Object event) {
		if (event.equals(ApplianceContext.Events.FREEZER_DOOR_CLOSE_EVENT)) {
			processDoorClosed();
		} else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}
	
	public void processDoorClosed() {
		context.changeCurrentFreezerState(FreezerDoorClosedState.instance());
	}
	
	@Override
	public void processClockTick() {
		context.setFreezerTimer(context.getFreezerTimer() + 1);
		if (context.getFreezerTimer() % (context.getFreezerOpenRate()) <= 0) {
			context.setFreezerCurrentTemp(context.getFreezerCurrentTemp() + 1);
			display.displayFreezerTemp(context.getFreezerCurrentTemp());
		}
	}
	
	@Override
	public void run() {
		display.turnFreezerLightOn();
		display.freezerCoolingOff();
	}
}