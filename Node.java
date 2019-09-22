public class Node {
    private String name;
    private int id;
    private double value;

    public Node(){
        this.name = "Default Node";
        this.id = 0;
        this.value = 0;
    }

    public Node(String name, int id, double value){
        this.name = name;
        this.id = id;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setValue(double value) {
        this.value = value;
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Node node) {
        return this.name.equals(node.name) && this.value == node.value;
    }

    @Override
    public String toString() {
        return String.format("%-8.8s %-2d %-3.2f", this.name, this.id, this.value);
    }
}
