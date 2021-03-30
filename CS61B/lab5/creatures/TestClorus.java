package creatures;
import org.junit.Test;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;
import static org.junit.Assert.*;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(1);
        assertEquals(1, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.stay();
        assertEquals(0.99, c.energy(), 0.01);
        c.stay();
        assertEquals(0.98, c.energy(), 0.01);
        c.move();
        assertEquals(0.95, c.energy(), 0.01);
        c.move();
        assertEquals(0.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        assertNotSame(c, c.replicate());
        assertEquals(1, c.energy(), 0.01);

    }

    @Test
    public void testAction() {

        HashMap<Direction, Occupant> sandbox = new HashMap<Direction, Occupant>();

        // STAY
        Clorus c = new Clorus(2);
        Plip plipVictim = new Plip(1.2);
        sandbox.put(Direction.TOP, plipVictim);
        sandbox.put(Direction.RIGHT, new Impassible());
        sandbox.put(Direction.BOTTOM, new Impassible());
        sandbox.put(Direction.LEFT, new Impassible());

        Action actual = c.chooseAction(sandbox);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        // ATTACK
        sandbox.clear();
        sandbox.put(Direction.TOP, plipVictim);
        sandbox.put(Direction.RIGHT, new Impassible());
        sandbox.put(Direction.BOTTOM, new Impassible());
        sandbox.put(Direction.LEFT, new Empty());

        actual = c.chooseAction(sandbox);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected, actual);

        // REPLICATE
        sandbox.clear();
        sandbox.put(Direction.TOP, new Impassible());
        sandbox.put(Direction.RIGHT, new Empty());
        sandbox.put(Direction.BOTTOM, new Impassible());
        sandbox.put(Direction.LEFT, new Impassible());

        actual = c.chooseAction(sandbox);
        expected = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
        assertEquals(expected, actual);

        // MOVE
        Clorus cc = new Clorus(0.5);
        sandbox.clear();
        sandbox.put(Direction.TOP, new Empty());
        sandbox.put(Direction.BOTTOM, new Impassible());
        sandbox.put(Direction.LEFT, new Impassible());
        sandbox.put(Direction.RIGHT, new Impassible());

        actual = cc.chooseAction(sandbox);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);
    }
}
