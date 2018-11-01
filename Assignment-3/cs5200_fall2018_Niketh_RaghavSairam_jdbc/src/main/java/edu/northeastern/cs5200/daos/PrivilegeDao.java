package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrivilegeDao implements PrivilegeImpl {
	private static PrivilegeDao instance = null;
	private java.sql.Connection conn;

	private PrivilegeDao() {
		try {
			this.conn = edu.northeastern.cs5200.Connection.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static PrivilegeDao getInstance() {
		if (instance == null) {
			instance = new PrivilegeDao();
		}
		return instance;
	}

	private final String INSERT_WEBSITE_PRIVILEGE = "INSERT INTO website_privileges (developer_id,website_id,privilege) VALUE (?,?,?)";
	private final String INSERT_PAGE_PRIVILEGE = "INSERT INTO page_privileges (developer_id,page_id,privilege) VALUE (?,?,?)";

	private final String DELETE_WEBSITE_PRIVILEGE = "DELETE FROM website_privileges WHERE developer_id=? AND website_id=? AND privilege=?";
	private final String DELETE_PAGE_PRIVILEGE = "DELETE FROM page_privileges WHERE developer_id=? AND page_id=? AND privilege=?";;

	public int assignWebsitePrivilege(int developerId, int websiteId, String privilege) {
		PreparedStatement pstatement = null;
		int result = 0;
		try {
			pstatement = conn.prepareStatement(INSERT_WEBSITE_PRIVILEGE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, websiteId);
			pstatement.setString(3, privilege);

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int assignPagePrivilege(int developerId, int pageId, String privilege) {
		PreparedStatement pstatement = null;
		int result = 0;
		try {
			pstatement = conn.prepareStatement(INSERT_PAGE_PRIVILEGE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, pageId);
			pstatement.setString(3, privilege);

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deleteWebsitePrivilege(int developerId, int websiteId, String privilege) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_WEBSITE_PRIVILEGE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, websiteId);
			pstatement.setString(3, privilege);

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deletePagePrivilege(int developerId, int pageId, String privilege) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_PAGE_PRIVILEGE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, pageId);
			pstatement.setString(3, privilege);

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}
}
