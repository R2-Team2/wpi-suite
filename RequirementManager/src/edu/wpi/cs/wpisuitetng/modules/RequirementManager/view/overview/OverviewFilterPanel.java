package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;

public class OverviewFilterPanel extends JPanel implements ListSelectionListener {

	private JButton editButton;
	private JButton backLogButton;
	
	/**
	 * Sets up the left hand panel of the overview
	 */
	public OverviewFilterPanel()
	{	
		
		backLogButton = new JButton("Assign to Backlog");
		
		backLogButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().assignSelectionToBacklog();
			}
			
		});
		
		
		backLogButton.setEnabled(false);
		this.add(backLogButton);
	}
	
	/**
	 * Modifies the edit button/backlog button enabled state based on if a requirement is selected.
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel model = (ListSelectionModel)e.getSource();
		
		if(ViewEventController.getInstance().getTableSelection().length > 0)
		{
			backLogButton.setEnabled(true);
		}
		else
		{
			backLogButton.setEnabled(false);
		}
	}
	
	
}
