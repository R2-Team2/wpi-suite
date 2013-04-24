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
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author Raphael
 *
 */
public class IterationTransferHandler extends TransferHandler{
	
	public IterationTransferHandler(JTree iterationTree) {
		super();
		
	}
		

	public boolean canImport(TransferHandler.TransferSupport info) {
        if(!info.isDrop()) {
            return false;
        }        
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
		return true;
	}
	
	 public int getSourceActions(JComponent c) {
	        return TransferHandler.COPY;
	    }
	 
	 public boolean importData(TransferHandler.TransferSupport info) {
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
	        
	        // get location to insert requirement name
	        JTree.DropLocation dl = (JTree.DropLocation)info.getDropLocation();	        
	        TreePath index = dl.getPath();
	        
	        Requirement requirement = RequirementModel.getInstance().getRequirement(requirementID);
	        DefaultMutableTreeNode newReqNode = new DefaultMutableTreeNode(requirement);
	        // finish
	        ViewEventController.getInstance().getOverviewTree();
//			{
//				this.tree.insertNodeInto((DnDNode) list.get(i).getLastPathComponent(),
//						(DnDNode) loc.getLastPathComponent(), ((DnDNode) loc
//								.getLastPathComponent()).getAddIndex((DnDNode) list.get(i)
//								.getLastPathComponent()));
//			}
//			// success!
//			return true;
//		}
			return false;
	       
	                                
	        // Perform the actual import.  
//	        for (int i = 0; i < values.length; i++) {
//	            if (insert) {
//	                listModel.add(index++, values[i]);
//	            } else {
//	                // If the items go beyond the end of the current
//	                // list, add them in.
//	                if (index < listModel.getSize()) {
//	                    listModel.set(index++, values[i]);
//	                } else {
//	                    listModel.add(index++, values[i]);
//	                }
//	            }
//	        }
//	        return true;
    }

}
