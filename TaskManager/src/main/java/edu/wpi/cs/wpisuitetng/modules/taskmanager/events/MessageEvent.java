package edu.wpi.cs.wpisuitetng.modules.taskmanager.events;

import java.util.EventObject;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;

public class MessageEvent extends EventObject {

    private AbstractAction action;

    public MessageEvent(Object source, AbstractAction action) {
        super(source);
        this.action = action;
    }

    public AbstractAction getAction() {
        return action;
    }
}
