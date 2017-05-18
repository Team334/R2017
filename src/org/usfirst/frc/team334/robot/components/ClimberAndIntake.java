package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;
import org.usfirst.frc.team334.robot.controls.Controls;

public class ClimberAndIntake extends Subsystem {

    private VictorSP climberIntakeMotor, climberIntakeMotor2;

    private Controls controls;

    public ClimberAndIntake(Controls controls) {
        this.controls = controls;

        climberIntakeMotor = new VictorSP(Constants.CLIMBER_INTAKE);
        climberIntakeMotor2 = new VictorSP(Constants.CLIMBER_INTAKE_2);
    }

    @Override
    protected void initDefaultCommand() {}

//    public void climbUpAndIntake() {
//        climberIntakeMotor.set(Constants.CLIMB_INTAKE_SPEED);
//    }

    public void climbUp() {
        climberIntakeMotor.set(Constants.CLIMB_SPEED);
        climberIntakeMotor2.set(-Constants.CLIMB_SPEED);
    }

    public void climbDown() {
        climberIntakeMotor.set(-Constants.CLIMB_SPEED);
        climberIntakeMotor2.set(Constants.CLIMB_SPEED);
    }

//    public void climbDownAndSpitBalls() {
//        climberIntakeMotor.set(-Constants.CLIMB_INTAKE_SPEED);
//    }

    public void intakeIn() {
        climberIntakeMotor.set(Constants.INTAKE_SPEED);
        climberIntakeMotor2.set(-Constants.INTAKE_SPEED);
    }

    public void intakeOut() {
        climberIntakeMotor.set(-Constants.INTAKE_SPEED);
        climberIntakeMotor2.set(Constants.INTAKE_SPEED);
    }

    public void stop() {
        climberIntakeMotor.set(0);
        climberIntakeMotor2.set(0);
    }

}
