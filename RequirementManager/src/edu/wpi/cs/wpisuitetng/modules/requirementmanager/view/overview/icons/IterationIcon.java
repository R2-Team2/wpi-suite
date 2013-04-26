package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.icons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class IterationIcon implements Icon {
	
	private int width;
	private int height;
	
	public IterationIcon(){
		width = 5;
		height = 5;
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
		g.setColor(new Color(175, 0, 255));
		g.fillOval(x, y, width, height);
	}

}
