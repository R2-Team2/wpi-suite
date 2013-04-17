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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class SubrequirementPanel extends JScrollPane implements RequirementSelectorListener
{
	private Requirement activeRequirement;
	private JTable subRequirementTable;
	private JButton addNewButton;
	private RequirementSelector existingReqSelector;
	private DefaultTableModel tableModel;

	/**
	 * Constructor for the subrequirement panel.
	 * @param requirementBeingEdited the current requirement being edited.
	 */
	public SubrequirementPanel(Requirement requirementBeingEdited)
	{
		JPanel contentPanel = new JPanel();
		this.activeRequirement = requirementBeingEdited;
		existingReqSelector = new RequirementSelector(this, activeRequirement, RequirementSelectorMode.POSSIBLE_CHILDREN, false);
		// Create new scroll pane for jtable
		
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Always show scroll bar

		addNewButton = new JButton("Add New");
		addNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().createChildRequirement(activeRequirement.getId());				
			}
		});

		// Layout manager for subrequirement panel
		GridBagLayout layout = new GridBagLayout();
		contentPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH; // Anchor to top of panel
		c.weightx = 1; // Fill horizontal space
		contentPanel.add(scroll, c); // Add scroll pane to panel

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTH; // Anchor to left
		c.gridy = 1;
		existingReqSelector.addButton(addNewButton);
		contentPanel.add(existingReqSelector,c);

		subRequirementTable = buildTable();
		scroll.setViewportView(subRequirementTable);

		this.setViewportView(contentPanel);
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

	/**
	 * Overrides the requirementSelected method to signal to refresh the table when the
	 * subrequirements are updated.
	 */
	@Override
	public void requirementSelected() {
		refreshTable();
	}
}
