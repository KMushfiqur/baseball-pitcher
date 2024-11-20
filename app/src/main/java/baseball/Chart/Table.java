

package baseball.Chart;



import baseball.Data.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * class is a JPanel which contains a JTable to display baseball data.
 * <p>
 * The table displays columns -- Team, Player, Game Score, and Innings.
 * </p>
 */
public class Table extends JPanel {

    public JTable table;
    private JButton menuButton;

    public Table() {
        initUI();
    }
 /**
     * Initializes the UI components including the JTable and "Menu" button.
     */
    private void initUI() {
        menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> switchToSelectionMenu());

       table = createTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(560, 320));

        setLayout(new BorderLayout());
        add(menuButton, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

/**
     * Creates a JTable with initial data.
     * 
     * @return the created JTable
     */
    private JTable createTable() {
        String[] columnNames = {"X", "Y"};
        Object[][] data = {
                {1, 2},
                {2, 2},
                {3, 2},
                {4, 2},
                {5, 2}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        return new JTable(model);
    }

    /**
     * Modifies the initialized table to show the best pitchers and their data
     * 
     * @param year year 
     */
    public void updateTableBest(String year) {
        Series provider = new Series();
        Object[][] result = provider.getTableBest(year);
    
        String[] columns = {"Team", "Player","Game Score", "Innings"};
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        model.setColumnIdentifiers(columns);
        model.setRowCount(0);
    
        // Add new rows
        for (Object[] row : result) {
            model.addRow(row);
        }
    
        // Refresh the table UI
        model.fireTableDataChanged();
    }

    /**
     * table modified to show worst performing pitchers
     * 
     * @param year the year 
     */
    public void updateTableWorst(String year) {
        Series provider = new Series();
        Object[][] result = provider.getTableWorst(year);

        String[] columns = {"Team", "Player", "Game Score","Innings"};
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.setColumnIdentifiers(columns);        
        model.setRowCount(0);

        // Add new rows
        for (Object[] row : result) {
            model.addRow(row);
        }

        // Refresh the table UI
        model.fireTableDataChanged();
    }
   /**
     * Switches to the selection menu using CardLayout.
     */
    private void switchToSelectionMenu() {
        ((CardLayout) getParent().getLayout()).show(getParent(), "SelectionMenu");
    }
}