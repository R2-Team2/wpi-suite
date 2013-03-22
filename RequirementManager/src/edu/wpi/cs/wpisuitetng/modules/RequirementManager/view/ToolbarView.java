package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;

/**
 * Sets up upper toolbar of RequirementManager tab
 * 
 * @author Arianna
 *
 */
public class ToolbarView extends JPanel {

	/**
	 * Creates and positions option buttons in upper toolbar
	 */
	public ToolbarView() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)); // add a border so you can see the panel
		
		SpringLayout toolbarLayout = new SpringLayout();
		this.setLayout(toolbarLayout);
		
		JButton createButton = new JButton("Create Requirement");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().createRequirement();
				
			}
		});
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GetRequirementsController grController = new GetRequirementsController(new RequirementModel());
				grController.retrieveRequirements();
			}
		});
		
		toolbarLayout.putConstraint(SpringLayout.NORTH, createButton, 25,SpringLayout.NORTH, this);
		toolbarLayout.putConstraint(SpringLayout.WEST, createButton, 50, SpringLayout.WEST, this);
		toolbarLayout.putConstraint(SpringLayout.NORTH, refreshButton, 25,SpringLayout.NORTH, this);
		toolbarLayout.putConstraint(SpringLayout.WEST, refreshButton, 50, SpringLayout.EAST, createButton);
		this.add(createButton);
		this.add(refreshButton);
	}
}
