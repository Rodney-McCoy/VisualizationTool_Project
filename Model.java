package VisualizationTool_Project;

import java.util.ArrayList;

/**
 * Represents the backend of the program
 */
public class Model {
    private static ArrayList<Graph> graphs = new ArrayList<Graph>();
    private static int currGraph = 0;
    private static int numGraphs = 0;

    /**
     * Adds a graph to the static Model graphs ArrayList and updates the number of graphs
     */
    public static void addGraph(){
        graphs.add(new Graph());
        numGraphs++;
    }

    /**
     * Adds a Node to the current graph in the static graphs ArrayList with a given name
     * @param name the name of the Node being added to the graph
     * @throws Exception if a Node with the same name already exists
     */
    public static void addNode(String name) throws Exception {
        if(numGraphs == 0){
            addGraph();
        }
        graphs.get(currGraph).addNode(name);
    }

    /**
     * Adds a Link to the current graph in the static graphs ArrayList with given names for the
     * Nodes connected to the Link
     * @param value the value of the Link being added
     * @param name1 the name of one Node being linked to
     * @param name2 the name of the other Node being linked to
     * @throws Exception if either Nodes don't exist or the Link being added already exists
     */
    public static void addLink(double value, String name1, String name2) throws Exception {
        if(numGraphs == 0){
            addGraph();
        }
        graphs.get(currGraph).addLink(value, name1, name2);
    }

    /**
     * Adds a Link to the current graph in the static graphs ArrayList with given ids for the
     * Nodes connected to the Link
     * @param value the value of the Link being added
     * @param id2 the name of one Node being linked to
     * @param id2 the name of the other Node being linked to
     * @throws Exception if either Nodes don't exist or the Link being added already exists
     */
    public static void addLink(double value, int id1, int id2) throws Exception {
        if(numGraphs == 0){
            addGraph();
        }
        graphs.get(currGraph).addLink(value, id1, id2);
    }

    /**
     * Removes a Node from the current graph in the static graphs ArrayList given a name
     * @param name the name of the Node trying to be removed
     * @throws Exception if the Node already doesn't exist
     */
    public static void removeNode(String name) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeNode(name);
    }

    /**
     * Removes a Node from the current graph in the static graphs ArrayList given a ID
     * @param id the name of the Node trying to be removed
     * @throws Exception if the Node already doesn't exist
     */
    public static void removeNode(int id) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeNode(id);
    }

    /**
     * Removes a Link from the current graph in the static graphs ArrayList given names for the
     * Nodes connected to the Link
     * @param name1 the name of one Node being linked to
     * @param name2 the name of the other Node being linked to
     * @throws Exception if either Nodes don't exist or the Link doesn't exist
     */
    public static void removeLink(String name1, String name2) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeLink(name1, name2);
    }

    /**
     * Removes a Link from the current graph in the static graphs ArrayList given IDs for the
     * Nodes connected to the Link
     * @param id1 the ID of one Node being linked to
     * @param id2 the ID of the other Node being linked to
     * @throws Exception if either Nodes don't exist or the Link doesn't exist
     */
    public static void removeLink(int id1, int id2) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeLink(id1, id2);
    }

    /**
     * Edits a Node in the current graph in the static graphs ArrayList given a Node name and
     * newName
     * @param name the name of the Node being edited
     * @param newName the new name of the Node
     * @throws Exception if the Node doesn't exist or the newName is already a Node
     */
    public static void editNode(String name, String newName) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(name,newName);
    }

    /**
     * Edits a Node in the current graph in the static graphs ArrayList given a Node ID and
     * newName
     * @param id the ID of the Node being edited
     * @param newName the new name of the Node
     * @throws Exception if the Node doesn't exist or the newName is already a Node
     */
    public static void editNode(int id, String newName) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(id,newName);
    }

    /**
     * Edits a Link in the current graph in the static graphs ArrayList given two Node names and
     * a newValue for the Link
     * @param name1 the name of one Node being linked to
     * @param name2 the name of one Node being linked to
     * @param newValue the new Value of the Link
     * @throws Exception if either Node doesn't exist or the Link doesn't exist
     */
    public static void editLink(String name1, String name2, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editLink(name1,name2,newValue);
    }

    /**
     * Edits a Link in the current graph in the static graphs ArrayList given two Node IDs and
     * a newValue for the Link
     * @param id1 the ID of one Node being linked to
     * @param id2 the ID of one Node being linked to
     * @param newValue the new Value of the Link
     * @throws Exception if either Node doesn't exist or the Link doesn't exist
     */
    public static void editLink(int id1, int id2, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editLink(id1,id2,newValue);
    }

    /**
     * 
     * @throws Exception
     */
    public static void printGraph() throws Exception {
        checkNumGraphs();
        System.out.println(graphs.get(currGraph).toString());
    }

    private static void checkNumGraphs() throws Exception {
        if(numGraphs == 0){
            throw new Exception("No graphs exist");
        }
    }

    public static void printFloydWarshall() {
        Algorithms.FloydWarshall(graphs.get(currGraph));
    }

    public static void bellmanFord(String in) throws Exception {
        System.out.print(Algorithms.bellmanFord(graphs.get(currGraph), graphs.get(currGraph).getNode(in)));
    }

    public static void printDijkstra(String start, String end) throws Exception {
        Graph graph = graphs.get(currGraph);
        System.out.println(Algorithms.dijkstra(graph, graph.getNode(start).getId(), graph.getNode(end).getId()));
    }
}