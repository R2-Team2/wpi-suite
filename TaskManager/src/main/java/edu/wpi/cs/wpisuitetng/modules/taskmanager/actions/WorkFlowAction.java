package edu.wpi.cs.wpisuitetng.modules.taskmanager.actions;

public class WorkFlowAction extends AbstractAction {

    private Enum<WorkFlowAction.Actions> action;

    public static enum Actions {
        VIEW_TASK, EDIT_TASK;
    }

    private WorkFlowAction(Enum<WorkFlowAction.Actions> action) {
        this.action = action;
    }

    public static WorkFlowAction newEditTask() {
        return new WorkFlowAction(WorkFlowAction.Actions.EDIT_TASK);
    }

    public static WorkFlowAction newViewTask() {
        return new WorkFlowAction(WorkFlowAction.Actions.View_TASK);
    }

    @Override
    Enum<WorkFlowAction.Actions> getAction() {
        return action;
    }

}
