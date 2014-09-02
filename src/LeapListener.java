import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

public class LeapListener extends Listener {
//	Servo bottom_servo = new Servo("13");
	Servo arm1_servo = new Servo("13");
	Servo arm2_servo = new Servo("12");
//	Servo bottom_servo = new Servo("10");
//	Servo bottom_servo = new Servo("9");
//	Servo bottom_servo = new Servo("8");

	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}
	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}
	public void onExit(Controller controller) {
		System.out.println("Exited");
	}
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		if (!frame.hands().isEmpty()) {
			// Get the first hand
			Hand hand = frame.hands().get( 0);
			// Get the hand's normal vector and direction
			Vector normal = hand.palmNormal();

			// Data which we need
			System.out.println("Hand sphere radius: " + hand.sphereRadius());
			System.out.println("X : " + hand.palmPosition().getX());
			System.out.println("Y : "+hand.palmPosition().getY());
			System.out.println("Z : " + hand.palmPosition().getZ());
			System.out.println(Math.toDegrees(normal.yaw()));
			
//			if(hand.palmPosition().getX()<=160 && hand.palmPosition().getX()>=(-160)) {
//				bottom_servo.BottomServo(hand.palmPosition().getX());
//			}
			
			arm1_servo.ArmSystemServo_1(hand.palmPosition().getY(), hand.palmPosition().getZ());
			arm2_servo.ArmSystemServo_2(hand.palmPosition().getY(), hand.palmPosition().getZ());
			
			//減少資訊量
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}