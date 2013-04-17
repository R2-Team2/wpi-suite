/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

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
