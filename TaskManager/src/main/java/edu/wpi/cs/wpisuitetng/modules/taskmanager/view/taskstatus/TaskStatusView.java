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

public class TaskStatusView extends JPanel {

	/**
	 * Create the panel.
	 */
	public TaskStatusView() {
		setLayout(new MigLayout("", "[200px,grow]", "[40px][200px,grow]"));
		
		String[] ar = {"one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen","twenty"};
		
//		Taskstatus newTaskStatus = new Taskstatus("In Development");
//		newTaskStatus.setTaskList(ar);
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setEditable(false);
		txtpnTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnTitle.setText("Title of Section");
		add(txtpnTitle, "cell 0 0,width 150,height 40");
		JList scrollableList = new JList(ar);
		JScrollPane scrollPane = new JScrollPane(scrollableList);
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, "cell 0 1,width 150,height 250");
		
	}

}
