package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class TaskCard extends JPanel {

	/**
	 * Create the panel.
	 */
	public TaskCard() {
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300};
		gridBagLayout.rowHeights = new int[] {100, 0, 100};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JTextPane txtpnSleeflsjsldfjDlsfJlsdj = new JTextPane();
		txtpnSleeflsjsldfjDlsfJlsdj.setText("SLEeflsjs;ldfj dlsf jlsdj flds fkldsj fklsdj flksd jlkf dslk fklds fl dlsk fkldsf");
		panel.add(txtpnSleeflsjsldfjDlsfJlsdj);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label_1 = new JLabel("11/16/14");
		panel_1.add(label_1);
		
		JLabel lblDmytro = new JLabel("Dima4ka");
		panel_1.add(lblDmytro);

	}

}
