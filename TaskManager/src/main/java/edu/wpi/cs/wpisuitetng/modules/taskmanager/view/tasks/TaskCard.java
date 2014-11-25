/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskCard.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class TaskCard extends JPanel {

    /** The task obj. */
    private Task taskObj;
    JTextPane taskName = new JTextPane();
    MouseListener listener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            ViewEventController.getInstance().viewTask(taskObj);
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

    /**
     * Create the panel.
     *
     * @param nameData the name data
     * @param dateData the date data
     * @param userNameData the user name data
     */
    public TaskCard(String nameData, String dateData, String userNameData, Task aTask) {
    	this.taskObj = aTask;
        setBorder(new LineBorder(Color.black));
        setLayout(new MigLayout("", "[grow,fill]", "[grow][bottom]"));


        // truncates the displayed task title if it's longer than 25 characters. if
        if (nameData.length() > 45) {
            taskName.setToolTipText(nameData);
            nameData = nameData.substring(0, 45).concat("...");
        }

        taskName.setText(nameData);
        taskName.setFont(new Font("Tahoma", Font.BOLD, 14));
        taskName.setEditable(false);
        taskName.setBackground(UIManager.getColor("Button.background"));
        taskName.addMouseListener(listener);
        this.add(taskName, "cell 0 0,alignx center,aligny center");

        final JPanel infoPanel = new JPanel();
        this.add(infoPanel, "cell 0 1,grow");
        infoPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));

        final JLabel date = new JLabel(dateData);
        date.setFont(new Font("Tahoma", Font.PLAIN, 12));
        infoPanel.add(date, "cell 0 0,alignx left");

        final JLabel userName = new JLabel(userNameData);
        userName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        infoPanel.add(userName, "cell 1 0,alignx right");

        addMouseListener(listener);
    }

    /**
     * Gets the task obj.
     *
     * @return the task obj
     */
    public Task getTaskObj() {
        return taskObj;
    }

    /**
     * Sets the task obj.
     *
     * @param taskObj the new task obj
     */
    public void setTaskObj(Task taskObj) {
        this.taskObj = taskObj;
    }
}
