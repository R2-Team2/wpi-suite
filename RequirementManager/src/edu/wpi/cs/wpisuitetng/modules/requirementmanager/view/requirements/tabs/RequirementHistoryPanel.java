package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs;

import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementViewMode;

public class RequirementHistoryPanel extends JScrollPane implements RequirementTab {

	private RequirementTabsPanel parentPanel;
	private RequirementViewMode viewMode;
	private Requirement currentRequirement;
	
	public RequirementHistoryPanel(RequirementTabsPanel parentPanel, RequirementViewMode vm, Requirement currentRequirement)
	{
		this.parentPanel = parentPanel;
		this.viewMode = vm;
		this.currentRequirement = currentRequirement;
		// Create scroll pane for window, set scroll bar to always be on
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Show the requirement's transaction history in the scroll pane

		this.refresh();
	}
	
	public void refresh() 
	{
		this.setViewportView(SingleHistoryPanel.createList(this.currentRequirement.getHistory()));		
	}

	@Override
	public boolean readyToRemove() {
		return true;
	}
}
