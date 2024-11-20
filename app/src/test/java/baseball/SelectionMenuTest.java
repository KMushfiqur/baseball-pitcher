package baseball;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import baseball.Panel.SelectionMenu;
import baseball.Panel.SelectionData;
import baseball.Chart.LineChart;
import baseball.Chart.Table;


public class SelectionMenuTest {

    private SelectionMenu selectionMenu;
    private AppLogic mockAppLogic;
    private LineChart mockLineChart;
    private Table mockTable;

    @BeforeEach
    public void setUp() {
        mockAppLogic = mock(AppLogic.class);
        mockTable = mock(Table.class);
        selectionMenu = new SelectionMenu(mockAppLogic, mockTable);
    }


    @Test
    public void testSubmitButtonActionPerformedForLineChart() {
        JPanel selectionPanel = selectionMenu.setupSelectionPanel();

        JComboBox<String> statisticComboBox = findComboBoxWithName(selectionPanel, "Select Type of Statistic: ");
        assertNotNull(statisticComboBox, "Statistic-jbox box was not found");
        statisticComboBox.setSelectedItem("Table");

        JComboBox<String> metricComboBox = findComboBoxWithName(selectionPanel, "Select Baseball Metric: ");
        assertNotNull(metricComboBox, "Metric-box not found");

        JPanel teamPlayerPanel = findPanelWithName(selectionPanel, "teamPlayerPanel");
        assertNotNull(teamPlayerPanel, " panel not found");

        JScrollPane scrollpanel = findScrollPanel(teamPlayerPanel,0);
        assertNotNull(scrollpanel, "jscrollpanel not found");
        JScrollPane scrollpanel2 = findScrollPanel(teamPlayerPanel,1);
         assertNotNull(scrollpanel2, "jscrollpanel not found");

        JList<String> teamList = findListInPanel(scrollpanel, 0);
        assertNotNull(teamList, "Team -list not found error");

        JList<String> playerList = findListInPanel(scrollpanel2, 0);
        assertNotNull(playerList, "player -list not found error");

        statisticComboBox.setSelectedItem("LineChart");
        metricComboBox.setSelectedItem("ERA");
        teamList.setSelectedIndex(4);
        playerList.setSelectedIndex(3);

        ActionEvent mockEvent = mock(ActionEvent.class);

        JButton submitButton = findButtonWithName(selectionPanel, "Submit");
        assertNotNull(submitButton, "Submit button not found");
        submitButton.getActionListeners()[0].actionPerformed(mockEvent);

        // Verify that the correct method in AppLogic was called
        verify(mockAppLogic, times(1)).setdata(any(SelectionData.class));
        verify(mockAppLogic, times(1)).switchCardBasedOnStatistic("LineChart");
    }

   

    // Helper methods to find components in the panel
    private JButton findButtonWithName(JPanel panel, String name) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals(name)) {
                return (JButton) component;
            }
        }
        return null;
    }

    private JComboBox<String> findComboBoxWithName(JPanel panel, String labelName) {
        Component[] components = panel.getComponents();
        
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            
            if (component instanceof JLabel && ((JLabel) component).getText().equals(labelName)) {
                if (i + 1 < components.length && components[i + 1] instanceof JComboBox) {
                    return (JComboBox<String>) components[i + 1];
                }
            }
        }
        
        return null;
    }
    

    private JPanel findPanelWithName(JPanel panel, String name) {
        return Arrays.stream(panel.getComponents())
                .filter(component -> component instanceof JPanel && component.getName().equals(name))
                .map(component -> (JPanel) component)
                .findFirst()
                .orElse(null);
    }

    private JScrollPane findScrollPanel(JPanel panel, int index) {
        int count = 0;
        for (Component component : panel.getComponents()) {
            if (component instanceof JScrollPane && count == index) {
                JScrollPane scrollPanel = (JScrollPane) component;
                return scrollPanel;
                }
                count++;
            }
            return null;
        }

    private JList<String> findListInPanel(JScrollPane scrollpanel, int index) {
        int count = 0;
                Component view = scrollpanel.getViewport().getView();
                if (view instanceof JList) {
                    if (count == index) {
                        System.out.println("Found JList<String> inside JScrollPane at index " + index);
                        return (JList<String>) view;
                    }
                    count++;
                }
        System.out.println("JList<String> not found at index " + index);
        return null;
    }
    
}
