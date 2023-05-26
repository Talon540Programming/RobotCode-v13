package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drivetrain.DriveBase;
import frc.robot.oi.DriverInterface;
import frc.robot.oi.DriverInterface.DriveMode;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;
import org.littletonrobotics.junction.networktables.LoggedDashboardNumber;

public class DriveControl extends CommandBase {
  private final DriveBase m_driveBase;

  private final DriverInterface m_driverInterface;

  private final LoggedDashboardChooser<DriveMode> m_driveModeChooser =
      new LoggedDashboardChooser<>("Drive/DriveMode");

  private final LoggedDashboardNumber m_leftMultiplier =
      new LoggedDashboardNumber("Drive/LeftDriveMultiplier", 1);
  private final LoggedDashboardNumber m_rightMultiplier =
      new LoggedDashboardNumber("Drive/RightDriveMultiplier", 1);

  public DriveControl(DriveBase driveBase, DriverInterface driverInterface) {
    m_driveBase = driveBase;
    m_driverInterface = driverInterface;

    addRequirements(m_driveBase);

    m_driveModeChooser.addDefaultOption("Arcade", DriveMode.Arcade);
    m_driveModeChooser.addOption("Tank / Differential", DriveMode.Tank);
  }

  @Override
  public void execute() {
    DriveMode mode = m_driveModeChooser.get();

    switch (mode) {
      case Arcade -> m_driveBase.arcadeDrivePercent(
          m_driverInterface.getLeftPercent(mode) * m_leftMultiplier.get(),
          m_driverInterface.getRightPercent(mode) * m_rightMultiplier.get());
      case Tank -> m_driveBase.tankDrivePercent(
          m_driverInterface.getLeftPercent(mode) * m_leftMultiplier.get(),
          m_driverInterface.getRightPercent(mode) * m_rightMultiplier.get());
    }
  }
}
