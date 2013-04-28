/**
 *  * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
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

<<<<<<< HEAD
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
=======
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
>>>>>>> 0ab84756ec22bafa8be2cef9c4da6afdb52cff81
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class ChartButtonsPanel extends ToolbarGroupView{
	
	private final JPanel contentPanel = new JPanel();
	
	JButton pieChart;
	JButton barChart;
	
	public ChartButtonsPanel(){
		super("");
		
		this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
<<<<<<< HEAD
		JButton pieChart = new JButton("<html>View Pie<br />Chart</html>");
=======
		pieChart = new JButton("View Pie Charts");
>>>>>>> 0ab84756ec22bafa8be2cef9c4da6afdb52cff81
		
		pieChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ViewEventController.getInstance().createPieChart("Status");
			}
		});
		
<<<<<<< HEAD
		JButton barChart = new JButton("<html>View Bar<br />Chart</html>");
=======
		barChart = new JButton("View Bar Charts");
>>>>>>> 0ab84756ec22bafa8be2cef9c4da6afdb52cff81
		
		barChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ViewEventController.getInstance().createBarChart("Status");
				
				
				
				
			}
		});
		
<<<<<<< HEAD
		try {
		    Image img = ImageIO.read(getClass().getResource("pie_chart.png"));
		    pieChart.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("bar_chart.png"));
		    barChart.setIcon(new ImageIcon(img));
		    
		} catch (IOException ex) {}
		
		contentPanel.add(pieChart);
		contentPanel.add(barChart);
		contentPanel.setOpaque(false);
=======
		barChart.setIcon(new ImageIcon("../RequirementManager/resources/view-bar-charts-icon.png"));
		barChart.setHorizontalTextPosition(AbstractButton.CENTER);
		barChart.setVerticalTextPosition(AbstractButton.BOTTOM);
		
		pieChart.setIcon(new ImageIcon("../RequirementManager/resources/view-pie-charts-icon.png"));
		pieChart.setHorizontalTextPosition(AbstractButton.CENTER);
		pieChart.setVerticalTextPosition(AbstractButton.BOTTOM);
		
		toolbarLayout.putConstraint(SpringLayout.WEST, barChart, 35,SpringLayout.WEST, this);
		toolbarLayout.putConstraint(SpringLayout.WEST, pieChart, 30, SpringLayout.EAST, barChart);
>>>>>>> 0ab84756ec22bafa8be2cef9c4da6afdb52cff81
		
		this.add(contentPanel);
	}

	/**
	 * Method getBarChartButton.
	 * @return JButton
	 */
	public JButton getBarChartButton() {
		return barChart;
	}

	/**
	 * Method getPieChartButton.
	 * @return JButton
	 */
	public JButton getPieChartButton() {
		return pieChart;
	}
	
}
