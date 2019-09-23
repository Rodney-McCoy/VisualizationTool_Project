package VisualizationTool_Project;

public class Node {
    private String name;
    private int id;

    public Node(){
        this.name = "Default Node";
        this.id = 0;
    }

    public Node(String name, int id, double value){
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
