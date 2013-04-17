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

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class SubrequirementPanel extends JPanel 
{
	private Requirement activeRequirement;
	private JTable subRequirementTable;
	private JButton addNewButton;
	private JButton addExistingButton;
	private RequirementSelector existingReqSelector;
	private DefaultTableModel tableModel;
	private double ratio;

	/**
	 * Constructor for the subrequirement panel.
	 * @param requirementBeingEdited the current requirement being edited.
	 */
	public SubrequirementPanel(Requirement requirementBeingEdited)
	{
		this.activeRequirement = requirementBeingEdited;
		existingReqSelector = new RequirementSelector();

		// Create new scroll pane for jtable
		final JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Always show scroll bar

		final JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
		split.setLeftComponent(scroll);
		split.setEnabled(false);
		split.setDividerSize(0);
		split.setDividerLocation(1.0d);
		
		addNewButton = new JButton("Add New");
		addNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().createChildRequirement(activeRequirement.getId());				
			}
		});

		addExistingButton = new JButton("Add Existing");
		addExistingButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent k) {
				ratio = 1.0;
				split.setDividerLocation(1.0d);
				split.setRightComponent(existingReqSelector);
				final Timer timer = new Timer(38, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ratio -= (0.85 / 25);
						if(ratio <= 0 || ratio >= 1)
						{
							((Timer)e.getSource()).stop();
						}
						split.setDividerLocation(ratio);
						if(ratio <= 0.75) ((Timer)e.getSource()).stop();
					}
				});	
				timer.start();
			}
		});

		// Layout manager for subrequirement panel
		GridBagLayout layout = new GridBagLayout();
				this.setLayout(layout);
				GridBagConstraints c = new GridBagConstraints();

				// Layout manager for button panel
				GridBagLayout bl = new GridBagLayout();
				JPanel buttons = new JPanel(bl);
				GridBagConstraints bc = new GridBagConstraints();

				c.fill = GridBagConstraints.BOTH; // Fill grid cell with elements
				c.anchor = GridBagConstraints.NORTH; // Anchor to top of panel
				c.weightx = 1; // Fill horizontal space
				c.weighty = 1; // Fill all the vertical space
				this.add(split, c); // Add scroll pane to panel

				bc.anchor = GridBagConstraints.WEST; // Anchor to left
				buttons.add(addNewButton, bc);

				bc.gridx = 1; // Column 1
				buttons.add(addExistingButton);

				c.fill = GridBagConstraints.NONE; // Don't fill cell
				c.anchor = GridBagConstraints.WEST; // Anchor to left of panel
				c.gridy = 1; // Row 1
				c.weighty = 0; // Do not stretch vertically
				this.add(buttons, c); // Add buttons to panel

				subRequirementTable = buildTable();
				scroll.setViewportView(subRequirementTable);

				this.refreshTable();
	}

	/**
	 * Constructs the jtable that holds all of the subrequirements.
	 * @return the subrequirement table.
	 */
	private JTable buildTable()
	{
		subRequirementTable = new JTable()
		{
			/**
			 * Overrides the isCellEditable method to ensure no cells are editable.
			 * 
			 * @param row	row of OverviewTable cell is located
			 * @param col	column of OverviewTable cell is located
			 */
			@Override
			public boolean isCellEditable(int row, int col)
			{
				return false;
			}
		};

		String[] columnNames = {"Name", "Description", "Priority", "Estimate"}; 		
		tableModel = new DefaultTableModel(columnNames, 0);
		subRequirementTable.setModel(tableModel);

		subRequirementTable.getTableHeader().setReorderingAllowed(false);
		subRequirementTable.setAutoCreateRowSorter(true);
		subRequirementTable.setFillsViewportHeight(true);

		subRequirementTable.getColumnModel().getColumn(0).setMaxWidth(240);
		subRequirementTable.getColumnModel().getColumn(2).setMaxWidth(75);
		subRequirementTable.getColumnModel().getColumn(3).setMaxWidth(75);

		return subRequirementTable;
	}

	/**
	 * Populates the subrequirement table with the sub requirements of the requirement being edited.
	 */
	private void refreshTable()
	{
		tableModel.setRowCount(0); //clear the table

		List<Requirement> subRequirements = activeRequirement.getChildren();

		for (int i = 0; i < subRequirements.size(); i++) {
			Requirement req = subRequirements.get(i);
			if (!req.isDeleted()) {
				tableModel.addRow(new Object[]{ req,
						req.getDescription(),
						req.getPriority(),
						req.getEstimate()
				});
			}
		}
	}

	/**
	 * Overrides the paintComponent method to retrieve the requirements on the first painting.
	 * 
	 * @param g	The component object to paint
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		refreshTable();

		super.paintComponent(g);
	}
}
