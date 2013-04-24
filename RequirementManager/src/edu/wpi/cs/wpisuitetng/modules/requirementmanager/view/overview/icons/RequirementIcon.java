package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.icons;

import java.awt.Color;
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
		g.setColor(new Color(255, 127, 0));
		g.fillOval(x, y, width, height);
	}

}
