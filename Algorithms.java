package VisualizationTool_Project;

public class Algorithms {
    public static Graph algorithmName(Graph graph, Node source){
        return null;
    }


    public static double[] bellmanFord(Graph graph, Node source){
        double distance[graph.getNumNodes()];


        //Set all weights to "infinite" except for source to source
        for(int i = 1; i< graph.getNumNodes(); i++){
            if(graph.getNode(i).equals(source)){
                distance[i] = 0;
            }
            else {
                distance[i] = double.MAXVAL;
            }
        }

        //max of numNodes - 1 iterations
        for(int x = 1; x < graph.getNumNodes(); x++){

            //loops through every node each time
            for(int i = 0; i < graph.getNumNodes(); i++) {
                //ensures only if there is already a path to it
                if(distance[i] < double.MAXVAL-1) {


                    for (int j = 0; j < graph.getNumLinks(); j++) {
                        //checks if the directed link starts at specified node
                        if (graph.links.get(j).getNode1().equals(graph.getNode(i))) {
                            //if current distance from source to node2 is greater than link of node1 and 2 plus distance from source to node1
                            if (distance[graph.links.get(j).getNode2().getID()] > (graph.links.get(j).getValue() + distance[graph.links.get(j).getNode1().getID()])){
                                //sets equal as the same thing
                                distance[graph.links.get(j).getNode2().getID()] = (graph.links.get(j).getValue() + distance[graph.links.get(j).getNode1().getID()]);
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
