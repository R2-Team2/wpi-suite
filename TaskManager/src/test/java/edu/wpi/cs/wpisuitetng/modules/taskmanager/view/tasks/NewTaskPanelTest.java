package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class NewTaskPanelTest {

	@Test
	public void TitleButNoDescription() {
		NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("Title");
		NTP.infoPanel.getDescription().setText("");
		NTP.buttonPanel.validateTaskInfo();
		assertFalse(NTP.buttonPanel.buttonCreate.isEnabled());
	}
	
	@Test
	public void NoFieldsFilled() {
		NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("");
		NTP.infoPanel.getDescription().setText("");
		NTP.buttonPanel.validateTaskInfo();
		assertFalse(NTP.buttonPanel.buttonCreate.isEnabled());
	}
	
	@Test
	public void DescriptionButNoTitle() {
		NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("");
		NTP.infoPanel.getDescription().setText("Description");
		NTP.buttonPanel.validateTaskInfo();
		assertFalse(NTP.buttonPanel.buttonCreate.isEnabled());
	}
	
	@Test
	public void TitleAndDescription() {
		NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.getTitle().setText("Title");
		NTP.infoPanel.getDescription().setText("Description");
		NTP.buttonPanel.validateTaskInfo();
		assertTrue(NTP.buttonPanel.buttonCreate.isEnabled());
	}
	
	@Test
	public void EndDatePreceedsStartDate() {
		NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.calDueDate.setDate(new Date(0));
		NTP.infoPanel.calStartDate.setDate(new Date(86400000));
		NTP.buttonPanel.validateTaskDate();
		assertFalse(NTP.infoPanel.labelDueDate.getText().equals("Due Date: "));
	}
	
	@Test
	public void EndDateIsPostStartDate() {
		NewTaskPanel NTP = new NewTaskPanel(null);
		NTP.infoPanel.calDueDate.setDate(new Date(86400000));
		NTP.infoPanel.calStartDate.setDate(new Date(0));
		System.out.println(NTP.infoPanel.calStartDate.getDate().toString());
		System.out.println(NTP.infoPanel.calDueDate.getDate().toString());
		NTP.buttonPanel.validateTaskDate();
		assertTrue(NTP.infoPanel.labelDueDate.getText().equals("Due Date: "));
	}

}
