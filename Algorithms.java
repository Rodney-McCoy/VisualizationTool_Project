package VisualizationTool_Project;

public class Algorithms {

    private static final double INF = Double.POSITIVE_INFINITY;

    public static double[][] FloydWarshall(Graph graph){
        //make adjacency matrix
        double matrix[][] = new double[graph.getNumNodes()][];

        //Initialize matrix distances
        for (int i = 0; i < graph.getNumNodes(); ++i) {
            for (int j = 0; j < graph.getNumNodes(); ++j) {
                matrix[i][j] = INF;
            }
            System.out.print("\n");
        }

        //fill matrix with graph data
        for(int i = 0; i < graph.getNumNodes(); ++i) {
            for (int j = 0; j < graph.getNumLinks(); ++j) {
                if(i == graph.getLinks().get(j).getNode1().getId()){
                    matrix[i][graph.getLinks().get(j).getNode2().getId()] = graph.getLinks().get(j).getValue();
                }
            }
        }
        //print adjacency matrix for testing purposes
        printMatrix(matrix, graph.getNumNodes());

        int V = matrix.length;
        double dist[][] = new double[V][V]; //output matrix

        //initialize output matrix as input matrix
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                dist[i][j] = matrix[i][j];
            }
        }

        //run core algorithm
        for (int k = 0; k < V; ++k) {
            for (int i = 0; i < V; ++i) {
                for (int j = 0; j < V; ++j) {

                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        //print distances matrix for testing and return dist for future use
        printMatrix(dist, V);
        return dist;
    }

    private static void printMatrix(double[][] matrix, int numNodes){
        System.out.println("Shortest distance matrix:");
        for (int i = 0; i < numNodes; ++i) {
            for (int j = 0; j < numNodes; ++j) {
                if (matrix[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.printf("%.1f\t", matrix[i][j]);
                }
            }
            System.out.print("\n");
        }
    }


    public static double[] bellmanFord(Graph graph, Node source){
        double[] distance = new double[graph.getNumNodes()];


        //Set all weights to "infinite" except for source to source
        for(int i = 1; i< graph.getNumNodes(); i++){
            if(graph.getNode(i).equals(source)){
                distance[i] = 0;
            }
            else {
                distance[i] = INF;
            }
        }

        //max of numNodes - 1 iterations
        for(int x = 1; x < graph.getNumNodes(); x++){

            //loops through every node each time
            for(int i = 0; i < graph.getNumNodes(); i++) {
                //ensures only if there is already a path to it
                if(distance[i] < INF-1) {


                    for (int j = 0; j < graph.getNumLinks(); j++) {
                        //checks if the directed link starts at specified node
                        if (graph.getLinks().get(j).getNode1().equals(graph.getNode(i))) {
                            //if current distance from source to node2 is greater than link of node1 and 2 plus distance from source to node1
                            if (distance[graph.getLinks().get(j).getNode2().getId()] > (graph.getLinks().get(j).getValue() + distance[graph.getLinks().get(j).getNode1().getId()])){
                                //sets equal as the same thing
                                distance[graph.getLinks().get(j).getNode2().getId()] = (graph.getLinks().get(j).getValue() + distance[graph.getLinks().get(j).getNode1().getId()]);
                            }
                        }


                    }


                }
            }


        }

        //distance should be an array of the shortest path from the source to each corresponding node on the graph
        return distance;
    }
}
