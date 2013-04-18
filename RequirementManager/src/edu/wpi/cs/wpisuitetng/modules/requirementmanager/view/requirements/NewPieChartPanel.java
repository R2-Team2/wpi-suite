/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
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
		int numStatusNew = 0;
		int numStatusDeleted = 0;
		int numStatusInprogress = 0;
		int numStatusComplete = 0;
		int numStatusOpen = 0;
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<Requirement> requirements = RequirementModel.getInstance().getRequirements();
		for(int i = 0; i < requirements.size(); i++){
			if(requirements.get(i).getStatus() == RequirementStatus.NEW){
				numStatusNew += 1;
			}
			else if(requirements.get(i).getStatus() == RequirementStatus.DELETED){
				numStatusDeleted += 1;
			}
			else if(requirements.get(i).getStatus() == RequirementStatus.INPROGRESS){
				numStatusInprogress += 1;
			}
			else if(requirements.get(i).getStatus() == RequirementStatus.COMPLETE){
				numStatusComplete += 1;
			}
			else{
				numStatusOpen += 1;
			}
		}
		dataSet.setValue("New", numStatusNew);
		dataSet.setValue("Deleted", numStatusDeleted);
		dataSet.setValue("In Progress", numStatusInprogress);
		dataSet.setValue("Complete", numStatusComplete);
		dataSet.setValue("Open", numStatusOpen);
		return dataSet;
	}
	
private static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(
            "Pie Chart Status",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
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
