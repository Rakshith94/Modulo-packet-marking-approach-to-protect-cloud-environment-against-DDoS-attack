import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class Server extends JFrame 
{
	JLabel l1,l2,l3,l4,l5,l6;
	static JTextArea t1;
	JButton b1,b2,b3,b11;
	JFrame jf1;
	String ip;
	public static int num=24;
	 static ServerSocket server;
	 static Socket socket;
	 static DataInputStream in,in1;
	 static DataOutputStream out,out1;
	private static String line,files;
	private static Socket client1;
	public Server(){
		initialize();	
}
	private void initialize() {
		Container cp=this.getContentPane();
		cp.setLayout(null);
		l1 = new JLabel("A Modulo Packet Marking Approach to Protect",JLabel.CENTER);
		l1.setFont(new Font("Serif",Font.BOLD,20));
		l3 = new JLabel("Cloud Environment against DDoS Attacks",JLabel.CENTER);
		l3.setFont(new Font("Serif",Font.BOLD,20));

		t1= new JTextArea();
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(0,0,0),new Color(0,0,0)));
		t1.setBorder(BorderFactory.createEtchedBorder(new Color(123,10,225),new Color(193,201,55)));
		JScrollPane jsp1=new JScrollPane(t1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		t1.setEditable(false);
		
		l4=new JLabel("Server",JLabel.CENTER);
		l4.setFont(new Font("Serif",Font.BOLD,18));

		b1 = new JButton("Resources");
		//b2.setToolTipText("Click to clear the text.");

		l5=new JLabel("Recieved message:");

		b2=new JButton("Exit");

		//b3=new JButton("Server info.");

		addcomponent(cp,l1,0,0,600,20);
		addcomponent(cp,l5,20,80,600,20);
		addcomponent(cp,l4,0,40,600,20);
		addcomponent(cp,l3,0,20,600,20);
		addcomponent(cp,jsp1,40,100,500,150);
		addcomponent(cp,b1,110,280,120,25);
		addcomponent(cp,b2,320,280,120,25);
		//addcomponent(cp,b3,30,280,150,25);
		this.setSize(600,350);
		this.setVisible(true);
		this.setBounds(430,380,600,360);
		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				System.exit(0);
			
}
		
});
		
		

				  // Directory path here
				String path = "E:\\d\\"; 
				
				  File folder = new File(path);
				  File[] listOfFiles = folder.listFiles(); 
				  
				
				  for (int i = 0; i<listOfFiles.length; i++) 
				  {
				   if (listOfFiles[i].isFile()) 
				   {

				   files += listOfFiles[i].getName()+"\n";
				 
				   }
				  }
				   b1.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e){
							jf1=new JFrame();
							jf1.setLayout(null);

							

							l1=new JLabel("List of files:",JLabel.CENTER);
							l1.setFont(new Font("Serif",Font. BOLD,20));
							
							final JTextArea ta1 = new JTextArea();
							JScrollPane jsp = new JScrollPane(ta1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
						    ta1.setEditable(false);    

							b11=new JButton("Exit");				
							addcomponent(jf1,l1,10,20,300,20);
							addcomponent(jf1,jsp,10,50,250,180);
							addcomponent(jf1,b11,100,235,80,25);
							jf1.setSize(300, 300);
							jf1.setVisible(true);
							ta1.append(files);
				  
				  b11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){

					ta1.setText(null);
					Container frame = b11.getParent();
					do 
						frame = frame.getParent(); 
					while (!(frame instanceof JFrame));                                      
					((JFrame) frame).dispose();
			
				
}
			
});
				  
}

});
}
	
	public static void main(String[] args) {

	
	
		Server obj=new Server();

		try {

			server = new ServerSocket(5005);
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

				System.out.println("Server waiting at "+ server.getLocalPort());
				socket = server.accept();
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				line = in.readUTF();
				String[] splits= obj.line.split("&");
				
				t1.setText("Source IP : "+splits[1]+"\n"+"MAC address : "+splits[2]+"\n"+"File Type : "+splits[3]+"\n"+"File name:"+splits[4]+"\n"+"Link no : "+splits[5]+"\n"+"Time:"+ new SimpleDateFormat("HH:mm:ss").format(new Date())+"\n"+"Unique value:"+splits[0]+"\n"+"Marking value:"+splits[8]);
			obj.create_file(splits[4],splits[6]);
				in.close();
				out.close();
				socket.close();
			
} catch (IOException e) {

				
				e.printStackTrace();
			
}
			try{
				
				client1 = new Socket("localhost", 5006);
				OutputStream outtoserver1 = client1.getOutputStream();
				out1 = new DataOutputStream(outtoserver1);
				InputStream infromserver1 = client1.getInputStream();
				in1 = new DataInputStream(infromserver1);				
				out1.writeUTF(files);
				System.out.println("done");
				out1.flush();
				in1.close();
				out1.close();
				client1.close();			
	}

			catch (IOException e1) {

			
				e1.printStackTrace();
			
	}		
		
}
		
}
	
	
	private void addcomponent(Container cp,Component c, int startx, int starty, int width, int height) {


		c.setBounds(startx,starty,width,height);
		cp.add(c);

	
}
public void create_file(String fname, String msg) throws IOException{

	String add="E:\\d\\";
	add+=fname;
	FileWriter ryt=new FileWriter(add);
	BufferedWriter out=new BufferedWriter(ryt);
	out.write(msg+"!");
	out.close();

}

}
