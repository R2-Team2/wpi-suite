/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
// this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.AbsWorkFlowView;

/**
 * The Class WorkFlowView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class WorkFlowEditView extends AbsWorkFlowView {

    private final int maxTitleLen = 30;

    private final JPanel sidePanel = new JPanel(new MigLayout());

    /** The default border. */
    protected final Border defaultBorder = BorderFactory.createEtchedBorder();
    protected final Dimension textFieldDimension = new Dimension(200, 10);
    protected final Dimension listMinSize = new Dimension(200, 100);
    protected final Dimension listMaxSize = new Dimension(200, 10000000);

    private final JButton addButton = new JButton("Add Status");
    private final JButton removeButton = new JButton("Remove Status");
    private final JButton saveButton = new JButton("Save");

    private final JButton upButton = new JButton("\u2191");
    private final JButton downButton = new JButton("\u2193");


    // Add Status Fields
    private final JTextField newStatusTitleField = new JTextField();
    private final JLabel newStatusTitleLabel = new JLabel("New Status Title");
    private final JTextField newStatusTypeField = new JTextField();
    private final JLabel newStatusTypeLabel = new JLabel("New Status Type");

    // Show and Remove Status Components

    private final DefaultListModel<TaskStatusView> model = new DefaultListModel<TaskStatusView>();
    private final JList<TaskStatusView> listOfStatus = new JList<TaskStatusView>(model);

    /**
     * Create the panel.
     */
    public WorkFlowEditView() {
        newStatusTitleField.setBorder(defaultBorder);
        newStatusTitleField.setMinimumSize(textFieldDimension);
        newStatusTypeField.setMinimumSize(textFieldDimension);

        // Add status Components
        sidePanel.add(newStatusTitleLabel, "wrap");
        sidePanel.add(newStatusTitleField, "wrap");
        sidePanel.add(newStatusTypeLabel, "wrap");
        sidePanel.add(newStatusTypeField, "wrap");
        addButton.setEnabled(false);
        sidePanel.add(addButton, "wrap");

        // Remove Status Components
        buildList();
        listOfStatus.setBorder(defaultBorder);
        listOfStatus.setMinimumSize(listMinSize);
        listOfStatus.setMaximumSize(listMaxSize);
        listOfStatus.setSize(textFieldDimension);
        sidePanel.add(listOfStatus, "span 2 2");
        sidePanel.add(upButton, "wrap");
        sidePanel.add(downButton, "wrap");
        if (model.size() <= 1) {
            removeButton.setEnabled(false);
        }
        sidePanel.add(removeButton, "wrap");

        // Save Changes
        // This needs to work with database to actually save the changes made in this view
        sidePanel.add(saveButton, "wrap");

        taskStatusPanel.add(sidePanel, "dock east,");
        setupListeners();
    }

    private void buildList() {
        for (TaskStatusView t : getViews()) {
            if (!model.contains(t)) {
                model.addElement(t);
            }
        }
    }

    /**
     * Gets the work flow obj.
     *
     * @return the work flow obj
     */
    @Override
    public WorkFlow getWorkFlowObj() {
        return super.getWorkFlowObj();
    }

    /**
     * Sets the work flow obj.
     *
     * @param workFlowObj the new work flow obj
     */
    @Override
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        setWorkFlowObj(workFlowObj);
    }

    /**
     * Refresh.
     */
    @Override
    public void refresh() {
        super.refresh();
        buildList();
    }

    /**
     * Set up Listeners for Work Flow Edit Components
     */
    protected void setupListeners() {
        model.addListDataListener(new ListDataListener() {

            @Override
            public void intervalRemoved(ListDataEvent e) {
                removeButton.setEnabled(isModelSizeValid());
            }

            @Override
            public void intervalAdded(ListDataEvent e) {
                removeButton.setEnabled(isModelSizeValid());
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                removeButton.setEnabled(isModelSizeValid());
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNewStatusTitleFieldValid()) {
                    addTaskStatusView(new TaskStatusView(new TaskStatus(newStatusTitleField
                            .getText())));
                    clearNewStatusFields();
                }
                refresh();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove selected from JList
                if ((model.size() > 1)) {
                    final TaskStatusView taskStatusViewToRemove = listOfStatus.getSelectedValue();
                    model.removeElement(taskStatusViewToRemove);
                    removeTaskStatusView(taskStatusViewToRemove);
                    buildTaskStatusViews();
                    refresh();
                    revalidate();
                    repaint();
                }
            }
        });

        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove selected from JList
                final TaskStatusView taskStatusViewToMoveUp = listOfStatus.getSelectedValue();
                moveStatusUp(taskStatusViewToMoveUp);
                refresh();
                revalidate();
                repaint();
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove selected from JList
                final TaskStatusView taskStatusViewToMoveDown = listOfStatus.getSelectedValue();
                moveStatusDown(taskStatusViewToMoveDown);

                buildTaskStatusViews();
                refresh();
                revalidate();
                repaint();
            }
        });

        newStatusTitleField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                validateNewStatusFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateNewStatusFields();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateNewStatusFields();
            }
        });
        newStatusTypeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                validateNewStatusFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateNewStatusFields();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateNewStatusFields();
            }
        });
    }

    /**
     * Validate Model
     *
     * @return Valid if at least one TaskStatusView is in model
     */
    protected boolean isModelSizeValid() {
        return !(model.size() <= 1);
    }

    /**
     * @param taskStatusViewToMoveUp this TaskStatusView is moved up in the order of Work Flow View
     */
    protected void moveStatusUp(TaskStatusView taskStatusViewToMoveUp) {
        if (!(model.indexOf(taskStatusViewToMoveUp) <= 0)) {
            // Move in views
            moveUp(taskStatusViewToMoveUp);
            // update JList
            final int source = model.indexOf(taskStatusViewToMoveUp);
            final int dest = model.indexOf(taskStatusViewToMoveUp) - 1;

            final TaskStatusView sourceStatusView = model.get(source);
            final TaskStatusView destStatusView = model.get(dest);

            model.set(dest, sourceStatusView);
            model.set(source, destStatusView);
            listOfStatus.setSelectedValue(taskStatusViewToMoveUp, true);
            buildList();
        }
    }

    /**
     * @param taskStatusViewToMoveDown this TaskStatusView is moved down in the order of Work Flow
     *        View
     */
    protected void moveStatusDown(TaskStatusView taskStatusViewToMoveDown) {
        if (!(model.indexOf(taskStatusViewToMoveDown) >= model.getSize() - 1)) {
            // Move in views
            moveDown(taskStatusViewToMoveDown);
            // update JList
            final int source = model.indexOf(taskStatusViewToMoveDown);
            final int dest = model.indexOf(taskStatusViewToMoveDown) + 1;

            final TaskStatusView sourceStatusView = model.get(source);
            final TaskStatusView destStatusView = model.get(dest);

            model.set(dest, sourceStatusView);
            model.set(source, destStatusView);
            listOfStatus.setSelectedValue(taskStatusViewToMoveDown, true);
            buildList();
        }
    }

    /**
     * Clear The Fields for creating a new TaskStatusView usually called when a
     * {@link TaskStatusView}usView has just been added to clear that view for a new
     * {@link TaskStatusView}skStatusView to be added
     */
    protected void clearNewStatusFields() {
        newStatusTitleField.setText("");
        newStatusTypeField.setText("");
    }


    private void validateNewStatusFields() {
        if (isNewStatusTitleFieldValid() && isNewStatusTypeFieldValid()) {
            addButton.setEnabled(true);
        } else {
            addButton.setEnabled(false);
        }
    }

    private boolean isNewStatusTitleFieldValid() {
        return !(newStatusTitleField.getText().length() <= 0)
                && (newStatusTitleField.getText().length() <= maxTitleLen);
    }

    private boolean isNewStatusTypeFieldValid() {
        return !(newStatusTypeField.getText().length() <= 0);
    }
}
