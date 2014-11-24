package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

public abstract class AbsWorkFlowView extends JPanel{
    /** The work flow obj. */
    private WorkFlow workFlowObj;

    /** The task status panel. */
    public final JPanel taskStatusPanel;

    /** The task status views. */
    private List<TaskStatusView> views;
    
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

        taskStatusPanel.setLayout(new MigLayout(
                        "",
                        "[350px:n:500px,grow,left][350px:n:500px,grow,left]"
                                + "[350px:n:500px,grow,left][350px:n:500px,grow,left]",
                        "[278px,grow 500]"));
        buildTaskStatusViews();
        this.add(taskStatusPanel, BorderLayout.CENTER);
    }
    public void addTaskStatusView(TaskStatusView taskStatusView){
    	views.add(taskStatusView);
    	buildTaskStatusViews();
    }
    public void buildTaskStatusViews(){
        for(TaskStatusView t : views){
        	taskStatusPanel.add(t, "grow");
        }
        this.add(taskStatusPanel, BorderLayout.CENTER);
    }
    public void addStatus(TaskStatusView taskStatus){
    	views.add(taskStatus);
    	buildTaskStatusViews();
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
    public boolean removeTaskStatusView(TaskStatusView taskStatusView){
    	taskStatusPanel.remove(taskStatusView);
    	return views.remove(taskStatusView);
    }
    
    public void moveUp(TaskStatusView taskStatusViewToMoveUp){
    	if(!(views.indexOf(taskStatusViewToMoveUp) <=0)){
	    	// find index of tomove
	    	// get index moveto
	    	// move that to next
	    	int source = views.indexOf(taskStatusViewToMoveUp);
	    	int dest = views.indexOf(taskStatusViewToMoveUp)-1;
	    	
	    	TaskStatusView sourceStatusView = views.get(source);
	    	TaskStatusView	destStatusView = views.get(dest);
	    	
	    	views.set(dest, sourceStatusView);
	    	views.set(source, destStatusView);
	    	refresh();
	    	buildTaskStatusViews();
    	}
    }
    public void moveDown(TaskStatusView taskStatusViewToMoveDown){
    	if(!(views.indexOf(taskStatusViewToMoveDown) >= views.size()-1)){
	    	// find index of tomove
	    	// get index moveto
	    	// move that to next
	    	int source = views.indexOf(taskStatusViewToMoveDown);
	    	int dest = views.indexOf(taskStatusViewToMoveDown)+1;
	    	
	    	TaskStatusView sourceStatusView = views.get(source);
	    	TaskStatusView	destStatusView = views.get(dest);
	    	
	    	views.set(dest, sourceStatusView);
	    	views.set(source, destStatusView);
	    	refresh();
	    	buildTaskStatusViews();
    	}
    }
}
