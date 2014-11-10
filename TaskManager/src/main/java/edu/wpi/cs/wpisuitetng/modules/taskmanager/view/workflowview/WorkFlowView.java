
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WorkFlowView extends JPanel {
	private JTextField txtText;
	private JPanel taskStatusPanel;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;

	/**
	 * Create the panel.
	 */
	public WorkFlowView() {
		
		setLayout(new BorderLayout());
		
		txtText = new JTextField();
		txtText.setText("Workflow View Goes Here");
		this.add(txtText, BorderLayout.NORTH);
		txtText.setColumns(20);
		
		taskStatusPanel = new JPanel();
		this.add(taskStatusPanel, BorderLayout.CENTER);
		taskStatusPanel.setLayout(new GridLayout(1, 4, 0, 0));
		
		txt1 = new JTextField();
		txt1.setText("The thing");
		taskStatusPanel.add(txt1);
		txt1.setColumns(10);
		
		txt2 = new JTextField();
		txt2.setText("The Other thing");
		taskStatusPanel.add(txt2);
		txt2.setColumns(10);
		
		txt3 = new JTextField();
		txt3.setText("The Thing that Works");
		taskStatusPanel.add(txt3);
		txt3.setColumns(10);
		
		txt4 = new JTextField();
		txt4.setText("Something Else");
		taskStatusPanel.add(txt4);
		txt4.setColumns(10);
		
		
	}

}
