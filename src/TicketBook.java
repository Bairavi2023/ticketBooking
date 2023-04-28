import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TicketBook {
	static String user_name;
	static PreparedStatement pstmt;
	static Connection con = MainApp.con;
	static Scanner sc = new Scanner(System.in);
	private static ResultSet res;
	static void ticketList(String user) {
		user_name = user;
		System.out.println(" Select an option: \n"
				+ "1. Add ticket\n"
				+ "2. View ticket\n"
				+ "3. Update ticket\n"
				+ "4. Remove ticket\n"
				+ "5. Logout");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			newBooking();
			break;
		}
		case 2: {
			viewTicket();
			break;
		}
		case 3: {
			updateTicket();
			break;
		}
		case 4: {
			removeTicket();
			break;
		}
		
		case 5: {
			MainApp.main(null);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	

}
   static void removeTicket() {
		// TODO Auto-generated method stub
	   
		
	}
	static void updateTicket() {
		// TODO Auto-generated method stub
		try {
		String sql="update booking set movie_name=? where username=? and ticket_no=?";
		pstmt=con.prepareStatement(sql);
		System.out.println("Enter the ticket number");
		pstmt.setInt(3, sc.nextInt());
		pstmt.setString(2,user_name);
		System.out.println("Enter the movie name");
		pstmt.setString(1, sc.next());
		int x=pstmt.executeUpdate();
		if(x>0) {
			System.out.println("Ticket updated");
			ticketList(user_name);
		}
		else {
			System.out.println("Ticket update failed");
			ticketList(user_name);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	static void viewTicket() {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from booking where user_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			res = pstmt.executeQuery();
			while(res.next()==true) {
				System.out.println("Ticket No\t: "+res.getInt(1));
				System.out.println("Movie Name \t: "+res.getString(2));
				System.out.println("Review \t: "+res.getString(3));
				System.out.println("--------------------------------------");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void newBooking() {
		// TODO Auto-generated method stub
		try {
			System.out.println(con);
			System.out.println("Enter the ticket_no");
			int ticketNo=sc.nextInt();
			System.out.println("Enter the movie name:");
			String movieName = sc.next();
			System.out.println("Enter the review: ");
			String review = sc.next();
			String sql = "insert into booking (ticket_no,user_name,movie_name,review) values (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ticketNo);
			pstmt.setString(2, user_name);
			pstmt.setString(3, movieName);
			pstmt.setString(4, review);
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Ticket Added successfully.\n");
				ticketList(user_name);
			}
			else {
				System.out.println("Failed to add ticket");
				ticketList(user_name);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
