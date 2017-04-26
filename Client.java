import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client extends JFrame{

	JLabel l1,l2,l3,l4,l5;
	JTextArea t1, jTextField1;
	JButton b1,b2,b3,b4;
	static JFrame frame;
	private String msg,packet,file_name;
	private Socket client;
	private DataOutputStream out;
	private DataInputStream in;
	static ServerSocket C_server;
	int len,packets,rem;
	String MAC=address.mac(),ext,marking="false";
	
	String a[]=new String[2];
	public Client(){
		JDBC.ConnectDatabase();
		JDBC.authenticate(signin.nm);
		initialize();	
}
	String key=JDBC.key;
	int link=JDBC.link;
	private void initialize() 
	{

		frame=new JFrame("client");
		frame.setLayout(null);

		l1 = new JLabel("A Modulo Packet Marking Approach to Protect",JLabel.CENTER);
		l1.setFont(new Font("Serif",Font.BOLD,16));
		l1.setBounds(0,0,400,20);
		l3 = new JLabel("Cloud Environment against DDoS Attacks",JLabel.CENTER);
		l3.setFont(new Font("Serif",Font.BOLD,16));
		l3.setBounds(0,20,400,20);
		t1= new JTextArea();
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(0,0,0),new Color(0,0,0)));
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(123,10,225),new Color(193,201,55)));
		//JScrollPane jsp1=new JScrollPane(t1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		t1.setEditable(false);
		t1.setBounds(30,85,330,120);
		t1.setText("choose a .txt file to upload into Cloud");
		l4=new JLabel("Client",JLabel.CENTER);
		l4.setBounds(0,40,400,20);
		l5=new JLabel("Uploaded File info:");
		l5.setBounds(10,60,400,20);
		b1=new JButton("Clear");
		b1.setBounds(105,220,80,25);
		b2=new JButton("Upload");
		b2.setBounds(200,220,80,25);
		b3=new JButton("Exit");
		b3.setBounds(300,220,80,25);
		b4=new JButton("Info");
		b4.setBounds(10,220,80,25);
		frame.add(l1);
		frame.add(l3);
		frame.add(t1);
		frame.add(l5);
		frame.add(b3);
		frame.add(l4);
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.add(b4);
		frame.setSize(400, 300);
		frame.setVisible(true);
		frame.setBounds(0,20,400,300);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				System.exit(0);
			
}
		
});

		b1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				t1.setText(null);
				msg=null;
			
}
});
		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				JFrame jf=new JFrame();
				jf.setLayout(null);

				JLabel il1=new JLabel("Client Info",JLabel.CENTER);
				il1.setFont(new Font("Serif",Font.BOLD,16));
				il1.setBounds(0, 0, 300, 25);

				try {

					JLabel il2=new JLabel("IP Address : "+Inet4Address.getLocalHost().getHostAddress());					
					il2.setBounds(0,40, 300, 25);
					JLabel il3=new JLabel("MAC Address : "+MAC);
					
		
					il3.setBounds(0,80, 300, 25);
					JLabel il4=new JLabel("Port number : "+JDBC.link);
					
					il4.setBounds(0,120, 300, 25);
					jf.add(il1);
					jf.add(il2);
					jf.add(il3);
					jf.add(il4);
					jf.setSize(300, 300);
					jf.setVisible(true);
			
}
				catch (UnknownHostException e1) {
					e1.printStackTrace();
				
}
			
}
		
});

		b2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
				{

				JFileChooser fc=new JFileChooser();
				int option = fc.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION)
					{

					try{
						
						msg="";
						String sf=fc.getSelectedFile().getAbsolutePath();


						FileInputStream in = new FileInputStream(sf);
						byte str[] = new byte[in.available()];
						in.read(str,0,str.length);
						File fn1=new File(sf);
						file_name=fn1.getName();
						//extractFileName(sf);

						int b;
						//file_name=in.getname();
						while((b=in.read())!=-1)
						{

							msg+=(char)b;
						
						}
						//t1.setText(new String (str));
						msg=new String (str);
						len=msg.length();
						//packets=len/48;
						//rem=len%48;
						//packets++;
						ext=file_name.substring(file_name.lastIndexOf('.'),file_name.length());
					
						t1.append("\nFile path : "+sf+"\n");
						t1.append("File extention: "+ext+"\n");
						t1.append("Total length: "+len+"\n");
						//t1.append("Number of packets: "+packets+"\n");
						makepacket(msg,file_name);
						String alrt="packets are created and \nSecret key, MAC, IP, link_no are appended to the packet";
						JOptionPane.showMessageDialog(null,alrt,"",JOptionPane.PLAIN_MESSAGE);
					}
					catch (Exception ee)
					{

						JOptionPane.showMessageDialog(null,"Reload File Again..");
					
					}
}
	
				try{

					System.out.println("connecting to localhost on port" +5002);
					client = new Socket("localhost",5002);
					System.out.println("just connected to"+ client.getRemoteSocketAddress());
					OutputStream outtoserver = client.getOutputStream();
					out = new DataOutputStream(outtoserver);
					InputStream infromserver = client.getInputStream();
					in = new DataInputStream(infromserver);
					
				//	t1.setText(null);
				
					out.writeUTF(packet);
					out.flush();
					in.close();
					out.close();
					client.close();
}

				catch (IOException e1) {
					e1.printStackTrace();		
				}
				
}		

});

}


	public static void main(String[] args) {

	
		new Client();
		
	}
	public void makepacket(String msg, String fname) {
		try {
			packet = key+"&"+Inet4Address.getLocalHost().getHostAddress()+"&"+MAC+"&"+ext+"&"+fname+"&"+link+"&"+msg+"&";
			System.out.println(packet);
		
		} 
		catch (UnknownHostException e) 
		{
		e.printStackTrace();
		
		}	
	}
}	