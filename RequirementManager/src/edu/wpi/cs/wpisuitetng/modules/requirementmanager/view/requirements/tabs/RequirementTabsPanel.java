/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementViewMode;

public class RequirementTabsPanel extends JTabbedPane
{
	private RequirementViewMode viewMode;
	private RequirementPanel parentPanel;
	private Requirement currentRequirement;
	private RequirementHistoryPanel historyPanel;
	private RequirementNotePanel notePanel;
	private RequirementTestPanel testPanel;
	private SubrequirementPanel subReqPanel;
	
	public RequirementTabsPanel(RequirementPanel parentPanel, RequirementViewMode viewMode, Requirement currentRequirement) 
	{
		this.parentPanel = parentPanel;
		this.viewMode = viewMode;
		this.currentRequirement = currentRequirement;
		
		this.historyPanel = new RequirementHistoryPanel(this, this.viewMode, this.currentRequirement);
		this.notePanel = new RequirementNotePanel(this, this.viewMode, this.currentRequirement);
		this.testPanel = new RequirementTestPanel(this, this.viewMode, this.currentRequirement);
		this.subReqPanel = new SubrequirementPanel(this, this.viewMode, this.currentRequirement);
		
		this.add("Notes", notePanel);
		this.add("Transaction History", historyPanel);
		this.add("Acceptance Tests", testPanel);
		this.add("Subrequirements", subReqPanel);
		
		this.setMinimumSize(new Dimension(500,100));		
	}

	public void updateHistoryPanel() 
	{
		historyPanel.refresh();
	}

	public boolean readyToRemove() {
		return historyPanel.readyToRemove() && notePanel.readyToRemove() && testPanel.readyToRemove() && subReqPanel.readyToRemove();
	}
	
	public void fireDeleted(boolean b)
	{
		subReqPanel.enableChildren(!b);
	}
}
