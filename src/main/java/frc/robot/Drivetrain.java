package frc.robot;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drivetrain extends DriveSubsystem {

    private static Drivetrain instance = null;
    public static Drivetrain getInstance(){
        if(instance == null) instance = new Drivetrain();
        return instance;
    }

    public static final TalonSRX
            leftMotorA = new TalonSRX(3),
            leftMotorB = new TalonSRX(4),
            rightMotorA = new TalonSRX(2),
            rightMotorB = new TalonSRX(1);
    
    private static final TalonSRX[] motors = {leftMotorA, leftMotorB, rightMotorB, rightMotorA};
    private static final TalonSRX[] leftMotors = {leftMotorA, leftMotorB};
    private static final TalonSRX[] rightMotors = {rightMotorA, rightMotorB};

    private static final int kTimeout = 10;

    private Drivetrain(){
        leftMotorB.follow(leftMotorA);
        rightMotorB.follow(rightMotorA);

        Arrays.stream(leftMotors).forEach(motor -> motor.setInverted(true));
        Arrays.stream(rightMotors).forEach(motor -> motor.setInverted(false));

        for(TalonSRX motor : motors){

            //Current and voltage settings
            motor.enableCurrentLimit(false);
            motor.configVoltageCompSaturation(12, kTimeout);
            motor.enableVoltageCompensation(true);

        }

        //Left drivetrain encoder
        leftMotorA.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
        leftMotorA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        leftMotorA.setSensorPhase(false);
        
        //Right drivetrain encoder
        rightMotorA.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
        rightMotorA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        rightMotorA.setSensorPhase(false);
    }

    @Override
    public void drive(double left, double right) {
        leftMotorA.set(ControlMode.PercentOutput, left);
        rightMotorA.set(ControlMode.PercentOutput, right);
    }

    @Override
    public int[] getEncoderValues() {
        int[] encVals = {
            leftMotorA.getSelectedSensorPosition(0), 
            rightMotorA.getSelectedSensorPosition(0)
        };

        return encVals;
    }

    public double[] getVoltages(){
        double[] voltages = {
            leftMotorA.getMotorOutputVoltage(),
            rightMotorB.getMotorOutputVoltage()
        };
        return voltages;
    }

    @Override
    protected void initDefaultCommand() {
        
    }

}