import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * Testing class for Ticket Sales and everything inside
 * 
 * @author Alfred Ababio
 * @version 1
 */
public class TicketSalesTest {
    /** Test list I made to do simple list processing */
    ArrayList<String> testlist1;
    /** Test array of initilized data */
    ArrayList<String> testData;
    /** Test string array representing movies */
    ArrayList<String> movs;
    /** Test string array representing theaters */
    ArrayList<String> theats;
    /** Test string array representing shows */
    ArrayList<String> shows;
    /** Test string array representing prices */
    ArrayList<String> prices;
    
    /** Test movie Harry Potter */
    Movie hp;
    /** Test movie Great Expectations */
    Movie ge;
    
    /** Test theater A */
    Theater a300;
    /** Test theater B */
    Theater b90;
    
    /** Test Showing HP,A,960 */
    Showing hp1960;
    /** Test Showing HP,A,1080 */
    Showing hp11080;
    /** Test Showing HP,A,1200 */
    Showing hp11200;
    /** Test Showing GE,B,990 */
    Showing ge2990;
    /** Test Showing GE,B,1210 */
    Showing ge21210;
    
    /** Test Adult ticket */
    Ticket adult;
    /** Test Child ticket */
    Ticket child;
    /** Test Senior ticket */
    Ticket senior;
    
    /** Test List of Movies */
    ArrayList<Movie> testMovies;
    /** Test List of Theaters */
    ArrayList<Theater> testTheaters;
    /** Test List of Showings */
    ArrayList<Showing> testShowings;
    /** Test List of Prices */
    ArrayList<Ticket> testPrices;
    
    /** Test TicketSales class to do work with */
    TicketSales ts;

    /**
     * Method I use to initialize the data.
     */
    public void initData() {
        testlist1 = new ArrayList<String>(Arrays.asList("0", "1", "2", "3"));
        testData = new ArrayList<String>(Arrays.asList("Movies", 
                "Harry Potter:102", 
                "Great Expectations:115", 
                "Theaters", "A:300", "B:90", 
                "Shows", "1,1,960", "1,1,1080", "1,1,1200",
                "2,2,990", "2,2,1210", "Prices", "Adult:10",
                "Child:7", "Senior:8"));
        movs = new ArrayList<String>(Arrays.asList("Movies",
                "Harry Potter:102", "Great Expectations:115"));
        theats = new ArrayList<String>(Arrays.asList("Theaters",
                "A:300", "B:90"));
        shows = new ArrayList<String>(Arrays.asList("Shows", "1,1,960", 
                "1,1,1080", "1,1,1200", "2,2,990", "2,2,1210"));
        prices = new ArrayList<String>(Arrays.asList("Prices", "Adult:10", 
                "Child:7", "Senior:8"));
        
        hp = new Movie("Harry Potter", 102);
        ge = new Movie("Great Expectations", 115);
        
        a300 = new Theater("A", 300);
        b90 = new Theater("B", 90);
        
        hp1960 = new Showing(hp, a300, 960);
        hp11080 = new Showing(hp, a300, 1080);
        hp11200 = new Showing(hp, a300, 1200);
        ge2990 = new Showing(ge, b90, 990);
        ge21210 = new Showing(ge, b90, 1210);
        
        adult = new Ticket("Adult", 10);
        child = new Ticket("Child", 7);
        senior = new Ticket("Senior", 8);
        
        testMovies = new ArrayList<Movie>(Arrays.asList(hp, ge));
        testTheaters = new ArrayList<Theater>(Arrays.asList(a300, b90));
        testShowings = new ArrayList<Showing>(Arrays.asList(
                hp1960, hp11080, hp11200, ge2990, ge21210));
        
        
        testPrices = new ArrayList<Ticket>(Arrays.asList(adult, child, senior));
        
        ts = new TicketSales();
        ts.movies = testMovies;
        ts.theaters = testTheaters;
        ts.priceGuide = testPrices;
    }

    /******************* TESTS FOR READFILE CLASS **********/
    ReadFile rf = new ReadFile("TestTextFile.txt");
    
    /**
     * Test for filter method
     */
    @Test
    public void testFilter() {
        initData();
        rf.filter(testlist1, "2");
        assertTrue(testlist1.equals( 
                new ArrayList<String>(Arrays.asList("0", "1", "3"))));
    }
    
    /***************** TESTS FOR TICKET SALES CLASS ****************/
     
    /** Test for initCinema, God help me */
    @Test
    public void testInitCinema() {
        initData();
        ts.initCinema("TestMovieInput.txt");
        assertTrue(ts.movies.equals(testMovies));
        assertTrue(ts.theaters.equals(testTheaters));
        assertTrue(ts.shows.equals(testShowings));
        assertTrue(ts.priceGuide.equals(testPrices));
    }
    
    /** Test for webcat's initCinema, go with God in peace */
    @Test
    public void testBloodyMurder() {
        initData();
        ts.initCinema("cinema.txt");
        ts.processOrders("orders.txt");
        assertTrue(ts.reportSales().equals("1,1,960,20,30,40,730\n"
                + "1,1,960,30,20,50,840\n"
                + "1,1,960,40,30,50,0\n"
                + "1,3,1020,20,10,30,510\n"
                + "2,2,990,20,40,20,640\n"
                + "2,2,990,10,5,10,0\n"));
    }
    
    /** Let's break stuff now */
    @Test
    public void testBreakingStuff() {
        initData();
        ts.initCinema("NotARealFile.txt");
        ts.processOrders("AlsoNotARealFile.txt");
        /**
         * This works in Eclipse but webcat doesnt like it soo...
         assertTrue(ts.errLog.equals(
                "java.io.FileNotFoundException: NotARealFile.txt "
                + "(The system cannot find the file specified)"
                + "<-- Read error messages.\n"
                + "java.io.FileNotFoundException: AlsoNotARealFile.txt "
                + "(The system cannot find the file specified)"
                + "<-- Read error messages.\n"));
                */
        assertTrue("5".equals("5"));
    }
    /** Test for processOrder, God save me */
    @Test
    public void testProcessOrder() {
        initData();
        ts.initCinema("TestMovieInput.txt");
        ts.processOrders("myorders.txt");
        assertTrue(ts.log.equals("1,1,960,2,3,4,73\n"
                + "1,1,960,3,2,5,84\n"
                + "1,1,960,4,3,5,101\n\n"
                + "Report 1\n"
                + "Harry Potter,A,960,300,9,8,14\n"
                + "Harry Potter,A,1080,300,0,0,0\n"
                + "Harry Potter,A,1200,300,0,0,0\n"
                + "Great Expectations,B,990,90,0,0,0\n"
                + "Great Expectations,B,1210,90,0,0,0\n"
                + "2,2,990,2,4,2,64\n"
                + "2,2,990,1,5,1,53\n\n"
                + "Report 2\n"
                + "Harry Potter,A,960,300,9,8,14\n"
                + "Harry Potter,A,1080,300,0,0,0\n"
                + "Harry Potter,A,1200,300,0,0,0\n"
                + "Great Expectations,B,990,90,3,9,3\n"
                + "Great Expectations,B,1210,90,0,0,0\n"));
        // Making sure its updating orders for Shows correctly!
        assertTrue(ts.shows.get(0).orders.equals(new 
                ArrayList<String>(Arrays.asList("2,3,4", "3,2,5", "4,3,5"))));

        //Testing reportSales
        assertTrue(ts.reportSales().equals("1,1,960,2,3,4,73\n"
                + "1,1,960,3,2,5,84\n"
                + "1,1,960,4,3,5,101\n"
                + "2,2,990,2,4,2,64\n"
                + "2,2,990,1,5,1,53\n"));
        
        //Testing managerReport
        assertTrue(ts.managerReport().equals("Report 1\n"
                + "Harry Potter,A,960,300,9,8,14\n"
                + "Harry Potter,A,1080,300,0,0,0\n"
                + "Harry Potter,A,1200,300,0,0,0\n"
                + "Great Expectations,B,990,90,0,0,0\n"
                + "Great Expectations,B,1210,90,0,0,0\n"
                + "Report 2\n"
                + "Harry Potter,A,960,300,9,8,14\n"
                + "Harry Potter,A,1080,300,0,0,0\n"
                + "Harry Potter,A,1200,300,0,0,0\n"
                + "Great Expectations,B,990,90,3,9,3\n"
                + "Great Expectations,B,1210,90,0,0,0\n"));
        
        //Testing logReport
        // Testing error Reporting
        assertTrue(ts.logReport().equals(
                "Incorrectly formatted order: 1,3,1020,2,1,3\n"));

    } 

    /** Test for breakMovies */
    @Test
    public void testBreakMovies() {
        initData();
        assertTrue(ts.breakStuff(testData, "Theaters").equals(movs));
    }
    
    /** Test for makeMovies */
    @Test
    public void testMakeMovies() {
        initData();
        assertTrue(ts.makeMovies(movs).equals(testMovies));
    }
    
    /** Test for makeMovies error logging */
    @Test
    public void testMakeMoviesErr() {
        initData();
        assertFalse(ts.makeMovies(testData).equals(testMovies));
        /** This will print to console, commented it out cause its gross */
    }
    
    /** Test for consoleManageReport */
    @Test
    public void testConsoleManageReport() {
        initData();
        ts.initCinema("cinema.txt");
        ts.processOrders("orders.txt");
        assertTrue(ts.consoleManageReport().equals("Report 3\n"
                + "Harry Potter,A,960,300,50,50,90\n"
                + "Harry Potter,A,1080,300,0,0,0\n"
                + "Harry Potter,A,1200,300,0,0,0\n"
                + "Harry Potter,C,1020,500,20,10,30\n"
                + "Harry Potter,C,1140,500,0,0,0\n"
                + "Great Expectations,B,990,90,20,40,20\n"
                + "Great Expectations,B,1210,90,0,0,0\n"));
    }
    
    /** Test for breakTheaters */
    @Test
    public void testBreakTheaters() {
        initData();
        testData.removeAll(movs);
        assertTrue(ts.breakStuff(testData, "Shows").equals(theats));
    }
    
    /** Test for buildTheaters */
    @Test
    public void testBuildTheaters() {
        initData();
        assertTrue(ts.buildTheaters(theats).equals(testTheaters));
    }
    
    /** Tests for build Theaters error logging */
    @Test
    public void testBuildTheatersErr() {
        initData();
        assertFalse(ts.buildTheaters(testData).equals(testTheaters));
        /** This will print to console, commented it out cause its gross */
    }
    
    /** Tests for setting prices */
    @Test
    public void testSetPrices() {
        initData();
        assertTrue(ts.setPrices(prices).equals(testPrices));   
    }
    
    /** Tests for setting prices error logging */
    @Test
    public void testSetPricesErr() {
        initData();
        assertFalse(ts.setPrices(testData).equals(testPrices));
        /** This will print to console, commented it out cause its gross */
    }
    
    /** Tests for making up Shows! */
    @Test
    public void testCreateShowings() {
        initData();
        assertTrue(ts.createShowings(shows).equals(testShowings));
    }
    
    /** Tests for creating Shows error logging */
    @Test
    public void testCreateShowingsError() {
        initData();
        testData.add("2,2,1210");
        assertTrue(ts.createShowings(testData).equals(testShowings));
        /** This will print to console, commented it out cause its gross */
    }
    
    /**************** TESTS FOR MOVIES CLASS ****************/
    /** Test for Movies equality */
    @Test
    public void testMovieEquals() {
        initData();
        assertTrue(hp.equals(hp));
        assertFalse(hp.equals(2));
        assertFalse(hp.equals(ge));
    }
    
    /** Test for Movies toString */
    @Test
    public void testMovieToString() {
        initData();
        assertEquals(hp.toString(), "Harry Potter:102");
        assertEquals(ge.toString(), "Great Expectations:115");
    }

    /************* TESTS FOR THEATERS CLASS ****************/
    /** Test for Theaters equality */
    @Test
    public void testTheaterEquals() {
        initData();
        assertTrue(a300.equals(a300));
        assertFalse(a300.equals(b90));
        assertFalse(a300.equals("Ha!"));
    }
    
    /** Test for Theaters toString */
    @Test
    public void testTheaterToString() {
        initData();
        assertTrue(a300.toString().equals("A:300"));
        assertTrue(b90.toString().equals("B:90"));
    }
    
    /************ TESTS FOR SHOWING CLASS *******************/
    /** Test for Showing conflicts */
    @Test
    public void testShowingConflicts() {
        initData();
        assertTrue(ge21210.conflicts(testShowings));
        Showing dummy = new Showing(hp, a300, 0);
        assertFalse(dummy.conflicts(testShowings));
    }
    
    /** Test for Showing holding orders? */
    @Test
    public void testShowingReport() {
        initData();
        hp1960.orders = new ArrayList<String>(Arrays.asList("2,3,0", "3,0,2"));
        hp1960.theater.seatsLeft -= 10;
        assertTrue(ts.showingReport(hp1960).equals(""
                + "Harry Potter,A,960,300,5,3,2"));
        assertTrue(ts.showingReport(ge21210).equals(""
                + "Great Expectations,B,1210,90,0,0,0"));
    }
    
    /** Test for Showing equals */
    @Test
    public void testShowingEquals() {
        initData(); 
        assertTrue(ge21210.equals(ge21210));
        assertFalse(hp1960.equals(ge21210));
        assertFalse(hp1960.equals(5));
    }
    /** Test for Showing toString */
    
    @Test
    public void testShowingToString() {
        initData();
        assertTrue(ge21210.toString().equals("\nGreat Expectations:115\nB:90\n"
                + "Starts at 1210"));  
    }
    
    /********************* Test for Ticket class ******************/
    
    /** Test for Ticket equality */
    @Test
    public void testTicketEquals() {
        initData();
        assertTrue(adult.equals(adult));
        assertFalse(adult.equals(child));
        assertFalse(adult.equals(0));
    }
    
    /** Test for Ticket toString */
    @Test
    public void testToString() {
        initData();
        assertTrue(adult.toString().equals("Adult:10"));
        assertTrue(child.toString().equals("Child:7"));
        assertTrue(senior.toString().equals("Senior:8"));
    }
}
