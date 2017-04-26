import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Modulo_packet extends JFrame {

	JLabel l1,l2,l3,l4,l5,l6;
	static JTextArea t1;
	JTextArea jTextField1;
	JButton b1,b2,b3,b4;
	static JFrame frame;
	String msg,packet,line;
	static String d="";
	static String[] splits;
	static int no_of_interfaces=24;
	private static Socket m_socket;
	private static DataOutputStream out;
	private static DataInputStream in;
	static ServerSocket m_server;
	static DataInputStream m_in;
	public static DataOutputStream m_out;
	private static Socket client;

	public Modulo_packet(){

		JDBC.ConnectDatabase();
		initialize();
	
}
	private void initialize() {

		frame=new JFrame("modulo_packet");
		frame.setLayout(null);

		l1 = new JLabel("A Modulo Packet Marking Approach to Protect",JLabel.CENTER);
		l1.setBounds(0,0,400,20);
		l3 = new JLabel("Cloud Environment against DDoS Attacks",JLabel.CENTER);
		l3.setBounds(0,20,400,20);
		t1= new JTextArea();
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(0,0,0),new Color(0,0,0)));
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(123,10,225),new Color(193,201,55)));
		t1.setBounds(30,110,320,120);
		JScrollPane jsp1=new JScrollPane(t1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		t1.setEditable(false);
		t1.setText("***Router Information***");
		l4=new JLabel("Modulo packet marking",JLabel.CENTER);
		l4.setFont(new Font("Serif",Font.BOLD,14));
		l4.setBounds(0,40,400,50);
		l5=new JLabel("Status");
		l5.setBounds(10,90,400,20);
		b3=new JButton("Exit");
		b3.setBounds(160,233,70,20);
		frame.add(l1);
		frame.add(l3);
		frame.add(t1);
		frame.add(l5);
		frame.add(b3);
		frame.add(l4);
		frame.setSize(400, 300);
		frame.setVisible(true);
		frame.setBounds(690,20,400,300);
		
		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				System.exit(0);
			
}
		
});

	
}

	public static void main(String[] args) throws Exception  {

		Modulo_packet obj=new Modulo_packet();
		
		try {

			m_server = new ServerSocket(5003);
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

				System.out.println("Server waiting at "+ m_server.getLocalPort());
				m_socket = m_server.accept();
				m_in = new DataInputStream(m_socket.getInputStream());
				m_out = new DataOutputStream(m_socket.getOutputStream());
				obj.line = m_in.readUTF();
				obj.line=edge_router(obj.line);
				obj.line=core_router(obj.line);
				
			
				try{

					System.out.println("connecting to localhost on port" +5003);
					client = new Socket("localhost", 5004);
					//	System.out.println("just connected to"+ client.getRemoteSocketAddress());
					OutputStream outtoserver = client.getOutputStream();
					out = new DataOutputStream(outtoserver);
					InputStream infromserver = client.getInputStream();
					in = new DataInputStream(infromserver);

					out.writeUTF(obj.line);
					out.flush();
					in.close();
					out.close();
					client.close();
				
}

				catch (IOException e1) {


					e1.printStackTrace();
				
}		
				m_in.close();
				m_out.close();
				m_socket.close();
			
} catch (IOException e) {

				
				e.printStackTrace();
			
}
		

		finally{
			
			client.close();
			m_socket.close();
		}
		}	
}
	public static String core_router(String string) throws Exception {

		
		String[] split1=string.split("&");
		int marking_value=Integer.parseInt(split1[8]);
		int link=Integer.parseInt(split1[5]);
		//split1=string.split("&");
		if(split1[7].equals("true")){

			marking_value=(marking_value*no_of_interfaces+link);
			split1[8]=Integer.toString(marking_value);
			d+="\nMarking value at core:"+split1[8]+"\n";
			t1.setText(d);
		
}
		
		return make_packet(split1);
	
}
	public static String edge_router(String splits2) {

		
		String split=splits2;
		String[] spli = null;
		String marking_value = null;
		split+="&"+"true"+"&"+"0";
		spli=split.split("&");
		spli[7]="true";
		try {

			marking_value = JDBC.mactoid_value(spli[2]);
		
} catch (Exception e) {

			
			e.printStackTrace();
		
}
		spli[8]=marking_value;
		d+="Mark field :"+spli[7]+"\nMarking value at edge:"+spli[8];
		
		//System.out.println("Mark field:"+split[7]+"Marking value:"+split[8]);

		JOptionPane.showMessageDialog(null,"marking values are calculated with router and appended to packet","",JOptionPane.PLAIN_MESSAGE);
		
		return make_packet(spli);
		

	
}
	public static String make_packet(String[] split) {

		
		String line1=split[0]+"&"+split[1]+"&"+split[2]+"&"+split[3]+"&"+split[4]+"&"+split[5]+"&"+split[6]+"&"+split[7]+"&"+split[8];
return line1;


	
}
	

	
}
