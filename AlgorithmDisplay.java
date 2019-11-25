import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorithmDisplay {
    public JPanel mainPanel;
    private JPanel algDisplay;
    private JTextArea feedback;
    private JButton button1;
    private JButton restartButton;
    public AnimationArea animationArea2;
    private Graph userGraph;

//    private void createUIComponents() {
//        animationArea2 = new AnimationArea(this.feedback, new Graph());
//    }

    public AlgorithmDisplay(Graph userGraph) {
        this.userGraph=userGraph;
        animationArea2.setFeedback(feedback);
        feedback.setEnabled(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationArea2.paint(animationArea2.getGraphics());
                feedback.setText("Hello");
            }
        });
    }

    private void createUIComponents() {
        animationArea2 = new AnimationArea(userGraph);
    }
}
