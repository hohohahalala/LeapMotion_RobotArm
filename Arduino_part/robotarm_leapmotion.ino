int incomingByte = 0;	// 用來儲存收進來的 data byte


void setup() {
  // 開啟 Serial port, 通訊速率為 9600 bps
  Serial.begin(9600);
  Serial.println("Hello Arduino");
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);
}

void loop() {
  // 檢查是否有資料可供讀取
  if (Serial.available() > 0) {
    // 讀取進來的 byte
    //incomingByte = Serial.read();
    
    char buffer[1];
    Serial.readBytes(buffer,1);
    int tmp = buffer[0];
    Serial.print(tmp);
    if(tmp == 72)
    {
      digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
      delay(10);               // wait for a second
      digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
      delay(10);
    }
    
    // 印出收到的資料
    //Serial.print("data received: ");
    //Serial.print(incomingByte, DEC);
    //Serial.print(", ");
    //Serial.print(incomingByte, HEX);
    //Serial.println(" (HEX)");
  }
}
