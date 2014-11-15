
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import net.miginfocom.swing.MigLayout;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class WorkFlowView extends JPanel {
	private String title;
	private JPanel taskStatusPanel;

	/**
	 * Create the panel.
	 */
	public WorkFlowView() {
		
		setLayout(new BorderLayout());
		
		JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL,30,20,0,300);
		this.add(hbar, BorderLayout.SOUTH);
		//JScrollPane scrollPane = new JScrollPane();
		//this.add(scrollPane, BorderLayout.SOUTH);
		
		taskStatusPanel = new JPanel();
		this.add(taskStatusPanel, BorderLayout.CENTER);
		TaskStatusView taskStatusNew = new TaskStatusView("TITLE THAT IS SUPER UBER LONG OMG THIS IS REALLY LONG AAAAAAA AAAAA AAAAA  AAA AAAAA");
		TaskStatusView taskStatusSelDev = new TaskStatusView("Selected for Development");
		TaskStatusView taskStatusInDev = new TaskStatusView("Currently in Development");
		TaskStatusView taskStatusDone = new TaskStatusView("Completed");
		
		taskStatusPanel.setLayout(new MigLayout("", "[350px:n:500px,grow,left][350px:n:500px,grow,left][350px:n:500px,grow,left][350px:n:500px,grow,left]", "[278px,grow 500]"));
		taskStatusPanel.add(taskStatusNew, "cell 0 0,grow");
		taskStatusPanel.add(taskStatusSelDev, "cell 1 0,grow");
		taskStatusPanel.add(taskStatusInDev, "cell 2 0,grow");
		taskStatusPanel.add(taskStatusDone, "cell 3 0,grow");
		
	}

}
