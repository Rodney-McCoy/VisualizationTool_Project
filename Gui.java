import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import java.lang.Thread;

public class Gui {
    public JPanel userPanel;
    private JButton floydWarshallButton;
    private JButton bellmanFordButton;
    private JButton dijkstraButton;
    private JTextArea feedback;
    private JButton buttonAddNode;
    private JButton buttonAddEdge;
    private JButton buttonDelete;

    public int setting = 0;
    public int exit = 0;

    public Gui() {
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
    }

    private void createUIComponents() {
        ImageIcon addNode = new ImageIcon ("addNode.png");
        buttonAddNode = new JButton(addNode);

        ImageIcon addEdge = new ImageIcon("addEdge.png");
        buttonAddEdge = new JButton(addEdge);

        ImageIcon delete = new ImageIcon("delete.png");
        buttonDelete = new JButton(delete);

    }
}
