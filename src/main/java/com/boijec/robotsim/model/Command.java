package com.boijec.robotsim.model;

import com.boijec.robotsim.exception.RobotSimulationException;

public enum Command {
    PLACE("PLACE"),
    MOVE("MOVE"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    REPORT("REPORT");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command parseCommand(String name) throws RobotSimulationException {
        for (Command command : Command.values()) {
            if (command.name.equals(name)) {
                return command;
            }
        }
        throw new RobotSimulationException("Robot instruction \"" + name + "\" is not valid!");
    }
}
