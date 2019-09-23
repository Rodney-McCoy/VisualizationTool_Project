package VisualizationTool_Project;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Node> nodes;
    private ArrayList<Link> links;
    private int numNodes;
    private int numLinks;

    public Graph(){
        this.nodes = new ArrayList<Node>();
        this.links = new ArrayList<Link>();
        this.numNodes = 0;
        this.numLinks = 0;
    }

    public void addNode(String name, double value) throws Exception {
        if(getNode(name) != null){
            throw new Exception("Node with same name already exists");
        }
        Node node = new Node(name, numNodes, value);
        nodes.add(node);
        numNodes++;
    }

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

    public Node getNode(String name){
        for(Node n: nodes){
            if(n.getName().equals(name)){
                return n;
            }
        }
        return null;
    }

    public void editNode(String name, String newName) throws Exception {
        Node node = getNode(name);
        if(node == null){
            throw new Exception("Node does not exist");
        }
        node.setName(newName);
    }

    public void editNode(int id, String newName) throws Exception {
        Node node = getNode(id);
        if(node == null){
            throw new Exception("Node does not exist");
        }
        node.setName(newName);
    }

    public void editLink(String name1, String name2, double newValue) throws Exception {
        Link link = getLink(name1, name2);
        if(link == null){
            throw new Exception("Link does not exist");
        }
        link.setValue(newValue);
    }

    public void editLink(int id1, int id2, double newValue) throws Exception {
        Link link = getLink(id1, id2);
        if(link == null){
            throw new Exception("Link does not exist");
        }
        link.setValue(newValue);
    }

    public Node getNode(int id){
        if(id >= numNodes || id < 0) {
            return null;
        }
        return nodes.get(id);
    }

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

    private void reNumberNodes(){
        for(int i = 0; i < numNodes; i ++){
            nodes.get(i).setId(i);
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public int getNumLinks() {
        return numLinks;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public void setNumLinks(int numLinks) {
        this.numLinks = numLinks;
    }

    @Override
    public String toString() {
        String finalString = "";
        finalString = finalString.concat("____Nodes_____\n");
        finalString = finalString.concat("Name     ID\n");
        finalString = finalString.concat("-------- --\n");
        for(Node n: nodes){
            finalString = finalString.concat(n.toString() + "\n");
        }

        finalString = finalString.concat("\n");
        finalString = finalString.concat("____Links_____\n");
        finalString = finalString.concat("Link    Val\n");
        finalString = finalString.concat("------- ------\n");
        for(Link l: links){
            finalString = finalString.concat(l.toString() + "\n");
        }

        return finalString;
    }
}
