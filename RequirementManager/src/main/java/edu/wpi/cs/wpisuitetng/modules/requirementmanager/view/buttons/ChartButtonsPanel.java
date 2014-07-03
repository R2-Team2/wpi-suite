/**
 * * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons;

/**
 *
 */

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class ChartButtonsPanel extends ToolbarGroupView {
	private final JPanel contentPanel = new JPanel();
	
	JButton pieChart;
	JButton barChart;
	
	public ChartButtonsPanel() {
		super("");
		
		this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		this.pieChart = new JButton("<html>View Pie<br />Chart</html>");
		
		pieChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().createPieChart("Status");
			}
		});
		
		this.barChart = new JButton("<html>View Bar<br />Chart</html>");
		
		barChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().createBarChart("Status");
			}
		});
		
		try {
			Image img = ImageIO.read(this.getClass().getResourceAsStream("pie_chart.png"));
			pieChart.setIcon(new ImageIcon(img));
			
			img = ImageIO.read(this.getClass().getResourceAsStream("bar_chart.png"));
			barChart.setIcon(new ImageIcon(img));
			
		} catch (IOException ex) {
			//TODO: Are we swallowing execeptions?!? We need to fix this ASAP
		}
		
		contentPanel.add(pieChart);
		contentPanel.add(barChart);
		contentPanel.setOpaque(false);
		
		this.add(contentPanel);
	}
	
	/**
	 * Method getBarChartButton.
	 * 
	 * @return JButton
	 */
	public JButton getBarChartButton() {
		return barChart;
	}
	
	/**
	 * Method getPieChartButton.
	 * 
	 * @return JButton
	 */
	public JButton getPieChartButton() {
		return pieChart;
	}
	
}
