package edu.wpi.cs.wpisuitetng.modules.taskmanager.eventmessengers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.events.AbstractMessageEvent;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.TaskCard;

public class TaskEventMessenger extends AbstractMessageEventMessenger {

    private TaskCard task;
    MouseListener taskListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO: create new View task message
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };

    public TaskEventMessenger(TaskCard task, AbstractMessageEventMessenger parent) {
        super(parent);
        this.task = task;
        this.task.addMouseListener(taskListener);
    }

    @Override
    void passMessage(AbstractMessageEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    void handleMessage(AbstractMessageEvent event) {
        // TODO Auto-generated method stub

    }

    TaskCard getTask() {
        return task;
    }

}
