package ui;

import model.HealthJournal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// EFFECTS: creates a new panel that shows past entries and allows user to select entry to view
public class ViewPastEntries extends JFrame implements ActionListener {
    private final HealthJournal healthJournal;
    private final List<Integer> journalIDs;
    private JTextField entrySelection;

    // MODIFIES: this
    // EFFECTS: constructs a panel that displays the IDs of existing entries in the journal
    public ViewPastEntries(HealthJournal healthJournal) {
        this.healthJournal = healthJournal;
        this.journalIDs = healthJournal.getJournalEntryIDs();

        this.setTitle("My Health Journal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));

        JPanel panel = (JPanel) getContentPane();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new FlowLayout());

        JScrollPane scrollPane = createScrollPane();
        add(scrollPane);
        addViewEntryButtons();

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    // EFFECTS: creates the scroll pane that displays the IDs of the entries
    public JScrollPane createScrollPane() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Date of Entry");

        for (int id : journalIDs) {
            Object[] rowData = {id};
            tableModel.addRow(rowData);
        }

        JTable entryTable = new JTable(tableModel);
        return new JScrollPane(entryTable);
    }

    // EFFECTS: creates the panel buttons and labels and adds them to the panel
    public void addViewEntryButtons() {
        JLabel message = new JLabel("Enter the date of the entry you'd like to view:");
        JButton viewEntry = new JButton("View Entry");
        viewEntry.addActionListener(this);
        viewEntry.setBackground(Color.GREEN);
        entrySelection = new JTextField(10);
        JButton back = new JButton("Back");
        back.addActionListener(this);
        back.setBackground(Color.PINK);

        add(message);
        add(entrySelection);
        add(viewEntry);
        add(back);
    }

    // REQUIRES: button click
    // EFFECTS: processes the user's button click
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Entry")) {
            int entrySelectionInt = Integer.parseInt(entrySelection.getText());
            SelectedEntry selectedEntry = new SelectedEntry(healthJournal, entrySelectionInt);
            this.setVisible(false);
        }

        if (e.getActionCommand().equals(("Back"))) {
            MenuPanel menuPanel = new MenuPanel(healthJournal);
            this.setVisible(false);
        }
    }
}
