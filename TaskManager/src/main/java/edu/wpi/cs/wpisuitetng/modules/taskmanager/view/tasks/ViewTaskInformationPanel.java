package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import javax.swing.JList;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

public class ViewTaskInformationPanel extends AbstractInformationPanel{
	
	protected ViewTaskPanel parentPanel;
	
	public ViewTaskInformationPanel(ViewTaskPanel aParentPanel){
		super();
		this.parentPanel = aParentPanel;
		this.buildLayout();
		this.disableAll(true);
		setTask();
	}

	//@Override
	public void setTask() {
		Task viewTask = parentPanel.aTask;
		
		//viewTask.getTaskID();
		String t = viewTask.getTitle();
		this.boxTitle.setText(t);
		this.boxDescription.setText(viewTask.getDescription());
		this.dropdownStatus.setSelectedItem(viewTask.getStatus().toString());
		//requirement
		this.listChosenAssignees= viewTask.getAssignedUsers();
		this.calStartDate.setDate(viewTask.getStartDate());
		this.calDueDate.setDate(viewTask.getDueDate());
		this.spinnerEstimatedEffort.setValue(viewTask.getEstimatedEffort());
		this.spinnerActualEffort.setValue(viewTask.getActualEffort());
	}	

}
