package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventmessengers;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.WorkFlowAction;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.eventlisteners.ChildMessageEventListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.events.MessageEvent;

public class WorkFlowMessenger extends AbstractMessageEventMessenger {

    public WorkFlowMessenger(ChildMessageEventListener parentListener) {
        super(parentListener);
        // TODO Auto-generated constructor stub
    }

    @Override
    boolean handleMessage(MessageEvent event) {
        boolean handled = false;
        final AbstractAction action = event.getAction();
        if (action instanceof WorkFlowAction) {
            final Enum<WorkFlowAction.Actions> workflowAction =
                    ((WorkFlowAction) action).getAction();
            if (workflowAction.equals(WorkFlowAction.Actions.EDIT_TASK)) {
                // TODO: What to do when editing a task
            } else if (workflowAction.equals(WorkFlowAction.Actions.VIEW_TASK)) {
                // TODO: What to do when viewing a task
            }
            handled = true;
        }
        return handled;
    }
}
