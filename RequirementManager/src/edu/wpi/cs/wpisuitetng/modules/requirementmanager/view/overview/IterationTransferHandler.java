/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 *
 */
public class IterationTransferHandler extends TransferHandler{

	public IterationTransferHandler() {
		super();		
	}		

	public boolean canImport(TransferHandler.TransferSupport info) {
		if(!info.isDrop()) {
			return false;
		}        
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}

		// check if the source actions (a bitwise-OR of supported actions)
		// contains the COPY action
		boolean copySupported = (COPY & info.getSourceDropActions()) == COPY;
		if (copySupported) {
			info.setDropAction(COPY);
			return true;
		}

		// COPY is not supported, so reject the transfer
		return false;
	}

	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY;
	}

	public boolean importData(TransferHandler.TransferSupport info) {

		// TODO: checking to see if the requirement has estimate>0 should be done upon drag/export attempt

		// make sure a drop is being handled
		if (!info.isDrop()) {
			return false;
		}	        
		// Get the string that is being dropped.
		Transferable t = info.getTransferable();
		int requirementID;
		try {
			requirementID = (Integer)t.getTransferData(DataFlavor.stringFlavor);
		} 
		catch (Exception e) { return false; }

		// get location to insert requirement
		JTree.DropLocation dl = (JTree.DropLocation)info.getDropLocation();	        
		TreePath index = dl.getPath();

		// TODO: use the treepath index to figure out what the Iteration is
		Iteration iteration = null;

		// retrieve the requirement with ID requirementID
		Requirement requirement = RequirementModel.getInstance().getRequirement(requirementID);

		// check to see if the requirement is already in the iteration
		if (requirement.getIteration() == iteration.getName()) return false;

		// TODO: ensure the target iteration is not in the past

		// check to see if the requirement is not in Backlog
		// TODO: if it isn't, prompt to confirm that the iteration should be changed
		if (requirement.getIteration() == "Backlog") {

		}

		// add the requirement to the iteration
		requirement.setIteration(iteration.getName(), false);

		// update the Iteration Tree
		ViewEventController.getInstance().getOverviewTree().refresh();

		return true;       
	}

}
