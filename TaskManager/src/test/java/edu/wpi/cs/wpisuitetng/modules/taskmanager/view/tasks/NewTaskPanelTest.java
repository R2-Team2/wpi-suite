/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class NewTaskPanelTest.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
public class NewTaskPanelTest {

	/**
	 * Title but no description.
	 */
	@Test
	public void TitleButNoDescription() {
		final NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("Title");
		NTP.infoPanel.getDescription().setText("");
		NTP.buttonPanel.validateTaskInfo();
		assertFalse(NTP.buttonPanel.buttonCreate.isEnabled());
	}

	/**
	 * No fields filled.
	 */
	@Test
	public void NoFieldsFilled() {
		final NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("");
		NTP.infoPanel.getDescription().setText("");
		NTP.buttonPanel.validateTaskInfo();
		assertFalse(NTP.buttonPanel.buttonCreate.isEnabled());
	}

	/**
	 * Description but no title.
	 */
	@Test
	public void DescriptionButNoTitle() {
		final NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("");
		NTP.infoPanel.getDescription().setText("Description");
		NTP.buttonPanel.validateTaskInfo();
		assertFalse(NTP.buttonPanel.buttonCreate.isEnabled());
	}

	/**
	 * Title and description.
	 */
	@Test
	public void TitleAndDescription() {
		final NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("Title");
		NTP.infoPanel.getDescription().setText("Description");
		NTP.infoPanel.calStartDate.setDate(Calendar.getInstance().getTime());
		NTP.infoPanel.calDueDate.setDate(Calendar.getInstance().getTime());
		NTP.buttonPanel.validateTaskInfo();
		assertTrue(NTP.buttonPanel.buttonCreate.isEnabled());
	}

	/**
	 * End date preceeds start date.
	 */
	@Test
	public void EndDatePreceedsStartDate() {
		final NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.calDueDate.setDate(new Date(0));
		NTP.infoPanel.calStartDate.setDate(new Date(86400000));
		NTP.buttonPanel.validateTaskDate();
		assertFalse(NTP.infoPanel.labelDueDate.getText().equals("Due Date: "));
	}

	/**
	 * End date is post start date.
	 */
	@Test
	public void EndDateIsPostStartDate() {
		final NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.calDueDate.setDate(new Date(86400000));
		NTP.infoPanel.calStartDate.setDate(new Date(0));
		NTP.buttonPanel.validateTaskDate();
		assertTrue(NTP.infoPanel.labelDueDate.getText().equals("Due Date: "));
	}

}
