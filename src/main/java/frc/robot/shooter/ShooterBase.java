package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants.HardwareIDs;

public class ShooterBase extends SubsystemBase {
  private final TalonSRX shooterLeader = new TalonSRX(HardwareIDs.kShooterFlyLeader);
  private final TalonSRX shooterFollower = new TalonSRX(HardwareIDs.kShooterFlyFollower);
  private final TalonSRX kickup = new TalonSRX(HardwareIDs.kKickupWheel);

  public ShooterBase() {
    this.shooterFollower.follow(shooterLeader);
  }

  public void setFlywheelPercent(double percent) {
    this.shooterLeader.set(ControlMode.PercentOutput, percent);
  }

  public void setKickupPercent(double percent) {
    this.kickup.set(ControlMode.PercentOutput, percent);
  }
}
