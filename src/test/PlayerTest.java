package test;


import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Player 1", 1000);
    }

    @Test
    public void testGetName() {
        assertEquals("Player 1", player.getName());
    }

    @Test
    public void testGetChips() {
        assertEquals(1000, player.getChips());
    }

    @Test
    public void testBetValidAmount() {
        player.bet(500);
        assertEquals(500, player.getChips());
    }

    @Test
    public void testBetInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () -> player.bet(1500));
    }

    @Test
    public void testIncrementChips() {
        player.incrementChips(200);
        assertEquals(1200, player.getChips());
    }

    @Test
    public void testTakeActionCall() {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int currentBetAmount = 100;
        int action = player.takeAction(currentBetAmount, false);
        assertEquals(currentBetAmount, action);
    }

    @Test
    public void testTakeActionRaise() {
        String input = "2\n300\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int currentBetAmount = 100;
        int action = player.takeAction(currentBetAmount, false);
        assertEquals(currentBetAmount + 300, action);
    }

    @Test
    public void testTakeActionFold() {
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int currentBetAmount = 100;
        int action = player.takeAction(currentBetAmount, false);
        assertEquals(0, action);
    }
}
