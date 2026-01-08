package services;

import datastructures.Graph;

public class DeliveryService {

    private Graph deliveryNetwork;

    public DeliveryService(int totalNodes) {
        deliveryNetwork = new Graph(totalNodes);
    }

    // Add route (bidirectional)
    public void addRoute(int fromNode, int toNode) {
        deliveryNetwork.addEdge(fromNode, toNode);
        System.out.println("Route added between " + fromNode + " and " + toNode);
    }

    // Display graph
    public void displayNetwork() {
        deliveryNetwork.displayGraph();
    }

    // BFS from node
    public void bfsRoute(int start) {
        deliveryNetwork.BFS(start);
    }

    // DFS from node
    public void dfsRoute(int start) {
        deliveryNetwork.DFS(start);
    }
}
