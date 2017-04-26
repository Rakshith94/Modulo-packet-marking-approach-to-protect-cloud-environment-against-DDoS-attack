import java.net.*;
import java.io.File;
public class address {
	static String IP,MAC;
	static InetAddress uip;
	
 public static String ip()
 { 
	 try
	 {
	 uip=InetAddress.getLocalHost();
	 IP=uip.getHostAddress().toString(); 
	 }
 catch(UnknownHostException e) 
 {
  		e.printStackTrace();
 }
	 return IP;
 }
 public static String mac()
 {
	try{
		uip=InetAddress.getLocalHost();
		NetworkInterface net = NetworkInterface.getByInetAddress(uip);
		byte[] maddr = net.getHardwareAddress();
		 
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < maddr.length; i++) 
		{
		sb.append(String.format("%02X%s", maddr[i], (i < maddr.length - 1) ? "-" : ""));		
		}
		MAC=sb.toString();
		
	}
	catch(UnknownHostException e) 
	 {
	  		e.printStackTrace();
	 }
		catch (SocketException e)
	{
		e.printStackTrace();
 	
	}  
 return MAC;		
 }
   public static void main(String[] args)
   {
	 String a=ip();
	 String b=mac();
	 System.out.println(a);
	 System.out.println(b);
   }
}