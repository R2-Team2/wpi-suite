package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
//CATS!!
@SuppressWarnings("serial")
public class NewRequirementPanel extends JPanel {
	public NewRequirementPanel()
    {

            setLayout(new GridLayout(1,2));
            
            
            JPanel leftPanel = new JPanel();
            
            JLabel labelName = new JLabel("Name");
            JLabel labelDescription = new JLabel("Description");
            
            final JTextField boxName = new JTextField();
            final JTextArea boxDescription = new JTextArea();
            boxDescription.setLineWrap(true);
                                                                                                                                           
            boxName.setPreferredSize(new Dimension(200,20));
            boxDescription.setPreferredSize(new Dimension(200,100));

            final JTextArea testField = new JTextArea();        // For testing purpose
            testField.setPreferredSize(new Dimension(200,200));
            
            SpringLayout layout = new SpringLayout();
            
            // Setting layout (set-> add -> adjust)
            leftPanel.setLayout(layout);
            
            leftPanel.add(labelName);
            leftPanel.add(boxName);
            leftPanel.add(labelDescription);
            leftPanel.add(boxDescription);
            /**/leftPanel.add(testField);
            
            layout.putConstraint(SpringLayout.NORTH, labelName, 15, SpringLayout.NORTH, leftPanel);
            layout.putConstraint(SpringLayout.WEST, labelName, 15, SpringLayout.WEST, leftPanel);
            
            layout.putConstraint(SpringLayout.NORTH, boxName, 15, SpringLayout.SOUTH, labelName);
            layout.putConstraint(SpringLayout.WEST, boxName, 15, SpringLayout.WEST, leftPanel);
            
            layout.putConstraint(SpringLayout.NORTH, labelDescription, 15, SpringLayout.SOUTH, boxName);
            layout.putConstraint(SpringLayout.WEST, labelDescription, 15, SpringLayout.WEST, leftPanel);
            
            layout.putConstraint(SpringLayout.NORTH, boxDescription, 15, SpringLayout.SOUTH, labelDescription);
            layout.putConstraint(SpringLayout.WEST, boxDescription, 15, SpringLayout.WEST, leftPanel);
            
            layout.putConstraint(SpringLayout.NORTH, testField, 15, SpringLayout.SOUTH, boxDescription);
            layout.putConstraint(SpringLayout.WEST, testField, 15, SpringLayout.WEST, leftPanel);
            
            
            // RIGHT Panel
            
            JPanel rightPanel = new JPanel();

            
            JLabel labelStatus = new JLabel("Status");
            JLabel labelPriority = new JLabel("Priority");
            JLabel labelReleaseNum = new JLabel("Release #");
            
            
            final JTextField boxReleaseNum = new JTextField();
            boxReleaseNum.setPreferredSize(new Dimension(200,20));
            
            String[ ] statusString = {"New","In Progress","Open", "Complete","Delete"};
            final JComboBox dropdownStatus = new JComboBox(statusString);
            
            JPanel priorityPanel = new JPanel();
            
            // Radio buttons
            
            final JRadioButton priorityHigh = new JRadioButton("High");
            final JRadioButton priorityMedium = new JRadioButton("Medium");
            final JRadioButton priorityLow = new JRadioButton("Low");
    
            // Toggle for Radio Button
            // TODO: Clean this up
            priorityHigh.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                            priorityMedium.setSelected(false);
                            priorityLow.setSelected(false);
                    }
            });
            
            priorityMedium.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                            priorityHigh.setSelected(false);
                            priorityLow.setSelected(false);
                    }
            });
            
            priorityLow.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                            priorityMedium.setSelected(false);
                            priorityHigh.setSelected(false);
                    }
            });
            
            ButtonGroup group = new ButtonGroup();
            group.add(priorityHigh);
            group.add(priorityMedium);
            group.add(priorityLow);
            
            priorityPanel.add(priorityLow);
            priorityPanel.add(priorityMedium);
            priorityPanel.add(priorityHigh);
            
            JPanel buttonPanel = new JPanel();
            JButton buttonUpdate = new JButton("Update");
            JButton buttonCancel = new JButton("Cancel");
            JButton buttonDelete = new JButton("Delete");
            
            buttonUpdate.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                            String stringName = boxName.getText();
                            String stringDescription = boxDescription.getText();
                            String stringStatus = (String) dropdownStatus.getSelectedItem();
                            String stringPriority;
                            
                            boolean stateHigh = priorityHigh.isSelected();
                            boolean stateMedium = priorityMedium.isSelected();
                            boolean stateLow = priorityLow.isSelected();
                            
                            if(stateHigh) stringPriority = "High";
                            else if(stateMedium) stringPriority = "Medium";
                            else if(stateLow) stringPriority = "Low";
                            else stringPriority = "Not selected";
                            
                            String stringReleaseNum = boxReleaseNum.getText();
                            
                            String entireData = stringName +"\n";
                            entireData = entireData + stringDescription +"\n";
                            entireData = entireData + stringStatus + "\n";
                            entireData = entireData + stringPriority + "\n";
                            entireData = entireData + stringReleaseNum+ "\n";
                            
                            
                            testField.setText(entireData); 
                           
                    }
            });
            
            buttonPanel.add(buttonUpdate);
            buttonPanel.add(buttonCancel);
            buttonPanel.add(buttonDelete);
            
            SpringLayout rightLayout = new SpringLayout();
            
            rightPanel.setLayout(rightLayout);
            
            rightPanel.add(labelStatus);
            rightPanel.add(dropdownStatus);
            rightPanel.add(labelPriority);
            rightPanel.add(priorityPanel);
            rightPanel.add(labelReleaseNum);
            rightPanel.add(boxReleaseNum);
            rightPanel.add(buttonPanel);
            
            rightLayout.putConstraint(SpringLayout.NORTH, labelStatus, 15, SpringLayout.NORTH, rightPanel);
            rightLayout.putConstraint(SpringLayout.WEST, labelStatus, 15, SpringLayout.WEST, rightPanel);
            
            rightLayout.putConstraint(SpringLayout.NORTH, dropdownStatus, 15, SpringLayout.SOUTH, labelStatus);
            rightLayout.putConstraint(SpringLayout.WEST, dropdownStatus, 15, SpringLayout.WEST, rightPanel);
            
            rightLayout.putConstraint(SpringLayout.NORTH, labelPriority, 15, SpringLayout.SOUTH, dropdownStatus);
            rightLayout.putConstraint(SpringLayout.WEST, labelPriority, 15, SpringLayout.WEST, rightPanel);
            
            rightLayout.putConstraint(SpringLayout.NORTH, priorityPanel, 15, SpringLayout.SOUTH, labelPriority);
            rightLayout.putConstraint(SpringLayout.WEST, priorityPanel, 15, SpringLayout.WEST, rightPanel);
            
            rightLayout.putConstraint(SpringLayout.NORTH, labelReleaseNum, 15, SpringLayout.SOUTH, priorityPanel);
            rightLayout.putConstraint(SpringLayout.WEST, labelReleaseNum, 15, SpringLayout.WEST, rightPanel);
            
            rightLayout.putConstraint(SpringLayout.NORTH, boxReleaseNum, 15, SpringLayout.SOUTH, labelReleaseNum);
            rightLayout.putConstraint(SpringLayout.WEST, boxReleaseNum, 15, SpringLayout.WEST, rightPanel);
            
            rightLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 15, SpringLayout.SOUTH, boxReleaseNum);
            rightLayout.putConstraint(SpringLayout.WEST, buttonPanel, 15, SpringLayout.WEST, rightPanel);
            

            add(leftPanel);
            add(rightPanel);
    }
}
