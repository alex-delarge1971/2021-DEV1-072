package com.example.bnp.tennis.logic;

import com.example.bnp.tennis.ConfigurationTest;
import com.example.bnp.tennis.model.TennisMatch;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigurationTest.class }, loader = AnnotationConfigContextLoader.class)
public class TennisMatchServiceTest extends TestCase {

    TennisMatch testMatch = new TennisMatch("foo", "bar");

    @Autowired
    private TennisMatchService tennisMatchService;

    @Test
    public void testPlayerCanScore()
    {
        tennisMatchService.player1Scores(testMatch);
        assertEquals(testMatch.getPlayerOne().getPoint(), 1);
    }

    @Test
    public void canCreateMatch()
    {

        TennisMatch tennisMatch = tennisMatchService.createTennisMatch("foo", "bar");

        assertTrue(tennisMatch != null);
    }

    @Test
    public void testMatchWinningConditions() throws Exception
    {

        testMatch.getPlayerOne().setPoint(2);
        testMatch.getPlayerTwo().setPoint(0);

        assertEquals(tennisMatchService.isMatchFinished(testMatch), false);

        testMatch.getPlayerOne().setPoint(0);
        testMatch.getPlayerTwo().setPoint(0);

        assertEquals(tennisMatchService.isMatchFinished(testMatch), false);

        testMatch.getPlayerOne().setPoint(5);
        testMatch.getPlayerTwo().setPoint(0);

        assertTrue(tennisMatchService.isMatchFinished(testMatch));

        testMatch.getPlayerOne().setPoint(4);
        testMatch.getPlayerTwo().setPoint(3);

        assertEquals(tennisMatchService.isMatchFinished(testMatch), false);

        testMatch.getPlayerOne().setPoint(5);
        testMatch.getPlayerTwo().setPoint(3);

        assertTrue(tennisMatchService.isMatchFinished(testMatch));

        testMatch.getPlayerOne().setPoint(4);
        testMatch.getPlayerTwo().setPoint(2);

        assertTrue(tennisMatchService.isMatchFinished(testMatch));

        testMatch.getPlayerOne().setPoint(10);
        testMatch.getPlayerTwo().setPoint(9);

        assertTrue(!tennisMatchService.isMatchFinished(testMatch));

        testMatch.getPlayerOne().setPoint(11);
        testMatch.getPlayerTwo().setPoint(9);

        assertTrue(tennisMatchService.isMatchFinished(testMatch));

    }

    @Test
    public void testScorePrintingEqualityUnder3Points()
    {

        testMatch.getPlayerOne().setPoint(1);
        testMatch.getPlayerTwo().setPoint(1);

        assertNotSame("fifteen all", tennisMatchService.printScores(testMatch));
    }

    @Test
    public void testScorePrintingEqualityOver3Points()
    {
        testMatch.getPlayerOne().setPoint(5);
        testMatch.getPlayerTwo().setPoint(5);

        assertEquals("Deuce", tennisMatchService.printScores(testMatch));
    }

    @Test
    public void testScorePrintingNoPoint()
    {
        testMatch.getPlayerOne().setPoint(0);
        testMatch.getPlayerTwo().setPoint(0);

        assertEquals("love all", tennisMatchService.printScores(testMatch));
    }

    @Test
    public void testScorePrinting1to0()
    {
        testMatch.getPlayerOne().setPoint(1);
        testMatch.getPlayerTwo().setPoint(0);

        assertEquals("foo: fifteen to bar: love", tennisMatchService.printScores(testMatch));
    }

    @Test
    public void testScorePrinting3to2()
    {
        testMatch.getPlayerOne().setPoint(3);
        testMatch.getPlayerTwo().setPoint(2);

        assertEquals("foo: forty to bar: thirty", tennisMatchService.printScores(testMatch));
    }

    @Test
    public void testScorePrintingTestAdvantagePlayerOne()
    {
        testMatch.getPlayerOne().setPoint(5);
        testMatch.getPlayerTwo().setPoint(4);

        assertEquals("Advantage foo", tennisMatchService.printScores(testMatch));
    }

    @Test
    public void testScorePrintingTestAdvantagePlayerTwo()
    {
        testMatch.getPlayerOne().setPoint(4);
        testMatch.getPlayerTwo().setPoint(5);

        assertEquals("Advantage bar", tennisMatchService.printScores(testMatch));
    }

    @Test
    public void testWinnerPrinting()
    {
        testMatch.getPlayerOne().setPoint(4);
        testMatch.getPlayerTwo().setPoint(6);

        assertEquals("bar wins", tennisMatchService.printWinner(testMatch));
    }
}
