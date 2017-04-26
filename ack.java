import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class ack extends JFrame 
{
	JLabel l1,l3,l5,l6;
	static JTextArea t1;
	JTextArea jTextField1;
	JButton b1,b2,b3,b4;
	static JFrame frame;
	static ServerSocket server;
	 static Socket socket;
	 static DataInputStream in;
	 static DataOutputStream out;
	static String af;
	public ack()
	{
		initialize();	
	}
	private void initialize() 
	{

		frame = new JFrame("ack");
		frame.setLayout(null);
		l1 = new JLabel("A Modulo Packet Marking \nApproach to Protect",JLabel.CENTER);
		l1.setBounds(0,5,300,20);
		l3 = new JLabel("Cloud Environment against DDoS Attacks",JLabel.CENTER);
		l3.setBounds(0,28,300,20);
		t1= new JTextArea();
		//t1.setBorder(BorderFactory.createEtchedBorder(new Color(0,0,0),new Color(0,0,0)));
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(123,10,225),new Color(193,201,55)));
		t1.setBounds(30,90,250,200);
		//JScrollPane jsp1=new JScrollPane(t1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		t1.setEditable(false);
		t1.setText("");
		l5=new JLabel("Files on Server");
		l5.setBounds(10,70,300,20);
		b3=new JButton("Exit");
		b3.setBounds(120,300,70,20);
		frame.add(l1);
		frame.add(l3);
		frame.add(t1);
		frame.add(l5);
		frame.add(b3);
		frame.setSize(320, 370);
		frame.setVisible(true);
		frame.setBounds(0,350,320,370);
		b3.addActionListener(new ActionListener() 
		{

		public void actionPerformed(ActionEvent e)
		{

				System.exit(0);
			
		}
		
		});

}

	public static void main(String[] args) {

			
new ack();
try {

	server = new ServerSocket(5006);
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
	System.out.println("Server waiting at "+ server.getLocalPort());
	socket = server.accept();
	in = new DataInputStream(socket.getInputStream());
	af = in.readUTF();
	t1.setText(af);
	in.close();
	socket.close();
	
	System.out.println(af);
} catch (IOException e) 
{
	
	e.printStackTrace();

}
}
}
}
