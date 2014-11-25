package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventmessengers;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

public abstract class AbstractMessageEventMessenger {

    protected EventListenerList listenerList = new EventListenerList();
    protected AbstractMessageEventMessenger parent = null;
    protected ArrayList<AbstractMessageEventMessenger> children =
            new ArrayList<AbstractMessageEventMessenger>();

    AbstractMessageEventMessenger(AbstractMessageEventMessenger parent) {
        this.parent = parent;
    }

    public void addChild(AbstractMessageEventMessenger child) {
        children.add(child);
    }

    public void addChildren(AbstractMessageEventMessenger... children) {
        for (AbstractMessageEventMessenger child : children) {
            this.children.add(child);
        }
    }

    public void removeChild(AbstractMessageEventMessenger child) {
        int index = children.indexOf(child);
        if (index != -1)
            children.remove(index);
    }

    public void removeChildren(AbstractMessageEventMessenger... children) {
        for (AbstractMessageEventMessenger child : children) {
            int index = this.children.indexOf(child);
            if (index != -1)
                this.children.remove(index);
        }
    }


    public void addMessageEventListener(MessageEventListener listener) {
        listenerList.add(MessageEventListener.class, listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        listenerList.remove(MessageEventListener.class, listener);
    }

    // notify listeners
    void fireMessengerEvent(AbstractMessageEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int counter = 0; counter < listeners.length; counter += 2) {
            if (listeners[counter] == MessageEventListener.class) {
                ((MessageEventListener) listeners[counter + 1]).handleMessage();
            }
        }
    }

    abstract void handleMessage(AbstractMessageEvent event);
}
