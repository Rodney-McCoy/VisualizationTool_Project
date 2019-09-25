import java.util.ArrayList;

public class Model {
    private static ArrayList<Graph> graphs = new ArrayList<Graph>();
    private static int currGraph = 0;
    private static int numGraphs = 0;

    public static void addGraph(){
        graphs.add(new Graph());
        numGraphs++;
    }

    public static void addNode(String name, double value) throws Exception {
        if(numGraphs == 0){
            addGraph();
        }
        graphs.get(currGraph).addNode(name, value);
    }

    public static void addLink(double value, String name1, String name2) throws Exception {
        if(numGraphs == 0){
            addGraph();
        }
        graphs.get(currGraph).addLink(value, name1, name2);
    }

    public static void addLink(double value, int id1, int id2) throws Exception {
        if(numGraphs == 0){
            addGraph();
        }
        graphs.get(currGraph).addLink(value, id1, id2);
    }

    public static void removeNode(String name) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeNode(name);
    }

    public static void removeNode(int id) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeNode(id);
    }

    public static void removeLink(String node1, String node2) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeLink(node1, node2);
    }

    public static void removeLink(int id1, int id2) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).removeLink(id1, id2);
    }

    public static void editNode(String name, String newName, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(name,newName,newValue);
    }

    public static void editNode(String name, String newName) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(name,newName);
    }

    public static void editNode(String name, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(name,newValue);
    }

    public static void editNode(int id, String newName, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(id,newName,newValue);
    }

    public static void editNode(int id, String newName) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(id,newName);
    }

    public static void editNode(int id, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editNode(id,newValue);
    }

    public static void editLink(String name1, String name2, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editLink(name1,name2,newValue);
    }

    public static void editLink(int id1, int id2, double newValue) throws Exception {
        checkNumGraphs();
        graphs.get(currGraph).editLink(id1,id2,newValue);
    }

    public static void printGraph() throws Exception {
        checkNumGraphs();
        System.out.println(graphs.get(currGraph).toString());
    }

    private static void checkNumGraphs() throws Exception {
        if(numGraphs == 0){
            throw new Exception("No graphs exist");
        }
    }
}
