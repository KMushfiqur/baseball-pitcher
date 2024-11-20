/**
 * The ChartSwitcherInterface provides logic for switching between different chart panels.
 * <p>
 * </p>
 * 
 */
package baseball.ChartSwitcher;

import javax.swing.JPanel;
import java.awt.CardLayout;
import baseball.AppLogic;

public interface ChartSwitcherInterface {

    /**
     * Switches the displayed chart panel based on the selected statistic.
     * 
     * @param selectedStat the selected statistic 
     * @param appLogic the AppLogic instance 
     * @param cardsPanel the JPanel representing cards
     * @param cardLayout the CardLayout manages cardpanel
     */
    void switchCard(String selectedStat, AppLogic appLogic, JPanel cardsPanel, CardLayout cardLayout);
}
