package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//fjdsakl;fjdlksa
@SuppressWarnings("serial")
public class RequirementPanel extends JPanel {
	protected JTextArea txtDescription;
	protected JButton btnSubmit;
	protected TextField txtField;
	
	public RequirementPanel() {
		txtDescription = new JTextArea();
		btnSubmit = new JButton("Submit");
		JLabel lblDescription = new JLabel("Description:");
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		final TextField txtField = new TextField(20);
		add(btnSubmit);
		add(lblDescription);
		add(txtDescription);
		add(txtField);
		btnSubmit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtDescription.getText());
			}	
		});
	}
}
