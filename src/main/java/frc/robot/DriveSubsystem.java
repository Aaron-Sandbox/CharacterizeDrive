package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class DriveSubsystem extends Subsystem {
    public abstract void drive(double left, double right);
    public abstract int[] getEncoderValues();
    public abstract double[] getVoltages();
}