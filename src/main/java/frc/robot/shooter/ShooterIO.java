package frc.robot.shooter;

import frc.lib.LoggedIO;
import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO extends LoggedIO<ShooterIO.ShooterInputs> {
  @AutoLog
  public class ShooterInputs {
    public double[] TemperatureCelcius = new double[] {};
    public double[] CurrentAmps = new double[] {};
  }

  default void setShooterVoltage(double voltage) {}

  default void setKickupVoltage(double voltage) {}
}
