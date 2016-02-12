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
public class Room extends JApplet implements ActionListener 
{
	Button okButton;
	// A textField to get text input
	String actionMessage="";
	
	Label L1=new Label("room_no");
	TextField T1 = new TextField(10);
	
	Label L2=new Label("status");
	TextField T2 = new TextField(50);
	
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
	L1.setBounds(20,10,90,40);
	L1.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T1.setBounds(300,10,290,30);
	L2.setBounds(20,70,100,30);
	L2.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T2.setBounds(300,70,290,30);


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

	Integer room_no=Integer.parseInt(T1.getText());


	String status=T2.getText();


	PreparedStatement pstmt=conn.prepareStatement("insert into room values(?,?)");

	pstmt.setInt(1, room_no);

	

	pstmt.setString(2, status);

	
	ResultSet rs = pstmt.executeQuery();

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

	Integer room_no=Integer.parseInt(T1.getText());


	PreparedStatement pstmt=conn.prepareStatement("select * from room");

	ResultSet rs = pstmt.executeQuery();

	while(rs.next()){

	T1.setText(rs.getString("room_no"));

	//T2.setText(rs.getString("NAME"));

	//T3.setText(rs.getString("SEX"));

	T2.setText(rs.getString("status"));

	//T5.setText(rs.getString("DATEADMIT"));

	//T6.setText(rs.getString("DATEDISCHARGE"));

	//T7.setText(rs.getString("CONTACTNO"));

	}

	}

	catch (SQLException sqle) {

	System.out.println(sqle);

	System.exit(1);

	}

	} //end of add button

	}
}