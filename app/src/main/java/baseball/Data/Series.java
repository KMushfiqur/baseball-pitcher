package baseball.Data;

import baseball.Panel.*;
import org.jfree.data.xy.*;
import java.util.Set;


/**
 * Uses the CSV handler to generate data 
 * My attempt of making a Facade Data pattern.
 */
public class Series {

    /**
     * Get a sample dataset for testing.
     * 
     * @return The  dataset type XYSeriesCollection.
     */
    public XYSeriesCollection GetDataset() {
        XYSeries series1 = createSeries("Series 1", new double[]{1, 2, 3, 4, 5}, new double[]{2, 2, 2, 2, 2});

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    /**
     * THis table initializes a table with dummy values .
     * 
     * @param name     series name usually a players.
     * @param xValues  The x-axis values.
     * @param yValues  The y-axis values.
     * @return a series of yupe xyseries.
     */
    public XYSeries createSeries(String name, double[] xValues, double[] yValues) {
        XYSeries series = new XYSeries(name);
        for (int i = 0; i < xValues.length; i++) {
            series.add(xValues[i], yValues[i]);
        }
        return series;
    }

    /**
     * Generate an array of player names for a given team.
     * 
     * @param team The team name.
     * @return An array of player names.
     */
    public static String[] generatePlayerName(String team) {
        CSVHandler csvHandler = CSVHandler.getInstance();
        Set<String> PlayersNamesList = csvHandler.readPlayersFromCSV(team);
        return PlayersNamesList.toArray(new String[0]);
    }

    /**
     * ] array of team names.
     * 
     * @return An array of team names.
     */
    public static String[] generateTeamName() {
        CSVHandler csvHandler = CSVHandler.getInstance();
        Set<String> teamNamesList = csvHandler.readTeamsFromCSV();
        return teamNamesList.toArray(new String[0]);
    }

    /**
     * sends the linechart dataset after adding series 
     * 
     * @param Data The selection data.
     * @return dataset of type XYSeriesCollection
     * */
    public XYSeriesCollection GetLineChartDataset(SelectionData Data) {
        CSVHandler csvHandler = CSVHandler.getInstance();
        XYSeriesCollection dataset = csvHandler.lineChartFromCSV(Data);
        return dataset;
    }

    /**
     * returns a table displaying the best players with theirs stats for a given year
     * 
     * @param year The year.
     * @return The table data as a 2D Object array.
     */
    public Object[][] getTableBest(String year) {
        CSVHandler csvHandler = CSVHandler.getInstance();
        Object[][] data = csvHandler.BestTable(year);
        return data;
    }

    /**
     * returns a table displaying the worst players with theirs stats for a given year
     * 
     * 
     * @param year The year.
     * @return The table data as a 2D Object array.
     */
    public Object[][] getTableWorst(String year) {
        CSVHandler csvHandler = CSVHandler.getInstance();
        Object[][] data = csvHandler.WorstTable(year);
        return data;
    }
}
