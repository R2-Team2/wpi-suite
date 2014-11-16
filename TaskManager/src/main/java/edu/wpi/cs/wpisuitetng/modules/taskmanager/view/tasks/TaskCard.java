package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.GridBagLayout;

import javax.swing.border.LineBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import net.miginfocom.swing.MigLayout;

import java.awt.GridLayout;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class TaskCard extends JPanel {

	/**
	 * Create the panel.
	 */
	public TaskCard() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow,fill]", "[grow][bottom]"));
		
		JTextPane taskName = new JTextPane();	
		taskName.setText("This text will represent a title in the near future!");
		taskName.setFont(new Font("Tahoma", Font.BOLD, 14));
		taskName.setBackground(UIManager.getColor("Button.background"));
		add(taskName, "cell 0 0,alignx center,aligny center");
		
		JPanel infoPanel = new JPanel();
		add(infoPanel, "cell 0 1,grow");
		infoPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		JLabel date = new JLabel("11/15/2014");
		date.setFont(new Font("Tahoma", Font.PLAIN, 12));
		infoPanel.add(date, "cell 0 0,alignx left");
		
		JLabel username = new JLabel("mcforman...");
		username.setFont(new Font("Tahoma", Font.PLAIN, 12));
		infoPanel.add(username, "cell 1 0,alignx right");


	}

}
