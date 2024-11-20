
package baseball.Panel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import baseball.AppLogic;
import baseball.Data.Series;
import baseball.Chart.*;

/**
 * The SelectionMenu class provides a menu for selecting which baseball statistics to display
 * This class also sends data back to applogic to generate chart.
 */
public class SelectionMenu {
    private JPanel selectionPanel;
    private JButton submitButton;

    private AppLogic appLogic;
    private Table table1;

      /**
     * Constructor to send over instances to carry out operations
     * 
     * @param appLogic the applogic instqance .
     * @param table1 The table instance.
     */
    public SelectionMenu(AppLogic appLogic,Table table1) {
        this.appLogic = appLogic;
        this.table1 = table1;
    }
    public JPanel setupSelectionPanel() {
        selectionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String[] statisticTypes = {"Select", "LineChart", "Table"};
        JComboBox<String> statisticComboBox = new JComboBox<>(statisticTypes);
        
        // statisticComboBox
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 0;  // Row 0
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left
        selectionPanel.add(new JLabel("Select Type of Statistic: "), gbc);
        gbc.gridx = 1;  // Column 1
        gbc.gridy = 0;  // Row 0
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        selectionPanel.add(statisticComboBox, gbc);
        
        // baseball metric jbox 
        String[] baseballMetrics = {"ERA", "IP", "SO", "BB","Team.R","Opponent.R","Team.h"};
        JComboBox<String> metricComboBox = new JComboBox<>(baseballMetrics);
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 1;  // Row 1
        gbc.gridwidth = 2;  // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        JLabel label1 = new JLabel("Select Baseball Metric: ");
        selectionPanel.add(label1, gbc);
        gbc.gridx = 1;  // Column 1
        gbc.gridy = 1;  // Row 1
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        selectionPanel.add(metricComboBox, gbc);
    
        // Panel to hold team and player selection components
        JPanel teamPlayerPanel = new JPanel();
        teamPlayerPanel.setLayout(new GridLayout(0, 2, 10, 10));  // 0 rows to allow adding dynamically
        teamPlayerPanel.setName("teamPlayerPanel");


        // Add components to select teams and players
        for (int i = 0; i < 4; i++) {
            JList<String> teamList = new JList<>(Series.generateTeamName());
            teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            teamList.setVisibleRowCount(3);
            JScrollPane teamScrollPane = new JScrollPane(teamList);

            JList<String> playerList = new JList<>();
            playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            playerList.setVisibleRowCount(3);
            JScrollPane playerScrollPane = new JScrollPane(playerList);
            
            teamPlayerPanel.add(teamScrollPane);
            teamPlayerPanel.add(playerScrollPane);
            
            teamList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        String selectedTeam = teamList.getSelectedValue();
                        if (selectedTeam != null && !selectedTeam.isEmpty()) {
                            String[] players = Series.generatePlayerName(selectedTeam);
                            playerList.setListData(players);
                        }
                    }
                }
            });
        }
        // Initially hide team and player selection
        teamPlayerPanel.setVisible(false);
        
        // Add teamPlayerPanel to selectionPanel
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 2;  // Row 2
        gbc.gridwidth = 2;  // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        selectionPanel.add(teamPlayerPanel, gbc);

        // ComboBox for baseball metrics
        String[] tableoption = {"Best Players","Worst Players"};
        JComboBox<String> tableComboBox = new JComboBox<>(tableoption);
        
        // Position metricComboBox
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 1;  // Row 1
        gbc.gridwidth = 2;  // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        JLabel label2 = new JLabel("Select: ");
        selectionPanel.add(label2, gbc);
        
        gbc.gridx = 1;  // Column 1
        gbc.gridy = 1;  // Row 1
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        selectionPanel.add(tableComboBox, gbc);


        // ComboBox for baseball metrics
        String[] yearoption = {"2017","2018","2019","2020"};
        JComboBox<String> tableyearComboBox = new JComboBox<>(yearoption);
        
        // Position metricComboBox
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 2;  // Row 1
        gbc.gridwidth = 2;  // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        JLabel label3 = new JLabel("Select Year: ");
        selectionPanel.add(label3, gbc);
        
        gbc.gridx = 1;  // Column 1
        gbc.gridy = 2;  // Row 1
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        selectionPanel.add(tableyearComboBox, gbc);
    
        // Submit Button
        submitButton = new JButton("Submit");
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 4;  // Row 3
        selectionPanel.add(submitButton, gbc);

        //Submit button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedTeams = new ArrayList<>();
                List<String> selectedPlayers = new ArrayList<>();
                
                String selectedMetric = (String) metricComboBox.getSelectedItem();
                String selectedStat = (String) statisticComboBox.getSelectedItem();
        
                Component[] components = teamPlayerPanel.getComponents();
                for (int i = 0; i < components.length; i += 2) {
                    JScrollPane teamScrollPane = (JScrollPane) components[i];
                    JList<String> teamList = (JList<String>) teamScrollPane.getViewport().getView();
                    selectedTeams.add(teamList.getSelectedValue());
        
                    JScrollPane playerScrollPane = (JScrollPane) components[i + 1];
                    JList<String> playerList = (JList<String>) playerScrollPane.getViewport().getView();
                    selectedPlayers.add(playerList.getSelectedValue());
                }
                    
                    SelectionData data = new SelectionData(selectedTeams,selectedPlayers,selectedMetric,selectedStat);
              

                if(selectedStat.equals("Table")){
                    String selectedTable = (String) tableComboBox.getSelectedItem();
                    String data2[] = {selectedTable,(String)tableyearComboBox.getSelectedItem()};
                    appLogic.setdata2(data2);
                }

                if(selectedStat.equals("LineChart")){
                    appLogic.setdata(data);
                }

                appLogic.switchCardBasedOnStatistic(selectedStat);                
            }
        });
        
        
        // Add ItemListener to statisticComboBox
        statisticComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedStat = (String) e.getItem();
                teamPlayerPanel.setVisible("LineChart".equals(selectedStat));
                metricComboBox.setVisible("LineChart".equals(selectedStat));
                label1.setVisible("LineChart".equals(selectedStat));
                tableComboBox.setVisible("Table".equals(selectedStat));
                label3.setVisible("Table".equals(selectedStat));
                tableyearComboBox.setVisible("Table".equals(selectedStat));
            }
        }
        );
    
        return selectionPanel;
    }
}    