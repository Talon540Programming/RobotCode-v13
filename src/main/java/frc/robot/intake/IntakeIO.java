package frc.robot.intake;

import frc.lib.LoggedIO;
import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO extends LoggedIO<IntakeIO.IntakeInputs> {
  @AutoLog
  public class IntakeInputs {
    public double[] TemperatureCelcius = new double[] {};
    public double[] CurrentAmps = new double[] {};
  }

  default void setRollerVoltage(double voltage) {}

  default void setIndexerVoltage(double voltage) {}
}
