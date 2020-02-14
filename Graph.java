import java.util.HashMap;

class Graph{

    private int[][] graph;
    private HashMap<String,Integer> labelToIndexMap;
    private Node[] nodes;
    private int currentNumberOfNodes;

    public Graph(int numNodes){
        graph = new int[numNodes][numNodes];
        labelToIndexMap = new HashMap<String,Integer>(numNodes);
        nodes = new Node[numNodes];
        currentNumberOfNodes = 0;
    }
    
    public void addNode(String label){        
        if(currentNumberOfNodes == nodes.length){
            throw new IllegalStateException();
        }
        if(label == null || nodeExists(label)){
            return;
        }
        addNodeInternal(label);
    }

    private boolean nodeExists(String label){
        return labelToIndexMap.containsKey(label);
    }

    private void addNodeInternal(String label){
        int nextFreeIndex = getNextFreeIndex();
        labelToIndexMap.put(label, nextFreeIndex);
        nodes[nextFreeIndex] = new Node(label);
        currentNumberOfNodes++;
    }

    // Assumes there is at least one null value in nodes array
    private int getNextFreeIndex(){
        for(int i = 0; i< nodes.length ; i++){
            if(nodes[i] == null){
                return i;
            }
        }
        return 0;        
    }

    public void removeNode(String label){        
        if(label == null || !nodeExists(label)){
            return;
        }
        int nodeIndex = labelToIndexMap.get(label);
        nodes[nodeIndex] = null;
        for(int i = 0; i < nodes.length; i++){
            graph[i][nodeIndex] = 0;
            graph[nodeIndex][i] = 0;
        }
        currentNumberOfNodes--;
    }

    public void addEdge(String from, String to){
        if(from == null || to == null){
            return;
        }
        if(!labelToIndexMap.containsKey(from) || !labelToIndexMap.containsKey(to)){
            throw new IllegalArgumentException();
        }
        graph[labelToIndexMap.get(from)][labelToIndexMap.get(to)] = 1;
    }

    public void removeEdge(String from, String to){
        if(from == null || to == null){
            return;
        }
        if(!labelToIndexMap.containsKey(from) || !labelToIndexMap.containsKey(to)){
            throw new IllegalArgumentException();
        }
        graph[labelToIndexMap.get(from)][labelToIndexMap.get(to)] = 0;
    }
    
    public void print(){
        if(currentNumberOfNodes == 0){
            return;
        }
        int nodeArrayLength = nodes.length;
        
        for(Node n : nodes){
            if(n == null){
                continue;
            }
            System.out.println(n.label + " is connected with [");
            int index = getNodeIndex(n);
            for (int i = 0; i < nodeArrayLength; i++){
                if(graph[index][i] != 0){
                    System.out.println(nodes[i].label + ",");
                }                                
            }
            System.out.println("]");
        }
    }

    private int getNodeIndex(Node node){
        return labelToIndexMap.get(node.label);
    }

    private class Node{
        String label;

        public Node(String label) {
            this.label = label;
        }
    }
}