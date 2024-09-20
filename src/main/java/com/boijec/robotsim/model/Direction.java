package com.boijec.robotsim.model;

import com.boijec.robotsim.exception.RobotSimulationException;

public enum Direction {
    NORTH(1, "NORTH"),
    EAST(2, "EAST"),
    SOUTH(3, "SOUTH"),
    WEST(4, "WEST");

    private final int id;
    private final String name;

    Direction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public static Direction getDirection(int id) throws RobotSimulationException {
        for (Direction direction : Direction.values()) {
            if (direction.id == id) {
                return direction;
            }
        }
        throw new RobotSimulationException("No valid direction found for id: " + id);
    }

    public static Direction getDirection(String name) throws RobotSimulationException {
        for (Direction direction : Direction.values()) {
            if (direction.name.equals(name)) {
                return direction;
            }
        }
        throw new RobotSimulationException("No valid direction found for String: " + name);
    }
}
