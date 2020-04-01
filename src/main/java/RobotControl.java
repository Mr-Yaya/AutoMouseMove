import java.awt.*;
import java.util.Random;

import static java.awt.MouseInfo.getPointerInfo;

public class RobotControl
{
    private final Robot robot;
    private volatile boolean running = false;
    private volatile int time = 2;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        System.out.println("Set time to "+ time);
        this.time = time;
    }

    RobotControl()
    {
        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    void startMoving()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                running = true;
                try {
                    performMove();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    void stopMoving()
    {
        System.out.println("Stopping");
        running = false;
    }

    private void performMove() throws Exception {
        boolean right = true;
        System.out.println("Starting");
        while (running)
        {
            robot.delay(1000 * this.getTime());
            right = moveMouse(right);
        }
    }

    public boolean moveMouse(boolean right) throws Exception{
        PointerInfo mouseInfo = getPointerInfo();
        int x = mouseInfo.getLocation().x;
        int y = mouseInfo.getLocation().y;
        if(right) {
            x = x + 50;
            right = false;
        }
        else {
            x = x - 50;
            right = true;
        }

        System.out.println("Moving Mouse");
        robot.mouseMove(x,y);
        return right;
    }
}