/**
 * handle switching to the LineChart panel.
 * <p>
 * updates line chart and displays.
 * </p>
 */
package baseball.ChartSwitcher;

import javax.swing.JPanel;
import java.awt.CardLayout;
import baseball.*;
import baseball.Chart.*;

public class LineChartSwitcher implements ChartSwitcherInterface {

    /**
     * Switches to the LineChart panel and updates its data based on the selected statistic.
     * 
     * @param selectedStat the selected statistic to determine which chart to display and update
     * @param appLogic the AppLogic instance
     * @param cardsPanel the JPanel 
     * @param cardLayout the CardLayout  manages the chart panels
     */
    @Override
    public void switchCard(String selectedStat, AppLogic appLogic, JPanel cardsPanel, CardLayout cardLayout) {
        if ("LineChart".equals(selectedStat)) {
            ((LineChart) cardsPanel.getComponent(1)).updateData(appLogic.getdata());
        }
        cardLayout.show(cardsPanel, "LineChart");
    }
}
