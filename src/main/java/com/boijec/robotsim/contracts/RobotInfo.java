package com.boijec.robotsim.contracts;

import com.boijec.robotsim.model.Direction;

/**
 * Robot stats that can be verified by the tests
 * (since I'm not using lombok and I don't want to miss stuff)
 */
public interface RobotInfo {
    Integer getX();
    Integer getY();
    Direction getDirection();
}
