  /* All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributors:
  *    R2-Team2
  ******************************************************************************/
//this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.awt.GridLayout;






import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import net.miginfocom.swing.MigLayout;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowView.
 */
@SuppressWarnings("serial")


public class WorkFlowView extends JPanel {
<<<<<<< HEAD
	private WorkFlow workFlowObj;
	
	private JTextField txtText;
	private JPanel taskStatusPanel;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
=======
	
	/** The title. */
	private String title;
	
	/** The task status panel. */
	private JPanel taskStatusPanel;

>>>>>>> develop
	
	/**
	 * Create the panel.
	 */
	public WorkFlowView() {
		workFlowObj = new WorkFlow();
		
		setLayout(new BorderLayout());
		
		JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL,30,20,0,300);
		//this.add(hbar, BorderLayout.SOUTH);
		//JScrollPane scrollPane = new JScrollPane();
		//this.add(scrollPane, BorderLayout.SOUTH);
		
		taskStatusPanel = new JPanel();
		this.add(taskStatusPanel, BorderLayout.CENTER);
		TaskStatusView taskStatusNew = new TaskStatusView("New");
		TaskStatusView taskStatusSelDev = new TaskStatusView("Selected for Development");
		TaskStatusView taskStatusInDev = new TaskStatusView("Currently in Development");
		TaskStatusView taskStatusDone = new TaskStatusView("Completed");
		
		taskStatusPanel.setLayout(new MigLayout("", "[350px:n:500px,grow,left][350px:n:500px,grow,left][350px:n:500px,grow,left][350px:n:500px,grow,left]", "[278px,grow 500]"));
		taskStatusPanel.add(taskStatusNew, "cell 0 0,grow");
		taskStatusPanel.add(taskStatusSelDev, "cell 1 0,grow");
		taskStatusPanel.add(taskStatusInDev, "cell 2 0,grow");
		taskStatusPanel.add(taskStatusDone, "cell 3 0,grow");
		
	}

	public WorkFlow getWorkFlowObj() {
		return workFlowObj;
	}

	public void setWorkFlowObj(WorkFlow workFlowObj) {
		this.workFlowObj = workFlowObj;
	}

}
