package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotConfig;
import frc.robot.constants.Constants.HardwareIDs;

public class DrivetrainBase extends SubsystemBase {
  private final MotorControllerGroup left;
  private final MotorControllerGroup right;

  private double kMaxOutput = RobotConfig.kMaxDrivetrainPercent;

  public DrivetrainBase() {
    WPI_TalonFX frontLeft = new WPI_TalonFX(HardwareIDs.kDrivetrainFrontLeft);
    WPI_TalonFX frontRight = new WPI_TalonFX(HardwareIDs.kDrivetrainFrontRight);
    WPI_TalonFX backLeft = new WPI_TalonFX(HardwareIDs.kDrivetrainBackLeft);
    WPI_TalonFX backRight = new WPI_TalonFX(HardwareIDs.kDrivetrainBackRight);

    this.left = new MotorControllerGroup(frontLeft, backLeft);
    this.right = new MotorControllerGroup(frontRight, backRight);

    this.left.setInverted(true);
  }

  public void setPercent(double leftPercent, double rightPercent) {
    this.left.set(leftPercent * kMaxOutput);
    this.right.set(rightPercent * kMaxOutput);
  }
}
