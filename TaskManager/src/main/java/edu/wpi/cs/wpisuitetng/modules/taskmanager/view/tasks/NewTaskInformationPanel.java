/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    R2-Team2
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.toedter.calendar.JCalendar;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;
//import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;
import net.miginfocom.swing.MigLayout;

/**
 *
 */
@SuppressWarnings("serial")
public class NewTaskInformationPanel extends AbstractInformationPanel {

	/**
	 * Constructor for NewTaskInformationPanel
	 * @param parentPanel
	 */
	public NewTaskInformationPanel(AbstractTaskPanel parentPanel) {
		this.parentPanel = parentPanel;
        this.setMinimumSize(new Dimension(500, 200));
        
		this.buildLayout();
	}
	
	
}

