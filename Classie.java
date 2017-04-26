import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

import javax.swing.*;

public class Classie extends JFrame 
{
	JLabel l1,l3,l5,l6;
	static JTextArea t1;
	JTextArea jTextField1;
	JButton b1,b2,b3,b4;
	static JFrame frame;
	static String msg;
	String packet;
	static String line,extra,match;
	static String[]  splits;
	private static Socket c_socket;
	private static DataOutputStream out;
	private static DataInputStream in;
	static ServerSocket c_server;
	static DataInputStream c_in;
	public static DataOutputStream c_out;
	private static Socket client;
	int std_length=7;
	public Classie()
	{
		initialize();	
	}
	private void initialize() 
	{

		JDBC.ConnectDatabase();
		frame=new JFrame("classie");
		frame.setLayout(null);
		l1 = new JLabel("A Modulo Packet Marking \nApproach to Protect",JLabel.CENTER);
		l1.setBounds(0,5,300,20);
		l3 = new JLabel("Cloud Environment against DDoS Attacks",JLabel.CENTER);
		l3.setBounds(0,28,300,20);
		t1= new JTextArea();
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(123,10,225),new Color(193,201,55)));
		t1.setBounds(30,130,250,100);
		//JScrollPane jsp1=new JScrollPane(t1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		t1.setEditable(false);
		t1.setText("packet are checking for HX-DOS");
		l5=new JLabel("Status");
		l5.setBounds(10,100,300,20);
		b3=new JButton("Exit");
		b3.setBounds(120,245,70,20);
		l6=new JLabel("CLASSIE",JLabel.CENTER);
		l6.setBounds(0,80,300,20);
		frame.add(l1);
		frame.add(l3);
		frame.add(t1);
		frame.add(l5);
		frame.add(b3);
		frame.add(l6);
		frame.setSize(320, 330);
		frame.setVisible(true);
		frame.setBounds(390,20,320,330);
		
		b3.addActionListener(new ActionListener() 
		{

		public void actionPerformed(ActionEvent e)
		{

				System.exit(0);
			
		}
		
		});
}

	public static void main(String[] args) {

		
Classie obj=new Classie();

try {

			c_server = new ServerSocket(5002);
			//c_server.setSoTimeout(10000);
} catch (SocketTimeoutException s) {

			
			System.out.println("socket timed out");
		
} catch (IOException e) 
{
			
			e.printStackTrace();
		
}

		while(true)
		{
	
			try{

				System.out.println("Server waiting at "+ c_server.getLocalPort());
				c_socket = c_server.accept();
				c_in = new DataInputStream(c_socket.getInputStream());
				c_out = new DataOutputStream(c_socket.getOutputStream());
				line = c_in.readUTF();
				splits=line.split("&");
				int no_of_headers = splits.length;
				if(no_of_headers==obj.std_length)
				{
					
					try {
						
						match=JDBC.checking(splits[2],splits[5]);
						
					
					
} catch (Exception e) {

						
						e.printStackTrace();
					
}
					if(splits[0].equals(match))
					{
						obj.make_packet();
						
						try{

							System.out.println("connecting to localhost on port" +5003);
							client = new Socket("localhost", 5003);
							OutputStream outtoserver = client.getOutputStream();
							out = new DataOutputStream(outtoserver);
							InputStream infromserver = client.getInputStream();
							in = new DataInputStream(infromserver);
							t1.setText("packet forwarded");
							String alrt="no packets are spoofed/attacked by attacker";
							JOptionPane.showMessageDialog(null,alrt,"",JOptionPane.PLAIN_MESSAGE);
							
							out.writeUTF(obj.line);
							out.flush();
							in.close();
							out.close();
							client.close();
							
						
}

				catch (IOException e1) {


				e1.printStackTrace();
						
}
						finally{
							
							client.close();
							c_socket.close();
							
						
						}
	
}	
				else
				{
					
				t1.setText("packet dropped");
					JOptionPane.showMessageDialog(null,"packets are spoofed/attacked by attacker","",JOptionPane.PLAIN_MESSAGE);
				}
}
				else
				{	
					
				t1.setText("packet dropped");
				JOptionPane.showMessageDialog(null,"packets are spoofed/attacked by attacker","",JOptionPane.PLAIN_MESSAGE);
				}
				c_in.close();
				c_out.close();
				c_socket.close();
}
			catch (IOException e) 
			{				
				e.printStackTrace();
			}		
}	
}
private void make_packet() 
{
line=splits[0]+"&"+splits[1]+"&"+splits[2]+"&"+splits[3]+"&"+splits[4]+"&"+splits[5]+"&"+splits[6];	
}
}
