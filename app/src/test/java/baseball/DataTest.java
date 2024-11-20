package baseball;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import baseball.Data.Series;
import baseball.Panel.SelectionData;
import java.util.Arrays;

public class DataTest {

    private Series series;

    @BeforeEach
    public void setUp() {
        series = new Series();
    }
    @Test
    public void teamName() {
        String[] teams = series.generateTeamName();
        assertFalse(teams == null);
        assertTrue(teams.length > 0);
    }

    @Test
    public void linechart() {
        SelectionData data = new SelectionData(Arrays.asList("a"), Arrays.asList("b"), "ERA", "LineChart");
        XYSeriesCollection dataset = series.GetLineChartDataset(data);
        assertFalse(dataset == null);
        assertEquals(1, dataset.getSeriesCount());
        
        Object[][] data2 = series.getTableBest("2020"); 
        assertFalse(data2 == null);
        assertTrue(data2.length > 0);
        
       
        XYSeriesCollection dataset2 = series.GetLineChartDataset(data);
        dataset = series.GetDataset();
        assertFalse(dataset == null);
        if (dataset != null) {
            assertEquals(1, dataset.getSeriesCount());
        }
         }

    @Test
    public void Worst() {
        Object[][] data = series.getTableWorst("2020"); // Replace with an actual year
        assertFalse(data == null);
        assertTrue(data.length > 0);

        XYSeriesCollection dataset = series.GetDataset();
        assertEquals("Series 1", dataset.getSeries(0).getKey());
        assertEquals(5, dataset.getSeries(0).getItemCount());
    }

    @Test
    public void testGeneratePlayerName() {
        String[] players = series.generatePlayerName("FakeTeam");
        assertFalse(players.length > 0);

        players = series.generatePlayerName("San Francisco Giants");
        assertFalse(players == null);
        assertTrue(players.length > 0);
    }

}
