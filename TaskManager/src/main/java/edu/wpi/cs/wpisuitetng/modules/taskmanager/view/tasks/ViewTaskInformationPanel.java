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
		//this.disableAll(true);
		setTask();
	}
	
	public void disableAll(Boolean io)
	{
		io = !io;
		//aTask.getTaskID();
		this.boxTitle.setEnabled(io);
		this.boxDescription.setEnabled(io);
		//this.dropdownStatus.setSelectedItem(aTask.getStatus().toString());
		//requirement
		this.listChosenAssignees.setEnabled(io);
		this.calStartDate.setEnabled(io);
		this.calDueDate.setEnabled(io);
		this.spinnerEstimatedEffort.setEnabled(io);
		this.spinnerActualEffort.setEnabled(io);
	}


	//@Override
	public void setTask() {
		Task viewTask = parentPanel.aTask;
		
		//viewTask.getTaskID();
		String t = viewTask.getTitle();
		this.boxTitle.setText(t);
		this.boxDescription.setText(viewTask.getDescription());
		//this.dropdownStatus.setSelectedItem(viewTask.getStatus().toString());
		//requirement
		this.listChosenAssignees= viewTask.getAssignedUsers();
		this.calStartDate.setDate(viewTask.getStartDate());
		this.calDueDate.setDate(viewTask.getDueDate());
		this.spinnerEstimatedEffort.setValue(viewTask.getEstimatedEffort());
		this.spinnerActualEffort.setValue(viewTask.getActualEffort());
	}	

}
