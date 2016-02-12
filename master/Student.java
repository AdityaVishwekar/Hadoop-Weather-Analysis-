import java.applet.* ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JApplet;
public class Student extends JApplet implements ActionListener 
{
	Button okButton;
	// A textField to get text input
	String actionMessage="";
	
	Label L1=new Label("sid");
	TextField T1 = new TextField(10);
	
	Label L2=new Label("fname ");
	TextField T2 = new TextField(50);
	
	Label L3=new Label("mname ");
	TextField T3 = new TextField(50);


	Label L4=new Label("lname ");
	TextField T4 = new TextField(10);

	Label L5=new Label("address ");
	TextField T5 = new TextField(10);

	Label L6=new Label("ph_no(8 dig)");
	TextField T6 = new TextField(60);

	Label L7=new Label("room_no");
	TextField T7 = new TextField(60);
	Label L8=new Label("room_rent");
	TextField T8 = new TextField(60);
	Label L9=new Label("duration");
	TextField T9 = new TextField(60);

	Label L10 =new Label("STUDENT FORM");
	Button addButton = new Button("ADD");
	Button exitButton = new Button("Exit");
	Button hosButton = new Button("HOME");
	
	String hostname = "localhost";	// If PostgreSQL is running on some other machine enter the IP address of the machine here
	String username = "user1"; // Enter your PostgreSQL username
	String password = "user123"; // Enter your PostgreSQL password
	String dbName = "hostelmngt"; // Enter the name of the databa se that has the university tables.
	String connectionUrl = "jdbc:postgresql://" + hostname +  "/" + dbName;
	Connection conn = null;
	
	
	public void init()
	{
	// Tell the applet not to use a layout manager.
	setLayout(null);
	getContentPane().setBackground(Color.gray);
	L10.setBounds(300,10,390,40);
	L10.setFont(new Font("Monotype Corsiva",Font.BOLD,35));
	L1.setBounds(20,70,90,40);
	L1.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T1.setBounds(300,70,290,30);
	L2.setBounds(20,120,100,30);
	L2.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T2.setBounds(300,120,290,30);

	L3.setBounds(20,180,100,30);
	L3.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T3.setBounds(300,180,290,30);


	L4.setBounds(20,220,100,30);
	L4.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T4.setBounds(300,220,290,30);

	L5.setBounds(20,260,100,30);
	L5.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T5.setBounds(300,260,290,30);

	L6.setBounds(20,300,100,30);
	L6.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T6.setBounds(300,300,290,30);

	L7.setBounds(20,340,100,30);
	L7.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T7.setBounds(300,340,290,30);
	L8.setBounds(20,380,100,30);
	L8.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T8.setBounds(300,380,290,30);
	L9.setBounds(20,420,100,30);
	L9.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T9.setBounds(300,420,290,30);

	addButton.setBounds(70,570,90,50);
	addButton.setFont(new Font("Times New Roman",Font.BOLD,14));
	exitButton.setBounds(370,570,90,50);
	exitButton.setFont(new Font("Times New Roman",Font.BOLD,14));
	hosButton.setBounds(200,570,90,50);
	hosButton.setFont(new Font("Times New Roman",Font.BOLD,14));

	// now that all is set we can add these components to the applet
	add(L1);
	add(T1);
	add(L2);
	add(T2);

	add(L3);
	add(T3);

	add(L4);
	add(T4);

	add(L5);
	add(T5);
	add(L6);
	add(T6);

	add(L7);
	add(T7);
	add(L8);
	add(T8);
	add(L9);
	add(T9);
	add(L10);
	add(addButton);
	add(exitButton);
	add(hosButton);
	
	addButton.addActionListener(this);
	exitButton.addActionListener(this);
	hosButton.addActionListener(this);
	}
	
	public void start() 
	{

		try
		{
		Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException cnfe)
		{
		System.out.println("Could not find the JDBC driver!");
		System.exit(1);
		}
		
		
		//Connect to the database
		try 
		{
		conn = DriverManager.getConnection(connectionUrl,username, password);
		System.out.println("Connectedcccccc successfullly");
		}
		catch (SQLException sqle) 
		{
		System.out.println("Connection failed");
		System.out.println(sqle);
		// Uncomment the below line for a more detailed stack trace
		sqle.printStackTrace();
		System.exit(1);
		} 

	}
	public void actionPerformed(ActionEvent evt)

	{
		if (evt.getSource()==hosButton)
		 {
		
	  	  try
		{	
	  		Hostelmngt c=new Hostelmngt();
			c.init();
			c.start();
			this.setContentPane(c);
			revalidate();
			repaint();
		}
		catch(Exception ez)
		{
			System.out.println(ez);
		}	
		}

	if (evt.getSource() == addButton)

	{

	try

	{

	Integer sid=Integer.parseInt(T1.getText());

	String fname=T2.getText();

	String mname=T3.getText();

	String lname=T4.getText();

	String address=T5.getText();

	Integer ph_no=Integer.parseInt(T6.getText());

	Integer room_no=Integer.parseInt(T7.getText());
	Integer room_rent=Integer.parseInt(T8.getText());

	Integer duration=Integer.parseInt(T9.getText());
	

	PreparedStatement pstmt=conn.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?,?)");

	pstmt.setInt(1, sid);

	pstmt.setString(2, fname);

	pstmt.setString(3, mname);

	pstmt.setString(4, lname);

	pstmt.setString(5, address);

	pstmt.setInt(6, ph_no);

	pstmt.setInt(7, room_no);
	pstmt.setInt(8, room_rent);
	pstmt.setInt(9, duration);

	ResultSet rs = pstmt.executeQuery();
	PreparedStatement stmt=conn.prepareStatement("update room set status ='allocated' where room_no=?");
	stmt.setLong(1,room_no);

	ResultSet rs2 = stmt.executeQuery();

	}

	catch (SQLException sqle) {

	System.out.println(sqle);

	System.exit(1);

	}

	} //end of add button

	
	else

	if (evt.getSource() == exitButton)

	{

	try

	{

	//String qs = "CREATE TABLE IF NOT EXISTS PATIENT5(PID VARCHAR(5) NOT NULL,NAME VARCHAR (20) 

	//NOT NULL, SEX CHAR(1) NOT NULL,ADDRESS CHAR (25), DATEADMIT VARCHAR (10),DATEDISCHARGE 

	//VARCHAR(10),CONTACTNO VARCHAR(10),PRIMARY KEY (PID));";

	//String qs="INSERT INTO PATIENT1 

	//values(T1.getText(),T2.getText(),T3.getText(),T4.getText(),T5.getText(),T6.getText(),T7.getText()";

	//String qs="insert into Patient1 values('y','b','b','b','a','a','a')";

	//reparedStatement pstmt=conn.prepareStatement(qs);

	//ResultSet rs = pstmt.executeQuery();

	PreparedStatement pstmt=conn.prepareStatement("select * from Student");

	ResultSet rs = pstmt.executeQuery();

	while(rs.next()){

	T1.setText(rs.getString("sid"));

	T2.setText(rs.getString("fname"));

	T3.setText(rs.getString("mname"));

	T2.setText(rs.getString("lname"));

	T5.setText(rs.getString("address"));

	T6.setText(rs.getString("ph_no"));

	T7.setText(rs.getString("room_no"));
	T8.setText(rs.getString("room_rent"));
	T9.setText(rs.getString("duration"));

	}

	}

	catch (SQLException sqle) {

	System.out.println(sqle);

	System.exit(1);

	}

	} //end of add button

	}
}
