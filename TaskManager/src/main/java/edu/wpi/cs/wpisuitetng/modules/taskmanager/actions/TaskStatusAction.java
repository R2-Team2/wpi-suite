package edu.wpi.cs.wpisuitetng.modules.taskmanager.actions;

public class TaskStatusAction extends AbstractAction {

    private Enum<TaskStatusAction.Actions> action;

    public static enum Actions {

    }

    public TaskStatusAction(Enum<TaskStatusAction.Actions> action) {
        this.action = action;
    }

    @Override
    public num<TaskStatusAction.Actions> getAction() {
        return action;
    }

}
