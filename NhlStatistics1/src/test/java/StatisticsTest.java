/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kape
 */
public class StatisticsTest {
    private ArrayList<Player> players;
    
    private Reader readerStub=() -> {
        players = new ArrayList<>();
        
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));
        
        return players;
    };
    
    Statistics stats;
    List<Player> playerList= readerStub.getPlayers();
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
//    //
//     @Test
//     public void hello() {}
    @Test
    public void returnPlayer() {
        Player p=playerList.get(0);
        Player s=null;
        assertEquals(p.toString(), stats.search(p.getName()).toString());
        assertEquals(s, stats.search("notinthelist"));
        
    }
    @Test
    public void teamList() {
        String teamm=playerList.get(0).getTeam();
        List<Player> lista=stats.team(teamm);
        for(Player p: lista){
            assertEquals(p.getTeam(), teamm);
        }
    }
    @Test
    public void getTopScorers() {
        List<Player> tops= stats.topScorers(3);
        Collections.sort(tops);
        Iterator<Player> playerIterator = tops.iterator();
        assertEquals(playerIterator.next().getName(),"Gretzky");
        assertEquals(playerIterator.next().getName(),"Lemieux");
        assertEquals(playerIterator.next().getName(),"Yzerman");
        
 
        
    }
}
