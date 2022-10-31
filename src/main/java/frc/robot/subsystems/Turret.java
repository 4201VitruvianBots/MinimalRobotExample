// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboardTab;
import frc.robot.Constants;
// import frc.robot.Constants.CONTROL_MODE;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  /** Creates a new Turret. */
  private CANSparkMax m_motor;
  private XboxController xController;

  private PIDController turretPID;
  
  private final double Kp = 0.5; // Example values
  private final double Ki = 0.5; // Example values
  private final double Kd = 0.8; // Example values

  public Turret() {
    // Motor setup
    m_motor = new CANSparkMax(64, MotorType.kBrushless);
    m_motor.restoreFactoryDefaults();

    // Controller setup
    xController = new XboxController(0);

    // PID Control
    turretPID = new PIDController(Kp, Ki, Kd);

    turretPID.setSetpoint(0.24); // Example values
    turretPID.setTolerance(0.7); // Example values

    turretPID.enableContinuousInput(0, 1); // Example values
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPercentOutput() {
    m_motor.set(xController.getRawAxis(1));
  }
}
