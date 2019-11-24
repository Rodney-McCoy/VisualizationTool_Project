public class Node {
    private String name;
    private int id;
    private int xPos;
    private int yPos;

    /**
     * Node constructor that sets parameters
     * @param name Name of Node used to disallow duplicate Nodes
     * @param id Id of Node used to locate the Node within a list
     */
    public Node(String name, int id){
        this(name, id, 0,0);
    }

    /**
     * Node constructor that sets parameters
     * @param name Name of Node used to disallow duplicate Nodes
     * @param id Id of Node used to locate the Node within a list
     */
    public Node(String name, int id, int xPos, int yPos){
        this.name = name;
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Returns the ID of the current Node
     * @return the ID of the current Node
     */
    int getId() { return id; }

    /**
     * Returns the Name of the Node
     * @return the Name of the Node
     */
    String getName() { return name; }

    /**
     * Returns the x position of node
     * @return xPos
     */
    int getxPos(){ return xPos;}

    /**
     * Returns the y position of node
     * @return yPos
     */
    int getyPos(){ return yPos;}

    /**
     * Sets the ID of the Node
     * @param id the new ID of the Node
     */
    void setId(int id) { this.id = id; }

    /**
     * Sets the Name of the Node
     * @param name the new Name of the Node
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Compares the Names of two Nodes and returns if they're equal
     * @param node The Node being compared with the current Node
     * @return True if both Nodes have the same name, False otherwise
     */
    boolean equals(Node node) {
        return this.getId() - node.getId() == 0;
    }

    @Override
    public String toString() {
        return String.format("%-8.8s %-2d", this.name, this.id);
    }
}
