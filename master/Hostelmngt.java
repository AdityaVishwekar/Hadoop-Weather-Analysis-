import java.applet.* ;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.net.*;

public class Hostelmngt extends JApplet implements ActionListener
{
	
	Label name = new Label("HOSTEL MANAGEMENT");
	Button RoomButton=new Button("ROOM");
	Button StudentButton=new Button("STUDENT");
	Button WorkingButton=new Button("WORKING");	
	Button ExpensesButton=new Button("BILL");
	Button ExpenButton=new Button("DETAILS");

	public void init() 
	{
	  // A textField to get text input
		setLayout(null);
		getContentPane().setBackground(Color.gray);
			name.setBounds(20,10,190,40);
			name.setFont(new Font("Times New Roman", Font.BOLD, 14));

			RoomButton.setBounds(20,90,100,30);
			RoomButton.setFont(new Font("Times New Roman",Font.BOLD,14));
			StudentButton.setBounds(20,190,100,30);
			StudentButton.setFont(new Font("Times New Roman", Font.BOLD, 14));


			WorkingButton.setBounds(20,290,100,30);
			WorkingButton.setFont(new Font("Times New Roman", Font.BOLD, 14));

			ExpensesButton.setBounds(20,390,100,30);
			ExpensesButton.setFont(new Font("Times New Roman", Font.BOLD, 14));

			ExpenButton.setBounds(20,490,100,30);
			ExpenButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
	        add(name);
			add(RoomButton);
			add(StudentButton); 
			add(WorkingButton);
			add(ExpensesButton);
			add(ExpenButton);
			
			
	     RoomButton.addActionListener(this);
		 StudentButton.addActionListener(this);
		 WorkingButton.addActionListener(this);
		ExpensesButton.addActionListener(this);
		ExpenButton.addActionListener(this);
	
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
		

	}
		public void actionPerformed(ActionEvent ae)
		{
				
				
			if (ae.getSource()==RoomButton)
			 {
			
		  	  try
			{	
		  		Room c=new Room();
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
			
			 if (ae.getSource()==StudentButton)
			 {
		 	try
		 	{		
		 		Student c=new Student();
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
		                else if (ae.getSource()== WorkingButton)
			 {
			try
			{	
				
				Working c=new Working();
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
			else if (ae.getSource()== ExpensesButton)
			 {
			try
			{	
				Expenses c=new Expenses();
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
		  
		if(ae.getSource()== ExpenButton)
		{
			try
			{	
			HostelerDetails c=new HostelerDetails();
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
}


	
}