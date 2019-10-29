import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationArea {

    private final int nodeDiameter = 50;

    public void paintNode(Graphics g, int x, int y){
        g.fillOval(x, y, nodeDiameter,nodeDiameter);
    }

    public void animate(Graphics g, DrawingPanel panel, Gui gui){
        panel.setBackground(Color.WHITE);

        while(gui.exit != 1) {
            panel.copyGraphicsToScreen();
            //panel.setBackground(Color.WHITE);
            //draw Nodes
            //drawEdges
            try {
                Thread.sleep(100);
            } catch (Exception e) {}

            if(gui.setting == 1) {
                if (panel.mouseClickHasOccurred(0)) {
                    g.setColor(Color.RED);
                    //createNode
                    paintNode(g, panel.getMouseClickX(0) - nodeDiameter/2, panel.getMouseClickY(0) - nodeDiameter/2);
                }
            }

            if(gui.setting == 2) {
                if (panel.mouseClickHasOccurred(0)) {
                    g.setColor(Color.BLACK);
                    //createEdge
                    int firstX = panel.getMouseClickX(0);
                    int firstY = panel.getMouseClickY(0);

                    while(!panel.mouseClickHasOccurred(0) && gui.setting == 2) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {}
                    }
                    g.drawLine(firstX, firstY, panel.getMouseClickX(0), panel.getMouseClickY(0));
                }
            }

            if(gui.setting == 3){
                if(panel.mouseClickHasOccurred(0)){
                    g.setColor(Color.WHITE);
                    //deleteNode
                    //deleteEde
                    paintNode(g, panel.getMouseClickX(0), panel.getMouseClickY(0));
                }
            }
        }
    }

    private void drawNodes(){

    }
}
