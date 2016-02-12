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
public class HostelerDetails extends JApplet implements ActionListener 
{
	Button okButton;
	// A textField to get text input
	String actionMessage="";
	
	Label L1=new Label("STUDENT ID:");
	TextField T1 = new TextField(10);
	
	Label L2=new Label("WORKING ID:");
	TextField T2 = new TextField(50);
	
	Label L3=new Label("FNAME");
	TextField T3 = new TextField(50);


	Label L4=new Label("MNAME");
	TextField T4 = new TextField(10);

	Label L5=new Label("LNAME");
	TextField T5 = new TextField(10);

	Label L6=new Label("ADDRESS");
	TextField T6 = new TextField(60);

	Label L7=new Label("PHONE");
	TextField T7 = new TextField(60);
	Label L8=new Label("ROOM NO:");
	TextField T8 = new TextField(60);
	Label L10=new Label("HOSTELER DETAILS");
	Button hosButton = new Button("HOME");
	Button calculateButton = new Button("CALCULATE");
	Button studentButton = new Button("STUDENT");
	Button workingButton = new Button("WORKING");

	String hostname = "localhost";	// If PostgreSQL is running on some other machine enter the IP address of the machine here
	String username = "user1"; // Enter your PostgreSQL username
	String password = "user123"; // Enter your PostgreSQL password
	String dbName = "hostelmngt"; // Enter the name of the databa se that has the university tables.
	String connectionUrl = "jdbc:postgresql://" + hostname +  "/" + dbName;
	Connection conn = null;
	public void init()
	{
	setLayout(null);
	getContentPane().setBackground(Color.gray);
	L1.setBounds(20,100,90,40);
	L1.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T1.setBounds(300,100,290,30);
	L2.setBounds(20,140,100,30);
	L2.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T2.setBounds(300,140,290,30);

	L3.setBounds(20,180,100,30);
	L3.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T3.setBounds(300,180,290,30);


	L4.setBounds(20,220,100,30);
	L4.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T4.setBounds(300,220,290,30);

	L5.setBounds(20,270,100,30);
	L5.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T5.setBounds(300,270,290,30);

	L6.setBounds(20,310,100,30);
	L6.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T6.setBounds(300,310,290,30);
	
	L7.setBounds(20,350,100,30);
	L7.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T7.setBounds(300,350,290,30);
	L8.setBounds(20,390,100,30);
	L8.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T8.setBounds(300,390,290,30);
	L10.setBounds(20,10,590,40);
	L10.setFont(new Font("Monotype Corsiva",Font.BOLD,35));
	workingButton.setBounds(270,570,90,50);
	workingButton.setFont(new Font("Times New Roman",Font.BOLD,14));
	calculateButton.setBounds(70,570,90,50);
	calculateButton.setFont(new Font("Times New Roman",Font.BOLD,14));
	studentButton.setBounds(170,570,90,50);
	studentButton.setFont(new Font("Times New Roman",Font.BOLD,14));
	hosButton.setBounds(370,570,90,50);
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
	add(L7);
	add(T7);
	add(L8);
	add(T8);
	add(L10);
	add(workingButton);
	add(calculateButton);
	add(studentButton);
	add(hosButton);
	calculateButton.addActionListener(this);
	studentButton.addActionListener(this);
	workingButton.addActionListener(this);
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
		if(evt.getSource()==studentButton)
		{
			try
			{
			int room_no=Integer.parseInt(T8.getText());
			PreparedStatement pstmt=conn.prepareStatement("select * from student where room_no=?");
			pstmt.setInt(1,room_no);
			ResultSet rs2 = pstmt.executeQuery();
			while(rs2.next())
			{
			T3.setText(rs2.getString("fname"));
			T4.setText(rs2.getString("mname"));
			T5.setText(rs2.getString("lname"));
			T6.setText(rs2.getString("address"));
			T7.setText(rs2.getString("ph_no"));
			T1.setText(rs2.getString("sid"));
			}
			}
		
		catch (SQLException sqle)
		{
		System.out.println(sqle);
		System.exit(1);
		}
	   }
		if(evt.getSource()==workingButton)
		{
			try
			{
			int room_no=Integer.parseInt(T8.getText());
			PreparedStatement pstmt=conn.prepareStatement("select * from WORKING where room_no=?");
			pstmt.setInt(1,room_no);
			ResultSet rs2 = pstmt.executeQuery();
			while(rs2.next())
			{
			T3.setText(rs2.getString("fname"));
			T4.setText(rs2.getString("mname"));
			T5.setText(rs2.getString("lname"));
			T6.setText(rs2.getString("address"));
			T7.setText(rs2.getString("ph_no"));
			T2.setText(rs2.getString("wid"));
			}
			}
		
		catch (SQLException sqle)
		{
		System.out.println(sqle);
		System.exit(1);
		}
	   }
	}
}	