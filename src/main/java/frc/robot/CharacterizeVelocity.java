package frc.robot;

import java.util.ArrayList;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CharacterizeVelocity extends Command{

    double endVoltage;

    @Override
    protected boolean isFinished() {
        return currentVoltage > endVoltage;
    }

    private double currentVoltage = 0;
    private SimpleRegression lRegression = new SimpleRegression();
    private SimpleRegression rRegression = new SimpleRegression();
    private final Timer timer = new Timer();

    private ArrayList<VoltageXPosition> leftSide = new ArrayList<VoltageXPosition>();
    private ArrayList<VoltageXPosition> rightSide = new ArrayList<VoltageXPosition>();

    //private ArrayList right = new ArrayList();

    public CharacterizeVelocity(double endVoltage){
        this.endVoltage = endVoltage;
        requires(Robot.drivetrain);
        setInterruptible(false);
    }

    protected void initialize(){
        timer.start();
    }

    private void changeSpeeds(double addVolts){
        currentVoltage += addVolts/12;
    }

    private void applySpeeds(double speed){
        Robot.drivetrain.drive(speed, speed);
    }

    protected void execute(){
        if(timer.hasPeriodPassed(1.0)){
            applySpeeds(this.currentVoltage);
            changeSpeeds(0.25);

            int[] encoders = Drivetrain.getInstance().getEncoderValues();
            double[] voltages = Drivetrain.getInstance().getVoltages();

            if(voltages[0] != 0 && !(encoders[0] < 100)){
                leftSide.add(new VoltageXPosition(voltages[0], encoders[0]));
            }
            if(voltages[1] != 0 && !(encoders[1] < 100)){
                rightSide.add(new VoltageXPosition(voltages[1], encoders[1]));
            }

            timer.reset();
        }
    }

    protected void end(){
        applySpeeds(0);

        for(VoltageXPosition data : leftSide){
            lRegression.addData(data.getVoltage(), data.getPosition());
        }

        for(VoltageXPosition data : rightSide){
            rRegression.addData(data.getVoltage(), data.getPosition());
        }

        System.out.println("Left side: Slope: " + lRegression.getSlope() + ", Intercept: " + lRegression.getIntercept() + ", Linearity: " + lRegression.getRSquare());
        System.out.println("Right side: Slope: " + rRegression.getSlope() + ", Intercept: " + rRegression.getIntercept() + ", Linearity: " + rRegression.getRSquare());

    }     

}
