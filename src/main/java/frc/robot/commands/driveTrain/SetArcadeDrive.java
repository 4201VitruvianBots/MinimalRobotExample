// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrain;

import java.util.DoubleSummaryStatistics;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;

public class SetArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  // Declare subsystem
  DriveTrain m_driveTrain;
  
  DoubleSupplier m_throttle;
  DoubleSupplier m_turn;

  /**
   *
   */
  public SetArcadeDrive(DriveTrain driveTrain, DoubleSupplier throttle, DoubleSupplier turn) {
    // Copy constructor parameters to class variables
    m_driveTrain = driveTrain;
    m_throttle = throttle;
    m_turn = turn;
    
    //Add subsystem requirements
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.setMotorArcadeDrive(m_throttle.getAsDouble(), m_turn.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
