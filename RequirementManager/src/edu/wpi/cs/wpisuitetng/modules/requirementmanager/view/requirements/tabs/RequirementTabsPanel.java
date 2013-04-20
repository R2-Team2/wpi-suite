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
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementViewMode;

public class RequirementTabsPanel extends JTabbedPane implements RequirementPanelListener
{
	private final List<RequirementPanelListener> listeners = new LinkedList<RequirementPanelListener>();
	private final RequirementViewMode viewMode;
	private final RequirementPanel parentPanel;
	private final Requirement currentRequirement;
	private final RequirementHistoryPanel historyPanel;
	private final RequirementNotePanel notePanel;
	private final RequirementTestPanel testPanel;
	private final SubrequirementPanel subReqPanel;
	
	/**
	 * Constructor for the requirement tabs panel
	 * @param parentPanel parent panel
	 * @param viewMode view mode
	 * @param currentRequirement current requirement
	 */
	public RequirementTabsPanel(RequirementPanel parentPanel, RequirementViewMode viewMode, Requirement currentRequirement) 
	{
		this.parentPanel = parentPanel;
		this.viewMode = viewMode;
		this.currentRequirement = currentRequirement;
		
		historyPanel = new RequirementHistoryPanel(this, this.viewMode, this.currentRequirement);
		listeners.add(historyPanel);
		notePanel = new RequirementNotePanel(this, this.viewMode, this.currentRequirement);
		listeners.add(notePanel);
		testPanel = new RequirementTestPanel(this, this.viewMode, this.currentRequirement);
		listeners.add(testPanel);
		subReqPanel = new SubrequirementPanel(this, this.viewMode, this.currentRequirement);
		listeners.add(subReqPanel);
		
		this.add("Notes", notePanel);
		this.add("Transaction History", historyPanel);
		this.add("Acceptance Tests", testPanel);
		this.add("Subrequirements", subReqPanel);
		
		this.setMinimumSize(new Dimension(500,100));		
	}

	public boolean readyToRemove() {
		boolean readyToRemove = true;
		for(RequirementPanelListener listener : listeners)
		{
			readyToRemove &= listener.readyToRemove();
		}
		return readyToRemove;
	}
	
	public void fireDeleted(boolean b)
	{
		for(RequirementPanelListener listener : listeners)
		{
			listener.fireDeleted(b);
		}	
	}

	@Override
	public void fireValid(boolean b) {
		for(RequirementPanelListener listener : listeners)
		{
			listener.fireValid(b);
		}		
	}

	@Override
	public void fireChanges(boolean b) {
		for(RequirementPanelListener listener : listeners)
		{
			listener.fireChanges(b);
		}		
	}

	@Override
	public void fireRefresh() {
		for(RequirementPanelListener listener : listeners)
		{
			listener.fireRefresh();
		}
	}
}
