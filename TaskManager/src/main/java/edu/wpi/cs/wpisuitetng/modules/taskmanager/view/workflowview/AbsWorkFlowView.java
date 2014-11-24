package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

public abstract class AbsWorkFlowView extends JPanel{
    /** The work flow obj. */
    private WorkFlow workFlowObj;

    /** The task status panel. */
    private final JPanel taskStatusPanel;

    /** The task status views. */
    List<TaskStatusView> views;
    
    /** The view event controller. */
    private final ViewEventController viewEventController = ViewEventController.getInstance();
    
    
    public AbsWorkFlowView(){
    	//ViewEventController.getInstance().setWorkFlowView(this);
        workFlowObj = new WorkFlow();
        views = new ArrayList<TaskStatusView>();
        views.add(new TaskStatusView("New", "new"));
        views.add(new TaskStatusView("Selected for Development", "scheduled"));
        views.add(new TaskStatusView("Currently in Development", "in progress"));
        views.add(new TaskStatusView("Completed", "complete"));
        
        setLayout(new BorderLayout());

        taskStatusPanel = new JPanel();
        this.add(taskStatusPanel, BorderLayout.CENTER);
//        final TaskStatusView taskStatusNew = new TaskStatusView("New", "new");
//        final TaskStatusView taskStatusSelDev = new TaskStatusView("Selected for Development", "scheduled");
//        final TaskStatusView taskStatusInDev = new TaskStatusView("Currently in Development", "in progress");
//        final TaskStatusView taskStatusDone = new TaskStatusView("Completed", "complete");

        taskStatusPanel.setLayout(new MigLayout(
                        "",
                        "[350px:n:500px,grow,left][350px:n:500px,grow,left]"
                                + "[350px:n:500px,grow,left][350px:n:500px,grow,left]",
                        "[278px,grow 500]"));
        
        // Hard Coded Task Statuses, move this to database soon
        int x = 0;
        for(TaskStatusView t : views){
        	
        	taskStatusPanel.add(t, "grow");
        	x++;
        }
        
//        taskStatusPanel.add(taskStatusSelDev, "cell 1 0,grow");
//        taskStatusPanel.add(taskStatusInDev, "cell 2 0,grow");
//        taskStatusPanel.add(taskStatusDone, "cell 3 0,grow");

//        views.add(taskStatusNew);
//        views.add(taskStatusSelDev);
//        views.add(taskStatusInDev);
//        views.add(taskStatusDone);
        
    }
    
	/**
	 * @return the taskStatusPanel
	 */
	public JPanel getTaskStatusPanel() {
		return taskStatusPanel;
	}

	public WorkFlow getWorkFlowObj() {
		// TODO Auto-generated method stub
		return workFlowObj;
	}


	public void setWorkFlowObj(WorkFlow workFlowObj2) {
		// TODO Auto-generated method stub
		workFlowObj = workFlowObj2;
	}
	
    /**
     * Refresh.
     */
    public void refresh() {
        for (TaskStatusView v : views) {
            v.requestTasksFromDb();
        }
    }
    
    public List<TaskStatusView> getViews(){
    	return views;
    }
}
