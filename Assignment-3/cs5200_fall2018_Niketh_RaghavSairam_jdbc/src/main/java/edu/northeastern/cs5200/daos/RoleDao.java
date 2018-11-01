package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import edu.northeastern.cs5200.models.Role;

public class RoleDao implements RoleImpl {
	private HashMap<Integer, String> rolesDesigned = new HashMap<Integer, String>();
	private java.sql.Connection conn;
	private static RoleDao instance = null;

	private RoleDao() {
		try {
			this.conn = edu.northeastern.cs5200.Connection.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}

	public void setRolesDesigned(Collection<Role> roles) {
		for (Role role : roles)
			this.rolesDesigned.put(role.getId(), role.getRole());
	}

	public HashMap<Integer, String> getRolesDesigned() {
		return rolesDesigned;
	}

	private final String INSERT_WEBSITEROLE = "INSERT INTO website_roles (developer_id,website_id,role) VALUE (?,?,?)";
	private final String INSERT_PAGEROLE = "INSERT INTO page_roles (developer_id,page_id,role) VALUE (?,?,?)";

	private final String FIND_PAGEROLE_BYID = "SELECT role FROM page_roles WHERE developer_id=? AND page_id=?";

	private final String DELETE_WEBSITEROLE = "DELETE FROM website_roles WHERE developer_id=? AND website_id=? AND role=?";
	private final String DELETE_PAGEROLE = "DELETE FROM page_roles WHERE developer_id=? AND page_id=? AND role=?";;

	public int assignWebsiteRole(int developerId, int websiteId, int roleId) {
		PreparedStatement pstatement = null;
		int result = 0;
		try {
			pstatement = conn.prepareStatement(INSERT_WEBSITEROLE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, websiteId);
			pstatement.setString(3, rolesDesigned.get(roleId));

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int assignPageRole(int developerId, int pageId, int roleId) {
		PreparedStatement pstatement = null;
		int result = 0;
		try {
			pstatement = conn.prepareStatement(INSERT_PAGEROLE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, pageId);
			pstatement.setString(3, rolesDesigned.get(roleId));
			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public Role findPageroleById(int developerId, int pageId) {
		ResultSet results = null;
		Role role = new Role();
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(FIND_PAGEROLE_BYID);
			pstatement.setInt(1, developerId);
			pstatement.setInt(2, pageId);

			results = pstatement.executeQuery();

			if (results.next()) {
				String role_name = results.getString("role");
				role.setRole(role_name);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return role;
	}

	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_WEBSITEROLE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, websiteId);
			pstatement.setString(3, rolesDesigned.get(roleId));

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deletePageRole(int developerId, int pageId, int roleId) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_PAGEROLE);

			pstatement.setInt(1, developerId);
			pstatement.setInt(2, pageId);
			pstatement.setString(3, rolesDesigned.get(roleId));

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}
}
