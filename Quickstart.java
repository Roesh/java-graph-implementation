class Quickstart{

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addEdge("A","E");
        graph.addEdge("A","B");
        graph.addEdge("B","C");
        graph.addEdge("B","D");
        graph.addEdge("D","E");
        graph.addEdge("D","A");
        graph.removeNode("C");
        graph.print();
    }
}