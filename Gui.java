import jdk.jshell.spi.ExecutionControlProvider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
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

    public Gui() {
        animationArea1.setFeedback(feedback);
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
                try{
                    char[] sourceNodeC;
                    String sourceNodeS = JOptionPane.showInputDialog(dijkstraButton, "Enter the Char of the Source Node");
                    sourceNodeC = sourceNodeS.toUpperCase().toCharArray();
                    if(sourceNodeC.length != 1){
                        throw new IllegalArgumentException("Please enter only one character");
                    }

                    JFrame algFrame = new JFrame("Bellman Ford's Solution");
                    AlgorithmDisplay algPanel = new AlgorithmDisplay(Algorithms.bellmanFord(animationArea1.getGraph().clone(),
                            sourceNodeC[0] - 65));
                    algFrame.setContentPane(algPanel.mainPanel);
                    algFrame.setLocation(0,0);
                    algFrame.setSize(500,500);
                    algFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    algFrame.pack();
                    algFrame.setVisible(true);

                }catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception){
                    System.out.println(exception);
                }
            }
        });

        dijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //run dijkstra's
                //draw graph returned from dijkstra's in new window
                try{
                    char[] startNodeC;
                    String startNodeS = JOptionPane.showInputDialog(dijkstraButton, "Enter the Char of the Starting Node");
                    startNodeC = startNodeS.toUpperCase().toCharArray();
                    if(startNodeC.length != 1){
                        throw new IllegalArgumentException("Please enter only one character");
                    }
                    char[] endNodeC;
                    String endNodeS = JOptionPane.showInputDialog(dijkstraButton, "Enter the Char of the Ending Node");
                    endNodeC = endNodeS.toUpperCase().toCharArray();
                    if(endNodeC.length != 1){
                        throw new IllegalArgumentException("Please enter only one character");
                    }

                    JFrame algFrame = new JFrame("Dijkstra's Solution");
                    AlgorithmDisplay algPanel = new AlgorithmDisplay(Algorithms.dijkstra(animationArea1.getGraph().clone(),
                            startNodeC[0] - 65, endNodeC[0] - 65));
                    algFrame.setContentPane(algPanel.mainPanel);
                    algFrame.setLocation(0,0);
                    algFrame.setSize(500,500);
                    algFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    algFrame.pack();
                    algFrame.setVisible(true);

                }catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception){
                    System.out.println(exception);
                }
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
        animationPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(setting == 1){
                    animationArea1.resetNodes();
                    animationArea1.createNodes(e.getX(),e.getY());
                }else if(setting == 2){
                    animationArea1.createEdges(e.getX(),e.getY());
                }else if(setting == 3){
                    animationArea1.resetNodes();
                    animationArea1.deleteComponents(e.getX(),e.getY());
                }
            }
        });
    }

    private void createUIComponents() {
        ImageIcon addNode = new ImageIcon ("src/addNode.png");
        buttonAddNode = new JButton(addNode);

        ImageIcon addEdge = new ImageIcon("src/addEdge.png");
        buttonAddEdge = new JButton(addEdge);

        ImageIcon delete = new ImageIcon("src/delete.png");
        buttonDelete = new JButton(delete);

        animationArea1 = new AnimationArea(new Graph());
    }

    /**
     * main- runs the GUI
     * @param args - none
     */
    public static void main(String[] args) {
        JFrame userFrame = new
                JFrame("Shortest Path Algorithms");
        Gui userGui = new Gui();
        userFrame.setContentPane(userGui.userPanel);
        userFrame.setLocation(0,0);
        userFrame.setSize(500,1000);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.pack();
        userFrame.setVisible(true);
    }
}
