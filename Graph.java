import java.util.ArrayList;
import java.util.List;

/**
 * A Graph contains Nodes and Links with values connecting some Nodes together
 */
public class Graph {
    private List<Node> nodes;
    private List<Link> links;

    /**
     * Default constructor for a Graph that sets Nodes and Links to a new ArrayList of Nodes and Links
     * and sets numNodes and numLinks to 0
     */
    public Graph(){
        this.nodes = new ArrayList<Node>();
        this.links = new ArrayList<Link>();
    }

    /**
     * Creates and adds a new Node to the Graph
     * @param name The name of the Node
     * @throws IllegalArgumentException
     */
    public void addNode(String name) throws Exception {
        if(getNode(name) != null){
            throw new IllegalArgumentException("Node with same name already exists");
        }
        Node node = new Node(name, nodes.size());
        nodes.add(node);
    }

    /**
     * Creates and adds a new Node to the Graph
     * @param xPos xPosition on graph
     * @param yPos yPosition on graph
     */
    public void addNode(int xPos, int yPos) {
        String name = "" + (char)(nodes.size() + 65);
        Node node = new Node(name, nodes.size(), xPos, yPos);
        nodes.add(node);
    }

    /**
     * Makes a new Link using Node names and adds it to the Graph
     * @param value the value of the new Link
     * @param name1 the name of the second Node connected to the Link
     * @param name2 the name of the second Node connected to the Link
     * @throws IllegalArgumentException if the names of either node don't correlate with a Node on the Graph or
     * if the Link between the two Nodes already exists.
     */
    public void addLink(double value, String name1, String name2) throws IllegalArgumentException {
        Node node1 = getNode(name1);
        if(node1 == null){
            throw new IllegalArgumentException("Invalid node one, DNE");
        }
        Node node2 = getNode(name2);
        if(node2 == null){
            throw new IllegalArgumentException("Invalid node two, DNE");
        }
        if(getLink(name1,name2) != null){
            throw new IllegalArgumentException("Link already exists");
        }
        Link link = new Link(value, node1, node2);
        links.add(link);
    }

    /**
     * Makes a new Link using Node IDs and adds it to the Graph
     * @param value the value of the new Link
     * @param id1 the ID of the second Node connected to the Link
     * @param id2 the ID of the second Node connected to the Link
     * @throws IllegalArgumentException if the ID of either node don't correlate with a Node on the Graph or
     * if the Link between the two Nodes already exists.
     */
    public void addLink(double value, int id1, int id2) throws IllegalArgumentException {
        Node node1 = getNode(id1);
        if(node1 == null){
            throw new IllegalArgumentException("Node one does not exist");
        }
        Node node2 = getNode(id2);
        if(node2 == null){
            throw new IllegalArgumentException("Node two does not exist");
        }
        if(getLink(id1,id2) != null){
            throw new IllegalArgumentException("Link already exists");
        }
        Link link = new Link(value, node1, node2);
        links.add(link);
    }

    public void addLink(Link link) throws IllegalArgumentException {
        Node node1 = link.getNode1();
        if(node1 == null){
            throw new IllegalArgumentException("Node one does not exist");
        }
        Node node2 = link.getNode2();
        if(node2 == null){
            throw new IllegalArgumentException("Node two does not exist");
        }
        if(getLink(link.getNode1().getName(), link.getNode2().getName()) != null){
            throw new IllegalArgumentException("Link already exists");
        }
        links.add(link);
    }

    /**
     * Removes a Node from the Graph given it's name
     * @param name the name of the Node to remove
     * @throws IllegalArgumentException if the Node doesn't exist
     */
    public void removeNode(String name) throws IllegalArgumentException {
        Node node = getNode(name);
        if(node == null){
            throw new IllegalArgumentException("Node does not exist");
        }
        nodes.remove(node.getId());
        for(int i = links.size()-1; i > -1; i++){
            if(links.get(i).containsNode(node)){
                links.remove(i);
            }
        }
        reNumberNodes();
    }

    /**
     * Removes a Node from the Graph given it's ID
     * @param id the ID of the Node to remove
     * @throws IllegalArgumentException if the Node doesn't exist
     */
    public void removeNode(int id) throws IllegalArgumentException {
        Node node = getNode(id);
        if(node == null){
            throw new IllegalArgumentException("Node does not exist");
        }
        nodes.remove(node.getId());
        for(int i = links.size() - 1; i > -1; i--){
            //delete edges that connect to that node
            if(links.get(i).containsNode(node)){
                links.remove(i);
            }else {
                //deleting node changes node id, so change edge data to match new id
                int n1 = links.get(i).getNode1().getId();
                int n2 = links.get(i).getNode2().getId();
                if (n1 > id) {
                    links.get(i).setNode1(nodes.get(n1 - 1));
                }
                if (n2 > id) {
                    links.get(i).setNode2(nodes.get(n2 - 1));
                }
            }
        }
        reNumberNodes();
    }

    /**
     * Removes a Link from the Graph given two Node names
     * @param name1 the name of one node connected to the Link
     * @param name2 the name of the other node connected to the Link
     * @throws IllegalArgumentException if the Link doesn't exist
     */
    public void removeLink(String name1, String name2) throws IllegalArgumentException {
        Link link = getLink(name1,name2);
        if(link == null){
            throw new IllegalArgumentException("Link does not exist");
        }
        for(int i = links.size()-1; i > -1; i++){
            if(links.get(i).equals(link)){
                links.remove(i);
            }
        }
    }

    /**
     * Removes a Link from the Graph given two Node IDs
     * @param id1 the ID of one node connected to the Link
     * @param id2 the ID of the other node connected to the Link
     * @throws IllegalArgumentException if the Link doesn't exist
     */
    public void removeLink(int id1, int id2) throws IllegalArgumentException {
        Link link = getLink(id1,id2);
        if(link == null){
            throw new IllegalArgumentException("Link does not exist");
        }
        for(int i = links.size() - 1; i > -1; i--){
            if(links.get(i).equals(link)){
                links.remove(i);
            }
        }
    }

    /**
     * Returns a Node in the Graph given a name
     * @param name the name of the Node searching for
     * @return the Node with the given name, Null if the Node doesn't exits
     */
    public Node getNode(String name){
        for(Node n: nodes){
            if(n.getName().equals(name)){
                return n;
            }
        }
        return null;
    }

    /**
     * Edits a Node in the Graph given a Name and newName for the Node
     * @param name the name of the Node being edited
     * @param newName the new Name of the Node
     * @throws IllegalArgumentException if the Node doesn't exits
     */
    public void editNode(String name, String newName) throws IllegalArgumentException {
        Node node = getNode(name);
        if(node == null){
            throw new IllegalArgumentException("Node does not exist");
        }
        node.setName(newName);
    }

    /**
     * Edits a Node in the Graph given an ID and newName for the Node
     * @param id the ID of the Node being edited
     * @param newName the new Name of the Node
     * @throws IllegalArgumentException if the Node doesn't exits
     */
    public void editNode(int id, String newName) throws IllegalArgumentException {
        Node node = getNode(id);
        if(node == null){
            throw new IllegalArgumentException("Node does not exist");
        }
        node.setName(newName);
    }

    /**
     * Edits a Link in the Graph given two Node names and a new value for the Link
     * @param name1 the name of one Node the Link is connected to
     * @param name2 the name of the other Node the Link is connected to
     * @param newValue the new value of the Link
     * @throws IllegalArgumentException if the Node doesn't exits
     */
    public void editLink(String name1, String name2, double newValue) throws IllegalArgumentException {
        Link link = getLink(name1, name2);
        if(link == null){
            throw new IllegalArgumentException("Link does not exist");
        }
        link.setValue(newValue);
    }

    /**
     * Edits a Link in the Graph given two Node IDs and a new value for the Link
     * @param id1 the ID of one Node the Link is connected to
     * @param id2 the ID of the other Node the Link is connected to
     * @param newValue the new value of the Link
     * @throws IllegalArgumentException if the Node doesn't exits
     */
    public void editLink(int id1, int id2, double newValue) throws IllegalArgumentException {
        Link link = getLink(id1, id2);
        if(link == null){
            throw new IllegalArgumentException("Link does not exist");
        }
        link.setValue(newValue);
    }

    /**
     * Returns a Node in the Graph given a ID
     * @param id the name of the Node searching for
     * @return the Node with the given ID, Null if the Node doesn't exits
     */
    public Node getNode(int id){
        if(id >= nodes.size() || id < 0) {
            return null;
        }
        return nodes.get(id);
    }

    /**
     * Returns a Link in the Graph given two Node names
     * @param name1 the name of one Node the Link is connected to
     * @param name2 the name of the other Node the Link is connected to
     * @return the Link in the Graph given two Node names, Null if the Link doesn't exist
     */
    public Link getLink(String name1, String name2){
        Node node1 = getNode(name1);
        if(node1 == null){
            return null;
        }
        Node node2 = getNode(name2);
        if(node2 == null){
            return null;
        }
        for(Link l: links){
            if(l.containsNode(node1) && l.containsNode(node2)){
                return l;
            }
        }
        return null;
    }

    /**
     * Returns a Link in the Graph given two Node IDs
     * @param id1 the ID of one Node the Link is connected to
     * @param id2 the ID of the other Node the Link is connected to
     * @return the Link in the Graph given two Node names, Null if the Link doesn't exist
     */
    public Link getLink(int id1, int id2){
        Node node1 = getNode(id1);
        if(node1 == null){
            return null;
        }
        Node node2 = getNode(id2);
        if(node2 == null){
            return null;
        }
        for(Link l: links){
            if(l.containsNode(node1) && l.containsNode(node2)){
                return l;
            }
        }
        return null;
    }

    /**
     * Resets the Node's IDs in the Graph according to their placement in the List
     */
    private void reNumberNodes(){
        for(int i = 0; i < nodes.size(); i ++){
            nodes.get(i).setId(i);
            nodes.get(i).setName("" + (char)(i + 65));
        }
    }

    /**
     * Returns the List of Nodes in the Graph
     * @return the List of Nodes in the Graph
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Returns the List of Links in the Graph
     * @return the List of Links in the Graph
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * Returns the Number of Nodes in the Graph
     * @return the Number of Nodes in the Graph
     */
    public int getNumNodes() {
        return nodes.size();
    }

    /**
     * Returns the Number of Links in the Graph
     * @return the Number of Links in the Graph
     */
    public int getNumLinks() {
        return links.size();
    }

    /**
     * Sets the Node List of the Graph
     * @param nodes the new Node List of the Graph
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Sets the Link List of the Graph
     * @param links the new Link List of the Graph
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

        @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        finalString.append("______Nodes_______\n");
        finalString.append("Name     ID Val\n");
        finalString.append("-------- -- ------\n");
        for(Node n: nodes){
            finalString.append(n.toString() + "\n");
        }

        finalString.append("\n");
        finalString.append("______Links_______\n");
        finalString.append("Link    Val\n");
        finalString.append("------- ------\n");
        for(Link l: links){
            finalString.append(l.toString()).append("\n");
        }

        return finalString.toString();
    }
}
