package pattern.factory;

public class Main {
	public static void main(String[] args) {
		RobotFactory rf = new RobotStore();
		Robot r = rf.createRobot("super");
		Robot r2 = rf.createRobot("power");
		
		System.out.println(r.getName());
		System.out.println(r2.getName());
	}
}
