
public class Servo {
	public String pin_str;;
	
	public Servo(String pin) {
		pin_str =pin;
	}
	
	public String BottomServo(float x_axis) {
		int radius = 160;
		int angle = (int)Math.toDegrees(Math.acos(x_axis/radius));
		System.out.println("angle: " + angle);
		return setMsg(angle);
	}
	
	public String setMsg(int angle) {
		String tmp = Integer.toString(angle);
		if(Integer.toString(angle).length() == 2)
			tmp = "0" + Integer.toString(angle);
		if(Integer.toString(angle).length() == 1)
			tmp = "00" + Integer.toString(angle);
		if(pin_str.length() == 1) 
			pin_str = "0" + pin_str;
			
		tmp = pin_str + "," + tmp;
		return tmp;
	}
}
