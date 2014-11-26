package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventmessengers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.AbstractAction;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.TaskAction;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.actions.WorkFlowAction;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.eventlisteners.ChildMessageEventListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.events.MessageEvent;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.TaskCard;

public class TaskMessenger extends AbstractMessageEventMessenger implements MouseListener {

    private TaskCard taskCard;

    public TaskMessenger(ChildMessageEventListener parentListener, TaskCard taskCard) {
        super(parentListener);
        this.taskCard = taskCard;
        this.taskCard.addMouseListener(this);
    }

    @Override
    boolean handleMessage(MessageEvent event) {
        boolean handled = false;
        final AbstractAction action = event.getAction();
        if (action instanceof TaskAction) {
            if (action.equals(TaskAction.Actions.UPDATE)) {
                // TODO: What to do when updating a task
            }
            handled = true;
        }
        return handled;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        fireMessageEventToParent(new MessageEvent(this, WorkFlowAction.newViewTask()));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    TaskCard getTaskCard() {
        return taskCard;
    }

}
