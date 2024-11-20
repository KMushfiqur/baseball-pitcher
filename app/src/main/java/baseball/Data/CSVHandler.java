package baseball.Data;
import java.io.*;
import java.util.*;
import org.jfree.data.xy.*;
import baseball.Panel.SelectionData;
/**
 * reads  from csv and returns different data set 
 */
public class CSVHandler {
    
    private static CSVHandler instance;
    
    public  CSVHandler() {
    }
    /**
 * makes csv singleton
 * 
 * @return the CSVHandler instance
 */
    public static CSVHandler getInstance() {
        if (instance == null) {
            instance = new CSVHandler();
        }
        return instance;
    }
/**
 * reads team from csv
 * 
 * @return a set of team names
 */
    public Set<String> readTeamsFromCSV() {
        Set<String> teamNames = new HashSet<>();
        String filepath[] = {"pitching_2017.csv", "pitching_2018.csv", "pitching_2019.csv", "pitching_2020.csv"};
    
        for (String file : filepath) {
            try (InputStream is = getClass().getResourceAsStream("/" + file);
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
    
                String line;
                // Skip header
                br.readLine();
    
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    // Assuming the team name is the 33rd column (index 32)
                    String teamName = values[32].trim();
                    if (!teamName.isEmpty()) {
                        teamNames.add(teamName);
                    }
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        return teamNames;
    }
    /**
 * reads player from csv given a team used to display in selection card panel
 * 
 *  @param PlayerTeam the team name to filter players
 * @return a set of player names for the specified team
 */

    public Set<String> readPlayersFromCSV(String PlayerTeam) {
        Set<String> playerNames = new HashSet<>();
        String filepath[] = {"pitching_2017.csv", "pitching_2018.csv", "pitching_2019.csv", "pitching_2020.csv"};
    
        for (String file : filepath) {
            try (InputStream is = getClass().getResourceAsStream("/" + file);
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
    
                String line;
                // Skip header
                br.readLine();
    
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    String playerName = values[0].trim();
                    String playerofteam = values[32].trim();
    
                    if (!playerName.isEmpty() && PlayerTeam.equals(playerofteam)) {
                        playerNames.add(playerName);
                    }
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        return playerNames;
    }
    
  /**
     * Reads pitching statistics from CSV files and creates a dataset for a line chart.
     *
     * @param Data the selection data containing player and team information
     * @return an XYSeriesCollection dataset for the line chart
     */
    public XYSeriesCollection lineChartFromCSV( SelectionData Data) {
        XYSeriesCollection dataset = new XYSeriesCollection();
    
        if (Data != null && Data.selectedPlayers != null) {
            String filepath[] = {"pitching_2017.csv","pitching_2018.csv","pitching_2019.csv","pitching_2020.csv"};
            String year[] = {"2017", "2018", "2019", "2020"};
            int i = 0;
            int j = 0;
            StatMap statMap = new StatMap();
            int index = statMap.getStatIndex(Data.selectedMetric);
            while (j < Data.selectedPlayers.size()) {

                if(Data.selectedPlayers.get(j) == null){
                    j++;
                    break;
                }
                XYSeries series = new XYSeries(Data.selectedPlayers.get(j));

                while (i < filepath.length) {
                    try (InputStream is = getClass().getResourceAsStream("/" + filepath[i]);
                         BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
    
                        String line;
                        // Skip header
                        br.readLine();
    
                        
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split(",");
                            String playerName = values[0].trim();
                            String teamName = values[32].trim();
                            String Yvalue = values[index].trim();
                            if (Yvalue.equals("NA")) {
                                Yvalue = "0";
                            }
                            if (!playerName.isEmpty()) {
                                if (playerName.equals(Data.selectedPlayers.get(j)) && teamName.equals(Data.selectedTeams.get(j))) {
                                    series.add(Integer.parseInt(year[i]), Double.parseDouble(Yvalue));
                                    break;
                                }
                            }
                        }
    
                        i++;  
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                dataset.addSeries(series);
                i = 0;  
                j++;   
            }
        }
        return dataset;
    }

   
/**
     * generates the table for showing worst players in a given year.
     *
     * @param year the year 
     * @return a 2D array  worst-performing player data
     */
    public Object[][] WorstTable(String year) {
        Map<String, Double> playerGameScores = new HashMap<>();
        Map<String, Double> playerInnings = new HashMap<>();
        readDataFromFile(year, playerGameScores, playerInnings);
        
        List<Map.Entry<String, Double>> sortedPlayers = new ArrayList<>(playerGameScores.entrySet());

        sortedPlayers.sort(Map.Entry.<String, Double>comparingByValue());
        return getPlayerData(sortedPlayers, playerInnings);
    }

/**
     * generates the table for showing best players in a given year.
     *
     * @param year the year 
     * @return a 2D array  best-performing player data
     */
    
    public Object[][] BestTable(String year) {
        Map<String, Double> playerGameScores = new HashMap<>();
        Map<String, Double> playerInnings = new HashMap<>();
        readDataFromFile(year, playerGameScores, playerInnings);
    
        List<Map.Entry<String, Double>> sortedPlayers = new ArrayList<>(playerGameScores.entrySet());
        sortedPlayers.sort(Map.Entry.<String, Double>comparingByValue().reversed());
    
        return getPlayerData(sortedPlayers, playerInnings);
    }


    private void readDataFromFile(String year, Map<String, Double> playerGameScores, Map<String, Double> playerInnings) {
        String filepath = "pitching_" + year + ".csv";
        
        try (InputStream is = getClass().getResourceAsStream("/" + filepath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            
            br.readLine(); // Skip header
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                String playerName = values[0].trim();
                String teamName = values[32].trim();
                
                double gameScore = "NA".equalsIgnoreCase(values[21].trim()) ? 0 : Double.parseDouble(values[21].trim());
                playerGameScores.put(playerName + "_" + teamName, gameScore);
                
                if (playerInnings != null) {
                    double innings = Double.parseDouble(values[1].trim());
                    playerInnings.put(playerName + "_" + teamName, innings);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private Object[][] getPlayerData(List<Map.Entry<String, Double>> sortedPlayers, Map<String, Double> playerInnings) {
        List<Object[]> playerDataList = new ArrayList<>();
        int count = 0;
        
        for (Map.Entry<String, Double> entry : sortedPlayers) {
            if (count >= 20) {
                break;
            }
            
            String[] keyParts = entry.getKey().split("_");
            String playerName = keyParts[0];
            String teamName = keyParts[1];
            double gameScore = entry.getValue();
            double innings = (playerInnings != null) ? playerInnings.get(entry.getKey()) : 0.0; // Default to 0.0 if playerInnings is null
            
            playerDataList.add(new Object[]{teamName, playerName, gameScore, innings});
            count++;
        }
        
        return playerDataList.toArray(new Object[0][4]);
    }
}    