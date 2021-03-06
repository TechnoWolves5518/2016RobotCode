package org.usfirst.frc.team5518.robot.commands.hooker;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenCylinders extends Command {
	
    public OpenCylinders() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hooker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hooker.init();
    	Robot.hooker.openCylinders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
