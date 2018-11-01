package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

import edu.northeastern.cs5200.models.Page;

public class PageDao implements PageImpl {
	private static PageDao instance = null;
	private java.sql.Connection conn;

	private PageDao() {
		try {
			this.conn = edu.northeastern.cs5200.Connection.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static PageDao getInstance() {
		if (instance == null) {
			instance = new PageDao();
		}
		return instance;
	}

	private Statement statement = null;
	private ResultSet results = null;

	private final String INSERT_PAGE = "INSERT INTO page (id,title,description,created,updated,views,website_id) VALUE (?,?,?,?,?,?,?)";

	private final String FIND_ALL_PAGES = "SELECT * FROM page";
	private final String FIND_PAGE_BYID = "SELECT * FROM page WHERE id=?";
	private final String FIND_PAGES_FORWEBSITE = "SELECT * FROM page WHERE website_id=?";

	private final String UPDATE_PAGE = "UPDATE page set id=?,title=?,description=?,created=?,updated=?,views=? WHERE id=?";

	private final String DELETE_PAGE = "DELETE FROM page WHERE id=?";

	public void createPageForWebsite(int websiteId, Page page) {
		PreparedStatement pstatement = null;
		@SuppressWarnings("unused")
		int result = 0;
		try {
			pstatement = conn.prepareStatement(INSERT_PAGE);

			pstatement.setInt(1, page.getId());
			pstatement.setString(2, page.getTitle());
			pstatement.setString(3, page.getDescription());
			pstatement.setDate(4, page.getCreated());
			pstatement.setDate(5, page.getUpdated());
			pstatement.setInt(6, page.getViews());
			pstatement.setInt(7, websiteId);
			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Collection<Page> findAllPages() {
		Collection<Page> pages = new ArrayList<Page>();
		try {
			statement = conn.createStatement();

			results = statement.executeQuery(FIND_ALL_PAGES);
			while (results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");

				Page page = new Page(id, title, description, created, updated, views);
				pages.add(page);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return pages;
	}

	public Page findPageById(int pageId) {
		Page page = new Page();
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(FIND_PAGE_BYID);
			pstatement.setInt(1, pageId);

			results = pstatement.executeQuery();

			if (results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");

				page.setId(id);
				page.setTitle(title);
				page.setDescription(description);
				page.setCreated(created);
				page.setUpdated(updated);
				page.setViews(views);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return page;
	}

	public Collection<Page> findPagesForWebsite(int websiteId) {
		PreparedStatement pstatement = null;
		Collection<Page> pages = new ArrayList<Page>();
		try {
			pstatement = conn.prepareStatement(FIND_PAGES_FORWEBSITE);
			pstatement.setInt(1, websiteId);
			results = pstatement.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				Page page = new Page(id, title, description, created, updated, views);

				pages.add(page);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return pages;
	}

	public int updatePage(int pageId, Page page) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(UPDATE_PAGE);

			pstatement.setInt(1, page.getId());
			pstatement.setString(2, page.getTitle());
			pstatement.setString(3, page.getDescription());
			pstatement.setDate(4, page.getCreated());
			pstatement.setDate(5, page.getUpdated());
			pstatement.setInt(6, page.getViews());
			pstatement.setInt(7, pageId);

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deletePage(int pageId) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_PAGE);
			pstatement.setInt(1, pageId);
			result = pstatement.executeUpdate();
			pstatement = null;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}
}
