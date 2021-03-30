package bearmaps.hw4;
import edu.princeton.cs.algs4.Stopwatch;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private HashMap<Vertex, Double> dis = new HashMap<>();
    private HashMap<Vertex, Vertex> prev = new HashMap<>();
    private ExtrinsicMinPQ<Vertex> pq = new DoubleMapPQ<Vertex>();
    private List<Vertex> solution = new ArrayList<>();
    private Vertex current;
    private SolverOutcome outcome;
    double totalWeight = 0;
    double timeElapsed = 0;
    int numExplored = 0;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {

        Stopwatch sw = new Stopwatch();
        current = start;
        dis.put(start, (double) 0);

        while (sw.elapsedTime() <= timeout) {
            List<WeightedEdge<Vertex>> neighbors = input.neighbors(current);
            for (WeightedEdge<Vertex> neighbor : neighbors) {

                Vertex p = neighbor.from();
                Vertex q = neighbor.to();
                double weight = neighbor.weight();

                if (!dis.containsKey(q)) {
                    dis.put(q, dis.get(p) + weight);
                    prev.put(q, p);
                } else if (dis.get(p) + weight < dis.get(q)) {
                    dis.remove(q);
                    dis.put(q, dis.get(p) + weight);
                    prev.put(q, p);
                } else {
                    continue;
                }

                if (pq.contains(q)) {
                    pq.changePriority(q, dis.get(q) + input.estimatedDistanceToGoal(q, end));
                } else {
                    pq.add(q, dis.get(q) + input.estimatedDistanceToGoal(q, end));
                }

            }

            if (current.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                timeElapsed = sw.elapsedTime();
                List<Vertex> helper = new ArrayList<>();
                while (!current.equals(start)) {
                    helper.add(current);
                    current = prev.get(current);
                }
                solution.add(start);
                for (int i = helper.size() - 1; i >= 0; i--) {
                    solution.add(helper.get(i));
                }
                totalWeight = dis.get(end);
                return;
            }

            if (pq.size() > 0) {
                current = pq.removeSmallest();
                numExplored += 1;
            } else {
                outcome = SolverOutcome.UNSOLVABLE;
                timeElapsed = sw.elapsedTime();
                return;
            }
        }
        timeElapsed = sw.elapsedTime();
        outcome = SolverOutcome.TIMEOUT;
    }


    public SolverOutcome outcome() {
        return outcome;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return totalWeight;
    }
    public int numStatesExplored() {
        return numExplored;
    }
    public double explorationTime() {
        return timeElapsed;
    }
}
