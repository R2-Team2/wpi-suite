package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventlisteners;

import java.util.EventListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.events.MessageEvent;

public interface ParentMessageEventListener extends EventListener {
    public void handleParentMessage(MessageEvent event);
}
