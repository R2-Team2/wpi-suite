
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;

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
		txtText.setEditable(false);
		this.add(txtText, BorderLayout.NORTH);
		txtText.setColumns(20);
		
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
