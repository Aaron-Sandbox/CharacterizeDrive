package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  
  public static Drivetrain drivetrain;
  public static OI oi;

  @Override
  public void robotInit() {
    drivetrain = Drivetrain.getInstance();

    oi = new OI();

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testPeriodic() {
  }
}
