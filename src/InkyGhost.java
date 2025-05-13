import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InkyGhost extends Ghost {
    private final Random rand = new Random();
    private static class InkyGraph {
        private final Random rand = new Random();
        private final Node first_node;
        private Node target_node;
        private static class Node {
            List<Node> nodes = new ArrayList<>();
            private java.awt.Point target;

            public Node(java.awt.Point target) {
                this.target = target;
            }

            public java.awt.Point getTarget() {
                return target;
            }
            
            public List<Node> getNodes() {
                return nodes;
            }

            public void addNode(Node node) {
                nodes.add(node);
            }
        }

        public InkyGraph(java.awt.Point point) {
            first_node = new Node(point);
            target_node = new Node(point);
        }

        public java.awt.Point get() {
            return target_node.getTarget();
        }

        public void next() {
            List<Node> nodes = target_node.getNodes();
            if (nodes.size() == 0) {
                target_node = first_node;
            } else {
                target_node = nodes.get(Math.abs(rand.nextInt() % nodes.size()));
            }
        }

        public java.awt.Point getAndNext() {
            java.awt.Point ret = get();
            next();
            return ret;
        }

        public void appendNode(java.awt.Point point) {
            target_node.addNode(new Node(point));
        }
    }
    
    private InkyGraph graph;
    public InkyGhost(int x, int y) {
        super(x, y, "inky");
        graph = new InkyGraph(getHome());
        // Magic to setup graph
        for (int i = 0; i < 3; i++) {
            graph.appendNode(new java.awt.Point(2, 2));
            graph.appendNode(new java.awt.Point(2, 20));
            graph.next();
            graph.appendNode(new java.awt.Point(22, 2));
            graph.appendNode(new java.awt.Point(10, 10));
            graph.next();
        }
    }
    
    /**
     * This one is different than vanilla Pacman! We use a Graph!
     */
    protected java.awt.Point target(Maze maze, Player player) {
        if (rand.nextInt() % 20 == 0) {
            return graph.getAndNext();
        } else {
            return graph.get();
        }
    }
}
