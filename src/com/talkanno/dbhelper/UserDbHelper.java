/*package com.talkanno.dbhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talkanno.pojos.UserPojo;

public class UserDbHelper {

	public static int createUser(Connection con, String name, String pass, String email) {
		boolean isSuccess = false;
		int rs = 0;
		try {
			PreparedStatement pre = con
					.prepareStatement("insert into ANNO_USER set AU_NAME = ?,AU_PASS = ?, AU_EMAIL =? ");
			pre.setString(1, name);
			pre.setString(2, pass);
			pre.setString(3, email);

			rs = pre.executeUpdate();
			System.out.println("rs" + rs);
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public static boolean isUserExist(Connection con, String name) {
		boolean isSuccess = false;
		boolean rs = false;
		try {
			PreparedStatement pre = con.prepareStatement("select  AU_SERIAL from ANNO_USER where AU_NAME = ?");
			pre.setString(1, name);

			ResultSet rsk = pre.executeQuery();
			if (rsk.next()) {
				rs = true;
			}
			System.out.println("rs" + rs);
			pre.close();
			rsk.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public static int getUserSerial(Connection con, String name) {
		int serial = 0;
		try {
			PreparedStatement pre = con.prepareStatement("select  AU_SERIAL from ANNO_USER where AU_NAME = ?");
			pre.setString(1, name);

			ResultSet rsk = pre.executeQuery();
			if (rsk.next()) {
				serial = rsk.getInt(1);
			}
			System.out.println("serial" + serial);
			pre.close();
			rsk.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serial;
	}

	public static ResultSet getUsers(Connection con, String name) {

		if (name == null || name.equals("") || name.contains("%") || name.contains("_")) {
			return null;
		}
		ResultSet rsk = null;
		try {
			PreparedStatement pre = con
					.prepareStatement("select  AU_SERIAL,AU_NAME from ANNO_USER where AU_NAME LIKE ?");
			pre.setString(1, name.concat("%"));
			rsk = pre.executeQuery();
			System.out.println("rs" + rsk);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rsk;
	}

	public static UserPojo getUserInfo(Connection con, int serial) {
		UserPojo up = new UserPojo();
		if (serial == 0) {
			return null;
		}
		try {
			PreparedStatement pre = con
					.prepareStatement("select  AU_SERIAL,AU_NAME,AU_EMAIL from ANNO_USER where AU_SERIAL = ?");
			ResultSet rsk = null;
			pre.setInt(1, serial);
			rsk = pre.executeQuery();
			System.out.println("rs" + rsk);
			if (rsk.next()) {
				up.setSerial(rsk.getInt(1));
				up.setUserName(rsk.getString(2));
				up.setEmail(rsk.getString(3));

			}
			pre.close();
			rsk.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}

	public static void main(String[] args) {
		createUser(DbConnectionManager.getConnection(), "a", "b", "c");
	}

	public static boolean isCorrectPassword(Connection con, String uname, String password) {
		boolean isSuccess = false;
		boolean rs = false;
		try {
			PreparedStatement pre = con.prepareStatement(
					"select  AU_SERIAL from ANNO_USER where AU_NAME = ? AND AU_PASS=? COLLATE  Latin1_General_CS");
			pre.setString(1, uname);
			pre.setString(2, password);

			ResultSet rsk = pre.executeQuery();
			if (rsk.next()) {
				rs = true;
			}
			System.out.println("rs" + rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public static int createMessageEntry(Connection con, int serial, String msg) {
		int rs = 0;
		try {
			PreparedStatement pre = con.prepareStatement("insert into ANNO_MSG set AM_USER_SERIAL = ?,AM_MSG = ?");
			pre.setInt(1, serial);
			pre.setString(2, msg);

			rs = pre.executeUpdate();
			System.out.println("rs" + rs);
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public static List<String> getMessages(Connection con, String username) {
		List<String> msg = new ArrayList<String>();
		try {

			int serial = getUserSerial(con, username);

			PreparedStatement pre = con.prepareStatement("select  AM_MSG from ANNO_MSG where AM_USER_SERIAL = ?");
			pre.setInt(1, serial);

			ResultSet rsk = pre.executeQuery();
			while (rsk.next()) {
				msg.add(rsk.getString(1));
			}
			System.out.println("msg" + msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

}
*/