package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class Clorus extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    public void move() {
        energy = energy - 0.03;
    }

    public void stay() {
        energy = energy - 0.01;
    }

    public Creature replicate() {
        double ener = energy / 2;
        energy = ener;
        return new Clorus(ener);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        for (Direction i : neighbors.keySet()) {
            if (neighbors.get(i).name().equals("empty")) {
                emptyNeighbors.addFirst(i);
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Direction i : neighbors.keySet()) {
            if (neighbors.get(i).name().equals("plip")) {
                plipNeighbors.addFirst(i);
            }
        }

        if (plipNeighbors.size() > 0) {
            return new Action(Action.ActionType.ATTACK,
                    HugLifeUtils.randomEntry(plipNeighbors));
        }

        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE,
                    HugLifeUtils.randomEntry(emptyNeighbors));
        }

        return new Action(Action.ActionType.MOVE,
                HugLifeUtils.randomEntry(emptyNeighbors));
    }
}
