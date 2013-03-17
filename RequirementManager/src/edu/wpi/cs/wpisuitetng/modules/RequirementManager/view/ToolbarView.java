package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolbarView extends JPanel {

	public ToolbarView() {
		add(new JLabel("PostBoard toolbar placeholder")); // add a label with some placeholder text
		setBorder(BorderFactory.createLineBorder(Color.blue, 2)); // add a border so you can see the panel
	}
}
