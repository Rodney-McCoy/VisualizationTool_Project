//package VisualizationTool_Project;

public class Node {
    private String name;
    private int id;
    private double value;


    private int xPos;
    private int yPos;

    public Node(){
        this.name = "Default Node";
        this.id = 0;
        this.value = 0;
        this.xPos = 50;
        this.yPos = 50;
    }

    /**
     * Node constructor that sets parameters
     * @param name Name of Node used to disallow duplicate Nodes
     * @param id Id of Node used to locate the Node within a list
     */
    public Node(String name, int id, int x, int y){
        this.name = name;
        this.id = id;
        this.value = value;
        this.xPos = x;
        this.yPos = y;
    }

    public double getValue() {
        return value;
    }


    public int getxPos() { return xPos; }
    public int getyPos() { return yPos; }

    public void setxPos(int newX) { this.xPos = newX; }
    public void setyPos(int newY) { this.yPos = newY; }

    /**
     * Returns the ID of the current Node
     * @return the ID of the current Node
     */
    public int getId() { return id; }

    /**
     * Returns the Name of the Node
     * @return the Name of the Node
     */
    public String getName() { return name; }

    /**
     * Sets the ID of the Node
     * @param id the new ID of the Node
     */
    public void setId(int id) { this.id = id; }

    /**
     * Sets the Name of the Node
     * @param name the new Name of the Node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares the Names of two Nodes and returns if they're equal
     * @param node The Node being compared with the current Node
     * @return True if both Nodes have the same name, False otherwise
     */
    public boolean equals(Node node) {
        return this.name.equals(node.name) && this.value == node.value;
    }

    @Override
    public String toString() {
        return String.format("%-8.8s %-2d %-3.2f", this.name, this.id, this.value);
    }
}
