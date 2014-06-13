/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class RequirementButtonPanel extends JPanel implements RequirementPanelListener
{
	private final RequirementPanel parentPanel;
	private final ViewMode viewMode;
	
	private final ErrorPanel errorDisplay;
	
	private final JButton buttonOK;
	private final JButton buttonCancel;
	private final JButton buttonClear;
	private final JButton buttonDelete;
	
	private boolean changes;
	private boolean valid;
	
	/**
	 * Constructor for the requirement button panel
	 * @param parentPanel the panel this reports to
	 * @param mode viewmode for the panel
	 * @param curr current requirement
	 */
	public RequirementButtonPanel(RequirementPanel parentPanel, ViewMode mode, Requirement curr)
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		changes = false;
		valid = false;
		errorDisplay = new ErrorPanel();
		this.parentPanel = parentPanel;
		viewMode = mode;

		String okString;
		final String cancelString = "Cancel";
		String clearString;
		final String deleteString = "Delete";
		
		buttonOK = new JButton();
		buttonCancel = new JButton(cancelString);
		buttonClear = new JButton();
		buttonDelete = new JButton(deleteString);
		
		this.add(buttonOK);
		this.add(buttonClear);
		if(viewMode == ViewMode.CREATING)
		{
			okString = "Create";
			clearString = "Clear";
		}
		else
		{
			okString = "Update";
			clearString = "Undo Changes";
			this.add(buttonDelete);
		}
		
		try {
		    Image img = ImageIO.read(getClass().getResource("save-icon.png"));
		    buttonOK.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("undo-icon.png"));
		    buttonClear.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("delete-icon.png"));
		    buttonDelete.setIcon(new ImageIcon(img));
		} catch (IOException ex) {}
		
		buttonOK.setText(okString);
		buttonClear.setText(clearString);
		this.add(buttonCancel);
		buttonOK.setEnabled(false);
		buttonClear.setEnabled(false);
		this.add(errorDisplay);
		setupListeners();
	}
	
	/**
	 * Sets up the listeners for the buttons in the requirement button panel.
	 */
	private void setupListeners()
	{
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.OKPressed();
			}
		});

		buttonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.clearPressed();
			}

		});
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.cancelPressed();
			}
		});
		
		buttonDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				parentPanel.deletePressed();
			}
		});
	}

	/**
	 * Method fireDeleted.
	 * @param b boolean
	
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireDeleted(boolean) */
	@Override
	public void fireDeleted(boolean b) 
	{
		this.buttonDelete.setEnabled(!b);
	}

	/**
	 * Method fireValid.
	 * @param b boolean
	
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireValid(boolean) */
	@Override
	public void fireValid(boolean b) {
		valid = b;
		this.buttonOK.setEnabled(b && changes);
	}

	/**
	 * Method fireChanges.
	 * @param b boolean
	
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireChanges(boolean) */
	@Override
	public void fireChanges(boolean b) {
		changes = b;
		this.buttonOK.setEnabled(b && valid);
		this.buttonClear.setEnabled(b);
	}
	
	/**
	 * Method readyToRemove.
	
	
	 * @return boolean * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#readyToRemove() * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#readyToRemove()
	 */
	@Override
	public boolean readyToRemove() {
		return true;
	}

	/**
	 * Method fireRefresh.
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireRefresh()
	 */
	@Override
	public void fireRefresh() {}
	

	/**
	
	 * @return the error panel */
	public ErrorPanel getErrorPanel()
	{
		return this.errorDisplay;
	}
	
	/**
	 * 
	
	 * * @return the clear button */
	public JButton getButtonClear() {
		return buttonClear;
	}
	
	/**
	 *
	
	 * * @return the delete button  */
	public JButton getButtonDelete() {
		return buttonDelete;
	}
	
	/**
	 *
	
	 * * @return the ok button */
	public JButton getButtonOK() {
		return buttonOK;
	}
	
	/**
	 *
	
	 * * @return the cancel button  */
	public JButton getButtonCancel() {
		return buttonCancel;
	}
}
