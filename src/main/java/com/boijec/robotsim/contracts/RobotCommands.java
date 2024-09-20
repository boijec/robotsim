package com.boijec.robotsim.contracts;

import com.boijec.robotsim.model.Direction;

/**
 * Actions that the robot should be able to perform
 */
public interface RobotCommands {
    void move();
    void turnLeft();
    void turnRight();
    void report();
    void place(int x, int y, Direction direction);
}
