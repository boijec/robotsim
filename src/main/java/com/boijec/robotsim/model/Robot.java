package com.boijec.robotsim.model;

import com.boijec.robotsim.contracts.RobotCommands;
import com.boijec.robotsim.contracts.RobotInfo;
import com.boijec.robotsim.exception.RobotSimulationException;

public class Robot implements RobotExecution, RobotCommands, RobotInfo {
    private final int xLimit;
    private final int yLimit;
    private Integer x;
    private Integer y;
    private Direction direction;

    public Robot(int xLimit, int yLimit) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    @Override
    public Integer getX() {
        return x;
    }
    @Override
    public Integer getY() {
        return y;
    }
    @Override
    public Direction getDirection() {
        return direction;
    }

    public void reset() {
        this.x = null;
        this.y = null;
        this.direction = null;
    }

    @Override
    public void execute(Command command, String[] args) throws RobotSimulationException {
        switch (command) {
            case PLACE:
                if (args.length != 4) throw new RobotSimulationException("Invalid PLACE command");
                this.place(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Direction.getDirection(args[3]));
                break;
            case MOVE:
                this.move();
                break;
            case LEFT:
                this.turnLeft();
                break;
            case RIGHT:
                this.turnRight();
                break;
            case REPORT:
                this.report();
                break;
        }
    }

    @Override
    public void move() {
        if(direction == null) return;
        switch (this.direction) {
            case NORTH:
                if(this.y < yLimit) {
                    this.y++;
                }
                break;
            case SOUTH:
                if(this.y > 0) {
                    this.y--;
                }
                break;
            case EAST:
                if(this.x < xLimit) {
                    this.x++;
                }
                break;
            case WEST:
                if(this.x > 0) {
                    this.x--;
                }
                break;
        }
    }

    @Override
    public void turnLeft() {
        if(this.direction == null) return;

        if(this.direction.getId() == 1) {
            this.direction = Direction.getDirection(4);
            return;
        }
        this.direction = Direction.getDirection(this.direction.getId() - 1);
    }

    @Override
    public void turnRight() {
        if(this.direction == null) return;

        if(this.direction.getId() == 4) {
            this.direction = Direction.getDirection(1);
            return;
        }
        this.direction = Direction.getDirection(this.direction.getId() + 1);
    }

    @Override
    public void report() {
        if(this.direction == null) return;

        System.out.println(x + "," + y + "," + direction.name());
    }

    @Override
    public void place(int x, int y, Direction direction) {
        if(x > xLimit || x < 0 || y > yLimit || y < 0) {
            return;
        }
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
