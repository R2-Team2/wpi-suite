/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;

/**
 * @author R2-Team2
 *
 */
public class WorkFlowSplitTabbedPanel extends JTabbedPane {
    private final JPopupMenu popup = new JPopupMenu();
    private JMenuItem closeAll = new JMenuItem("Close All Tabs");
    private JMenuItem closeOthers = new JMenuItem("Close Others");
    private WorkFlowSplitView parentPanel;
    
	
	public WorkFlowSplitTabbedPanel(WorkFlowSplitView parentPanel) {
        this.parentPanel = parentPanel;
		
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		//this.setMaximumSize(new Dimension(100,100));
        this.closeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ViewEventController.getInstance().closeAllTabs();
            }
        });

        this.closeOthers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ViewEventController.getInstance().closeOthers();
            }
        });

        this.popup.add(this.closeAll);
        this.popup.add(this.closeOthers);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger())
                	WorkFlowSplitTabbedPanel.this.popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
		
	}
	
	public void addCreateTaskTab() {
        this.addTab("New Task", null, new NewTaskPanel(this), "New Task");
	}
	
	public void checkForHide() {
		if(this.getTabCount() <= 0) {
			ViewEventController.getInstance().closeNewTaskPanel();
		}
	}
}
