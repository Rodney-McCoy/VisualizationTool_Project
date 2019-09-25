/**
 * A Link connects two Nodes and has a value associated with it
 */
public class Link {
    private double value;
    private Node node1;
    private Node node2;

    /**
     * Default Link constructor that sets value to 0, and creates new Default Nodes for node 1 and 2
     */
    public Link(){
        this.value = 0;
        this.node1 = new Node();
        this.node2 = new Node();
    }

    /**
     * Link constructor that allows for unique values to be set for value, node1, and node2
     * @param value value of link
     * @param node1 connecting Node 1
     * @param node2 connecting Node 2
     */
    public Link(double value, Node node1, Node node2){
        this.value = value;
        this.node1 = node1;
        this.node2 = node2;
    }

    /**
     * Checks if the link is connected to a given Node
     * @param node the Node being checked for
     * @return True if the Link is connected to the given Node, False otherwise
     */
    public boolean containsNode(Node node){
        return this.node1.equals(node) || this.node2.equals(node);
    }

    /**
     * Returns the value of the Link
     * @return the value of the Link
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns the first Node the Link is connected to
     * @return the first Node the Link is connected to
     */
    public Node getNode1() {
        return node1;
    }

    /**
     * Returns the second Node the Link is connected to
     * @return the second Node the Link is connected to
     */
    public Node getNode2() {
        return node2;
    }

    /**
     * Sets the Link's value
     * @param value the new value for the Link
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the Link's node1
     * @param node1 the new node1
     */
    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    /**
     * Sets the Link's node2
     * @param node2 the new node2
     */
    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    /**
     * Evaluates if two Links have the same connecting nodes
     * @param link the Link being compared to
     * @return True if both Links are connecting the same nodes, False otherwise
     */
    public boolean equals(Link link) {
        return this.node1.equals(link.node1) && this.node2.equals(link.node2);
    }

    @Override
    public String toString() {
        return String.format("%-2d<->%2d %-3.2f", this.node1.getId(), this.node2.getId(), this.value);
    }
}
