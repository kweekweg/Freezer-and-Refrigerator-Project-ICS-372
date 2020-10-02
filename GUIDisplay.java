import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * GUI to implement the ApplianceDisplay interface A pretty elementary interface
 *
 */
public class GUIDisplay extends ApplianceDisplay implements ActionListener {
	private static SimpleDisplay frame;

	/**
	 * Makes it a singleton
	 */
	private GUIDisplay() {
		frame = new SimpleDisplay();
		initialize();
	}

	/**
	 * This class has most of the widgets
	 *
	 */
	private class SimpleDisplay extends JFrame {
		private JLabel roomTempLabel = new JLabel("Room Temperature");
		private JTextField roomTemp = new JTextField(5);
		private JButton setRoomTemp = new JButton("Set Room Temperature");
		private JLabel desiredFridgeTempLabel= new JLabel("Desired Fridge Temperature");
		private JTextField desiredFridgeTemp = new JTextField(5);
		private JButton setDesiredFridgeTemp = new JButton("Set desired Fridge Temperature");
		private JLabel desiredFreezerTempLabel= new JLabel("Desired Freezer Temperature");
		private JTextField desiredFreezerTemp = new JTextField(5);
		private JButton setDesiredFeezerTemp = new JButton("Set desired Feezer Temperature");
		private JButton openFridgeDoor = new JButton("Open fridge door");
		private JButton closeFridgeDoor = new JButton("Close fridge door");
		private JButton openFreezerDoor = new JButton("Open freezer door");
		private JButton closeFreezerDoor = new JButton("Close freezer door");
		private JLabel fridgeTempStatus = new JLabel("Fridge temperature");
		private JLabel freezerTempStatus = new JLabel("Fridge temperature");
		private JLabel fridgeLightStatus = new JLabel("Fridge Light Off");
		private JLabel freezerLightStatus = new JLabel("Freezer Light Off");
		private JLabel fridgeCoolingStatus = new JLabel("Fridge cooling");
		private JLabel freezerCoolingStatus = new JLabel("Freezer cooling");
		private JLabel fridgeDoorStatus = new JLabel("Fridge Door Closed");
		private JLabel freezerDoorStatus = new JLabel("Freezer  Door Closed");

		/**
		 * Sets up the interface
		 */
		private SimpleDisplay() {
			super("Refridgerator");
			getContentPane().setLayout(new FlowLayout());
			getContentPane().add(roomTempLabel);
			getContentPane().add(roomTemp );
			getContentPane().add(setRoomTemp);
			getContentPane().add(desiredFridgeTempLabel);
			getContentPane().add(desiredFridgeTemp);
			getContentPane().add(setDesiredFridgeTemp);
			getContentPane().add(desiredFreezerTempLabel);
			getContentPane().add(desiredFreezerTemp);
			getContentPane().add(setDesiredFeezerTemp);
			getContentPane().add(openFridgeDoor);
			getContentPane().add(closeFridgeDoor);
			getContentPane().add(openFreezerDoor);
			getContentPane().add(closeFreezerDoor);
			getContentPane().add(fridgeTempStatus);
			getContentPane().add(freezerTempStatus);
			getContentPane().add(fridgeLightStatus );
			getContentPane().add(freezerLightStatus);
			getContentPane().add(fridgeCoolingStatus);
			getContentPane().add(freezerCoolingStatus);
			getContentPane().add(fridgeDoorStatus);
			getContentPane().add(freezerDoorStatus);
			setRoomTemp.addActionListener(GUIDisplay.this);
			setDesiredFridgeTemp.addActionListener(GUIDisplay.this);
			setDesiredFeezerTemp.addActionListener(GUIDisplay.this);
			openFridgeDoor.addActionListener(GUIDisplay.this);
			closeFridgeDoor.addActionListener(GUIDisplay.this);
			openFreezerDoor.addActionListener(GUIDisplay.this);
			closeFreezerDoor.addActionListener(GUIDisplay.this);
			pack();
			setVisible(true);
		}
	}

	/**
	 * Handles the clicks
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(frame.closeFreezerDoor)) {
			ApplianceContext.instance().processEvent(
				ApplianceContext.Events.FREEZER_DOOR_CLOSE_EVENT);
		}else if (event.getSource().equals(frame.closeFridgeDoor)) {
			ApplianceContext.instance().processEvent(
				ApplianceContext.Events.FRIDGE_DOOR_CLOSE_EVENT);
		} else if (event.getSource().equals(frame.openFridgeDoor)) {
			ApplianceContext.instance().processEvent(
				ApplianceContext.Events.FRIDGE_DOOR_OPEN_EVENT);
		} else if (event.getSource().equals(frame.openFreezerDoor)) {
			ApplianceContext.instance().processEvent(
				ApplianceContext.Events.FREEZER_DOOR_OPEN_EVENT);
		} else if (event.getSource().equals(frame.setRoomTemp)) {
			setRoomTemp(Integer.parseInt(frame.roomTemp.getText()));
		} else if (event.getSource().equals(frame.setDesiredFridgeTemp)) {
			setFridgeDesiredTemp(Integer.parseInt(frame.desiredFridgeTemp.getText()));
		} else if (event.getSource().equals(frame.setDesiredFeezerTemp)) {
			setFreezerDesiredTemp(Integer.parseInt(frame.desiredFreezerTemp.getText()));
		}
	}

	@Override
	public void turnFridgeLightOn() {
		frame.fridgeLightStatus.setText("Fridge Light On");

	}

	@Override
	public void turnFridgeLightOff() {
		frame.fridgeLightStatus.setText("Fridge Light Off");

	}

	@Override
	public void turnFreezerLightOn() {
		frame.freezerLightStatus.setText("Freezer Light On");

	}

	@Override
	public void turnFreezerLightOff() {
		frame.freezerLightStatus.setText("Freezer Light Off");

	}

	@Override
	public void fridgeDoorClosed() {
		frame.fridgeDoorStatus.setText("Fridge door closed");

	}

	@Override
	public void fridgeDoorOpened() {
		frame.fridgeDoorStatus.setText("Fridge door open");

	}

	@Override
	public void freezerDoorOpened() {
		frame.freezerDoorStatus.setText("Freezer door open");

	}

	@Override
	public void freezerDoorClosed() {
		frame.freezerDoorStatus.setText("Freezer door closed");

	}

	@Override
	public void fridgeCoolingOn() {
		frame.fridgeCoolingStatus.setText("Fridge Cooling");

	}

	@Override
	public void freezerCoolingOn() {
		frame.freezerCoolingStatus.setText("Freezer Cooling");

	}

	@Override
	public void fridgeCoolingOff() {
		frame.fridgeCoolingStatus.setText("Fridge idle");

	}

	@Override
	public void freezerCoolingOff() {
		frame.freezerCoolingStatus.setText("Freezer idle");

	}

	public void setFridgeDesiredTemp(int temp){
		ApplianceContext.instance().setFridgeDesiredTemp(temp);
	}

	public void setFreezerDesiredTemp(int temp){
		ApplianceContext.instance().setFreezerDesiredTemp(temp);
	}

	public void setRoomTemp(int temp){
		ApplianceContext.instance().setRoomTemperature(temp);
	}

	@Override
	public void displayFreezerTemp(int temp) {
		frame.freezerTempStatus.setText("Freezer temperature "+temp+ "F");
	}

	@Override
	public void displayFridgeTemp(int temp) {
		frame.fridgeTempStatus.setText("Fridge temperature "+temp + "F");

	}

	public static void main(String[] args) {
		ApplianceDisplay display = new GUIDisplay();
	}

	
	
}