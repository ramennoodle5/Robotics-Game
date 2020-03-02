import java.awt.*;

public class Robot {

    public int xpos, ypos, width, height;
    public double velocity = 0, terminalvel = 3, acceleration = .2, drag = .1, ythrust, xthrust, angle;
    public boolean isThrusting, isRight, isLeft;
    public boolean isAlive;
    public Rectangle rec;

    public Robot(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        width = 20;
        height = 20;
        isThrusting = false;
        isAlive = true;
        angle = 90;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void reorient(double pAngle){
        angle += pAngle;
        if(angle<0){
            angle += 360;
        }
        if(angle>360)
        {
            angle = 360-angle;
        }
    }

    public void thrust() {

        if (velocity < terminalvel) {
            velocity += (acceleration - drag);
        }
        ythrust = Math.sin(Math.toRadians(angle)) * velocity;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity;
        ypos += ythrust;
        xpos += xthrust;
        rec = new Rectangle(xpos, ypos, width, height);
    }

}
