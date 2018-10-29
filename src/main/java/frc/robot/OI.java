package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    public XboxController xboxcontroller;
    public JoystickButton ButtonA;
    public JoystickButton ButtonB;

    public OI(){
        xboxcontroller = new XboxController(1);


        ButtonA = new JoystickButton(xboxcontroller, 1);
        ButtonB = new JoystickButton(xboxcontroller, 2);
    }

    public double throttleValue() {
        //Controllers y-axes are natively up-negative, down-positive
        return -xboxcontroller.getY(Hand.kLeft);
    }
    public double turnValue() {
        return xboxcontroller.getX(Hand.kRight);
        
    }

}