import com.boijec.robotsim.contracts.RobotCommands;
import com.boijec.robotsim.model.Direction;
import com.boijec.robotsim.model.Robot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RobotUnitTest {

    private RobotCommands robot;

    @BeforeAll
    void init() {
        robot = new Robot(5, 5);
    }

    @BeforeEach
    void setUp() {
        if(robot != null) {
            robot.reset();
        }
    }

    @Test
    void testRobotRotation() {
        robot.place(0, 0, Direction.NORTH);
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        robot.turnRight();
        assertSame(robot.getDirection(), Direction.EAST);
        robot.turnRight();
        assertSame(robot.getDirection(), Direction.SOUTH);
        robot.turnRight();
        assertSame(robot.getDirection(), Direction.WEST);
        robot.turnRight();
        assertSame(robot.getDirection(), Direction.NORTH);
        robot.turnLeft();
        assertSame(robot.getDirection(), Direction.WEST);
        robot.turnLeft();
        assertSame(robot.getDirection(), Direction.SOUTH);
        robot.turnLeft();
        assertSame(robot.getDirection(), Direction.EAST);
        robot.turnLeft();
        assertSame(robot.getDirection(), Direction.NORTH);
    }
    @Test
    void testLimitationLow() {
        robot.place(0, 0, Direction.NORTH);
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        robot.turnRight();
        robot.turnRight();
        assertSame(robot.getDirection(), Direction.SOUTH);
        robot.move();
        robot.move();
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        robot.turnRight();
        assertEquals(robot.getDirection(), Direction.WEST);
        robot.move();
        robot.move();
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
    }
    @Test
    void testLimitationHigh() {
        robot.place(5, 5, Direction.NORTH);
        assertEquals(5, robot.getX());
        assertEquals(5, robot.getY());
        robot.move();
        robot.move();
        assertEquals(5, robot.getX());
        assertEquals(5, robot.getY());
        robot.turnRight();
        assertEquals(robot.getDirection(), Direction.EAST);
        robot.move();
        robot.move();
        assertEquals(5, robot.getX());
        assertEquals(5, robot.getY());
    }
    @Test
    void testInvalidPlace() {
        robot.place(7, 5, Direction.NORTH);
        assertNull(robot.getDirection());
        robot.place(5, 7, Direction.NORTH);
        assertNull(robot.getDirection());
        robot.place(5, 5, null);
        assertNull(robot.getDirection());
        robot.place(-2,-2, Direction.NORTH);
        assertNull(robot.getDirection());
        robot.place(0, 0, Direction.NORTH);
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        assertSame(robot.getDirection(), Direction.NORTH);
    }
    @Test
    void testMoveOffTheTable() {
        robot.place(5, 3, Direction.EAST);
        robot.move();
        robot.move();
        assertEquals(5, robot.getX());
        assertEquals(3, robot.getY());
        robot.place(3, 0, Direction.SOUTH);
        robot.move();
        robot.move();
        assertEquals(3, robot.getX());
        assertEquals(0, robot.getY());
        robot.place(0, 3, Direction.WEST);
        robot.move();
        robot.move();
        assertEquals(0, robot.getX());
        assertEquals(3, robot.getY());
        robot.place(3, 5, Direction.NORTH);
        robot.move();
        robot.move();
        assertEquals(3, robot.getX());
        assertEquals(5, robot.getY());
    }

}
