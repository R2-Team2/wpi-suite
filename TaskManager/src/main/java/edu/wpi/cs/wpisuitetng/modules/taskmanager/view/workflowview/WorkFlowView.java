
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

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
		TaskStatusView taskStatusNew = new TaskStatusView("New");
		TaskStatusView taskStatusSelDev = new TaskStatusView("Selected for Development");
		TaskStatusView taskStatusInDev = new TaskStatusView("Currently in Development");
		TaskStatusView taskStatusDone = new TaskStatusView("Completed");
		taskStatusPanel.add(taskStatusNew);
		taskStatusPanel.add(taskStatusSelDev);
		taskStatusPanel.add(taskStatusInDev);
		taskStatusPanel.add(taskStatusDone);
		
	}

}
