/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.buttons;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

/**
 * @author dbogatov
 *
 */
public class TaskButtonsPanel extends ToolbarGroupView {
	// initialize the main view toolbar buttons
		private final JButton createButton = new JButton("<html>Create<br />Task</html>");
		
		private final JPanel contentPanel = new JPanel();
		
		public TaskButtonsPanel() {
			super("");
			
			this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
			this.setPreferredWidth(350);
			
			this.createButton.setHorizontalAlignment(SwingConstants.CENTER);
			try {
				Image img = ImageIO.read(this.getClass().getResourceAsStream("new_task.png"));
				this.createButton.setIcon(new ImageIcon(img));
			} catch (IOException ex) {
				// Hopefully, won't get here
			}
			
			// the action listener for the Create Requirement Button
			createButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// bring up a create requirement pane if not in Multiple Requirement Editing Mode
					
					//TODO ViewEventController.getInstance().createRequirement();
				}
			});
			
			
			contentPanel.add(createButton);
			contentPanel.setOpaque(false);
			
			this.add(contentPanel);
		}
		
		/**
		 * Method getCreateButton.
		 * 
		 * @return JButton
		 */
		public JButton getCreateButton() {
			return createButton;
		}
		
}
