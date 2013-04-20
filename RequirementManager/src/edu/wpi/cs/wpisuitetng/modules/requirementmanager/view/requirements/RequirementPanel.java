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

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs.RequirementTabsPanel;

/**
 * @author Pi 
 * @author Chris
 * @author Brian
 */
public class RequirementPanel extends JPanel implements RequirementButtonListener
{
	private Requirement displayRequirement;
	private RequirementViewMode viewMode;
	
	private RequirementInformationPanel infoPanel;
	private RequirementTabsPanel tabsPanel;
	private RequirementButtonPanel buttonPanel;
	
	private boolean readyToClose = false;
	
	public RequirementPanel(Requirement editingRequirement)
	{
		this.viewMode = (RequirementViewMode.EDITING);
		
		this.displayRequirement = editingRequirement;
		this.buildLayout();
	}
	
	public RequirementPanel(int parentID)
	{
		this.viewMode = (RequirementViewMode.CREATING);
		
		this.displayRequirement = new Requirement();
		this.displayRequirement.setId(-2);
		
		try 
		{
			displayRequirement.setParentID(parentID);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		this.buildLayout();
	}
	
	private void buildLayout()
	{
		buttonPanel = new RequirementButtonPanel(this, viewMode, displayRequirement);
		tabsPanel = new RequirementTabsPanel(this, viewMode, displayRequirement);
		infoPanel = new RequirementInformationPanel(this, viewMode, displayRequirement);
		
		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, tabsPanel);
		
		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void OKPressed() {
		if (infoPanel.validateFields(true)) {
			infoPanel.update();
			readyToClose = true;
			ViewEventController.getInstance().removeTab(this);
		}		
	}

	public void clearPressed() 
	{
		infoPanel.clearInfo();
	}

	public void cancelPressed() 
	{
		readyToClose = true;
		ViewEventController.getInstance().refreshTable();
		ViewEventController.getInstance().removeTab(this);		
	}
	
	/**
	 * Deletes the requirement. Sets all fields uneditable, sets status to
	 * deleted and closes the tab.
	 */
	public void deletePressed() 
	{
		if (this.displayRequirement.getStatus() == RequirementStatus.INPROGRESS)
			return;

		this.infoPanel.deleteRequirement();

		displayRequirement.setStatus(RequirementStatus.DELETED, false);

		UpdateRequirementController.getInstance().updateRequirement(displayRequirement);
		
		ViewEventController.getInstance().refreshTable();
		ViewEventController.getInstance().removeTab(this);	
	}	

	public void fireDeleted(boolean b) {
		this.buttonPanel.fireDeleted(b);
		this.tabsPanel.fireDeleted(b);
	}

	public void fireValid(boolean b) {		
		buttonPanel.fireValid(b);
	}
	
	public void fireChanges(boolean b) {	
		buttonPanel.fireChanges(b);
	}
	
	public void fireRefresh()
	{
		infoPanel.refreshInfo();
	}

	public boolean readyToRemove() {
		if(readyToClose) return true;
		
		if(infoPanel.readyToRemove() && tabsPanel.readyToRemove())
		{
			return true;
		}
		else
		{
			int result = JOptionPane.showConfirmDialog(this, "Discard unsaved changes and close tab?", "Discard Changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			return result == 0;
		}
	}
	
	public RequirementInformationPanel getInfoPanel()
	{
		return this.infoPanel;
	}
	
	public RequirementButtonPanel getButtonPanel()
	{
		return this.buttonPanel;
	}
	
	public Requirement getDisplayRequirement() {
		return displayRequirement;
	}
}
