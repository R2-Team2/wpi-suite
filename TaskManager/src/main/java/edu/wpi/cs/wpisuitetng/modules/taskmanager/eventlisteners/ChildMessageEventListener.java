package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventlisteners;

import java.util.EventListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.events.MessageEvent;

public interface ChildMessageEventListener extends EventListener {
    public void handleChildMessage(MessageEvent event);
}
