// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class USB {
        public static final int leftJoystick = 0;
        public static final int rightJoystick = 1;
        public static final int xBoxController = 2;
    }

    public static final class DriveTrain {
        // Number identification for the CAN motors
        public static final int leftFrontDriveMotor = 20;
        public static final int leftRearDriveMotor = 21;
        public static final int rightFrontDriveMotor = 22;
        public static final int rightRearDriveMotor = 23;

        public enum MotorPosition {
            LEFT_FRONT,
            LEFT_REAR,
            RIGHT_FRONT,
            RIGHT_REAR
        }

        public static final double kMaxVelocityMetersPerSecond = Units.feetToMeters(16);
        public static final double kWheelDiameterMeters = Units.feetToMeters(0.5);
    }
}
