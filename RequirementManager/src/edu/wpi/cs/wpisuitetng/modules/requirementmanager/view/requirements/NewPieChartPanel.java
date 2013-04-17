/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author Westley
 *
 */
public class NewPieChartPanel extends RequirementPanel{
	private String title;
	public NewPieChartPanel(String title){
		this.title = title;
		this.setViewportView(createPanel());

		
		
		
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

/* (non-Javadoc)
 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
 */
@Override
public void keyPressed(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

/* (non-Javadoc)
 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
 */
@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

/* (non-Javadoc)
 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
 */
@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

/* (non-Javadoc)
 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
 */
@Override
public void itemStateChanged(ItemEvent arg0) {
	// TODO Auto-generated method stub
	
}

/* (non-Javadoc)
 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel#readyToRemove()
 */
@Override
public boolean readyToRemove() {
	// TODO Auto-generated method stub
	return false;
}


		
}
