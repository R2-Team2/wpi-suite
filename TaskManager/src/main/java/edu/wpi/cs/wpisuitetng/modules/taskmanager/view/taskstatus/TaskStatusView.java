package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.*;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.GridLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.GridBagLayout;

import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TaskStatusView extends JPanel {

	TaskStatus taskStatusObj;
	JPanel panel = new JPanel();
	
	/**
	 * Create the panel.
	 */
	public TaskStatusView(String title) {
		this.taskStatusObj = new TaskStatus(title);
		setLayout(new MigLayout("", "[236px]", "[26px][200px,grow 500]"));
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setForeground(new Color(0, 0, 0));
		txtpnTitle.setEditable(false);
		txtpnTitle.setFont(txtpnTitle.getFont().deriveFont(txtpnTitle.getFont().getSize() + 5f));
		txtpnTitle.setText(this.taskStatusObj.getName());
		add(txtpnTitle, "cell 0 0,alignx left,aligny top");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, "cell 0 1,grow");
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		taskStatusObj.addTask("Go to sleep with a really long title that's kind of boring");
		taskStatusObj.addTask("Help Team");
		taskStatusObj.addTask("Get Ready");
		taskStatusObj.addTask("Aww Yeah");
		taskStatusObj.addTask("Fun Fun");
		taskStatusObj.addTask("Get Crunk");
		for(int i = 0; i < taskStatusObj.getTaskList().size(); i++){
			TaskCard card = new TaskCard();
			card.setTaskCardName(taskStatusObj.getTaskList().get(i));
			panel.add(card, "newline");
			System.out.println("Adding Card to Panel");
		}
	}
}


