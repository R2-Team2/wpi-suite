package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.icons;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class RequirementIcon implements Icon {

	private int height;
	private int width;
	
	public RequirementIcon(){
		height = 5;
		width = 5;
	}
	
	@Override
	public int getIconHeight() {
		return height;
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.drawRect(x, y, width, height);
	}

}
