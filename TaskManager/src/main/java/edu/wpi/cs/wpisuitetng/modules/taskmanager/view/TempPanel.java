package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class TempPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public TempPanel() {
		setLayout(new MigLayout("", "[61px,grow]", "[16px][]"));
		
		JLabel label = new JLabel("New label");
		add(label, "cell 0 0,alignx left,aligny top");
		
		textField = new JTextField();
		add(textField, "cell 0 1,growx");
		textField.setColumns(10);

	}

}
