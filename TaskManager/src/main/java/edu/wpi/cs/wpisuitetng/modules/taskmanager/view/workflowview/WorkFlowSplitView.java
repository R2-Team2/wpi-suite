  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributors:
  *    R2-Team2
  ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.CreateNewTaskPanel;

public class WorkFlowSplitView extends JSplitPane{
	public WorkFlowSplitView(){
		this.setLeftComponent(new JScrollPane(new WorkFlowView()));
		this.setRightComponent(null);
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               hideCreateNewTaskPanel();
            }
        });
	}


	public void hideCreateNewTaskPanel(){
		this.setRightComponent(null);
		this.setOneTouchExpandable(false);
		//this.setDividerLocation(0.0);
	}
	public void createNewTaskPanel(){
		this.setRightComponent(new JScrollPane(new CreateNewTaskPanel()));
		this.setOneTouchExpandable(true);
		this.setDividerLocation(.6);
		this.resetToPreferredSizes();
	}
	
	public void collapse(){
		this.setOneTouchExpandable(true);
		this.setDividerLocation(1.0);
	}
}
