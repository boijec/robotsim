package com.boijec.robotsim.model;

import com.boijec.robotsim.exception.RobotSimulationException;

@FunctionalInterface
public interface RobotExecution {
    void execute(Command command, String[] args) throws RobotSimulationException;
}
