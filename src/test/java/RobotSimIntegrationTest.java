import com.boijec.robotsim.RobotSimulator;
import com.boijec.robotsim.contracts.RobotCommands;
import com.boijec.robotsim.model.Direction;
import com.boijec.robotsim.model.Robot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RobotSimIntegrationTest {

    private RobotSimulator simulator;
    private RobotCommands robot;

    @BeforeAll
    void init() {
        robot = new Robot(5, 5);
        simulator = new RobotSimulator();
        simulator.setRobot(robot);
    }

    @BeforeEach
    void setUp() {
        if(robot != null) {
            robot.reset();
        }
        simulator.reset();
        simulator.setRobot(robot);
    }

    @Test
    void testFirstExample() {
        simulator.setInstructionSet("src/test/resources/first.txt");
        simulator.execute();
        assertEquals(0, robot.getX());
        assertEquals(1, robot.getY());
        assertSame(robot.getDirection(), Direction.NORTH);
    }
    @Test
    void testSecondExample() {
        simulator.setInstructionSet("src/test/resources/second.txt");
        simulator.execute();
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        assertSame(robot.getDirection(), Direction.WEST);
    }
    @Test
    void testThirdExample() {
        simulator.setInstructionSet("src/test/resources/third.txt");
        simulator.execute();
        assertEquals(3, robot.getX());
        assertEquals(3, robot.getY());
        assertSame(robot.getDirection(), Direction.NORTH);
    }
    @Test
    void testPrePlaceInstructions() {
        simulator.setInstructionSet("src/test/resources/third_with_extras.txt");
        simulator.execute();
        assertEquals(3, robot.getX());
        assertEquals(3, robot.getY());
        assertSame(robot.getDirection(), Direction.NORTH);
    }
    @Test
    void testRePlaceInstructions() {
        simulator.setInstructionSet("src/test/resources/third_with_extras_valid.txt");
        simulator.execute();
        assertEquals(3, robot.getX());
        assertEquals(3, robot.getY());
        assertSame(robot.getDirection(), Direction.NORTH);
    }
    @Test
    void testRePlaceInstructionsInvalid() {
        simulator.setInstructionSet("src/test/resources/third_with_extras_invalid.txt");
        simulator.execute();
        assertNull(robot.getX());
        assertNull(robot.getY());
        assertNull(robot.getDirection());
    }
    @Test
    void testInvalid() {
        simulator.setInstructionSet("src/test/resources/invalid.txt");
        simulator.execute();
        assertNull(robot.getX());
        assertNull(robot.getY());
        assertNull(robot.getDirection());
    }
    @Test
    void testRunWithInvalidData() {
        simulator.setInstructionSet("src/test/resources/totally_invalid_instructions.txt");
        simulator.execute();
        assertEquals(3, robot.getX());
        assertEquals(3, robot.getY());
        assertSame(robot.getDirection(), Direction.NORTH);
    }

}
