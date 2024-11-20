

package baseball;

import javax.swing.*;

import baseball.Chart.*;
import baseball.Data.*;
import baseball.Panel.*;
import baseball.ChartSwitcher.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
/**
 * The AppLogic class manages the main application logic and UI components.
 * It sets up the JFrame and CardLayout for switching between different panels.
 * <p>
 */
public class AppLogic {

    private JFrame frame;
    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private SelectionData data;

    private String data2[];

    private LineChart lineChart = new LineChart();
    private Table table1 = new Table();

    private final Map<String, ChartSwitcherInterface> switcherMap = new HashMap<>();
/**
     * Constructor for AppLogic to initialize the ChartSwitcherInterface instances.
     */
    public AppLogic() {
        switcherMap.put("LineChart", new LineChartSwitcher());
        switcherMap.put("Table", new TableSwitcher());
    }
     /**
     * Creates and shows the main GUI with a JFrame and CardLayout.
     */

    public void createAndShowGUI() {
        frame = new JFrame("Basesball pitcher data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        SelectionMenu selectionMenu = new SelectionMenu(this, table1);

        cardsPanel.add(selectionMenu.setupSelectionPanel(), "SelectionMenu");
        cardsPanel.add(lineChart, "LineChart");
        cardsPanel.add(table1, "Table");

        frame.add(cardsPanel);
        frame.setVisible(true);
    }

     /**
     * responsile for displaying the menu line chart and switching between this cards.
     * 
     * @param selectedStat selected statistics
     */
    public void switchCardBasedOnStatistic(String selectedStat) {
        if (cardLayout == null) {
            System.err.println("CardLayout is not initialized!");
            return;
        }

        ChartSwitcherInterface switcher = switcherMap.get(selectedStat);
        if (switcher != null) {
            switcher.switchCard(selectedStat, this, cardsPanel, cardLayout);
        }
    }
    
 /**
     * Sets the SelectionData object for LineChart.
     * 
     * @param data the SelectionData object to set
     */
    public void setdata(SelectionData data) {
        this.data = data;
    }

    /**
     * Sets the data array for Table.
     * 
     * @param data the data array to set
     */
    public void setdata2(String[] data) {
        this.data2 = data;
    }

    /**
     * Gets the data array for Table.
     * 
     * @return the data array
     */
    public String[] getdata2() {
        return data2;
    }

    /**
     * Gets the SelectionData object for LineChart.
     * 
     * @return the SelectionData object
     */
    public SelectionData getdata() {
        return data;
    }
}