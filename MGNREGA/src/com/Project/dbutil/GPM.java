package com.Project.dbutil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class GPM {

	Dbutil db = new Dbutil();
	Connection con = db.provideConnection();
	Scanner sc_obj = new Scanner(System.in);

	public void functions() throws SQLException, ParseException, IOException {
		System.out.print("Choose an appropriate option. \n "
				+ "1.Create Member \n"
				+ "2.Update Member \n"
				+ "3.Delete Member \n"
				+ "4.Issue Job Card \n"
				+ "5.Work allotment \n"
				+ "6.Exit \n");

		int option =sc_obj.nextInt();

		switch(option) {

		case 1:
			create_member();
			break;

		case 2:
			update_member();
			break;

		case 3:
			delete_member();
			break;

		case 4:
			issue_job_card();
			break;

		case 5:
			work_allot();
			break;

		case 6:
			System.out.println("Thank you");
			break;

		default:
			System.out.println("Wrong option...Try again \n");
			functions();
		}
	}

	public void create_member() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();
			sc_obj.nextLine();

			System.out.println("Enter name:");
			String name = sc_obj.nextLine();
			System.out.println("Enter email:");
			String email = sc_obj.nextLine();
			System.out.println("Enter password:");
			String password = sc_obj.nextLine();
			System.out.println("Enter area:");
			String area = sc_obj.nextLine();
			System.out.println("Enter pincode:");
			int pincode = sc_obj.nextInt();
			sc_obj.nextLine();
			System.out.println("Enter age:");
			int age = sc_obj.nextInt();
			sc_obj.nextLine();
			System.out.println("Enter GPM id");
			int id = sc_obj.nextInt();

			statement.execute("insert into Member(Name,Email,Password,Area,Pincode,Age,Gpmid)" +
					"values('" + name + "','" + email + "','" + password + "','" + area + "','" + pincode + "','" + age + "','" + id + "')");
			System.out.println("Done");
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error is:" + e.getMessage());
		} finally {
			functions();
		}
	}

	public void update_member() throws IOException, SQLException, ParseException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			Statement statement = con.createStatement();
			System.out.print("Enter Member Email_Id: ");
			String Email = bufferedReader.readLine();

			ResultSet rs = statement.executeQuery("select * from Member where Email = '" + Email + "' ");

			if (rs.next()) {
				System.out.print("1.Name: " + rs.getString(1));
				System.out.print("\n2.Password: " + rs.getString(3));
				System.out.print("\n3.Area: " + rs.getString(4));
				System.out.print("\n4.Pincode: " + rs.getInt(5));

				System.out.println("\nWhat would you want to update ?\n"
						+ "1. Name \n"
						+ "2. Password \n"
						+ "3. Area \n"
						+ "4. Pincode \n"
						+ "5. Age");

				int um = sc_obj.nextInt();

				switch (um) {

				case 1:
					System.out.println("Enter new name:");
					String name = bufferedReader.readLine();
					statement.executeUpdate("Update Member set Name ='" + name + "' where Email = '" + Email + "' ");
					System.out.println("Done");
					break;

				case 2:
					System.out.println("Enter new password:");
					String pw = bufferedReader.readLine();
					statement.executeUpdate("Update Member set Password ='" + pw + "' where Email = '" + Email + "' ");
					System.out.println("Password has changed");
					break;

				case 3:
					System.out.println("Enter to update Area");
					String area = bufferedReader.readLine();
					statement.executeUpdate("Update Member set Area ='" + area + "' where Email = '" + Email + "' ");
					System.out.println("Done");
					break;

				case 4:
					System.out.println("Enter to update Pincode");
					String pin = bufferedReader.readLine();
					statement.executeUpdate("Update Member set Pincode ='" + pin + "' where Email = '" + Email + "' ");
					System.out.println("Done");
					break;

				case 5:
					System.out.println("Enter to update Age");
					String age = bufferedReader.readLine();
					statement.executeUpdate("Update Member set Age ='" + age + "' where Email = '" + Email + "' ");
					System.out.println("Done");
					break;

				default:
					System.out.println("Wrong option");
					break;
				}
			} else
				System.out.print("Incorrect email/not present");

		} catch (SQLException e) {
			System.out.println("Error is" + e.getMessage());
		} finally {
			functions();
		}
	}

	public void delete_member() {
		try {
			Statement statement = con.createStatement();
			System.out.print("Enter Id to delete a Member: ");
			int id = sc_obj.nextInt();
			statement.executeUpdate("Delete from Member where Id = '"+id+"'");
			System.out.print("Done");

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void issue_job_card() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();
			System.out.println("Enter Member Id:");
			int mid = sc_obj.nextInt();

			ResultSet res = statement.executeQuery("select * from Member where Id='" + mid + "'");
			if (res.next()) {
				System.out.println("JOB CARD\n");
				System.out.println("1.Member Id: " + res.getInt(1));
				System.out.println("2.Name: " + res.getString(2));              
				System.out.println("3.Email_id: " + res.getString(3));
				System.out.println("4.Area: " + res.getString(5));
				System.out.println("5.Pincode: " + res.getInt(6));
				System.out.println("6.Age: " + res.getInt(7));
			}
			else
				System.out.print("Incorrect email/not present");

		} catch (SQLException e) {
			System.out.println("Error is:" + e.getMessage());
		} finally {
			functions();
		}
	}

	public void work_allot() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();	            
			System.out.println("Enter Project Id: ");
			int pid = sc_obj.nextInt();

			System.out.println("Enter Member Id: ");
			int mid = sc_obj.nextInt();

			System.out.println("Enter Gpm Id: ");
			int gpm_id = sc_obj.nextInt();

			statement.execute("insert into Project_Members(Pid,Mid,Gpm_id)" +
					"values('" + pid + "','" + mid + "','" + gpm_id + "')");

			statement.execute("update Project set Is_allocated_to='" + mid + "'");
			System.out.println("Alloted");
			statement.close();

		} catch (SQLException e) {
			System.out.println("Error is: " + e.getMessage());
		} finally {
			functions();
		}
	}

	public void gpm_login() throws SQLException {
		try {
			Statement statement = con.createStatement();
			System.out.print("Enter Email_Id: ");
			String Email = sc_obj.nextLine();
			System.out.print("Enter Password: ");
			String Password = sc_obj.nextLine();
			ResultSet rs = statement.executeQuery("select * from GPM where Email = '" + Email + "'  AND Password = '" + Password + "'");
			if (rs.next()) {
				System.out.print("Welcome...Gram Panchayat Member");
				functions();
			} else
				System.out.print("EmailId/Password is incorrect");
		} catch (Exception e) {
			System.out.print(e);
		}
	}
}

