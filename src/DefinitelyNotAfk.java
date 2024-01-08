import java.awt.AWTException;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

public class DefinitelyNotAfk {

    public static void main(String[] args) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new MouseMoveTask(), 0, 60 * 1000);
    }

    private static class MouseMoveTask extends TimerTask {
        @Override
        public void run() {
            try {
                Robot robot = new Robot();

                int xOffset = 50;
                int yOffset = 50;

                int currentX = (int) java.awt.MouseInfo.getPointerInfo().getLocation().getX();
                int currentY = (int) java.awt.MouseInfo.getPointerInfo().getLocation().getY();

                robot.mouseMove(currentX + xOffset, currentY + yOffset);

                System.out.println("Mouse moved!");

                Thread.sleep(1000);
            } catch (AWTException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
