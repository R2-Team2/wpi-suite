package edu.wpi.cs.wpisuitetng.modules.taskmanager.events;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;

public class TaskEvent extends AbstractMessageEvent {

    public TaskEvent(Object source, AbstractMessageEvent previousSender, AbstractAction action) {
        super(source, previousSender, action);
    }
}
