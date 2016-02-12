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
public class Expenses extends JApplet implements ActionListener 
{
	Button okButton;
	// A textField to get text input
	String actionMessage="";
	
	Label L1=new Label("id");
	TextField T1 = new TextField(10);
	
	Label L2=new Label("room_no ");
	TextField T2 = new TextField(50);
	
	Label L3=new Label("room_rent ");
	TextField T3 = new TextField(50);


	Label L4=new Label("duration ");
	TextField T4 = new TextField(10);

	Label L5=new Label("deposit ");
	TextField T5 = new TextField(10);

	Label L6=new Label("mess_fees");
	TextField T6 = new TextField(60);

	Label L7=new Label("total");
	TextField T7 = new TextField(60);
	Button hosButton = new Button("HOME");
	Label L8=new Label("BILL");

	
	Button calculateButton = new Button("CALCULATE");
	Button showButton = new Button("SHOW");
	Button addButton = new Button("ADD");

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
	L8.setBounds(20,10,90,40);
L8.setFont(new Font("Monotype Corsiva",Font.BOLD,35));
L1.setBounds(20,80,90,40);
	L1.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T1.setBounds(300,80,290,30);
	L2.setBounds(20,120,100,30);
	L2.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T2.setBounds(300,120,290,30);

	L3.setBounds(20,180,100,30);
	L3.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T3.setBounds(300,180,290,30);


	L4.setBounds(20,220,100,30);
	L4.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T4.setBounds(300,220,290,30);

	L5.setBounds(20,270,100,30);
	L5.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T5.setBounds(300,270,290,30);

	L6.setBounds(20,320,100,30);
	L6.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T6.setBounds(300,320,290,30);
	
	L7.setBounds(20,370,100,30);
	L7.setFont(new Font("Times New Roman", Font.BOLD, 14));
	T7.setBounds(300,370,290,30);
	addButton.setBounds(270,570,90,50);
	addButton.setFont(new Font("Times New Roman",Font.BOLD,14));
	calculateButton.setBounds(70,570,90,50);
	calculateButton.setFont(new Font("Times New Roman",Font.BOLD,14));
	showButton.setBounds(170,570,90,50);
	showButton.setFont(new Font("Times New Roman",Font.BOLD,14));
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
add(L8);
	add(addButton);
	add(calculateButton);
	add(showButton);
	add(hosButton);
	calculateButton.addActionListener(this);
	showButton.addActionListener(this);
	addButton.addActionListener(this);
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
		if(evt.getSource()==calculateButton)
		{
		
				 

	            	
	       	     		int d=3000;
	       				int ms=4000;
	       				String t1 = Integer.toString(d);
	       				String t2 = Integer.toString(ms);
	       				T5.setText(t1);
	       				T6.setText(t2);
				int f=Integer.parseInt(T3.getText());
				int h=Integer.parseInt(T4.getText());
				int total=0;
				total=f*h+d+ms;
				String e = Integer.toString(total);
   				T7.setText(e);
				
		}	

		if (evt.getSource() == addButton)

		{

		try

		{

		Integer id=Integer.parseInt(T1.getText());

		Integer room_no=Integer.parseInt(T2.getText());

		Integer room_rent=Integer.parseInt(T3.getText());

		Integer duration=Integer.parseInt(T4.getText());

		


		PreparedStatement pstmt=conn.prepareStatement("insert into expenses values(?,?,?,?)");

		pstmt.setInt(1, id);

		pstmt.setLong(2, room_no);

		pstmt.setLong(3, room_rent);

		pstmt.setLong(4, duration);
		
		ResultSet rs = pstmt.executeQuery();


		}

		catch (SQLException sqle) {

		System.out.println(sqle);

		System.exit(1);

		}

		} //end of add button
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
		if(evt.getSource()==showButton)
		{
			try
			{
			int id=Integer.parseInt(T1.getText());
			PreparedStatement pstmt=conn.prepareStatement("select * from expenses where id=?");
			pstmt.setInt(1,id);
			ResultSet rs2 = pstmt.executeQuery();
			while(rs2.next())
			{
			T2.setText(rs2.getString("room_no"));
			T3.setText(rs2.getString("room_rent"));
			T7.setText(rs2.getString("total"));
			T4.setText(rs2.getString("duration"));
			T5.setText(rs2.getString("deposit"));
			T6.setText(rs2.getString("mess_fees"));
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
