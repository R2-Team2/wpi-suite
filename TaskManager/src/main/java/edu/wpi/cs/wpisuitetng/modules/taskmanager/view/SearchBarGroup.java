/**
 *
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;

/**
 * @author R2-Team2
 * @version 0.1
 */
public class SearchBarGroup extends ToolbarGroupView {

    /** The content panel. */
    private final JPanel contentPanel = new JPanel();
    private final JTextField searchBar = new JTextField();
    final JButton btnCancel;
    final JCheckBox chckbxDescription;
    final JCheckBox chckbxRequirements;
    final JCheckBox chckbxAssignee;
    final JCheckBox chckbxArchieved;

    public SearchBarGroup() {
        super("");
        setPreferredWidth(350);
        contentPanel.setLayout(null);
        searchBar.setBounds(62, 6, 280, 28);

        contentPanel.add(searchBar);
        contentPanel.setOpaque(false);

        this.add(contentPanel);

        final JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(6, 12, 61, 16);
        contentPanel.add(lblSearch);

        chckbxDescription = new JCheckBox("Description");
        chckbxDescription.setToolTipText("Search through descriptions");
        chckbxDescription.setBounds(6, 40, 105, 23);
        chckbxDescription.setOpaque(false);
        contentPanel.add(chckbxDescription);

        chckbxDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().length() != 0) {
                    searchBarTextChanged();
                }
            }
        });

        chckbxArchieved = new JCheckBox("Archived");
        chckbxArchieved.setEnabled(false);
        chckbxArchieved.setBounds(123, 66, 94, 23);
        chckbxArchieved.setOpaque(false);

        btnCancel = new JButton("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.setBounds(225, 46, 117, 43);
        contentPanel.add(btnCancel);

        chckbxRequirements = new JCheckBox("Requirements");
        chckbxRequirements.setBounds(6, 66, 128, 23);
        chckbxRequirements.setOpaque(false);
        contentPanel.add(chckbxRequirements);
        chckbxRequirements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().length() != 0) {
                    searchBarTextChanged();
                }
            }
        });

        chckbxAssignee = new JCheckBox("Assignee");
        chckbxAssignee.setBounds(123, 40, 128, 23);
        chckbxAssignee.setOpaque(false);
        contentPanel.add(chckbxAssignee);
        chckbxAssignee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().length() != 0) {
                    searchBarTextChanged();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
                searchBarTextChanged();
            }
        });

        searchBar.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchBarTextChanged();

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchBarTextChanged();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchBarTextChanged();

            }
        });
    }

    private void searchBarTextChanged() {
        System.out.println("Search string: " + searchBar.getText());
        ViewEventController.getInstance().filterTasksWithParameters(searchBar.getText(),
                chckbxDescription.isSelected(), chckbxRequirements.isSelected(),
                chckbxAssignee.isSelected(), chckbxArchieved.isSelected());
        ViewEventController.getInstance().revalidateAll();

        btnCancel.setEnabled(searchBar.getText().length() != 0);
    }

    /**
     * Resets Search Fields
     */
    void resetFields() {
        searchBar.setText("");
        chckbxDescription.setSelected(false);
        chckbxRequirements.setSelected(false);
        chckbxAssignee.setSelected(false);
        chckbxArchieved.setSelected(false);
    }

    /**
     * Called when the mouse enters this toolbar group
     */
    @Override
    public void mouseEntered() {}

    /**
     * Called when the mouse exits this toolbar group
     */
    @Override
    public void mouseExited() {}
}
