package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class RequirementButtonsPanel extends JPanel{
	
	public RequirementButtonsPanel(){
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)); // add a border so you can see the panel
		
//		SpringLayout toolbarLayout = new SpringLayout();
//		this.setLayout(toolbarLayout);
		
		// initialize the main view toolbar buttons
		JButton createButton = new JButton("Create Requirement");

		// the action listener for the Create Requirement Button
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// bring up a create requirement pane if not in Multiple Requirement Editing Mode
				if (!ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
					ViewEventController.getInstance().createRequirement();
				}
			}
		});		
		
		//toolbarLayout.putConstraint(SpringLayout.NORTH, createButton, 25,SpringLayout.NORTH, this);
		//toolbarLayout.putConstraint(SpringLayout.WEST, createButton, 50, SpringLayout.WEST, this);
		this.add(createButton);
	}
	
}
