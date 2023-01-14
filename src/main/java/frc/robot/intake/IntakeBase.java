package frc.robot.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class IntakeBase extends SubsystemBase {
  private final TalonSRX indexMotor = new TalonSRX(RobotMap.kIndexer);
  private final TalonSRX intakeRollers = new TalonSRX(RobotMap.kIntakeRollers);

  public void setIndexPercent(double percent) {
    indexMotor.set(ControlMode.PercentOutput, percent);
  }

  public void setIntakeRollersPercent(double percent) {
    intakeRollers.set(ControlMode.PercentOutput, percent);
  }
}
