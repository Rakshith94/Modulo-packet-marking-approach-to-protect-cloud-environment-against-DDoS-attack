import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
public class Registration {
	JLabel l1,l2,l3,l4,l5,l6;
	JButton b1,b2;
	JTextField t1,t3,t5;
	JPasswordField t2,t4;
	JFrame fram;
	String key="",ip=address.ip(),password;
	String MAC=address.mac();
	String[] rand={"9","2","4","6","3","8","7","1","0","5","a","b","c","d","e","q","t",
"y","u","i","o","p","s","f","g","h","j","k","l","z","x","v","n","m","A","B","C","D","E","Q",
"T","Y","U","I","O","P","S","F","G","H","J","K","L","Z","X","V","N","M"};
	String name,username,confirm_password,email;
char[] cmp;
	public Registration() {
		JDBC.ConnectDatabase();
		
		fram = new JFrame("Rigester Form");
		fram.setLayout(null);

		l1=new JLabel("Name : ");
		l1.setBounds(10, 70, 50, 20);
		l1.setFont(new Font("Serif",Font.BOLD,14));
		t1=new JTextField();
		t1.setBounds(130, 72,150, 20);
		l2=new JLabel("User name : ");
		l2.setBounds(10, 100, 80, 20);
		l2.setFont(new Font("Serif",Font.BOLD,14));
		t3=new JTextField();
		t3.setBounds(130, 102,150, 20);
		l3=new JLabel("Password : ");
		l3.setBounds(10, 130, 80, 20);
		l3.setFont(new Font("Serif",Font.BOLD,14));
		t4=new JPasswordField();
		t4.setBounds(130, 132,150, 20);
		l4=new JLabel("confirm Password : ");
		l4.setBounds(10, 160,128, 20);
		l4.setFont(new Font("Serif",Font.BOLD,14));
		t2=new JPasswordField();
		t2.setBounds(130, 162,150, 20);
		l5=new JLabel("email id : ");
		l5.setBounds(10, 190, 80, 20);
		l5.setFont(new Font("Serif",Font.BOLD,14));
		t5=new JTextField();
		t5.setBounds(130, 192,150, 20);
		l6=new JLabel("Enter the details below to sign up ",JLabel.CENTER);
		l6.setBounds(0, 20, 300, 25);
		l6.setFont(new Font("Serif",Font.BOLD,18));
		b1=new JButton("Sign up");
		b1.setBounds(50, 230, 80, 20);
		b2=new JButton("Clear");
		b2.setBounds(150, 230, 80, 20);
	
		fram.add(l1);
		fram.add(b1);
		fram.add(t2);
		fram.add(t3);
		fram.add(t4);
		fram.add(t5);
		fram.add(t1);
		fram.add(b2);
		fram.add(l2);
		fram.add(l3);
		fram.add(l4);
		fram.add(l5);
		fram.add(l6);
		
		fram.setSize(320,370);
		fram.setVisible(true);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				t1.setText(null);	
				t2.setText(null);
				t3.setText(null);	
				t4.setText(null);
				t5.setText(null);
		}});
		b1.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e){
						if(!t1.getText().equals("") && !t2.getPassword().equals("") && !t3.getText().equals("") && !t4.getPassword().equals("") && !t5.getText().equals(""))
				{
String s1=Arrays.toString(t2.getPassword());
String s2=Arrays.toString(t4.getPassword());
					if(s1.equals(s2))
					{

						name=t1.getText();
						username=t3.getText();
						char[] upassword=t2.getPassword();
						password=new String(upassword);
						email=t5.getText();
					
					
						Random randomGenerator = new Random();
						for (int x=1; x<= 20;x++){

							int randomInt = randomGenerator.nextInt(58);
							key+=rand[randomInt];
						
							}
					int link_no=randomGenerator.nextInt(24);
					JDBC.update_sts(name, username, password, email, key, link_no,MAC);
					JDBC.mactoid(MAC);
					JDBC.mactointerface(MAC,link_no);
					JDBC.marking(ip, MAC);
					key="";
					t1.setText(null);	
					t2.setText(null);
					t3.setText(null);	
					t4.setText(null);
					t5.setText(null);
					fram.setVisible(false);
					signin.main(null);
					
					
					}
					else

						JOptionPane.showMessageDialog(fram,"password did't match"," ",JOptionPane.WARNING_MESSAGE);
				
				}
				else{

					JOptionPane.showMessageDialog(fram,"Text fields can't be blank"," ",JOptionPane.WARNING_MESSAGE);
				
									
}
}
		
});
	
}
	public static void main(String[] ar){

		new Registration();
	
}


}
