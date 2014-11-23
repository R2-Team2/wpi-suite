package edu.wpi.cs.wpisuitetng.modules.taskmanager.events;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;

public class WorkFlowEvent extends AbstractMessageEvent {

    public WorkFlowEvent(Object source, AbstractMessageEvent previousSender, AbstractAction action) {
        super(source, previousSender, action);
    }

}
