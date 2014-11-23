package edu.wpi.cs.wpisuitetng.modules.taskmanager.events;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;

public class TaskStatusEvent extends AbstractMessageEvent {

    public TaskStatusEvent(Object source, AbstractMessageEvent previousSender,
            AbstractAction action) {
        super(source, previousSender, action);
    }

}
