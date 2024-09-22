package com.boijec.robotsim;

import com.boijec.robotsim.exception.RobotSimulationException;
import com.boijec.robotsim.model.Command;
import com.boijec.robotsim.model.RobotExecution;
import com.boijec.robotsim.model.SimulationExecution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RobotSimulator implements SimulationExecution {
    private RobotExecution robot;
    private String instructionSet;

    public void setRobot(RobotExecution robot) {
        this.robot = robot;
    }
    public void setInstructionSet(String instructionSet) {
        this.instructionSet = instructionSet;
    }

    public void reset() {
        robot = null;
        instructionSet = null;
    }

    @Override
    public void execute() {
        if(robot == null || instructionSet == null) {
            System.err.println("ERROR: Simulator not configured correctly!");
            return;
        }
        try (FileReader fr = new FileReader(instructionSet); BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            while (line != null) {
                try {
                    if (line.isEmpty()) throw new RobotSimulationException("Empty command row");
                    String[] args = line.split(",");
                    robot.runCommand(Command.parseCommand(args[0]), args);
                } catch (RobotSimulationException | NumberFormatException e) {
                    System.err.println("EXECUTION WARNING: " + e.getMessage());
                } finally {
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found");
        } catch (Exception e) {
            System.err.println("ERROR: Error reading instruction set");
        }
    }
}
