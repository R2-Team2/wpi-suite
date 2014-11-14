
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class WorkFlowView extends JPanel {
	private String title;
	private JPanel taskStatusPanel;

	/**
	 * Create the panel.
	 */
	public WorkFlowView() {
		
		setLayout(new BorderLayout());
		taskStatusPanel = new JPanel();
		this.add(taskStatusPanel, BorderLayout.CENTER);
		TaskStatusView taskStatusNew = new TaskStatusView("New");
		TaskStatusView taskStatusSelDev = new TaskStatusView("Selected for Development");
		TaskStatusView taskStatusInDev = new TaskStatusView("Currently in Development");
		TaskStatusView taskStatusDone = new TaskStatusView("Completed");
		taskStatusPanel.setLayout(new MigLayout("", "[250px][250px][250px][250px]", "[278px,grow 500]"));
		taskStatusPanel.add(taskStatusNew, "cell 0 0,grow");
		taskStatusPanel.add(taskStatusSelDev, "cell 1 0,grow");
		taskStatusPanel.add(taskStatusInDev, "cell 2 0,grow");
		taskStatusPanel.add(taskStatusDone, "cell 3 0,grow");
		
	}

}
