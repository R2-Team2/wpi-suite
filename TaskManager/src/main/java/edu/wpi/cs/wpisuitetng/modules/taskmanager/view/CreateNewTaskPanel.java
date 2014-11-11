package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class CreateNewTaskPanel extends JPanel {
	private JTextField txtEnterTitle;
	private JTextField txtEnterEffort;
	private ViewEventController viewEventController = ViewEventController.getInstance();

	/**
	 * Create the panel.
	 */
	public CreateNewTaskPanel() {
		setLayout(new MigLayout("", "[grow]", "[grow][grow][108.00,grow][grow][137.00,grow][80.00,grow]"));
		
		JPanel panel_5 = new JPanel();
		add(panel_5, "cell 0 0,grow");
		panel_5.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, "cell 0 0,grow");
		
		JLabel lblTitle = new JLabel("Title:");
		panel_7.add(lblTitle);
		
		txtEnterTitle = new JTextField();
		panel_7.add(txtEnterTitle);
		txtEnterTitle.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, "cell 1 0,grow");
		
		JLabel lblEstimatedEffort = new JLabel("Estimated Effort:");
		panel_6.add(lblEstimatedEffort);
		
		txtEnterEffort = new JTextField();
		txtEnterEffort.setEnabled(false);
		txtEnterEffort.setToolTipText("");
		panel_6.add(txtEnterEffort);
		txtEnterEffort.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9, "cell 0 0,grow");
		
		JLabel lblDescription = new JLabel("Description");
		panel_9.add(lblDescription);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8, "cell 1 0,grow");
		
		JLabel lblDueDate = new JLabel("Due Date");
		panel_8.add(lblDueDate);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, "cell 0 2,grow");
		panel_3.setLayout(new MigLayout("", "[489.00,grow][493.00,grow]", "[grow]"));
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11, "cell 0 0,grow");
		panel_11.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		panel_11.add(textArea, "cell 0 0,grow");
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10, "cell 1 0,grow");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3"}));
		comboBox.setEnabled(false);
		panel_10.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBox_1.setEnabled(false);
		panel_10.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Year", "2014", "2015", "2016"}));
		comboBox_2.setEnabled(false);
		panel_10.add(comboBox_2);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, "cell 0 3,grow");
		panel_2.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, "cell 0 0,grow");
		
		JLabel lblAssignee = new JLabel("Assignee:");
		panel_13.add(lblAssignee);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Username"}));
		comboBox_3.setEnabled(false);
		panel_13.add(comboBox_3);
		
		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12, "cell 1 0,grow");
		
		JLabel lblRelatedRequirement = new JLabel("Related Requirement:");
		panel_12.add(lblRelatedRequirement);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Requirement"}));
		comboBox_4.setEnabled(false);
		panel_12.add(comboBox_4);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 0 4,grow");
		panel_1.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		JPanel panel_15 = new JPanel();
		panel_1.add(panel_15, "cell 0 0,grow");
		
		JPanel panel_14 = new JPanel();
		panel_1.add(panel_14, "cell 1 0,grow");
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 5,grow");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, txtEnterTitle.getText());
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, txtEnterTitle.getText());
			}
		});
		panel.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewEventController.removeTab();
			}
		});
		panel.add(btnCancel);

	}

}
