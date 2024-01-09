import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Robot;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DefinitelyNotAfk {

    public static void main(String[] args) {
        // Create and configure the JFrame
        JFrame frame = new JFrame("DefinitelyNotAfk");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 100);

        // Create and configure the JLabel
        JLabel label = new JLabel("This is a message");
        frame.add(label);

        // Set up the close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Set the JFrame visible
        frame.setVisible(true);

        try {
            // Create the Robot instance
            Robot robot = new Robot();

            // Set up the timer to move the mouse and update the label
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(new MouseMoveTask(robot, label), 0, 60 * 1000);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static class MouseMoveTask extends TimerTask {
        private final Robot robot;
        private final JLabel label;

        public MouseMoveTask(Robot robot, JLabel label) {
            this.robot = robot;
            this.label = label;
        }

        @Override
        public void run() {
            try {
                int xOffset = 50;
                int yOffset = 50;

                int currentX = (int) java.awt.MouseInfo.getPointerInfo().getLocation().getX();
                int currentY = (int) java.awt.MouseInfo.getPointerInfo().getLocation().getY();

                robot.mouseMove(currentX + xOffset, currentY + yOffset);

                label.setText("Mouse moved!");

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
