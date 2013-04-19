/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class RequirementSelector extends JPanel 
{
	final private Dimension buttonDimensions = new Dimension(125,25);
	private JList<Requirement> requirementList;
	private List<JButton> buttonList;
	private JPanel buttonPanel;
	private RequirementSelectorMode mode;
	private Requirement activeRequirement;
	private RequirementSelectorListener listener; 
	
	public RequirementSelector(RequirementSelectorListener listener, Requirement requirement, RequirementSelectorMode mode, boolean showBorder)
	{
		this.buttonList = new ArrayList<JButton>();
		this.listener = listener;
		this.activeRequirement = requirement;
		this.mode = mode;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JScrollPane listScroll = new JScrollPane();
		listScroll.setPreferredSize(new Dimension(300,125));
		requirementList = new JList<Requirement>();
		listScroll.setViewportView(requirementList);
			
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));		
		this.add(listScroll);
		this.add(buttonPanel);
		
		String okText;
		if(this.mode == RequirementSelectorMode.POSSIBLE_CHILDREN)
		{
			okText = "Add Existing";
		}
		else
		{
			requirementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			okText = "Select Parent";
		}
		
		JButton okButton = new JButton(okText);
		okButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				okPressed();
			}
		});
		
		this.addButton(okButton);
		
		if(showBorder) this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		this.refreshList();
	}
	
	public void addButton(JButton button)
	{
		button.setMinimumSize(buttonDimensions);
		button.setPreferredSize(buttonDimensions);
		button.setMaximumSize(buttonDimensions);
		buttonList.add(button);
		buttonPanel.add(button);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
	}
	
	private void refreshList()
	{
		ListModel<Requirement> reqList = new DefaultListModel<Requirement>();
		
		switch(mode)
		{
			case POSSIBLE_CHILDREN:
				reqList = RequirementModel.getInstance().getPossibleChildren(activeRequirement);
				break;
			case POSSIBLE_PARENTS:
				reqList = RequirementModel.getInstance().getPossibleParents(activeRequirement);
				break;
		}
		
		requirementList.setModel(reqList);
	}
	
	private void okPressed()
	{
		if(mode == RequirementSelectorMode.POSSIBLE_CHILDREN)
		{
			Object[] selectedList = requirementList.getSelectedValues();
			for(Object obj : selectedList)
			{
				Requirement newChild = (Requirement) obj;
				try {
					newChild.setParent(activeRequirement);
					ViewEventController.getInstance().refreshEditRequirementPanel(newChild);
					UpdateRequirementController.getInstance().updateRequirement(newChild);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			ViewEventController.getInstance().refreshEditRequirementPanel(activeRequirement);
		}
		else
		{
			Requirement parentRequirement = requirementList.getSelectedValue();
			try {
				activeRequirement.setParent(parentRequirement);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			UpdateRequirementController.getInstance().updateRequirement(activeRequirement);
		}
		
		listener.requirementSelected();
		this.refreshList();
	}
	
	/**
	 * Overrides the paintComponent method to retrieve the requirements on the first painting.
	 * 
	 * @param g	The component object to paint
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		refreshList();

		super.paintComponent(g);
	}
	
	/**
	 * Overriding set enabled function so we can disable child panels
	 * @param whether its enabled or not
	 */
	@Override
	public void setEnabled(boolean enabled)
	{
		for(JButton button : buttonList)
		{
			button.setEnabled(enabled);
		}
		requirementList.setEnabled(enabled);
		super.setEnabled(enabled);
	}
}

