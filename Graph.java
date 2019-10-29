//package VisualizationTool_Project;

import java.util.ArrayList;

/**
 * A Graph contains Nodes and Links with values connecting some Nodes together
 */
public class Graph {
    private ArrayList<Node> nodes;
    private ArrayList<Link> links;
    private int numNodes;
    private int numLinks;

    /**
     * Default constructor for a Graph that sets Nodes and Links to a new ArrayList of Nodes and Links
     * and sets numNodes and numLinks to 0
     */
    public Graph(){
        this.nodes = new ArrayList<Node>();
        this.links = new ArrayList<Link>();
        this.numNodes = 0;
        this.numLinks = 0;
    }

    /**
     * Creates and adds a new Node to the Graph
     * @param name The name of the Node
     * @throws Exception
     */
    public void addNode(String name) throws Exception {
        if(getNode(name) != null){
            throw new Exception("Node with same name already exists");
        }
        Node node = new Node(name, numNodes);
        nodes.add(node);
        numNodes++;
    }

    /**
     * Makes a new Link using Node names and adds it to the Graph
     * @param value the value of the new Link
     * @param name1 the name of the second Node connected to the Link
     * @param name2 the name of the second Node connected to the Link
     * @throws Exception if the names of either node don't correlate with a Node on the Graph or
     * if the Link between the two Nodes already exists.
     */
    public void addLink(double value, String name1, String name2) throws Exception {
        Node node1 = getNode(name1);
        if(node1 == null){
            throw new Exception("Invalid node one, DNE");
        }
        Node node2 = getNode(name2);
        if(node2 == null){
            throw new Exception("Invalid node two, DNE");
        }
        if(getLink(name1,name2) != null){
            throw new Exception("Link already exists");
        }
        Link link = new Link(value, node1, node2);
        links.add(link);
        numLinks++;
    }

    /**
     * Makes a new Link using Node IDs and adds it to the Graph
     * @param value the value of the new Link
     * @param id1 the ID of the second Node connected to the Link
     * @param id2 the ID of the second Node connected to the Link
     * @throws Exception if the ID of either node don't correlate with a Node on the Graph or
     * if the Link between the two Nodes already exists.
     */
    public void addLink(double value, int id1, int id2) throws Exception {
        Node node1 = getNode(id1);
        if(node1 == null){
            throw new Exception("Node one does not exist");
        }
        Node node2 = getNode(id2);
        if(node2 == null){
            throw new Exception("Node two does not exist");
        }
        if(getLink(id1,id2) != null){
            throw new Exception("Link already exists");
        }
        Link link = new Link(value, node1, node2);
        links.add(link);
        numLinks++;
    }

    public void addLink(Link link) throws Exception {
        Node node1 = link.getNode1();
        if(node1 == null){
            throw new Exception("Node one does not exist");
        }
        Node node2 = link.getNode2();
        if(node2 == null){
            throw new Exception("Node two does not exist");
        }
        if(getLink(link.getNode1().getName(), link.getNode2().getName()) != null){
            throw new Exception("Link already exists");
        }
        links.add(link);
        numLinks++;
    }

    /**
     * Removes a Node from the Graph given it's name
     * @param name the name of the Node to remove
     * @throws Exception if the Node doesn't exist
     */
    public void removeNode(String name) throws Exception {
        Node node = getNode(name);
        if(node == null){
            throw new Exception("Node does not exist");
        }
        nodes.remove(node.getId());
        for(int i = 0; i < numLinks; i++){
            if(links.get(i).containsNode(node)){
                links.remove(i);
                numLinks--;
            }
        }
        numNodes--;
        reNumberNodes();
    }

    /**
     * Removes a Node from the Graph given it's ID
     * @param id the ID of the Node to remove
     * @throws Exception if the Node doesn't exist
     */
    public void removeNode(int id) throws Exception {
        Node node = getNode(id);
        if(node == null){
            throw new Exception("Node does not exist");
        }
        nodes.remove(node.getId());
        for(int i = 0; i < numLinks; i++){
            if(links.get(i).containsNode(node)){
                links.remove(i);
                numLinks--;
            }
        }
        numNodes--;
        reNumberNodes();
    }

    /**
     * Removes a Link from the Graph given two Node names
     * @param name1 the name of one node connected to the Link
     * @param name2 the name of the other node connected to the Link
     * @throws Exception if the Link doesn't exist
     */
    public void removeLink(String name1, String name2) throws Exception {
        Link link = getLink(name1,name2);
        if(link == null){
            throw new Exception("Link does not exist");
        }
        for(int i = 0; i < numLinks; i++){
            if(links.get(i).equals(link)){
                links.remove(i);
                numLinks--;
            }
        }
    }

    /**
     * Removes a Link from the Graph given two Node IDs
     * @param id1 the ID of one node connected to the Link
     * @param id2 the ID of the other node connected to the Link
     * @throws Exception if the Link doesn't exist
     */
    public void removeLink(int id1, int id2) throws Exception {
        Link link = getLink(id1,id2);
        if(link == null){
            throw new Exception("Link does not exist");
        }
        for(int i = 0; i < numLinks; i++){
            if(links.get(i).equals(link)){
                links.remove(i);
                numLinks--;
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
     * @throws Exception if the Node doesn't exits
     */
    public void editNode(String name, String newName) throws Exception {
        Node node = getNode(name);
        if(node == null){
            throw new Exception("Node does not exist");
        }
        node.setName(newName);
    }

    /**
     * Edits a Node in the Graph given an ID and newName for the Node
     * @param id the ID of the Node being edited
     * @param newName the new Name of the Node
     * @throws Exception if the Node doesn't exits
     */
    public void editNode(int id, String newName) throws Exception {
        Node node = getNode(id);
        if(node == null){
            throw new Exception("Node does not exist");
        }
        node.setName(newName);
    }

    /**
     * Edits a Link in the Graph given two Node names and a new value for the Link
     * @param name1 the name of one Node the Link is connected to
     * @param name2 the name of the other Node the Link is connected to
     * @param newValue the new value of the Link
     * @throws Exception if the Node doesn't exits
     */
    public void editLink(String name1, String name2, double newValue) throws Exception {
        Link link = getLink(name1, name2);
        if(link == null){
            throw new Exception("Link does not exist");
        }
        link.setValue(newValue);
    }

    /**
     * Edits a Link in the Graph given two Node IDs and a new value for the Link
     * @param id1 the ID of one Node the Link is connected to
     * @param id2 the ID of the other Node the Link is connected to
     * @param newValue the new value of the Link
     * @throws Exception if the Node doesn't exits
     */
    public void editLink(int id1, int id2, double newValue) throws Exception {
        Link link = getLink(id1, id2);
        if(link == null){
            throw new Exception("Link does not exist");
        }
        link.setValue(newValue);
    }

    /**
     * Returns a Node in the Graph given a ID
     * @param id the name of the Node searching for
     * @return the Node with the given ID, Null if the Node doesn't exits
     */
    public Node getNode(int id){
        if(id >= numNodes || id < 0) {
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
     * Resets the Node's IDs in the Graph according to their placement in the ArrayList
     */
    private void reNumberNodes(){
        for(int i = 0; i < numNodes; i ++){
            nodes.get(i).setId(i);
        }
    }

    /**
     * Returns the ArrayList of Nodes in the Graph
     * @return the ArrayList of Nodes in the Graph
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Returns the ArrayList of Links in the Graph
     * @return the ArrayList of Links in the Graph
     */
    public ArrayList<Link> getLinks() {
        return links;
    }

    /**
     * Returns the Number of Nodes in the Graph
     * @return the Number of Nodes in the Graph
     */
    public int getNumNodes() {
        return numNodes;
    }

    /**
     * Returns the Number of Links in the Graph
     * @return the Number of Links in the Graph
     */
    public int getNumLinks() {
        return numLinks;
    }

    /**
     * Sets the Node ArrayList of the Graph
     * @param nodes the new Node ArrayList of the Graph
     */
    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Sets the Link ArrayList of the Graph
     * @param links the new Link ArrayList of the Graph
     */
    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    /**
     * Sets the Number of Nodes in the Graph
     * @param numNodes the new Number of Links for the Graph
     */
    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    /**
     * Sets the Number of Links in the Graph
     * @param numLinks the new Number of Links for the Graph
     */
    public void setNumLinks(int numLinks) {
        this.numLinks = numLinks;
    }

    @Override
    public String toString() {
        String finalString = "";
        finalString = finalString.concat("______Nodes_______\n");
        finalString = finalString.concat("Name     ID Val\n");
        finalString = finalString.concat("-------- -- ------\n");
        for(Node n: nodes){
            finalString = finalString.concat(n.toString() + "\n");
        }

        finalString = finalString.concat("\n");
        finalString = finalString.concat("______Links_______\n");
        finalString = finalString.concat("Link    Val\n");
        finalString = finalString.concat("------- ------\n");
        for(Link l: links){
            finalString = finalString.concat(l.toString() + "\n");
        }

        return finalString;
    }
}
