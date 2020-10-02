
public class FridgeOpenState extends ApplianceState {
	private static FridgeOpenState instance;
	static {
		instance = new FridgeOpenState();
	}
	
	public static FridgeOpenState instance() {
		return instance;
	}

	@Override
	public void handle(Object event) {
		if (event.equals(ApplianceContext.Events.FRIDGE_DOOR_CLOSE_EVENT)) {
			processDoorClosed();
		} else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}
	
	public void processDoorClosed() {
		context.changeCurrentState(FridgeDoorClosedState.instance());
	}
	
	@Override
	public void processClockTick() {
		context.setFridgeTimer(context.getFridgeTimer() + 1);
		if (context.getFridgeTimer() % (context.getFridgeOpenRate()) = 0) {
			context.setFridgeCurrentTemp(context.getFridgeCurrentTemp() + 1);
			display.displayFridgeTemp(context.getFridgeCurrentTemp());
		}
	}
	
	@Override
	public void run() {
		display.turnFridgeLightOn();
		display.fridgeCoolingOff();
	}
}