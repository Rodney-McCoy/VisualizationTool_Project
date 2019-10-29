import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationArea {

    private final int nodeDiameter = 50;

    public void paintNode(Graphics g, int x, int y){
        g.fillOval(x, y, nodeDiameter,nodeDiameter);
    }

    /**
     * animate- loop that draws the graph
     * @param g- graphics
     * @param panel- panel where things are drawn
     * @param gui- gui
     * @param graph- graph that will be drawn on panel
     */

    public void animate(Graphics g, DrawingPanel panel, Gui gui, Graph graph) throws Exception {
        panel.setBackground(Color.WHITE);

        while(gui.exit != 1) {
            panel.copyGraphicsToScreen();
            panel.setBackground(Color.WHITE);
            drawNodes(g, graph);
            drawEdges(g, graph);

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("Error in AnimationArea while: " + e);
            }

            if(gui.setting == 1) {
                //create Node
                if (panel.mouseClickHasOccurred(0)) {
                    int clickX = panel.getMouseClickX(0);
                    int clickY = panel.getMouseClickY(0);

                    //if cannot find a node in that location, add one
                    if(findNode(clickX, clickY, graph) == -1) {
                        graph.addNode(clickX, clickY);
                    }
                }
            }

            if(gui.setting == 2) {
                //create Edge
                //should: check click proximity to node, if in range make that node part of the edge
                //        do this for second node
                //        then print out the line from first node x,y and second node x,y
                //Proof of Concept:
                if (panel.mouseClickHasOccurred(0)) {
                    int firstX = panel.getMouseClickX(0);
                    int firstY = panel.getMouseClickY(0);
                    //findNode(firstX, firstY, graph);
                    while(!panel.mouseClickHasOccurred(0) && gui.setting == 2) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            System.out.println("Error in AnimationArea setting == 2: " + e);
                        }
                    }
                    g.drawLine(firstX, firstY, panel.getMouseClickX(0), panel.getMouseClickY(0));
                }

            }

            if(gui.setting == 3){
                //Delete node or Edge
                //should: Check mouse click proximity to node
                int num = findNode(panel.getMouseClickX(0), panel.getMouseClickY(0), graph);
                if(num != -1) {
                    //            if close, delete node
                    graph.removeNode(num);
                }else {
                    //        Then if not on node, check proximity to edge
                    num = findEdge(panel.getMouseClickX(0), panel.getMouseClickY(0), graph);
                    //        Check normalized vector from node xy to mouse xy.  then check if magnitude is smaller
                    //            if angle is similiar and magnitude smaller, delete edge
                    if(num != -1){
                        graph.removeLink(graph.getLinks().get(num).getNode1().getId(), graph.getLinks().get(num).getNode2().getId());
                    }
                }

                //Proof of Concept:
                if(panel.mouseClickHasOccurred(0)){
                    g.setColor(Color.WHITE);
                    paintNode(g, panel.getMouseClickX(0), panel.getMouseClickY(0));
                }
            }
        }
    }

    /**
     * drawNodes- goes through all nodes and draws each node
     * @param g- graphics
     * @param graph- graph
     */
    private void drawNodes(Graphics g, Graph graph){
        //iterate through every node in graph, draw to graphics
        for(int i = 0; i < graph.getNumNodes(); ++i){
            g.setColor(Color.RED);
            g.fillOval(graph.getNodes().get(i).xPos - this.nodeDiameter/2, graph.getNodes().get(i).yPos - this.nodeDiameter/2, this.nodeDiameter, this.nodeDiameter);
        }
    }

    /**
     * drawEdges- goes through all edges and draws each edge
     * @param g- graphics
     * @param graph- graph
     */
    private void drawEdges(Graphics g, Graph graph){
        //iterate through every edge in graph, draw to graphics
        //start x = node1, start y = node2
        for(int i = 0; i < graph.getNumLinks(); ++i){
            g.setColor(Color.BLACK);
            g.drawLine(graph.getLinks().get(i).getNode1().xPos, graph.getLinks().get(i).getNode1().yPos, graph.getLinks().get(i).getNode2().xPos,graph.getLinks().get(i).getNode2().yPos);
        }
    }

    /**
     * findNode- sees if mouse click is on a node
     * @param x- mouse click x
     * @param y- mouse click y
     * @param graph- graph
     * @return - -1 if mouse is on no node, else returns node index
     */
    private int findNode(int x, int y, Graph graph){
        for(int i = 0; i < graph.getNodes().size(); ++i){
            if(this.nodeDiameter > Math.abs(x - (graph.getNodes().get(i).xPos))){
                if(this.nodeDiameter > Math.abs(y - (graph.getNodes().get(i).yPos))){
                    return i;
                }
            }
        }
        return -1;
    }

    private int findEdge(int x, int y, Graph graph){
        int link = -1;
        for(int i =0; i<graph.getNumLinks(); i++){

            Vector330Class link_1 = new Vector330Class(graph.getLinks().get(i).getNode1().xPos-graph.getLinks().get(i).getNode2().xPos, graph.getLinks().get(i).getNode1().yPos-graph.getLinks().get(i).getNode2().yPos);
            Vector330Class link_2 = link_1.scale(-1);
            Vector330Class mouse = new Vector330Class(x-graph.getLinks().get(i).getNode1().xPos, y-graph.getLinks().get(i).getNode1().yPos);

            if(mouse.magnitude() < link_1.magnitude()){
                if(mouse.normalize().equals(link_1.normalize()) || mouse.normalize().equals(link_2.normalize())){
                    link = i;
                }
            }

        }
        return link;
    }

}
