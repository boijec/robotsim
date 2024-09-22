package com.boijec.robotsim.contracts;

import com.boijec.robotsim.model.Direction;
import com.boijec.robotsim.model.RobotExecution;

/**
 * Actions that the robot should be able to perform
 */
public interface RobotCommands extends RobotExecution {
    void move();
    void turnLeft();
    void turnRight();
    void report();
    void place(int x, int y, Direction direction);

    Integer getX();
    Integer getY();
    Direction getDirection();
    void reset();
}
