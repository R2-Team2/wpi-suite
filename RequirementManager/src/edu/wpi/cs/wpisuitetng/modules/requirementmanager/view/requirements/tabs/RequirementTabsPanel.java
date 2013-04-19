package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs;

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
		
	}

	public void updateHistoryPanel() 
	{
		historyPanel.refresh();
	}

	public boolean readyToRemove() {
		return historyPanel.readyToRemove() && notePanel.readyToRemove() && testPanel.readyToRemove() && subReqPanel.readyToRemove();
	}
}
