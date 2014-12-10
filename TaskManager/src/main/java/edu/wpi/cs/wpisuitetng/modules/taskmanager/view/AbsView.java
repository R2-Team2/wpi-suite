package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;

public abstract class AbsView extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public abstract void utilizeTaskStatuses(TaskStatus[] taskStatusArray);

}
