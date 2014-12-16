package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.CommentList;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;

public class TaskTabPane extends JTabbedPane {

    private final Task currentTask;
    private final TaskCommentPanel commentPanel;

    public TaskTabPane(Task currentTask, AbstractTaskPanel parentPanel) {
        this.currentTask = currentTask;

        commentPanel = new TaskCommentPanel(currentTask, parentPanel);
        this.addTab("Comments", commentPanel);
    }

    public void loadComments() {
        commentPanel.loadComments();
    }

    public CommentList getComments() {
        return currentTask.getComments();
    }
}
