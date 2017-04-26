import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class RAD   {
	JLabel l1,l2,l3,l4,l5,l6;
	static JFrame frame;
	static JTextArea t1;
	JTextArea jTextField1;
	JButton b1,b2,b3,b4;
	String msg,packet,line;
	static String value;
	private static Socket r_socket;
	private static DataOutputStream out;
	static ServerSocket r_server;
	static DataInputStream r_in;
	public static DataOutputStream r_out;
	private static Socket client;
	private static DataInputStream in;
	public RAD(){
		JDBC.ConnectDatabase();
		initialize();
	
}
	private void initialize() {

		frame = new JFrame("RAD");
		
		frame.setLayout(null);


		l1 = new JLabel("A Modulo Packet Marking Approach to Protect",JLabel.CENTER);
		l1.setBounds(0,0,300,20);
		l3 = new JLabel("Cloud Environment against DDoS Attacks",JLabel.CENTER);
		l3.setBounds(0,20,300,20);
		t1= new JTextArea();
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(0,0,0),new Color(0,0,0)));
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(123,10,225),new Color(193,201,55)));
		//JScrollPane jsp1=new JScrollPane(t1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		t1.setEditable(false);
		t1.setBounds(30,80,230,150);
		t1.setText("***Packet Status***");
		l4=new JLabel("Reconstruct & Drop",JLabel.CENTER);
		l4.setFont(new Font("Serif",Font.BOLD,14));
		l4.setBounds(0,40,300,30);
		l5=new JLabel("Status");
		l5.setBounds(10,60,300,20);
		b3=new JButton("Exit");
		b3.setBounds(100,240,70,20);

	frame.setSize(300, 320);
		frame.setVisible(true);
		frame.setBounds(1070, 20,300,320);
		frame.add(l1);
		frame.add(l3);
		frame.add(t1);
		frame.add(l4);
		frame.add(l5);
		frame.add(b3);
		
		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				System.exit(0);
			
}
		
});

	
}

	public static void main(String[] args) {

		
		RAD obj=new RAD();
		try {

			r_server = new ServerSocket(5004);
			//N1_server.setSoTimeout(10000);
		
} catch (SocketTimeoutException s) {

			
			System.out.println("socket timed out");
		
} catch (IOException e) {

			
			e.printStackTrace();
		
}

		while(true)
		{
	
			//b.shadow_queue();
			try{
				
				System.out.println("Server waiting at "+r_server.getLocalPort());
				r_socket = r_server.accept();
				r_in = new DataInputStream(r_socket.getInputStream());
				r_out = new DataOutputStream(r_socket.getOutputStream());
				obj.line = r_in.readUTF();
				String[] splits= obj.line.split("&");
				check_marking(splits);
				
				if(splits[8].equals(value)){
	
				try{
					
					System.out.println("connecting to localhost on port" +5001);
					client = new Socket("192.168.221.73", 5005);
					//	System.out.println("just connected to"+ client.getRemoteSocketAddress());
					OutputStream outtoserver = client.getOutputStream();
					out = new DataOutputStream(outtoserver);
					InputStream infromserver = client.getInputStream();
					in = new DataInputStream(infromserver);
									
					out.writeUTF(obj.line);
					out.flush();
					t1.setText("packet forwarded");
					JOptionPane.showMessageDialog(null,"Marking value matches..!\nFile uploaded successfully..!"," ",JOptionPane.PLAIN_MESSAGE);
					in.close();
					out.close();
					client.close();
								
}

				catch (IOException e1) {

				
					e1.printStackTrace();
				
}	
				finally{
					client.close();
					r_socket.close();
				}
}
				else{

					t1.setText("packet dropped");
					JOptionPane.showMessageDialog(null,"Marking value mismatches and \n failed to upload file"," ",JOptionPane.PLAIN_MESSAGE);
				}
				r_in.close();
				r_out.close();
				r_socket.close();
			
} catch (IOException e) {

				
				e.printStackTrace();
			
}
			
		
}
		
	
}
	private static void check_marking(String[] splits) {

		
		String ip_check=splits[1];
		value=JDBC.marking_check(ip_check);
		
		
	
}
	
}