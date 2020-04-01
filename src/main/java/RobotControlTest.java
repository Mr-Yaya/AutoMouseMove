import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class RobotControlTest
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setTitle("Branleur Tool");
        f.setPreferredSize(new Dimension(520, 100));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final RobotControl robotControl = new RobotControl();

        f.getContentPane().setLayout(new GridLayout(1,4,5,5));

        f.getContentPane().add(new JLabel("Enter refreshing time"));


        final JTextField tf = new JTextField("2",10);
        f.getContentPane().add(tf);

        final JButton startButton = new JButton("Start");
        f.getContentPane().add(startButton);

        final JButton stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        f.getContentPane().add(stopButton);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String value = tf.getText();
                try {
                    robotControl.setTime(Integer.parseInt(value));
                }catch (Exception ignored) {

                }
            }
        });

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startButton.setEnabled(false);
                tf.setEnabled(false);
                stopButton.setEnabled(true);
                robotControl.startMoving();
            }
        });

        stopButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                robotControl.stopMoving();
                startButton.setEnabled(true);
                tf.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}