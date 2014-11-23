package edu.wpi.cs.wpisuitetng.modules.taskmanager.actions;


public class TaskAction extends AbstractAction {

    private Enum<TaskAction.Actions> action;

    public static enum Actions {
        UPDATE;
    }

    public TaskAction(Enum<TaskAction.Actions> action) {
        this.action = action;
    }

    @Override
    Enum<TaskAction.Actions> getAction() {
        return action;
    }

}
