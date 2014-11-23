package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

public class ViewTaskPanel extends AbstractTaskPanel{

	//protected AbstractInformationPanel infoPanel;
    //protected AbstractButtonPanel buttonPanel;
	//private ViewEventController viewEventController = ViewEventController.getInstance();
	
	public ViewTaskPanel()
	{
		this.buildLayout();
	}
	
	protected void buildLayout() {
        buttonPanel = new ViewTaskButtonPanel(this);
        infoPanel = new EditTaskInformationPanel(this);
        
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void editPressed() {
		// TODO Auto-generated method stub
		Task passTask = this.aTask;
		ViewEventController.getInstance().closeNewTaskPanel();
		EditTaskPanel editView = new EditTaskPanel();
		editView.openEditView(passTask);
		//editView.buildLayout();
	}

	@Override
	public void createPressed() {
		// TODO Auto-generated method stub
		
	}
	
}
