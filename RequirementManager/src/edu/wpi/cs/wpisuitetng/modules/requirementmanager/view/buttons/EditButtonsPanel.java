/**
 *  * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class EditButtonsPanel extends ToolbarGroupView{
	
	private final JPanel contentPanel = new JPanel();
	JButton createEditButton = new JButton("<html>Edit<br />Estimates</html>");
	final JButton createCancelButton = new JButton("<html>Cancel<br />Changes</html>");
	private ImageIcon editImg = null;
	private ImageIcon saveImg = null;
	
	/**
	 *  disables the Edit Estimates/SaveChanges button 
	 */
	public void disableCreateEditButton() {
		createEditButton.setEnabled(false);
	}
	
	/**
	 *  enables the Edit Estimates/SaveChanges button 
	 */
	public void enableCreateEditButton() {
		createEditButton.setEnabled(true);
	}

	public EditButtonsPanel(){
		super("");
		
		this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		this.setPreferredWidth(390);
		
		createEditButton.setPreferredSize(new Dimension(150,50));	
		createCancelButton.setVisible(false);
		
		try {
		    Image img = ImageIO.read(getClass().getResource("cancel.png"));
		    createCancelButton.setIcon(new ImageIcon(img));
		    
		    editImg = new ImageIcon(ImageIO.read(getClass().getResource("edit.png")));
		    createEditButton.setIcon(editImg);
		    saveImg = new ImageIcon(ImageIO.read(getClass().getResource("save.png")));
		    
		} catch (IOException ex) {}
		
		createEditButton.setVisible(true);
		// the action listener for the Edit Estimates button
		createEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check to see if any other tab is currently open
				// if (ViewEventController.getInstance().getMainView().getTabCount() == 1) {
				
					// toggle the editing overview table mode
					ViewEventController.getInstance().toggleEditingTable(false);
					// edits the Edit Button text based on whether in editing overview table mode or not
					if (ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
						ViewEventController.getInstance().getOverviewTable().repaint();
						setButtonToSave();
					}	
					else {
						setButtonToEdit();
					}
				}
			//}
		});
		
		createCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// toggle the editing overview table mode
				ViewEventController.getInstance().toggleEditingTable(true);			
				setButtonToEdit();
				createEditButton.setEnabled(true);

			}
		});
		contentPanel.add(createEditButton);
		contentPanel.add(createCancelButton);
		contentPanel.setOpaque(false);
		
		this.add(contentPanel);
	}
	
	/**
	 * Method getEditButton.
	
	 * @return JButton */
	public JButton getEditButton() {
		return this.createEditButton;
	}
	public void setButtonToEdit(){
		if (editImg != null){
			createEditButton.setIcon(editImg);}
		createEditButton.setText("<html>Edit<br />Estimates</html>");
		createEditButton.setEnabled(true);
		createCancelButton.setVisible(false);
	}
	public void setButtonToSave(){
		if (saveImg != null){
			createEditButton.setIcon(saveImg);}
		createEditButton.setText("<html>Save<br />Changes</html>");
		createEditButton.setEnabled(false);
		createCancelButton.setVisible(true);
	}
	public void setSaveEnabled(boolean enabled){
		createEditButton.setEnabled(enabled);
	}
}
