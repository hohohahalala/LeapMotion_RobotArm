import java.io.IOException;
import java.io.OutputStream;
  
public class CommPortSender {  
    static OutputStream out;    
      
    //開始程式之設定
    public static void setWriterStream(OutputStream out) {    
        CommPortSender.out = out;    
    }    
        
    public static void send(byte[] bytes) {    
        try {    
        	//debug info
            System.out.println("SENDING: " + new String(bytes, 0, bytes.length));  
            System.out.println("length : "+bytes.length); 
                
            // sending through serial port is simply writing into OutputStream    
            out.write(bytes);    
            out.flush();
            
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
    }      
}  