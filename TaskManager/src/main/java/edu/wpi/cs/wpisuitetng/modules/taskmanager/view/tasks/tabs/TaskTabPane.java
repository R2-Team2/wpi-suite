package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs;

import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.CommentList;

public class TaskTabPane extends JTabbedPane {

    private final Task currentTask;
    private final TaskCommentPanel commentPanel;

    public TaskTabPane(Task currentTask) {
        this.currentTask = currentTask;

        commentPanel = new TaskCommentPanel(currentTask);
        this.addTab("Comments", commentPanel);
    }

    public void loadComments() {
        commentPanel.loadComments();
    }

    public CommentList getComments() {
        return currentTask.getComments();
    }
}
