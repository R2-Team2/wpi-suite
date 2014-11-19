/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 * The Class TaskCard.
 */
public class TaskCard extends JPanel {

    private Task taskObj;
    JTextPane taskName = new JTextPane();

    /**
     * Create the panel.
     */
    public TaskCard(String nameData, String dateData, String userNameData) {
        setBorder(new LineBorder(Color.black));
        setLayout(new MigLayout("", "[grow,fill]", "[grow][bottom]"));


        taskName.setText(nameData);
        taskName.setFont(new Font("Tahoma", Font.BOLD, 14));
        taskName.setEditable(false);
        taskName.setBackground(UIManager.getColor("Button.background"));
        add(taskName, "cell 0 0,alignx center,aligny center");

        JPanel infoPanel = new JPanel();
        add(infoPanel, "cell 0 1,grow");
        infoPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));

        JLabel date = new JLabel(dateData);
        date.setFont(new Font("Tahoma", Font.PLAIN, 12));
        infoPanel.add(date, "cell 0 0,alignx left");

        JLabel userName = new JLabel(userNameData);
        userName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        infoPanel.add(userName, "cell 1 0,alignx right");
    }

    public Task getTaskObj() {
        return taskObj;
    }

    public void setTaskObj(Task taskObj) {
        this.taskObj = taskObj;
    }
}
