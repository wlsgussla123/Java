package pattern.factory;

public class RobotStore extends RobotFactory {
	@Override
	Robot createRobot(String name) {
		switch(name) {
		case "super":
			return new SuperRobot();
		case "power":
			return new PowerRobot();
		}
		return null;
	}
}
