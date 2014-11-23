package edu.wpi.cs.wpisuitetng.modules.taskmanager.events;

import java.util.EventObject;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;

public abstract class AbstractMessageEvent extends EventObject {

    private AbstractMessageEvent previousSender;
    private AbstractAction action;

    public AbstractMessageEvent(Object source, AbstractMessageEvent previousSender,
            AbstractAction action) {
        super(source);
        this.previousSender = previousSender;
        this.action = action;
    }

    public AbstractMessageEvent getPreviousSender() {
        return previousSender;
    }

    public AbstractAction getAction() {
        return action;
    }
}
