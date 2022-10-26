// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import com.ctre.phoenix.motorcontrol.can.MotControllerJNI;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  // Initialize the motors
  VictorSPX[] motors = {
    new VictorSPX(Constants.DriveTrain.leftRearDriveMotor),
    new VictorSPX(Constants.DriveTrain.rightRearDriveMotor),
    new VictorSPX(Constants.DriveTrain.leftFrontDriveMotor),
    new VictorSPX(Constants.DriveTrain.rightFrontDriveMotor)
  };

  /*    _____
    2 o|  ^  |o 3
       |     |
    0 o|_____|o 1
  */

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    configureCTREMotors();
  }

  public void configureCTREMotors() {
    for (VictorSPX motor : motors) {
      // You don't have to type this all out, I copy-pasted it
      motor.configFactoryDefault();

      motor.configOpenloopRamp(0.1);
      motor.configClosedloopRamp(0.1);
      motor.setNeutralMode(NeutralMode.Brake);
      motor.configForwardSoftLimitEnable(false);
      motor.configReverseSoftLimitEnable(false);
      motor.config_kP(0, 0.1);
      motor.configOpenloopRamp(0.25);
      motor.configClosedloopRamp(0.1);

      // motor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 60, 0.1)); maybe try using configAllSettings(forwardLimitSwitchDifferent) ?


      motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

      
    }

    /*    _____
      2 o|  ^  |o 3
         |     |
      0 o|_____|o 1
    */

    // Inverting right motors
    motors[1].setInverted(true);
    motors[3].setInverted(true);

    // Left front motor follows left rear
    motors[2].set(ControlMode.PercentOutput, motors[0].getBaseID());

    // Right front motor follows right rear
    motors[3].set(ControlMode.PercentOutput, motors[1].getBaseID());
   

  }

  public void setWheelSpeeds(double leftSpeed, double rightSpeed) {
    setMotorPercentOutput(
      leftSpeed / Constants.DriveTrain.kMaxVelocityMetersPerSecond,
      rightSpeed / Constants.DriveTrain.kMaxVelocityMetersPerSecond
    );
  }
 

  

  /**
   * On a scale from -1.0 to 1.0, how fast is each wheel spinning?
   */
  public void setMotorPercentOutput(double leftOutput, double rightOutput) {
    motors[0].set(ControlMode.PercentOutput, leftOutput);
    motors[1].set(ControlMode.PercentOutput, rightOutput);
  }

  /**
   * throttle: forward/backward -1.0 to 1.0
   * turn: -1.0 (counterclockwise) to 1.0 (clockwise)
   */
  public void setMotorArcadeDrive(double throttle, double turn) {
    double leftOutput = throttle + turn;
    double rightOutput = throttle - turn;

    // If either output has an absolute value greater than 1
    if (Math.abs(leftOutput) > 1 || Math.abs(rightOutput) > 1) {
      double reductionFactor = Math.max(Math.abs(leftOutput), Math.abs(rightOutput));
      leftOutput /= reductionFactor;
      // Same as: leftOutput = leftOutput / reductionFactor;
      rightOutput /= reductionFactor;
    }

    setMotorPercentOutput(leftOutput, rightOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
