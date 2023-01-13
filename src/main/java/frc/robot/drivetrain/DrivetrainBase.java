package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class DrivetrainBase extends SubsystemBase {
    private final MotorControllerGroup left;
    private final MotorControllerGroup right;

    public DrivetrainBase() {
        WPI_TalonFX frontLeft = new WPI_TalonFX(RobotMap.kDrivetrainFrontLeft);
        WPI_TalonFX frontRight = new WPI_TalonFX(RobotMap.kDrivetrainFrontRight);
        WPI_TalonFX backLeft = new WPI_TalonFX(RobotMap.kDrivetrainBackLeft);
        WPI_TalonFX backRight = new WPI_TalonFX(RobotMap.kDrivetrainBackRight);

        this.left = new MotorControllerGroup(frontLeft, backLeft);
        this.right = new MotorControllerGroup(frontRight, backRight);

        this.left.setInverted(true);
    }

    public void setPercent(double leftPercent, double rightPercent) {
        this.left.set(leftPercent);
        this.right.set(rightPercent);
    }
}
