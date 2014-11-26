package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventmessengers;

import javax.swing.event.EventListenerList;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.eventlisteners.ChildMessageEventListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.eventlisteners.ParentMessageEventListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.events.MessageEvent;

public abstract class AbstractMessageEventMessenger implements ChildMessageEventListener,
ParentMessageEventListener {

    private EventListenerList childListenerList = new EventListenerList();
    private ChildMessageEventListener parentListener;

    protected AbstractMessageEventMessenger(ChildMessageEventListener parentListener) {
        this.parentListener = parentListener;
    }

    public void addChildListener(ParentMessageEventListener listener) {
        childListenerList.add(ParentMessageEventListener.class, listener);
    }

    public void removeChildListener(ParentMessageEventListener listener) {
        childListenerList.remove(ParentMessageEventListener.class, listener);
    }

    void fireMessageEventToParent(MessageEvent event) {
        parentListener.handleChildMessage(event);
    }

    void fireMessageEventToChildren(MessageEvent event) {
        Object[] listeners = childListenerList.getListenerList();
        for (int counter = 0; counter < listeners.length; counter += 2) {
            if (listeners[counter] == ParentMessageEventListener.class) {
                ((ParentMessageEventListener) listeners[counter + 1]).handleParentMessage(event);
            }
        }
    }

    abstract boolean handleMessage(MessageEvent event);

    @Override
    public void handleChildMessage(MessageEvent event) {
        if (!handleMessage(event)) {
            fireMessageEventToParent(event);
        }
    }

    @Override
    public void handleParentMessage(MessageEvent event) {
        if (!handleMessage(event)) {
            fireMessageEventToChildren(event);
        }
    }

}
