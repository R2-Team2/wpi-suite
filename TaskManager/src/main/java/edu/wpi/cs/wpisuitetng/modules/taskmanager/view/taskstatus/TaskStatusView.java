package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import javax.swing.JPanel;

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

		JList scrollableList = new JList(this.taskStatusObj);
		JScrollPane scrollPane = new JScrollPane(scrollableList);
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, "cell 0 1,grow");
		
	}

}


