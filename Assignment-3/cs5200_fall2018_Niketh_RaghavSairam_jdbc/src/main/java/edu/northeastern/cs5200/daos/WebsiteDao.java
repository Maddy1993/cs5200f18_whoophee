package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.models.Website;
import java.sql.Date;
import java.sql.Statement;

public class WebsiteDao implements WebsiteImpl {
	private static WebsiteDao instance = null;
	private java.sql.Connection conn;

	private WebsiteDao() {
		try {
			this.conn = edu.northeastern.cs5200.Connection.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static WebsiteDao getInstance() {
		if (instance == null) {
			instance = new WebsiteDao();
		}
		return instance;
	}

	private ResultSet results = null;
	private final String INSERT_WEBSITE = "INSERT INTO website (id,name,description,created,updated,visits,developer_id) VALUE (?,?,?,?,?,?,?)";
	private final String FIND_ALL_WEBSITES = "SELECT * FROM website";
	private final String FIND_WEBSITES_BYDEVELOPER = "SELECT * FROM website WHERE developer_id=?";
	private final String FIND_WEBSITE_BYID = "SELECT * FROM website WHERE id=?";
	private final String UPDATE_WEBSITE = "UPDATE website set id=?,name=?,description=?,created=?,updated=?,visits=? WHERE id=?";
	private final String DELETE_WEBSITE = "DELETE FROM website WHERE id=?";

	public void createWebsiteForDeveloper(int developerId, Website website) {
		PreparedStatement pstatement = null;
		@SuppressWarnings("unused")
		int result = 0;
		try {

			pstatement = conn.prepareStatement(INSERT_WEBSITE);

			pstatement.setInt(1, website.getId());
			pstatement.setString(2, website.getName());
			pstatement.setString(3, website.getDescription());
			pstatement.setDate(4, website.getCreated());
			pstatement.setDate(5, website.getUpdated());
			pstatement.setInt(6, website.getVisits());
			pstatement.setInt(7, developerId);
			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Collection<Website> findAllWebsites() {
		Statement statement = null;
		Collection<Website> websites = new ArrayList<Website>();
		try {
			statement = conn.createStatement();

			results = statement.executeQuery(FIND_ALL_WEBSITES);
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");

				Website website = new Website(id, name, description, created, updated, visits);

				websites.add(website);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return websites;
	}

	public Collection<Website> findWebsitesForDeveloper(int developerId) {
		PreparedStatement pstatement = null;
		Collection<Website> websites = new ArrayList<Website>();
		try {
			pstatement = conn.prepareStatement(FIND_WEBSITES_BYDEVELOPER);
			pstatement.setInt(1, developerId);
			results = pstatement.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				Website website = new Website(id, name, description, created, updated, visits);

				websites.add(website);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return websites;
	}

	public Website findWebsiteById(int websiteId) {
		Website website = new Website();
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(FIND_WEBSITE_BYID);
			pstatement.setInt(1, websiteId);

			results = pstatement.executeQuery();

			if (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");

				website.setId(id);
				website.setName(name);
				website.setDescription(description);
				website.setCreated(created);
				website.setUpdated(updated);
				website.setVisits(visits);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return website;
	}

	public int updateWebsite(int websiteId, Website website) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(UPDATE_WEBSITE);
			pstatement.setInt(1, website.getId());
			pstatement.setString(2, website.getName());
			pstatement.setString(3, website.getDescription());
			pstatement.setDate(4, website.getCreated());
			pstatement.setDate(5, website.getUpdated());
			pstatement.setInt(6, website.getVisits());
			pstatement.setInt(7, websiteId);

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deleteWebsite(int websiteId) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_WEBSITE);
			pstatement.setInt(1, websiteId);
			result = pstatement.executeUpdate();
			pstatement = null;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}
}
