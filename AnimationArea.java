import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class AnimationArea extends JPanel{
    private final int nodeDiameter = 50;
    private JTextArea feedback;
    private Graph graph;
    private int firstNode;
    private int secondNode;


    public Graph getGraph() {
        return graph;
    }


    /**
     * AnimationArea- contructor
     * @param graph - what graph is it updating
     */
    AnimationArea(Graph graph) {
        super();
        this.graph = graph;
    }

    /**deleteComponents- uses mouse to delete a node or edge
     */
    void deleteComponents(int xMouseClick, int yMouseClick) {
        //if find a node in that location, delete it
        int num = findNode(xMouseClick, yMouseClick, graph);
        if (num != -1) {
            try {
                graph.removeNode(num);
                paint(this.getGraphics());
            } catch (Exception e) {
                feedback.setText("" + e);
            }
        }
        //if didn't find a node, see if on an edge
        num = findEdge(xMouseClick, yMouseClick, graph);
        if (num != -1) {
            try {
                graph.removeLink(graph.getLinks().get(num).getNode1().getId(), graph.getLinks().get(num).getNode2().getId());
                    paint(this.getGraphics());
            } catch (Exception e) {
                feedback.setText("" + e);
            }
        }
    }

    /**
     * createNodes - create a node upon mouse click
     */
    void createNodes(int xMouseClick, int yMouseClick) {
        //if cannot find a node in that location, add one
        if (findNode(xMouseClick, yMouseClick, graph) == -1) {
            graph.addNode(xMouseClick, yMouseClick);
            paint(this.getGraphics());
        }
    }

    /**
     * createEdges- upon clicking 2 nodes, will add an edge
     */
    void createEdges(int xMouseClick, int yMouseClick){
        //find user selection of 1st node
        if(firstNode == -1) {
            firstNode = findNode(xMouseClick, yMouseClick, graph);
        }else{
            secondNode = findNode(xMouseClick, yMouseClick, graph);
            if(secondNode != -1 && secondNode != firstNode){
                try {
                    String input = JOptionPane.showInputDialog(this, "Enter a weight between 1 and 100");
                    try {
                        int weight = Integer.parseInt(input);
                        if(weight < 1 || weight > 100){
                            weight = 1;
                        }
                        graph.addLink(weight, firstNode, secondNode);

                    }catch(NumberFormatException e){
                        graph.addLink(1, firstNode, secondNode);
                    }
                    resetNodes();
                    paint(this.getGraphics());
                    feedback.setText("Edge placed, select 2 nodes to create another edge");
                } catch (IllegalArgumentException e) {
                    feedback.setText("" + e);
                }
            }
        }
    }

    /**
     * resetNodes- sets nodes found to -1
     */
    void resetNodes(){
        firstNode = -1;
        secondNode = -1;
    }

    /**
     * findEdge- iterate through all edges and see if mouse click is on one
     * @param x - x mouse click
     * @param y - y mouse click
     * @param graph - which graph to edit
     * @return - index of edge or -1 if none found
     */
    private int findEdge(int x, int y, Graph graph) {
        int link = -1;
        for(int i =0; i<graph.getNumLinks(); i++){

            //x= x distance between node 1 and 2, y = y distance between node 1 and two
            Vector330 link_1 = new Vector330(graph.getLinks().get(i).getNode1().getxPos() - graph.getLinks().get(i).getNode2().getxPos(),
                    graph.getLinks().get(i).getNode1().getyPos() - graph.getLinks().get(i).getNode2().getyPos());
            Vector330 link_2 = link_1.scale(-1);

            //x = x distance between node 1 and mouse, y = y distance between node 1 and mouse
            Vector330 mouse1 = new Vector330(x-graph.getLinks().get(i).getNode1().getxPos(), y - graph.getLinks().get(i).getNode1().getyPos());
            //x = x distance between node 2 and mouse, y = y distance between node 2 and mouse
            Vector330 mouse2 = new Vector330(x-graph.getLinks().get(i).getNode2().getxPos(), y - graph.getLinks().get(i).getNode2().getyPos());

            //checks if click is inside diamond between node 1 and node 2.
            //check if mouse click was same angle from node 1 to node 2 within a certain magnitude and tolerance
            double tolerance = 0.1;
            if(mouse1.magnitude() < link_1.magnitude()/2+3 ){
                //checks if normalized vector of mouse1 is equal to normalized of link1/2 within a tolerance
                if(Math.abs(mouse1.direction() - link_1.direction()) < tolerance || Math.abs(mouse1.direction() - link_2.direction()) < tolerance){
                    link = i;
                }
            }
            //check if mouse click was same angle from node 2 to node 1 within a certain magnitude and tolerance
            else if(mouse2.magnitude() < link_2.magnitude()/2+3){
                if(Math.abs(mouse2.direction() - link_1.direction()) < tolerance || Math.abs(mouse2.direction() - link_2.direction()) < tolerance){
                    link = i;
                }
            }
        }
        return link;
    }

    /**
     * drawNodes- goes through all nodes and draws each node
     * @param g- graphics
     * @param graph- graph
     */
    private void drawNodes(Graphics g, Graph graph) {
        //iterate through every node in graph, draw to graphics
        for (int i = 0; i < graph.getNumNodes(); ++i) {
            g.setColor(Color.RED);
            g.fillOval(graph.getNodes().get(i).getxPos() - this.nodeDiameter / 2, graph.getNodes().get(i).getyPos() - this.nodeDiameter / 2, this.nodeDiameter, this.nodeDiameter);
            g.setColor(Color.BLACK);
            g.drawString(graph.getNodes().get(i).getName(), graph.getNodes().get(i).getxPos()-5, graph.getNodes().get(i).getyPos()+5);
        }
    }

    /**
     * drawEdges- goes through all edges and draws each edge
     * @param g-     graphics
     * @param graph- graph
     */
    private void drawEdges(Graphics g, Graph graph) {
        //iterate through every edge in graph, draw to graphics
        //start x = node1, start y = node2
        for (int i = 0; i < graph.getNumLinks(); ++i) {
            Link link = graph.getLinks().get(i);
            g.setColor(Color.BLACK);
            g.drawLine(link.getNode1().getxPos(), link.getNode1().getyPos(), link.getNode2().getxPos(), link.getNode2().getyPos());
            int middleX = (link.getNode1().getxPos() - link.getNode2().getxPos())/2 + 10;
            int middleY = (link.getNode1().getyPos() - link.getNode2().getyPos())/2 + 10;
            g.setColor(Color.BLUE);
            g.drawString("" + (int)link.getValue(), link.getNode2().getxPos() + middleX, link.getNode2().getyPos() + middleY);
        }
    }

    /**
     * findNode- sees if mouse click is on a node
     *
     * @param x-     mouse click x
     * @param y-     mouse click y
     * @param graph- graph
     * @return - -1 if mouse is on no node, else returns node index
     */
    private int findNode(int x, int y, Graph graph) {
        for (int i = 0; i < graph.getNodes().size(); ++i) {
            if (this.nodeDiameter / 2 > Math.abs(x - (graph.getNodes().get(i).getxPos()))) {
                if (this.nodeDiameter / 2 > Math.abs(y - (graph.getNodes().get(i).getyPos()))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * paint- copies graphics to screen
     * @param g - the graphics
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.WHITE);
        //g.setFont(new Font ("TimesRoman", Font.BOLD, 15));
        drawEdges(g, graph);
        drawNodes(g, graph);
    }

    /**
     * Dimension- creates dimensions of Jlabel
     * @return - the dimensions
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }

    public void setFeedback(JTextArea feedback){
        this.feedback = feedback;
    }

}