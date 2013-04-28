package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 */
public class IterationOverviewPanel extends JSplitPane {	
	private IterationCalendar calendarView;
	
	private JButton nextYear;
	private JButton prevYear;
	private JButton nextMonth;
	private JButton prevMonth;
	private JButton today;
	
	private JLabel selectionLabel;
	
	/**
	 * Constructor for IterationOverviewPanel.
	 */
	public IterationOverviewPanel()
	{
		JScrollPane scrollPane = new JScrollPane();
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout());
		
		JPanel buttonPanel = new JPanel();
		
		nextYear = new JButton(">>");
		nextYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		nextMonth = new JButton(">");
		nextMonth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		today = new JButton ("Today");
		today.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		prevMonth = new JButton("<");
		prevMonth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		prevYear = new JButton("<<");
		prevYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		setupButtonListeners();
		
		buttonPanel.add(prevYear);
		buttonPanel.add(prevMonth);
		buttonPanel.add(today);
		buttonPanel.add(nextMonth);
		buttonPanel.add(nextYear);
		
		contentPanel.add(buttonPanel, "alignx center, dock north");
		 		
		JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarView = new IterationCalendar(this);
		calendarPanel.add(calendarView, BorderLayout.CENTER);
				
		calendarPanel.add(calendarView, BorderLayout.CENTER);	
		
		JPanel selectionPanel = new JPanel(new MigLayout("height 100:100:100, width 150:150:150","",""));
		selectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		selectionLabel = new JLabel("No Iteration Selected.");
		selectionPanel.add(selectionLabel);
		
		JPanel keyPanel = new JPanel(new MigLayout("height 100:100:100, width 150:150:150","", ""));
		keyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel keyLabel = new JLabel("Key:");
				
		JPanel selectedIteration = new JPanel ();
		selectedIteration.add(new JLabel("Selected Iteration"));
		selectedIteration.setBackground(IterationCalendar.SELECTION);
		 		
		JPanel allIterations = new JPanel();
		allIterations.add(new JLabel("All Iterations"));
		allIterations.setBackground(IterationCalendar.START_END_DAY);
		
		keyPanel.add(keyLabel, "alignx center, push, span, wrap");
		keyPanel.add(selectedIteration, "alignx left, push, span, wrap");
		keyPanel.add(allIterations, "alignx left, push, span, wrap");
		JPanel keyWrapper = new JPanel(new MigLayout());
		
		keyWrapper.add(keyPanel, "wrap");
		keyWrapper.add(selectionPanel);
		
		calendarPanel.add(keyWrapper, BorderLayout.EAST);
		
		
		
		contentPanel.add(calendarPanel, "alignx center, push, span");
		
		scrollPane.setViewportView(contentPanel);
		this.setRightComponent(scrollPane);
		this.setDividerLocation(180);
		ViewEventController.getInstance().setIterationOverview(this);
	}
	
	/**
	 * Adds action listeners to the year control buttons for the calendar view.
	 */
	private void setupButtonListeners()
	{
		nextYear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				nextYear();
			}	
		});
		
		prevYear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				previousYear();
			}
		});
		
		today.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				today();
			}
		});
		
		prevMonth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				previousMonth();
			}
		});
		
		nextMonth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				nextMonth();
			}
		});
		
	}
	
	/**
	 * Switches the calendar to the previous year.
	 */
	private void previousYear()
	{
		Calendar cal = calendarView.getCalendar();
		cal.add(Calendar.YEAR, -1);
		calendarView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the current date
	 */
	private void today()
	{
		Calendar cal = Calendar.getInstance();
		calendarView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the next month.
	 */
	private void nextMonth()
	{
		Calendar cal = calendarView.getCalendar();
		cal.add(Calendar.MONTH, 1);
		calendarView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the previous month.
	 */
	private void previousMonth()
	{
		Calendar cal = calendarView.getCalendar();
		cal.add(Calendar.MONTH, -1);
		calendarView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the next year.
	 */
	private void nextYear()
	{
		Calendar cal = calendarView.getCalendar();
		cal.add(Calendar.YEAR, +1);
		calendarView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Returns the iteration calendar
	
	 * @return the iteration calendar */
	public IterationCalendar getIterationCalendar()
	{
		return this.calendarView;
	}

	/**
	 * Highlights the given iteration on the iteration overview
	 * @param iter the iteration to highlight.
	 */
	public void highlight(Iteration iter) {
		calendarView.highlightIteration(iter);
		updateSelection(iter);
	}

	/**
	 * Updates the selection for the given iteration.
	 * @param iter
	 */
	private void updateSelection(Iteration iter) {
		if(iter == IterationModel.getInstance().getBacklog())
		{
			selectionLabel.setText("<html><b>No Iteration Selected.</b></html>");
		}
		else
		{
			String selectionText = "<html>";
			selectionText += "<b>Name: </b>" + iter.getName() + "<br>";
			selectionText += "<b>Estimate: </b>" + iter.getEstimate() + "<br>";
			selectionText += "</html>";
			
			selectionLabel.setText(selectionText);
		}
	}
	
	
}
