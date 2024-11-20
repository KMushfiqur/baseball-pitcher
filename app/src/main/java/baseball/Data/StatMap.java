package baseball.Data;
import java.util.Map;
import java.util.HashMap;

/**
 * to help CSV handler find indexes fo selected data uses the built in hashMap.
 */
public class StatMap {
    private final Map<String, Integer> csvHeaderMap;
 /**
     * Constructor which also populates using the populateStatIndexMap function
     */
    public StatMap() {
        this.csvHeaderMap = new HashMap<>();
        populateStatIndexMap();
    }
    /**
     * Populate the CSV header map with header names and their indices.
     */
    private void populateStatIndexMap() {
        csvHeaderMap.put("Player", 0);
        csvHeaderMap.put("IP", 1);
        csvHeaderMap.put("O", 2);
        csvHeaderMap.put("H", 3);
        csvHeaderMap.put("R", 4);
        csvHeaderMap.put("ER", 5);
        csvHeaderMap.put("BB", 6);
        csvHeaderMap.put("HBP", 7);
        csvHeaderMap.put("SO", 8);
        csvHeaderMap.put("HR", 9);
        csvHeaderMap.put("ERA", 10);
        csvHeaderMap.put("BF", 11);
        csvHeaderMap.put("Pit", 12);
        csvHeaderMap.put("Str", 13);
        csvHeaderMap.put("Ctct", 14);
        csvHeaderMap.put("StS", 15);
        csvHeaderMap.put("StL", 16);
        csvHeaderMap.put("GB", 17);
        csvHeaderMap.put("FB", 18);
        csvHeaderMap.put("LD", 19);
        csvHeaderMap.put("Unk", 20);
        csvHeaderMap.put("GSc", 21);
        csvHeaderMap.put("IR", 22);
        csvHeaderMap.put("IS", 23);
        csvHeaderMap.put("WPA", 24);
        csvHeaderMap.put("aLI", 25);
        csvHeaderMap.put("RE24", 26);
        csvHeaderMap.put("W", 27);
        csvHeaderMap.put("L", 28);
        csvHeaderMap.put("CG", 29);
        csvHeaderMap.put("BS", 30);
        csvHeaderMap.put("SV", 31);
        csvHeaderMap.put("Team", 32);
        csvHeaderMap.put("Team.Abbrev", 33);
        csvHeaderMap.put("Opponent", 34);
        csvHeaderMap.put("Opponent.Abbrev", 35);
        csvHeaderMap.put("Team.R", 36);
        csvHeaderMap.put("Team.H", 37);
        csvHeaderMap.put("Team.E", 38);
        csvHeaderMap.put("Opponent.R", 39);
        csvHeaderMap.put("Opponent.H", 40);
        csvHeaderMap.put("Opponent.E", 41);
        csvHeaderMap.put("Innings", 42);
        csvHeaderMap.put("Game.ID", 43);
        csvHeaderMap.put("X1B.Ump", 44);
        csvHeaderMap.put("X2B.Ump", 45);
        csvHeaderMap.put("X3B.Ump", 46);
        csvHeaderMap.put("HP.Ump", 47);
        csvHeaderMap.put("Date", 48);
        csvHeaderMap.put("Game.Time", 49);
        csvHeaderMap.put("H.A", 50);
        csvHeaderMap.put("Precipitation", 51);
        csvHeaderMap.put("Sky", 52);
        csvHeaderMap.put("Stadium", 53);
        csvHeaderMap.put("Temperature", 54);
        csvHeaderMap.put("Weather", 55);
        csvHeaderMap.put("Wind.Direction", 56);
        csvHeaderMap.put("Wind.Speed", 57);
        csvHeaderMap.put("Pitching.Total.FDP", 58);
        csvHeaderMap.put("Pitching.Total.DKP", 59);
        csvHeaderMap.put("Pitching.Total.YHP", 60);
        csvHeaderMap.put("Starter", 61);
        csvHeaderMap.put("Double.Header", 62);
        csvHeaderMap.put("Season", 63);
        csvHeaderMap.put("Starting.Pitcher", 64);
        csvHeaderMap.put("Over.Under", 65);
        csvHeaderMap.put("Moneyline", 66);
        csvHeaderMap.put("Wagers", 67);
    }
    /**
     * get index for the statistic selected 
     * 
     * @param statName input.
     * @return int index. 
     */
    public int getStatIndex(String statName) {
        return csvHeaderMap.get(statName); 
    }
}

