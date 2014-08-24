#include<Servo.h>

int incomingByte = 0;	// 用來儲存收進來的 data byte
Servo Bottom;

void setup() {
  // 開啟Serial port,通訊速率為9600bps
  Serial.begin(9600);
  Serial.println("Hello Arduino");
  Bottom.attach(13);
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
    Bottom.write(angle_int);
  }
  
   if(atoi(pin) == 12) {
  }
  
   if(atoi(pin) == 11) {
  }
  
   if(atoi(pin) == 10) {
  }
  
   if(atoi(pin) == 9) {
  }
  
   if(atoi(pin) == 8) {
  }
  
  Serial.flush();  
}