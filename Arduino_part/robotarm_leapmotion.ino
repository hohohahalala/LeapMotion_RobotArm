#include<Servo.h>

int incomingByte = 0;	// 用來儲存收進來的 data byte
Servo bottom_servo;
Servo arm1_servo;
Servo arm2_servo;
Servo wrist_front_rear;
Servo wrist_left_right;
Servo pinch_servo;

void setup() {
  // 開啟Serial port,通訊速率為9600bps
  Serial.begin(9600);
  Serial.println("Hello Arduino");
  
  bottom_servo.attach(13);
  arm1_servo.attach(12);
  arm2_servo.attach(11);
  wrist_front_rear.attach(10);
  wrist_left_right.attach(9);
  pinch_servo.attach(8);
}

void loop() {
}

void serialEvent(){
  char buffer[7] = {};
  char pin[2] = {};
  char angle[6] = {};
  
  Serial.readBytes(buffer,6);
  strcpy(pin,buffer);
  strcpy(angle,buffer);  
  angle[0] = ' '; 
  angle[1] = ' ';
  angle[2] = ' ';
  
  
  int angle_int;
  angle_int = atoi(angle);
  Serial.print(angle_int);

  if(atoi(pin) == 13) {
    bottom_servo.write(angle_int);
  }
  
   if(atoi(pin) == 12) {
     arm1_servo.write(angle_int);
  }
  
   if(atoi(pin) == 11) {
     arm2_servo.write(angle_int);
  }
  
   if(atoi(pin) == 10) {
     wrist_front_rear.write(angle_int);
  }
  
   if(atoi(pin) == 9) {
     wrist_left_right.write(angle_int);
  }
  
   if(atoi(pin) == 8) {
     pinch_servo.write(angle_int);
  }
  
  Serial.flush();  
}