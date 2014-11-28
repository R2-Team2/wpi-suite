package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventmessengers;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.TaskStatusAction;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.eventlisteners.ChildMessageEventListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.events.MessageEvent;

public class TaskStatusMessenger extends AbstractMessageEventMessenger {

    public TaskStatusMessenger(ChildMessageEventListener parentListener) {
        super(parentListener);
        // TODO Auto-generated constructor stub
    }

    @Override
    boolean handleMessage(MessageEvent event) {
        boolean handled = false;
        final AbstractAction action = event.getAction();
        if (action instanceof TaskStatusAction) {
            // no task status actions
            handled = true;
        }
        return handled;
    }

}
