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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.AbstractButton;
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
		
		JButton pieChart = new JButton("<html>View Pie<br />Chart</html>");
		
		pieChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ViewEventController.getInstance().createPieChart("Status");
			}
		});
		
		JButton barChart = new JButton("<html>View Bar<br />Chart</html>");
		
		barChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ViewEventController.getInstance().createBarChart("Status");
				
				
				
				
			}
		});
		
		try {
		    Image img = ImageIO.read(getClass().getResource("pie_chart.png"));
		    pieChart.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("bar_chart.png"));
		    barChart.setIcon(new ImageIcon(img));
		    
		} catch (IOException ex) {}
		
		contentPanel.add(pieChart);
		contentPanel.add(barChart);
		contentPanel.setOpaque(false);
		
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
