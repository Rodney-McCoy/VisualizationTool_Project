import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Thread;

public class Gui extends JFrame{
    public JPanel userPanel;
    private JButton floydWarshallButton;
    private JButton bellmanFordButton;
    private JButton dijkstraButton;
    public JTextArea feedback;
    private JButton buttonAddNode;
    private JButton buttonAddEdge;
    private JButton buttonDelete;
    public AnimationArea animationArea1;
    private JPanel animationPanel;

    public int setting = 0;
    public int exit = 0;

    public Gui() {
        feedback.setEnabled(false);
        feedback.setDisabledTextColor(Color.BLACK);
        buttonAddNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setting = 1;
                feedback.setText("Add node selected.  Click anywhere on screen to add node.");
            }
        });
        buttonAddEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setting = 2;
                feedback.setText("Add edge selected.  Click any node, then click another node to add edge.");
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setting = 3;
                feedback.setText("Delete selected.  Click any node or edge to delete them.");
            }
        });
        bellmanFordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //run bellmanFord
                //draw graph returned from bellman Ford in new window
                feedback.setText("Bellman Ford selected.  Not added yet");
            }
        });
        dijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //run dijkstra's
                //draw graph returned from dijkstra's in new window
                feedback.setText("Dijkstra's selected.  Not added yet");
            }
        });
        floydWarshallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //run floyd-warshall's
                //draw graph returned from floyd-warshall's
                feedback.setText("Floyd-warshall selected.  Not added yet");
            }
        });
        animationPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Press");
                animationArea1.setMouseClickX(e.getX());
                animationArea1.setMouseClickY(e.getY());
                animationArea1.setMouseClicked();
                //animationArea1.paint(animationArea1.getGraphics());
                System.out.println("Pr @ "+ e.getX() +", " + e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Re");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("En");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Ex");
            }
        });
        animationArea1.t.start();
    }

    private void createUIComponents() {
        ImageIcon addNode = new ImageIcon ("addNode.png");
        buttonAddNode = new JButton(addNode);

        ImageIcon addEdge = new ImageIcon("addEdge.png");
        buttonAddEdge = new JButton(addEdge);

        ImageIcon delete = new ImageIcon("delete.png");
        buttonDelete = new JButton(delete);

        animationArea1 = new AnimationArea(this, new Graph());
    }

    public AnimationArea getAnimationArea(){ return animationArea1;}

    /**
     * main- runs the GUI
     * @param args - none
     */
    public static void main(String[] args) {
        JFrame userFrame = new
                JFrame("Shortest Path Algorithms");
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
                    System.out.println("Error in main run: " + e);
                }
            }
        });
    }
}
