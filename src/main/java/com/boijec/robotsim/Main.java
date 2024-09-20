package com.boijec.robotsim;

import com.boijec.robotsim.model.Robot;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            printUsage();
            return;
        }
        RobotSimulator simulator = new RobotSimulator();
        simulator.setRobot(new Robot(5, 5));
        simulator.setInstructionSet(args[0]);
        simulator.execute();
    }
    private static void printUsage() {
        System.out.println("Usage: java -jar robotsim.jar <path-to-instructions-file>");
    }
}
