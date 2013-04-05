package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.TransactionHistory;

public class TransactionHistoryPanel extends JScrollPane {

	public TransactionHistoryPanel() {
		
		
		JPanel historyPanel = new JPanel();
		BoxLayout layout = new BoxLayout(historyPanel, BoxLayout.PAGE_AXIS);
		historyPanel.setLayout(layout);
		
	
		
		JPanel trans = new JPanel();
		trans.setLayout(new GridLayout(2,2));
		trans.setBackground(Color.WHITE);
		trans.add(new JLabel("test"));
		//trans.setAlignmentY(Component.LEFT_ALIGNMENT);
		
		JPanel trans2 = new JPanel();
		trans2.setLayout(new GridLayout(2,2));
		trans2.add(new JLabel("test2"));
		
		JPanel trans3 = new JPanel();
		trans3.setLayout(new GridLayout(2,2));
		trans3.add(new JLabel("test3"));
		
		JPanel trans4 = new JPanel();
		trans4.setLayout(new GridLayout(2,2));
		trans4.add(new JLabel("test4"));
		
		JPanel trans5 = new JPanel();
		trans5.setLayout(new GridLayout(2,2));
		trans5.add(new JLabel("test5"));
		
		
		historyPanel.add(trans);
		historyPanel.add(trans2);
		historyPanel.add(trans3);
		historyPanel.add(trans4);
		historyPanel.add(trans5);
		
		this.setViewportView(historyPanel);
	}
}
