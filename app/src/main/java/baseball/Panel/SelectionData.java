

package baseball.Panel;

import java.util.List;
/**
 * This class is used to store  teams, players, and metrics so can be sent to line chart .
 * 
 */
public class SelectionData {
    public List<String> selectedTeams;
    public List<String> selectedPlayers;
    public String selectedMetric;
    public String selectedStat;

      /**
     * Constructor for SelectionData.
     * 
     * @param selectedTeams represents teams.
     * @param selectedPlayers represent players.
     * @param selectedMetric baseball metric selection.
     * @param selectedStat Selected statistic type.
     */
    public SelectionData(List<String> selectedTeams,List<String> selectedPlayers,String selectedMetric,String selectedStat){
        this.selectedTeams = selectedTeams;
        this.selectedPlayers =selectedPlayers;
        this.selectedMetric =selectedMetric;
        this.selectedStat = selectedStat;
    }

     /**
     * String representation of SelectionData object.
     * 
     * @return String representation of SelectionData.
     */
    @Override
    public String toString() {
        return "SelectionData{" +
                "selectedTeams=" + selectedTeams +
                ", selectedPlayers=" + selectedPlayers +
                ", selectedMetric='" + selectedMetric + '\'' +
                ", selectedStat='" + selectedStat + '\'' +
                '}';
    }
    
}