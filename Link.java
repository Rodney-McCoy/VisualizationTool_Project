package VisualizationTool_Project;

public class Link {
    private double value;
    private Node node1;
    private Node node2;

    public Link(){
        this.value = 0;
        this.node1 = new Node();
        this.node2 = new Node();
    }

    public Link(double value, Node node1, Node node2){
        this.value = value;
        this.node1 = node1;
        this.node2 = node2;
    }

    public Link(int value, Node node1, Node node2){
        this.value = value;
        this.node1 = node1;
        this.node2 = node2;
    }

    public Link(long value, Node node1, Node node2){
        this.value = (double) value;
        this.node1 = node1;
        this.node2 = node2;
    }

    public boolean containsNode(Node node){
        return this.node1.equals(node) || this.node2.equals(node);
    }

    public double getValue() {
        return value;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public boolean equals(Link link) {
        return this.node1.equals(link.node1) && this.node2.equals(link.node2);
    }

    @Override
    public String toString() {
        return String.format("%-2d<->%2d %-3.2f", this.node1.getId(), this.node2.getId(), this.value);
    }
}
