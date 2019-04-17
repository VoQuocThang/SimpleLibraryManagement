import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 

class LibrianList extends JFrame {
    DefaultTableModel dmt;
    JTable table;
	public LibrianList() {
		    dmt = new DefaultTableModel();
		    dmt.setColumnIdentifiers(new Object[]{"ID","Name","Password","Email","Address","City","Contact"});
		    table = new JTable(dmt);
		    getList();
		    table.setModel(dmt);
		    JScrollPane panel = new JScrollPane(table);
		    add(panel);
		    setSize(550,400);
		    setVisible(true);
	}
	public void getList() {
		   try {
			   
			      String query = "select * from Librian";
			      Statement s = SimpleLibraryMangement.connector.createStatement();
			      ResultSet cursor = s.executeQuery(query);
			      while(cursor.next()) {
			    	    dmt.addRow(new Object[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),
			    	    		                cursor.getString(4),cursor.getString(5),cursor.getString(6),
			    	    		                cursor.getString(7)
			    	    		    });
			      }
			      cursor.close();
			   
		   }catch(Exception e) {
			   
		   }
	}
}

class IssuedBookList extends JFrame{
	 DefaultTableModel dmt;
	    JTable table;
	public IssuedBookList() {
		 dmt = new DefaultTableModel();
		    dmt.setColumnIdentifiers(new Object[]{"ID","Bookcallno","StudentId","StudentName","StudentContact","issued_date"});
		    table = new JTable(dmt);
		    getList();
		    table.setModel(dmt);
		    JScrollPane panel = new JScrollPane(table);
		    add(panel);
		    setSize(550,400);
		    setVisible(true);
	}
	public void getList() {
		   try {
			   
			      String query = "select * from Issued";
			      Statement s = SimpleLibraryMangement.connector.createStatement();
			      ResultSet cursor = s.executeQuery(query);
			      while(cursor.next()) {
			    	    dmt.addRow(new Object[]{cursor.getInt(1),cursor.getString(2),cursor.getInt(3),
			    	    		                cursor.getString(4),cursor.getString(5),cursor.getDate(6),
			    	    		       
			    	    		    });
			      }
			      cursor.close();
			   
		   }catch(Exception e) {
			   
		   }
	}
}

class BookList extends JFrame{
	 DefaultTableModel dmt;
	JTable table;
	public BookList() {
		dmt = new DefaultTableModel();
	    dmt.setColumnIdentifiers(new Object[]{"ID","callno","Name","author","publisher","quantity","issued","added_date"});
	    table = new JTable(dmt);
	    getList();
	    table.setModel(dmt);
	    JScrollPane panel = new JScrollPane(table);
	    add(panel);
	    setSize(550,400);
	    setVisible(true);
	}
	public void getList() {
		   try {
			   
			      String query = "select * from Book";
			      Statement s = SimpleLibraryMangement.connector.createStatement();
			      ResultSet cursor = s.executeQuery(query);
			      while(cursor.next()) {
			    	    dmt.addRow(new Object[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),
			    	    		                cursor.getString(4),cursor.getString(5),cursor.getInt(6),
			    	    		                cursor.getInt(7),cursor.getDate(8)
			    	    		    });
			      }
			      cursor.close();
			   
		   }catch(Exception e) {
			   
		   }
	}
}

class AdminView extends JFrame implements ActionListener{
	public AdminView() {
		   JPanel panel = new JPanel();
		   JLabel title = new JLabel("Admin section");
		   JButton add = new JButton("Add librian");
		   JButton delete = new JButton("Delete librian");
		   JButton view = new JButton ("View librian");
		   JButton logout = new JButton ("Log out");
		 
		   add.addActionListener(this);
		   delete.addActionListener(this);
		   view.addActionListener(this);
		   logout.addActionListener(this);
		   
		   panel.add(title);
		   panel.add(add);
		   panel.add(delete);
		   panel.add(view);
		   panel.add(logout);
		   panel.setLayout(new GridLayout(5,1,5,5));
		   add(panel);
		   setSize(400,450);
		   setVisible(true);
	}
	
	public void addLibrian() {
		  // create sign up form
		  JTextField name = new JTextField();
		  JPasswordField password = new JPasswordField();
		  JTextField email = new JTextField(); 
		  JTextField address = new JTextField(); 
		  JTextField city = new JTextField(); 
		  JTextField contact = new JTextField(); 
		  Object[] librian = {
				  "Name:",name,
				  "Password:",password,
				  "Email:",email,
				  "Address:",address,
				  "City:",city,
				  "Contact:",contact
		  };
		  int option = JOptionPane.showConfirmDialog(null, librian, "New librian", JOptionPane.OK_CANCEL_OPTION);
		  if(option==JOptionPane.OK_OPTION) {
         // add to the librian database
		   try {
			   
		   String query = "insert into Librian(name,password,email,address,city,contact)"
				          +" values(?,?,?,?,?,?)";
		   
		   PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
		   s.setString(1, name.getText());
		   s.setString(2, String.valueOf(password.getPassword()) );
		   s.setString(3, email.getText());
		   s.setString(4, address.getText());
		   s.setString(5, city.getText());
		   s.setString(6, contact.getText());
		   s.executeUpdate();
		   JOptionPane.showMessageDialog(null, "Add librian successfully", null, JOptionPane.INFORMATION_MESSAGE);

		   } catch(Exception e) {
			     JOptionPane.showMessageDialog(null, "Add librian failed", null, JOptionPane.ERROR_MESSAGE);
			   e.printStackTrace();
		   }
		 } 
		   
	}
	public void deleteLibrian() {
		      String input = JOptionPane.showInputDialog("Please enter the id of librian you want to delete");
		      System.out.println(input);
		      int id = Integer.parseInt(input);
		      try {
				   
			      String query = "Delete from Librian where ID = ?";
			      PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
			      s.setInt(1, id);
			      s.executeUpdate();
			      JOptionPane.showMessageDialog(null, "Delete librian successfully", null, JOptionPane.INFORMATION_MESSAGE);
			    
		   }catch(Exception e) {
			  JOptionPane.showMessageDialog(null, "Delete librian failed", null, JOptionPane.ERROR_MESSAGE);
		   }
	}
	public void actionPerformed(ActionEvent e) {
		   if(e.getActionCommand().equals("Add librian")) {
			       addLibrian();
		   }
		   else if (e.getActionCommand().equals("Delete librian")) {
			      deleteLibrian();
		   }
		   else if(e.getActionCommand().equals("View librian")) {
			       new LibrianList();     
		   }
		   else {
			           setVisible(false);
			           dispose();
		   }
	}
}

class Admin extends JFrame {
	
	    JTextField username;
	    JPasswordField password;
	    
	    public Admin() {
	           username = new JTextField();
	           password = new JPasswordField();
	           Object[] admin = {
	        	   "Username:",username,
	        	   "Password:",password
	           };
	        int option=JOptionPane.showConfirmDialog(null,admin , "Admin log in", JOptionPane.OK_CANCEL_OPTION);
	        if(option==JOptionPane.OK_OPTION) {
	        if(check()) {
	        	     System.out.println("Admin log in successfully"); 
	        	     setVisible(false);
	        	     new AdminView();
	        	     dispose();
	        }
	        else {
	        	    JOptionPane.showMessageDialog(null, "Wrong account", null, JOptionPane.ERROR_MESSAGE);
	        }
	       }
	        
	    }
	   public boolean check() {
		   
		   try {
			   
		   String query = "Select * from Admin where name = ? and password = ?";
		   PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
		   s.setString(1, username.getText());
		   s.setString(2, String.valueOf(password.getPassword()) );
		   ResultSet cursor = s.executeQuery();
		   if(cursor.next()) return true;
		   cursor.close();
		   
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
		  
		   return false;
	   }
}

class LibrianView extends JFrame implements ActionListener{
	 public LibrianView() {
		   JPanel panel = new JPanel();
		   JLabel title = new JLabel("Librian section");
		   JButton add = new JButton("Add books");
		   JButton view = new JButton ("View books");
		   JButton issue = new JButton("Issue book");
		   JButton issueView = new JButton("View issued book");
		   JButton returnBook = new JButton("Return book");
		   JButton logout = new JButton ("Log out");
		 
		   add.addActionListener(this);
		   view.addActionListener(this);
		   issue.addActionListener(this);
		   issueView.addActionListener(this);
		   returnBook.addActionListener(this);
		   logout.addActionListener(this);
		   
		   panel.add(title);
		   panel.add(add);
		   panel.add(view);
		   panel.add(issue);
		   panel.add(issueView);
		   panel.add(returnBook);
		   panel.add(logout);
		   panel.setLayout(new GridLayout(7,1,5,5));
		   add(panel);
		   setSize(400,450);
		   setVisible(true);
	 }
	 public void addBook() {
		  // create adding books form
		  JTextField callno = new JTextField();
		  JTextField name = new JTextField();
		  JTextField author = new JTextField(); 
		  JTextField publisher = new JTextField(); 
		  JTextField quantity = new JTextField(); 
		  Object[] book = {
				  "Callno:",callno,
				  "Name:",name,
				  "Author:",author,
				  "Publisher:",publisher,
				  "Quantity:",quantity
				
		  };
		  int option = JOptionPane.showConfirmDialog(null, book, "New book", JOptionPane.OK_CANCEL_OPTION);
		  if(option==JOptionPane.OK_OPTION) {
        // add to the Book database
		   try {
			   
		   String query = "insert into Book(callno,name,author,publisher,quantity,added_date)"
				          +" values(?,?,?,?,?,GETDATE())";
		   
		   PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
		   s.setString(1, callno.getText());
		   s.setString(2, name.getText());
		   s.setString(3, author.getText());
		   s.setString(4, publisher.getText());
	       s.setInt(5, Integer.parseInt(quantity.getText()));
		   s.executeUpdate();
		   JOptionPane.showMessageDialog(null, "Add book successfully", null, JOptionPane.INFORMATION_MESSAGE);

		   } catch(Exception e) {
			     JOptionPane.showMessageDialog(null, "Add book failed", null, JOptionPane.ERROR_MESSAGE);
			   e.printStackTrace();
		   }
		 } 
		   
	}
	 public int checkBook(String book) {
		  try {
			   
			   String query = "Select quantity from Book where callno = ?";
			   PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
			   s.setString(1, book);
			   ResultSet cursor = s.executeQuery();
			   if(cursor.next()) { 
				   if(cursor.getInt(1) == 0) return 0;
				   return 1;
			   }
			   cursor.close();
			   
			   } catch(Exception e) {
				   e.printStackTrace();
			   }
		 return -1;
	 }
	 public void updateIssuedBook(String book) {
		 try { 
		      String query = "Update Book set quantity = quantity-1,issued=issued+1 where callno=?";
		      PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
		      s.setString(1, book);
		      s.executeUpdate();
		    
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	 }
	 public void updateReturnBook(String book) {
		 try { 
		      String query = "Update Book set quantity = quantity+1,issued=issued-1 where callno=?";
		      PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
		      s.setString(1, book);
		      s.executeUpdate();
		    
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	 }
	 
	 public void issueBook() {
		 // create issue book form
		  JTextField callno = new JTextField();
		  JTextField name = new JTextField();
		  JTextField id = new JTextField(); 
		  JTextField contact = new JTextField();  
		  Object[] issued = {
				  "Bookcallno:",callno,
				  "Student ID:",id,
				  "Name:",name,
				  "Contact:",contact
				
		  };
		  int option = JOptionPane.showConfirmDialog(null, issued, "New issued book", JOptionPane.OK_CANCEL_OPTION);
		  if(option==JOptionPane.OK_OPTION) {
			int check = checkBook(callno.getText());
			if(check==-1) {
				  JOptionPane.showMessageDialog(null, "Wrong book call number", null, JOptionPane.ERROR_MESSAGE);
				  return;
			}
			else if(check == 0) {
				 JOptionPane.showMessageDialog(null, "Book is not available right now", null, JOptionPane.ERROR_MESSAGE);
				  return;
			}
       // add to the Book database
		   try {
			   
		   String query = "insert into Issued(bookcallno,studentId,studentName,studentContact,issued_date)"
				          +" values(?,?,?,?,GETDATE())";
		   
		   PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
		   s.setString(1, callno.getText());
		   s.setInt(2, Integer.parseInt(id.getText()));
		   s.setString(3, name.getText());
		   s.setString(4, contact.getText());
		   
		   s.executeUpdate();
		   updateIssuedBook(callno.getText());
		   JOptionPane.showMessageDialog(null, "Issue book successfully", null, JOptionPane.INFORMATION_MESSAGE);

		   } catch(Exception e) {
			     JOptionPane.showMessageDialog(null, "Issue book failed", null, JOptionPane.ERROR_MESSAGE);
			   e.printStackTrace();
		   }
		 } 
	 }
	 public boolean checkReturnBook(String book,String id) {
		 try {
			   
			   String query = "Select * from Issued where bookcallno = ? and studentId=?";
			   PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
			   s.setString(1, book);
			   s.setInt(2, Integer.parseInt(id));
			   ResultSet cursor = s.executeQuery();
			   if(cursor.next()) return true;
			   cursor.close();
			   
			   } catch(Exception e) {
				   e.printStackTrace();
			   }
		 return false;
	 }
	 public void returnBook() {
		  JTextField callno = new JTextField();
		  JTextField id = new JTextField();
		  Object[] returnBook = {
				  "Bookcallno:",callno,
				  "Student ID:",id,
				
		  };
		  int option = JOptionPane.showConfirmDialog(null, returnBook, "New issued book", JOptionPane.OK_CANCEL_OPTION);
		  if(option==JOptionPane.OK_OPTION) {
			  if(!checkReturnBook(callno.getText(),id.getText())) {
				  JOptionPane.showMessageDialog(null, "Can't find such issue", null, JOptionPane.ERROR_MESSAGE);
				  return;
			  }
			  try {
				   
			      String query = "Delete from Issued where bookcallno=? and studentId=?";
			      PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
			      s.setString(1, callno.getText());
			      s.setInt(2, Integer.parseInt(id.getText()));
			      s.executeUpdate();
			      updateReturnBook(callno.getText());
			      JOptionPane.showMessageDialog(null, "Return book successfully", null, JOptionPane.INFORMATION_MESSAGE);
			    
		     }catch(Exception e) {
			  JOptionPane.showMessageDialog(null, "Return book failed", null, JOptionPane.ERROR_MESSAGE);
		     }
			  
		  }
	 }
	 public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equals("Add books")) {
			   addBook();
		 }
		 if(e.getActionCommand().equals("View books")) {
			   new BookList();
		 }
		 else if(e.getActionCommand().equals("Issue book")) {
			 issueBook();
		 }
		 else if(e.getActionCommand().equals("View issued book")) {
			 new IssuedBookList();
		 }
		 else if(e.getActionCommand().equals("Return book")) {
			 returnBook();
		 }
		 else if(e.getActionCommand().equals("Log out")) {
			 setVisible(false);
			 dispose();
		 }
	 }
}

class Librian extends JFrame{
	 JTextField username;
	 JPasswordField password;    
	public Librian() {
		 username = new JTextField();
         password = new JPasswordField();
         Object[] admin = {
      	   "Username:",username,
      	   "Password:",password
         };
      int option=JOptionPane.showConfirmDialog(null,admin , "Librian log in", JOptionPane.OK_CANCEL_OPTION);
      if(option == JOptionPane.OK_OPTION) {
      if(check()) {
      	     
      	     setVisible(false);
      	     new LibrianView();
      	     dispose();
      }
      else {
      	    JOptionPane.showMessageDialog(null, "Wrong account", null, JOptionPane.ERROR_MESSAGE);
      }
     }
	}
	 public boolean check() {
		   
		   try {
			   
		   String query = "Select * from Librian where name = ? and password = ?";
		   PreparedStatement s = SimpleLibraryMangement.connector.prepareStatement(query);
		   s.setString(1, username.getText());
		   s.setString(2, String.valueOf(password.getPassword()) );
		   ResultSet cursor = s.executeQuery();
		   if(cursor.next()) return true;
		   cursor.close();
		   
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
		  
		   return false;
	   }
}


// Asking who is accessing?

class AccessOption extends JFrame implements ActionListener{
	 public AccessOption() {
		    JPanel panel = new JPanel();
		    JLabel label = new JLabel("Welcome to MyJava library management");
		    JButton admin = new JButton("Admin right");
		    JButton librian = new JButton("Librian right");
		    admin.addActionListener(this);
		    librian.addActionListener(this);
		    panel.add(label);
		    panel.add(admin);
		    panel.add(librian);
		    panel.setLayout(new FlowLayout());
		    add(panel);
		    setTitle("Access right");
		    setSize(300,200);
		    setVisible(true);
	 }
	 public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equals("Admin right")) {
			   // Admin right 
			  new Admin();
		 }
		 else {
               // Librian right 			 
			  new Librian();
			 
		 }
	 }
}


//main system

public class SimpleLibraryMangement {
	
    public static Connection connector = null;
    
    public static void createAdmintable() {
    	
    	   try {
    		   
    	    String query = "Create table Admin(" 
    	    		        +"ID varchar(50) primary key,"
    	    		        +"name varchar(50)," 
    	    		        +"password varchar(50)"
    	    		        +");";
    	    Statement s = connector.createStatement();
    	    s.executeUpdate(query);
    	    
           } catch(Exception e) {
        	    
           }
    }
    public static void createLibriantable() {
    	 try {
  		   
     	    String query = "Create table Librian(" 
     	    		        +"ID int identity(1,1) primary key,"
     	    		        +"name varchar(50)," 
     	    		        +"password varchar(50),"
     	    		        +"email varchar(50),"
     	    		        +"address varchar(50),"
     	    		        +"city varchar(50),"
     	    		        +"contact varchar(50)"
     	    		        +");";
     	    Statement s = connector.createStatement();
     	    s.executeUpdate(query);
     	    
            } catch(Exception e) {
         	    
            }
    }
    public static void createBooktable() {
    	 try {
    		   
      	    String query = "Create table Book(" 
      	    		        +"ID int identity(1,1) primary key,"
      	    		        +"callno varchar(50)," 
      	    		        +"name varchar(50),"
      	    		        +"author varchar(50),"
      	    		        +"publisher varchar(50),"
      	    		        +"quantity int default 0,"
      	    		        +"issued int default 0,"
      	    		        +"added_date date"
      	    		        +");";
      	    Statement s = connector.createStatement();
      	    s.executeUpdate(query);
      	    System.out.println("Create book successfully");
      	    
            } catch(Exception e) {
          	    
            }
    }
    public static void createIssuedtable() {
    	 try {
  		   
       	    String query = "Create table Issued(" 
       	    		        +"ID int identity(1,1) primary key,"
       	    		        +"bookcallno varchar(50)," 
       	    		        +"studentId int,"
       	    		        +"studentName varchar(50),"
       	    		        +"studentContact varchar(50),"
       	    		        +"issued_date date"
       	    		        +");";
       	    Statement s = connector.createStatement();
       	    s.executeUpdate(query);
       	    System.out.println("Create issued successfully");
       	    
             } catch(Exception e) {
           	    
             }
    }
    
	public SimpleLibraryMangement() {
	   
		//make connection with database on MS SQL Server 
		try {
			   String user ="sa";
			   String password="lethiloi1999";
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               String URL = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=LibraryDatabase";
               connector =DriverManager.getConnection(URL, user, password);
               System.out.println("Connected");
               
               createAdmintable();
               createLibriantable();
               createBooktable();
               createIssuedtable();
 
		}catch(Exception e) {
			   e.printStackTrace();
		}
	
		
	}
	
	
	public static void main(String[] args) {
		       SimpleLibraryMangement slm = new SimpleLibraryMangement();
		       new AccessOption(); 
	}

}
