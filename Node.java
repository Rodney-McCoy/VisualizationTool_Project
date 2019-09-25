package VisualizationTool_Project;

/**
 * A Node represents a point in a graph with a name and id
 */
public class Node {
    private String name;
    private int id;

    /**
     * Default Node constructor sets name to "Default Node" and id to 0
     */
    public Node(){
        this.name = "Default Node";
        this.id = 0;
    }

    /**
     * Node constructor
     * @param name
     * @param id
     */
    public Node(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Node node) {
        return this.name.equals(node.name);
    }

    @Override
    public String toString() {
        return String.format("%-8.8s %-2d", this.name, this.id);
    }
}
