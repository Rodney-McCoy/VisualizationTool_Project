import javax.swing.*;
import java.awt.*;

public class AnimationArea extends JPanel implements Runnable{

    private final int nodeDiameter = 50;
    private int mouseClickX;
    private int mouseClickY;
    private boolean mouseClicked;
    private Gui gui;
    private Graph graph;
    private boolean update = false;
    Thread t;

    /**
     * AnimationArea- contructor
     * @param gui - what gui is running this
     * @param graph - what graph is it updating
     */
    AnimationArea(Gui gui, Graph graph){
        super();
        mouseClicked = false;
        this.gui = gui;
        this.graph = graph;
        this.t = new Thread(this, "AnimationArea");
    }


    /**
     * animate- allows the gui to update the graph
     */
    public void animate() {
            Graphics g = this.getGraphics();
            if(update){
                paint(g);
                update = false;
            }
            if (gui.setting == 1) {
                createNodes(this, graph);
            }
            if (gui.setting == 2) {
                createEdges(this, graph, gui);
            }
            if (gui.setting == 3) {
                deleteComponents(this, graph, gui);
            }
        snooze();
    }

    /**deleteComponents- uses mouse to delete a node or edge
     * @param panel - which panel to search for with mouse
     * @param graph - which graph to edit
     * @param gui - which gui to send feedback to
     */
    private void deleteComponents(AnimationArea panel, Graph graph, Gui gui) {
        if (panel.mouseClicked) {
            mouseClicked = false;
            int clickX = panel.getMouseClickX();
            int clickY = panel.getMouseClickY();

            //if find a node in that location, delete it
            int num = findNode(clickX, clickY, graph);
            if (num != -1) {
                try {
                    graph.removeNode(num);
                    update = true;
                } catch (Exception e) {
                    gui.feedback.setText("" + e);
                }
            }
            //if didn't find a node, see if on an edge
            num = findEdge(clickX, clickY, graph);

            if (num != -1) {
                try {
                    graph.removeLink(graph.getLinks().get(num).getNode1().getId(), graph.getLinks().get(num).getNode2().getId());
                    update = true;
                } catch (Exception e) {
                    gui.feedback.setText("" + e);
                }
            }
        }
    }

    /**
     * createNodes - create a node upon mouse click
     * @param panel - which panel to get input from
     * @param graph - which graph to edit
     */
    private void createNodes(AnimationArea panel, Graph graph) {
        if (panel.mouseClicked) {
            mouseClicked = false;
            System.out.println("Mouse Clicked mode 1");
            int clickX = panel.getMouseClickX();
            int clickY = panel.getMouseClickY();

            //if cannot find a node in that location, add one
            if (findNode(clickX, clickY, graph) == -1) {
                graph.addNode(clickX, clickY);
                update = true;
                System.out.println(graph.toString());
            }
        }
    }

    /** createEdges- upon clicking 2 nodes, will add an edge
     * @param panel - which panel to get input from
     * @param graph - which graph to upfate
     * @param gui - which gui to send feedback to
     */
    private void createEdges(AnimationArea panel, Graph graph, Gui gui){
        if (panel.mouseClicked) {
            mouseClicked = false;
            //find user selection of 1st node
            int firstNode;
            do{
                snooze();
                firstNode = findNode(panel.getMouseClickX(), panel.getMouseClickY(), graph);
            }while(firstNode == -1 && gui.setting == 2);

            gui.feedback.setText("Now click a second node for the edge");

            //find user selection of 2nd node
            int secondNode;
            do{
                snooze();
                secondNode = findNode(panel.getMouseClickX(), panel.getMouseClickY(), graph);

                //ensure don't select same node
                if(secondNode == firstNode){
                    secondNode = -1;
                }
            }while(secondNode == -1 && gui.setting == 2);

            //nodes selected, try to draw an edge
            try {
                graph.addLink(1, firstNode, secondNode);
                update = true;
                gui.feedback.setText("Edge placed, select 2 nodes to create another edge");
            } catch (Exception e){
                gui.feedback.setText("" + e);
            }
        }
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
            Vector330 link_1 = new Vector330(graph.getLinks().get(i).getNode1().xPos - graph.getLinks().get(i).getNode2().xPos,
                    graph.getLinks().get(i).getNode1().yPos - graph.getLinks().get(i).getNode2().yPos);
            Vector330 link_2 = link_1.scale(-1);

            //x = x distance between node 1 and mouse, y = y distance between node 1 and mouse
            Vector330 mouse1 = new Vector330(x-graph.getLinks().get(i).getNode1().xPos, y - graph.getLinks().get(i).getNode1().yPos);
            //x = x distance between node 2 and mouse, y = y distance between node 2 and mouse
            Vector330 mouse2 = new Vector330(x-graph.getLinks().get(i).getNode2().xPos, y - graph.getLinks().get(i).getNode2().yPos);

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
            g.fillOval(graph.getNodes().get(i).xPos - this.nodeDiameter / 2, graph.getNodes().get(i).yPos - this.nodeDiameter / 2, this.nodeDiameter, this.nodeDiameter);
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
            g.setColor(Color.BLACK);
            g.drawLine(graph.getLinks().get(i).getNode1().xPos, graph.getLinks().get(i).getNode1().yPos, graph.getLinks().get(i).getNode2().xPos, graph.getLinks().get(i).getNode2().yPos);
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
            if (this.nodeDiameter / 2 > Math.abs(x - (graph.getNodes().get(i).xPos))) {
                if (this.nodeDiameter / 2 > Math.abs(y - (graph.getNodes().get(i).yPos))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     *Getters and Setters for the mouse listener in Gui.java
     */
    public int getMouseClickX(){ return mouseClickX; }
    public int getMouseClickY(){ return  mouseClickY; }
    void setMouseClickX(int mouseClickX) { this.mouseClickX = mouseClickX; }
    void setMouseClickY(int mouseClickY) { this.mouseClickY = mouseClickY; }
    void setMouseClicked() { this.mouseClicked = true; }

    /**
     * snooze- makes the thread sleep, if no arguements
     *
     * @param errorMessage-     message to display in terminal
     * @param millis-     milliseconds to sleep
     */
    void snooze(String errorMessage, int millis){
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            System.out.println(errorMessage + e);
        }
    }

    /**
     * prints out no extra message, and sleeps 10 millis
     */
    void snooze(){
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    /**
     * paint- copies graphics to screen
     * @param g - the graphics
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.WHITE);
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

    /**
     * run- runs animationArea as a new thread, until exit JFrame
     */
    @Override
    public void run() {
        while(true) {
            animate();
            snooze("exiting thread "+ t, 10);
        }
    }
}