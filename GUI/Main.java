import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
import java.lang.Thread;

public class Main {

    public static void main(String[] args) throws Exception {
        JFrame userFrame = new JFrame("Shortest Path Algorithms");
        Gui userGui = new Gui();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                userFrame.setContentPane(userGui.userPanel);
                userFrame.setLocation(0,0);
                userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                userFrame.pack();
                //userFrame.enableInitialControls(true);
                userFrame.setVisible(true);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        });
        DrawingPanel panel = new DrawingPanel(500,500);
        Graphics2D g = panel.getGraphics();
        AnimationArea drawing = new AnimationArea();
        Graph userGraph = new Graph();
        drawing.animate(g, panel, userGui, userGraph);
        panel.closeWindow();
        userFrame.dispose();
    }
}
