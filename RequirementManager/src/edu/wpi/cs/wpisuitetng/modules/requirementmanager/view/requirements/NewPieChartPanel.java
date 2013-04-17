/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;



import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.*;


import org.jfree.ui.ApplicationFrame;

import java.util.Date;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author Westley
 *
 */
public class NewPieChartPanel extends ApplicationFrame{
	
	public NewPieChartPanel(String title){
		super(title);
		setContentPane(createPanel());
		
		
		
	}
	
	private static PieDataset setData(){
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("One", 1);
		dataSet.setValue("Two", 2);
		dataSet.setValue("Three", 3);
		return dataSet;
	}
	
private static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 1",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }

public static JPanel createPanel() {
    JFreeChart chart = createChart(setData());
    return new ChartPanel(chart);
}


		
}
