import java.sql.*;
public class JDBC {
	static String update_string;
	static PreparedStatement ps;
	static Connection con;
	static String MAC,key;
	static int link;
	public static void ConnectDatabase()
	{

		try
		{

			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata","root","prakash");
			System.out.println("Connected to Database...");			
		
		}
		catch (Exception e)
		{

			System.out.println(e);
		
		}
	
	}
	public static void update_sts(String Name,String u_name,String pass,String email,String key,int num, String mac)
	{

		PreparedStatement ps;
		update_string="insert into  User_data values (?,?,?,?,?,?,?)";
		try {

			ps = con.prepareStatement(update_string);
			ps.setString(1, Name);
			ps.setString(2, u_name);
			ps.setString(3, pass);
			ps.setString(4, email);
			ps.setString(5, key);
			ps.setInt(6,num);
			ps.setString(7,mac);
			ps.executeUpdate();
			System.out.println("updated  successfully");
		
		} 
		catch (SQLException e)
		{

			e.printStackTrace();
		
		}

	}
	public static String authenticate(String name)
	{
		String val = null;
		String query; 
		query ="select password,mac,key1,link_no from user_data where username=?";

		try {

			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setString(1,name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				val=rs.getString("password");
				MAC=rs.getString("mac");
				key=rs.getString("key1");
				link=Integer.parseInt(rs.getString("link_no"));
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e ) 
		{
		e.printStackTrace();
		} 
		return val;
	
}
public static void mactoid(String mac) {

		String MAC=mac;
		int macint;
		String[] MACS = MAC.split("-");
		MAC="";
		for(int i=3;i<6;i++)
			MAC+=MACS[i];
		System.out.println(MAC);
		macint = Integer.parseInt(MAC, 16);
		PreparedStatement ps;
		update_string="insert into  MAC_to_ID values (?,?)";
		try {

			ps = con.prepareStatement(update_string);
			ps.setString(1, mac);
			ps.setInt(2, macint);
			ps.executeUpdate();
			System.out.println("mac to id updated  successfully");
		
		} 
		catch (SQLException e) 
		{
		e.printStackTrace();
		
		}
	
}

public static String mactoid_value(String mac)throws Exception
{

	String val = null;
	String query = null ;

	// Statement stmt = null;
	query ="select NUMERIC1 from MAC_to_ID where MAC=?";
	
	try {

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,mac);
		//stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			//  sts = rs.getInt("B");
			val=rs.getString(1);
		
		}
		rs.close();
		stmt.close();

	}

	catch (SQLException e ) 
	{
		e.printStackTrace();

	} 
	return val;
	}
	public static int mactointerface_value(String mac)throws Exception
	{

	int val = 0;
	String query = null ;


	// Statement stmt = null;

	query =
			"select LINK_NO from MAC_to_INTERFACE where MAC=?";


	try {

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1,mac);
		//stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			//  sts = rs.getInt("B");
			val=rs.getInt(1);
		}
		rs.close();
		stmt.close();
	}

	catch (SQLException e ) 
	{
		e.printStackTrace();
	} 
	return val;
}

public static void mactointerface(String mac,int link) {

	int macinter=link;


	PreparedStatement ps;
	update_string="insert into  MAC_to_INTERFACE values (?,?)";
	try {

		ps = con.prepareStatement(update_string);
		ps.setString(1, mac);
		ps.setInt(2, macinter);
		ps.executeUpdate();
		System.out.println("mac to interface  table updated  successfully");
	
} catch (SQLException e) {

		
		e.printStackTrace();
	
}



}

public static void marking(String ip,String mac) 
{

	try {
		String IP=ip;

		int link=mactointerface_value(mac);
		int previous_marking=Integer.parseInt(mactoid_value(mac));
		int marking_value=previous_marking*24+link;
		PreparedStatement ps;
		update_string="insert into  Marking_value_table values (?,?)";

		ps = con.prepareStatement(update_string);
		ps.setString(1, IP);
		ps.setInt(2, marking_value);
		ps.executeUpdate();
		System.out.println("marking  table updated  successfully");
	
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

}
public static String checking(String mac,String link)throws Exception
{
	String check = null;
	String query = null ;
	// Statement stmt = null;
	query ="select KEY1 from User_data where MAC=? and link_no=?";
	try {
		PreparedStatement stmt1 = con.prepareStatement(query);
		stmt1.setString(1,mac);
		stmt1.setString(2,link);
		//stmt = con.createStatement();
		ResultSet rs = stmt1.executeQuery();
		if(rs.next())
		{
			//  sts = rs.getInt("B");
			check=rs.getString(1);
			
}
		rs.close();
		stmt1.close();	
}
	catch (SQLException e ) 
	{
		e.printStackTrace();	
	} 
	return check;
}
public static String marking_check(String ip_check) {

	String marking = null;
	String query = null ;

	query =
			"select MARKING_VALUE from Marking_value_table where IP=?";


	try {

		PreparedStatement stmt1 = con.prepareStatement(query);
		stmt1.setString(1,ip_check);
		ResultSet rs = stmt1.executeQuery();
		if(rs.next())
		{
			marking=rs.getString(1);
		
}
		rs.close();
		stmt1.close();

	
}

	catch (SQLException e ) {


		e.printStackTrace();

} 
	return marking;


}


}