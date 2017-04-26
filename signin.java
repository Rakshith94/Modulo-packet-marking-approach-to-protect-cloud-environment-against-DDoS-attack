import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
public class signin {
	JFrame frame;
	static String nm;

	public signin(){
		JLabel l1,l2,l5,l6,l7;
		final JTextField t1;
		final JPasswordField p1;
		final JButton b2,b1;

		frame = new JFrame("Client");
		frame.setLayout(null);

		l1=new JLabel("Username:");
		l1.setBounds(50, 120, 80, 20);
		
		l5=new JLabel("Sign in",JLabel.CENTER);
		l5.setBounds(110, 70, 80, 20);
		
		l2=new JLabel("Password:");
		l2.setBounds(50,160, 80, 20);

		t1=new JTextField();
		t1.setBounds(140,120, 100, 20);

		p1=new JPasswordField();
		p1.setBounds(140,160, 100, 20);

		b1=new JButton("login");
		b1.setBounds(50,230, 80, 20);

		b2=new JButton("Sign up");
		b2.setBounds(160,230, 80, 20);

		l6 = new JLabel("A Modulo Packet Marking Approach to Protect",JLabel.CENTER);
		l6.setBounds(0, 5, 320, 20);

		l7 = new JLabel("Cloud Environment against DDoS Attacks",JLabel.CENTER);
		l7.setBounds(0, 30, 320, 20);

		frame.add(b1);
		frame.add(b2);
		frame.add(l5);
		frame.add(t1);
		frame.add(p1);
		frame.add(l1);
		frame.add(l2);
		frame.add(l6);
		frame.add(l7);
		frame.setSize(320,350);
		frame.setVisible(true);
		b2.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				Registration.main(null);
				
			}
		});
		b1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				String check="";
				String uname;
			char[] pass;
			String res="";
			uname=t1.getText();
			pass=p1.getPassword();
			nm=uname;
			for(int i=0;i<pass.length;i++)
			check+=Character.toString(pass[i]);
			try {
				JDBC.ConnectDatabase();
				 res= JDBC.authenticate(uname);				
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			
			if(res.equals(check))
			{
				//destry(b1);
				//initialize();
				String alrt="login successfull\n\nyour secret key is used to communicate with server";
				JOptionPane.showMessageDialog(frame,alrt,"",JOptionPane.PLAIN_MESSAGE);
				frame.setVisible(false);
				Client.main(null);
			}
			else
			{

				JOptionPane.showMessageDialog(frame,"password/username  entered is incorrect"," ",JOptionPane.PLAIN_MESSAGE);
				t1.setText(null);
				p1.setText(null);
			}
			}
		});
}
public static void main(String[] args) {
		
new signin();
		
}
}