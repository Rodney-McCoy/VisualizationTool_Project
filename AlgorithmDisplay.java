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

//    private void createUIComponents() {
//        animationArea2 = new AnimationArea(this.feedback, new Graph());
//    }

    public AlgorithmDisplay() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedback.setText("Hello");
            }
        });
    }

    private void createUIComponents() {
        animationArea2 = new AnimationArea(this.feedback, new Graph());
    }
}
