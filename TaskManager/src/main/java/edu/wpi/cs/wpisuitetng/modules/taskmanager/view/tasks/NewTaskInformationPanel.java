/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.toedter.calendar.JCalendar;

import net.miginfocom.swing.MigLayout;

/**
 * @author R2-Team2
 *
 */
@SuppressWarnings("serial")
public class NewTaskInformationPanel extends JScrollPane {
	//Class Variables
	private NewTaskPanel parentPanel;
	
	private String[] listOfChosenAssignees = new String[] {"None"};
	private String[] listOfPossibleAssignees = new String[] {"None"};
	private String[] listOfStatuses = new String[] {"None"};
	private String[] listOfRequirements = new String[] {"None"};
	
    private final Border defaultBorder = (new JTextField()).getBorder();
	
	private JTextField boxTitle;
	private JTextArea boxDescription;
	private JComboBox<String> dropdownStatus;
	private JComboBox<String> dropdownRequirement;
	private JList<String> listChosenAssignees;
	private JList<String> listPossibleAssignees;
	private JSpinner spinnerEstimatedEffort;
	private JSpinner spinnerActualEffort;
	private JButton buttonAdd;
	private JButton buttonRemove;
	private JCalendar calStartDate;
	private JCalendar calDueDate;
	
	/**
	 * Constructor for NewTaskInformationPanel
	 * @param parentPanel
	 */
	public NewTaskInformationPanel(NewTaskPanel parentPanel) {
		this.parentPanel = parentPanel;
        this.setMinimumSize(new Dimension(500, 200));
        
		this.buildLayout();
	}
	
	/**
	 * Builds the GUI for the New Task
	 */
	private void buildLayout() {
		//Set the Panel
		ScrollablePanel contentPanel = new ScrollablePanel();
		contentPanel.setLayout(new MigLayout("","","shrink"));
		//Instantiate GUI Elements
		//Labels
		JLabel labelTitle = new JLabel("Title: ");
		JLabel labelDescription = new JLabel("Description: ");
		JLabel labelStatus = new JLabel("Status: ");
		JLabel labelEstimatedEffort = new JLabel("Estimated Effort: ");
		JLabel labelActualEffort = new JLabel("Actual Effort: ");
		JLabel labelDueDate = new JLabel("Due Date: ");
		JLabel labelStartDate = new JLabel("Start Date: ");
		JLabel labelRequirement = new JLabel("Requirement: ");
		JLabel labelPossibleAssignee = new JLabel("Open Assignees: ");
		JLabel labelChosenAssignee = new JLabel("Chosen Assignees: ");
		//Text Fields
		boxTitle = new JTextField();
		//Text Areas
		JScrollPane descrScroll = new JScrollPane();
		boxDescription = new JTextArea();
		boxDescription.setLineWrap(true);
		boxDescription.setBorder(defaultBorder);
		descrScroll.setViewportView(boxDescription);
		//Drop Down Menus
		dropdownRequirement = new JComboBox<String>();
		dropdownRequirement.setModel(new DefaultComboBoxModel<String>(listOfRequirements));
		dropdownRequirement.setEnabled(false);
		dropdownRequirement.setBackground(Color.WHITE);
		dropdownStatus = new JComboBox<String>();
		dropdownStatus.setModel(new DefaultComboBoxModel<String>(listOfStatuses));
		dropdownStatus.setEnabled(false);
		dropdownStatus.setBackground(Color.WHITE);
		//Lists
		listChosenAssignees = new JList<String>(listOfChosenAssignees);
		listPossibleAssignees = new JList<String>(listOfPossibleAssignees);
		//Spinners
		spinnerEstimatedEffort = new JSpinner(new SpinnerNumberModel(0,0,255,1));
		spinnerActualEffort = new JSpinner(new SpinnerNumberModel(0,0,255,1));
		//Buttons
		buttonAdd = new JButton("Add");
		buttonRemove = new JButton("Remove");
		//Calendars
		calStartDate = new JCalendar();
		calDueDate = new JCalendar();
		
		//Setup GUI
		contentPanel.add(labelTitle, "wrap");
		contentPanel.add(boxTitle, "growx, pushx, shrinkx, span, wrap");
		
		contentPanel.add(labelDescription, "wrap");
		contentPanel.add(descrScroll, "growx, pushx, shrinkx, span, height 200px, wmin 10, wrap");
		
		//Setup Columns
        JPanel leftColumn = new JPanel(new MigLayout());
        JPanel rightColumn = new JPanel(new MigLayout());

        leftColumn.add(labelStatus, "left, wrap");
        leftColumn.add(dropdownStatus, "left, width 200px, wrap");
        
        leftColumn.add(labelRequirement, "left, wrap");
        leftColumn.add(dropdownRequirement, "left, width 200px, wrap");
        
        leftColumn.add(labelStartDate, "left, wrap");
        leftColumn.add(calStartDate, "left, wrap");
		
        leftColumn.add(labelPossibleAssignee, "left, wrap");
        leftColumn.add(listPossibleAssignees, "left, width 200px, height 150px, wrap");
        
        leftColumn.add(buttonAdd, "center, wrap");
		
        rightColumn.add(labelEstimatedEffort, "left, wrap");
        rightColumn.add(spinnerEstimatedEffort, "left, width 200px, height 25px, wrap");
		
        rightColumn.add(labelActualEffort, "left, wrap");
        rightColumn.add(spinnerActualEffort, "left, width 200px, height 25px, wrap");
		
        rightColumn.add(labelDueDate, "left, wrap");
        rightColumn.add(calDueDate, "left, wrap");

        rightColumn.add(labelChosenAssignee, "left, wrap");
        rightColumn.add(listChosenAssignees, "left, width 200px, height 150px, wrap");

        rightColumn.add(buttonRemove, "center, wrap");
        
		contentPanel.add(leftColumn, "left, spany, growy, push");
        contentPanel.add(rightColumn, "right, spany, growy, push");
		
		this.setViewportView(contentPanel);
	}
	
	/**
	 * Returns the JTextField holding the title
	 * @return JTextField
	 */
	public JTextField getTitle() {
		return boxTitle;
	}
	
	/**
	 * Returns the JTextArea holding the description
	 * @return JTextArea
	 */
	public JTextArea getDescription() {
		return boxDescription;
	}
	
	/**
	 * Returns the JSpinner holding the estimated effort
	 * @return JSpinner
	 */
	public JSpinner getEstimatedEffort() {
		return spinnerEstimatedEffort;
	}
	
	/**
	 * Returns the JSpinner holding the actual effort
	 * @return JSpinner
	 */
	public JSpinner getActualEffort() {
		return spinnerEstimatedEffort;
	}
	
	/**
	 * Returns the JComboBox holding the status
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getStatus() {
		return dropdownStatus;
	}
	
	/**
	 * Returns the JComboBox holding the Requirement
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getRequirement() {
		return dropdownRequirement;
	}
	
	/**
	 * Returns the JCalendar holding the Start Date
	 * @return JCalendar
	 */
	public JCalendar getStartDate() {
		return calStartDate;
	}
	
	/**
	 * Returns the JCalendar holding the Due Date
	 * @return JCalendar
	 */
	public JCalendar getDueDate() {
		return calDueDate;
	}
}

