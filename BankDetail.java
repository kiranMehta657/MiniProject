
import java.lang.*;
import java.sql.*;
import java.util.regex.Pattern;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class BankDetail extends JFrame implements ActionListener
{

JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,sub;
JLabel l1,l2,l3,l4,l5;
JTextField tf1,tf2,tf3,tf4,tf5;
JComboBox cb;
String name;

    public void showDetail()
    {
    	super.setBounds(250,250,400,300);
    	super.setResizable(false);
    	super.setTitle("HomePage");
    	 
    	btn1=new JButton("Open_Account");
        btn1.setBounds(100,20,150,50);
    	super.add(btn1);
    	btn1.addActionListener(new ActionListener()
    			{

    		
    		public void actionPerformed(ActionEvent e) 
    			
    		
    			{
    			BankDetail bd2=new BankDetail();
   		         bd2.setGui();
   		           bd2.setVisible(true);		
    			}});
    			
    			
    	btn2=new JButton("Balance_Check");
    	btn2.setBounds(100,80,150,50);
    	super.add(btn2);
    	btn2.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent e) 
		
		
		{
		BankDetail bd3=new BankDetail();
	     bd3.check();
		bd3.setVisible(true);		
		}	
	});
    	
    	
    	super.setLayout(null); 
    	super.setVisible(true);
    	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public  void setGui()
    {
    	
  	  super.setBounds(500,400,600,500);
  	  super.setResizable(false);
  	  super.setTitle("Open_account_Screen");
  	  
  	  l1=new JLabel("Name");
  	  l1.setBounds(40,100,100,40);
  	  super.add(l1);
  	  
  	  tf1=new JTextField("");
  	  tf1.setBounds(120,100,180,40);
  	  super.add(tf1);
  	  
  	  l2=new JLabel("Email_id");
  	  l2.setBounds(40,150,100,40);
  	  super.add(l2);
  	  
  	  tf2=new JTextField("");
  	  tf2.setBounds(120,150,180,40);
  	  super.add(tf2);
  	  
  	  l3=new JLabel("Bank");
  	  l3.setBounds(40,200,100,40);
  	  super.add(l3);
  	  
  	  String[] elements = "sbi pnb icici syndicate_bank ".split(" ");
	  cb=new JComboBox(elements);
	  cb.setBounds(120,200,180,40);
	  super.add(cb);
	
  	  
  	  l4=new JLabel("Account_no");
  	  l4.setBounds(40,250,100,50);
  	  super.add(l4);
  	  
  	  tf4=new JTextField("");
  	  tf4.setBounds(120,250,180,40);
  	  super.add(tf4);
  	  
  	 l5=new JLabel("Amount");
 	  l5.setBounds(40,300,100,50);
 	  super.add(l5);
 	  
 	  tf5=new JTextField("");
 	  tf5.setBounds(120,300,180,40);
 	  super.add(tf5);
 	  
  	  btn1=new JButton("Create_Account");
  	  btn1.setBounds(200,350,200,50);
  	  super.add(btn1);
  	btn1.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent e) 
	
	
	
	{
	 String Name=tf1.getText();
   	 String Email_id=tf2.getText();
   	String Bank=cb.getSelectedItem().toString();
   	String Account_no=tf4.getText();
	String Amount=tf5.getText();
    conn(Name,Email_id,Bank,Account_no,Amount);
	
}});  
  	  

  		super.setLayout(null);
  		super.setVisible(true);
  		 super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	 
    }
    public void check()
    {
    	super.setBounds(400,450,400,300);
    	super.setResizable(false);
    	super.setTitle("Balance_check_Screen");
    	  
    	
    	l1=new JLabel("Account_Number");
    	l1.setBounds(50,50,120,40);
    	super.add(l1);
    	
    	tf1=new JTextField();
    	tf1.setBounds(170,50,120,40);
    	super.add(tf1);
    	
    	l2=new JLabel("Bank");
    	l2.setBounds(50,100,120,40);
    	super.add(l2);
    	
    	
 String[] elements = "SBI PNB ICICI SYNDICATE_BANK ".split(" ");
  	  cb=new JComboBox(elements);
  	  cb.setBounds(170,100,120,40);
  	  super.add(cb);
  	  
  	  
  	sub=new JButton("Check_Balance");
	sub.setBounds(70,200,150,50);
	super.add(sub);
 sub.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
	
try

	{
Class.forName("com.mysql.jdbc.Driver");
Connection c1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdetail","root","root123");
Statement stmt=c1.createStatement();
ResultSet rs=stmt.executeQuery("select Bank,Account_no ,Amount from Account_Details where Account_no='"+tf1.getText()+"' and Bank='"+cb.getSelectedItem().toString()+"'");
if(e.getSource()==sub)
{
if(rs.next())
{
JOptionPane.showMessageDialog(null,"Your Account have "+rs.getString(3)+"rupees");
}
else
{
JOptionPane.showMessageDialog(null,"check your details");
						
}
}
}
catch(Exception ex)
	{
	System.out.println("Error is\t"+ex);
	}
		}});
  	   
         super.setLayout(null);
	     super.setVisible(true);
		 super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	 
    }
    
  public void conn(String Name,String Email_id,String Bank,String Account_no,String Amount)
  {
	   
 try
  {
   Class.forName("com.mysql.jdbc.Driver");
   Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdetail","root","root123");
   String sq="insert into Account_Details (Name,Email_id,Bank,Account_no,Amount) values(?,?,?,?,?)";
   PreparedStatement psm=c1.prepareStatement(sq);
   psm.setString(1, Name);
   psm.setString(2, Email_id);
   psm.setString(3, Bank);
   psm.setString(4, Account_no);
   psm.setString(5, Amount);
   int i=Integer.parseInt(Amount);
   if(i<1000)
   {
   System.out.println("this Amount is not enough for creating acount");
   }
   else
   {
		psm.execute();
		System.out.println("done");
	}
  }
	  
 catch(Exception ex)
{
     System.out.println("Error is\t"+ex);
}
	}
public static void main(String args[])
    {
	BankDetail bd1=new BankDetail();
	  bd1.showDetail();
}
@Override
	public void actionPerformed(ActionEvent e) {
		
		

}  
}





