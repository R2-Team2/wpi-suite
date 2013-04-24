package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.icons.IterationIcon;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.icons.RequirementIcon;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
	Icon requirementIcon;
	Icon iterationIcon;
	
	public CustomTreeCellRenderer(){
		DefaultTreeCellRenderer temp = new DefaultTreeCellRenderer();
		requirementIcon = new RequirementIcon();
		iterationIcon = new IterationIcon();
	}
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus){
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		
		if(node.getUserObject() instanceof Requirement){
			setIcon(requirementIcon);
		}
		else{
			setIcon(iterationIcon);
		}
		
		return this;
	}

}
