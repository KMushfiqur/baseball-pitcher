/**
 * <p>
 * This class is responsible for updating the Table with best or worst performance data and displaying it using CardLayout.
 * </p>
 */
package baseball.ChartSwitcher;

import javax.swing.JPanel;
import java.awt.CardLayout;
import baseball.*;
import baseball.Chart.*;

public class TableSwitcher implements ChartSwitcherInterface {
    
    /**
     * Switches to the Table panel and updates its data based on the selected statistic.
     * 
    * @param selectedStat the selected statistic 
     * @param appLogic the AppLogic instance 
     * @param cardsPanel the JPanel representing cards
     * @param cardLayout the CardLayout manages cardpanel
     */
    @Override
    public void switchCard(String selectedStat, AppLogic appLogic, JPanel cardsPanel, CardLayout cardLayout) {
        if ("Table".equals(selectedStat)) {
            if ("Best Players".equals(appLogic.getdata2()[0])) {
                ((Table) cardsPanel.getComponent(2)).updateTableBest(appLogic.getdata2()[1]);
            } else {
                ((Table) cardsPanel.getComponent(2)).updateTableWorst(appLogic.getdata2()[1]);
            }
        }
        cardLayout.show(cardsPanel, "Table");
    }
}
