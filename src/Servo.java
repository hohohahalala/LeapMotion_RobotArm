import com.leapmotion.leap.Vector;

public class Servo {
	public String pin_str;
	
	public Servo(String pin) {
		pin_str =pin;
	}
	
	public void BottomServo(float x_axis) {
		if(x_axis<=160 && x_axis>=(-160)) {
			int radius = 160;
			int angle = (int)Math.toDegrees(Math.acos(x_axis/radius));
			System.out.println("angle: " + angle);
			setMsg(angle);
		}
	}
	
	public void ArmSystemServo_1(float y_orig, float z_orig) {
		int y_axis = (int) ((y_orig-40)/340*100);
		int z_axis = z_orig > 0 ?
				(int) (Math.abs(z_orig-170)/340*100) : (int) ((Math.abs(z_orig)+170)/340*100);
		int beveledge = (int) Math.sqrt(Math.pow(z_axis,2) + Math.pow(y_axis,2));
//		System.out.println("z,y,beveledge " +z_axis+" "+y_axis+" "+ beveledge);
		
		if(beveledge>=50 && beveledge<=100) {
			float Cos_Arm1 = (float)(Math.pow(50,2) + Math.pow(beveledge,2) - Math.pow(50,2)) / (float)(2*50*beveledge);
			float Cos_Bottom = (float)(Math.pow(z_axis,2) + Math.pow(beveledge,2) - Math.pow(y_axis,2)) / (float)(2*beveledge*z_axis);
//			System.out.println("Cos_Arm2,Cos_Arm1,Cos_Bottom " +Cos_Arm2+" "+Cos_Arm1+" "+ Cos_Bottom);
			
			int Bottom_angle = (int)Math.toDegrees(Math.acos(Cos_Bottom));
			int Arm1_angle = Bottom_angle + (int)Math.toDegrees(Math.acos(Cos_Arm1));
//			System.out.println("(int)Math.toDegrees(Math.acos(Cos_Arm2)) : " + (int)Math.toDegrees(Math.acos(Cos_Arm2)));
//			System.out.println("Bottom_angle : " + Bottom_angle);
			
//			System.out.println("1角度 = " + Arm1_angle);
//			System.out.println("2角度 = " + Arm2_angle);
			setMsg(180-Arm1_angle);
		}
	}
	
	public void ArmSystemServo_2(float y_orig, float z_orig) {
		int y_axis = (int) ((y_orig-40)/340*100);
		int z_axis = z_orig > 0 ?
				(int) (Math.abs(z_orig-170)/340*100) : (int) ((Math.abs(z_orig)+170)/340*100);
		int beveledge = (int) Math.sqrt(Math.pow(z_axis,2) + Math.pow(y_axis,2));
		
		if(beveledge>=50) {
			float Cos_Arm2 = (float)(Math.pow(50,2) + Math.pow(50,2) - Math.pow(beveledge,2)) / (float)(2*50*50);			
			int Arm2_angle = (int)Math.toDegrees(Math.acos(Cos_Arm2)) - 90;
			setMsg(Math.abs(Arm2_angle));
		}
	}
	
	public void setWristRotationDegree(Vector palmNormal) {
		Vector v=palmNormal;
		float left_right_degree=90;
		float x=v.getX();
		left_right_degree=90-90*x;
		setMsg((int)left_right_degree);
	}
	
	public void setWristDropDegree(Vector palmNormal) {
		Vector v=palmNormal;
		float front_rear_degree=90;
		float z=v.getZ();
		front_rear_degree=90+90*z;
		setMsg((int)front_rear_degree);
	}
	
	public void setPincherDegree(float grabStrength) {
		float strength=grabStrength;		
		float degree=120;
		float minDegree=120,maxDegree=180;
//		float marginMinRad=40,marginMaxRad=90;
//
//		use sphereRadius
//		if(rad<marginMinRad)
//			degree=(int)maxDegree;
//		else if(rad>=marginMaxRad)
//			degree=(int)minDegree;
//		else
//			degree=(int)(maxDegree-((rad-marginMinRad)/(marginMaxRad-marginMinRad)*(maxDegree-minDegree)));
		degree=minDegree+strength*(maxDegree-minDegree);
		setMsg((int)degree);
	}
	
	public void setMsg(int angle) {
		String tmp = Integer.toString(angle);
		if(Integer.toString(angle).length() == 2)
			tmp = "0" + Integer.toString(angle);
		if(Integer.toString(angle).length() == 1)
			tmp = "00" + Integer.toString(angle);
		if(pin_str.length() == 1) 
			pin_str = "0" + pin_str;
			
		tmp = pin_str + "," + tmp;
		CommPortSender.send(new ProtocolImpl().getMessage(tmp));
	}
}
