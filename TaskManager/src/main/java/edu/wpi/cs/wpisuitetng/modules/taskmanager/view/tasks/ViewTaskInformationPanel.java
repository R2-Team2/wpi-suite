package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import javax.swing.JList;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

public class ViewTaskInformationPanel extends AbstractInformationPanel{

	public ViewTaskInformationPanel(ViewTaskPanel parentPanel){
		this.parentPanel = parentPanel;
		this.buildLayout();
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


	@Override
	public void setTask(Task aTask) {
		aTask.getTaskID();
		this.boxTitle.setText(aTask.getTitle());
		this.boxDescription.setText(aTask.getDescription());
		//this.dropdownStatus.setSelectedItem(aTask.getStatus().toString());
		//requirement
		this.listChosenAssignees=(JList)aTask.getAssignedUsers();
		this.calStartDate.setDate(aTask.getStartDate());
		this.calDueDate.setDate(aTask.getDueDate());
		this.spinnerEstimatedEffort.setValue(aTask.getEstimatedEffort());
		this.spinnerActualEffort.setValue(aTask.getActualEffort());
	}	

}
