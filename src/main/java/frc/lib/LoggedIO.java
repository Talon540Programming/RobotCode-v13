package frc.lib;

public interface LoggedIO<T> {
  default void updateInputs(T inputs) {}
}
