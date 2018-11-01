package edu.northeastern.cs5200.daos;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetDao implements WidgetImpl {
	private static WidgetDao instance = null;
	private java.sql.Connection conn;

	private WidgetDao() {
		try {
			this.conn = edu.northeastern.cs5200.Connection.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static WidgetDao getInstance() {
		if (instance == null) {
			instance = new WidgetDao();
		}
		return instance;
	}

	private ResultSet results = null;

	private final String INSERT_WIDGET = "INSERT INTO widgets (id,name,width,height,css_class,css_style,text,`order`,page_id)  VALUE (?,?,?,?,?,?,?,?,?)";
	private final String INSERT_WIDGET_HEADING = "INSERT INTO widgets (name,width,height,css_class,css_style,text,`order`,heading_size, dtype,page_id) VALUE (?,?,?,?,?,?,?,?,?,?)";
	private final String INSERT_WIDGET_HTML = "INSERT INTO widgets (name,width,height,css_class,css_style,text,`order`,html,dtype,page_id) VALUE (?,?,?,?,?,?,?,?,?,?)";
	private final String INSERT_WIDGET_IMAGE = "INSERT INTO widgets (name,width,height,css_class,css_style,text,`order`,src,dtype,page_id) VALUE (?,?,?,?,?,?,?,?,?,?)";
	private final String INSERT_WIDGET_YOUTUBE = "INSERT INTO widgets (name,width,height,css_class,css_style,text,`order`,url_youtube,`shareable_youtube`,`expandable_youtube`,dtype,page_id) VALUE (?,?,?,?,?,?,?,?,?,?,?,?)";

	private final String FIND_ALL_WIDGETS = "SELECT * FROM widgets";

	private final String FIND_WIDGET_BYID = "SELECT * FROM widgets WHERE id=?";

	private final String FIND_WIDGETS_FORPAGE = "SELECT * FROM widgets WHERE page_id=?";

	private final String UPDATE_WIDGET = "UPDATE widgets set name=?,width=?,height=?,css_class=?,css_style=?,text=?,`order`=? WHERE id=?";
	private final String UPDATE_WIDGET_HEADING = "UPDATE widgets set name=?,width=?,height=?,css_class=?,css_style=?,text=?,`order`=?,heading_size=?,dtype=? WHERE id=?";
	private final String UPDATE_WIDGET_HTML = "UPDATE widgets set name=?,width=?,height=?,css_class=?,css_style=?,text=?,`order`=?,html=?,dtype=? WHERE id=?";
	private final String UPDATE_WIDGET_IMAGE = "UPDATE widgets set name=?,width=?,height=?,css_class=?,css_style=?,text=?,`order`=?,src=?,dtype=? WHERE id=? ";
	private final String UPDATE_WIDGET_YOUTUBE = "UPDATE widgets set name=?,width=?,height=?,css_class=?,css_style=?,text=?,`order`=?,url_youtube=?,`shareable_youtube`=?,`expandable_youtube`=?,dtype=? WHERE id=? ";

	private final String DELETE_WIDGET = "DELETE FROM widgets WHERE id=?";

	private void setWidgetDims(PreparedStatement pstatement, Widget widget) throws SQLException {
		pstatement.setString(1, widget.getName());
		if (widget.getWidth() == 0)
			pstatement.setNull(2, java.sql.Types.INTEGER);
		else
			pstatement.setObject(2, widget.getWidth(), JDBCType.INTEGER);
		if (widget.getHeight() == 0)
			pstatement.setNull(3, java.sql.Types.INTEGER);
		else
			pstatement.setObject(3, widget.getHeight(), JDBCType.INTEGER);
		pstatement.setString(4, widget.getCss_class());
		pstatement.setString(5, widget.getCss_style());
		pstatement.setString(6, widget.getText());
		pstatement.setInt(7, widget.getOrder());
	}

	public void createWidgetForPage(int pageId, Widget widget) {
		int DEFAULT_HEADING = 2;
		PreparedStatement pstatement = null;
		try {
			@SuppressWarnings("unused")
			int result = 0;
			if (widget instanceof HeadingWidget) {
				pstatement = conn.prepareStatement(INSERT_WIDGET_HEADING);
				setWidgetDims(pstatement, widget);
				pstatement.setInt(8, ((HeadingWidget) widget).getSize());

				if (((HeadingWidget) widget).getSize() == 0)
					pstatement.setInt(8, DEFAULT_HEADING);
				else
					pstatement.setObject(8, ((HeadingWidget) widget).getSize(), JDBCType.INTEGER);

				pstatement.setString(9, "heading");
				pstatement.setInt(10, pageId);
				result = pstatement.executeUpdate();

			} else if (widget instanceof HtmlWidget) {
				pstatement = conn.prepareStatement(INSERT_WIDGET_HTML);
				setWidgetDims(pstatement, widget);
				pstatement.setString(8, ((HtmlWidget) widget).getHtml());
				pstatement.setString(9, "html");
				pstatement.setInt(10, pageId);
				result = pstatement.executeUpdate();
			} else if (widget instanceof ImageWidget) {
				pstatement = conn.prepareStatement(INSERT_WIDGET_IMAGE);
				setWidgetDims(pstatement, widget);
				pstatement.setString(8, ((ImageWidget) widget).getSrc());
				pstatement.setString(9, "image");
				pstatement.setInt(10, pageId);
				result = pstatement.executeUpdate();
			} else if (widget instanceof YouTubeWidget) {
				pstatement = conn.prepareStatement(INSERT_WIDGET_YOUTUBE);
				setWidgetDims(pstatement, widget);
				pstatement.setString(8, ((YouTubeWidget) widget).getUrl());
				pstatement.setBoolean(9, ((YouTubeWidget) widget).getShareable());
				pstatement.setBoolean(10, ((YouTubeWidget) widget).getExpandable());
				pstatement.setString(11, "youtube");
				pstatement.setInt(12, pageId);
				result = pstatement.executeUpdate();
			} else {
				pstatement = conn.prepareStatement(INSERT_WIDGET);
				setWidgetDims(pstatement, widget);
				pstatement.setInt(9, pageId);
				result = pstatement.executeUpdate();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Collection<Widget> findAllWidgets() {
		Statement statement = null;
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
			statement = conn.createStatement();

			results = statement.executeQuery(FIND_ALL_WIDGETS);
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String css_class = results.getString("css_class");
				String css_style = results.getString("css_style");
				String text = results.getString("text");
				int order = results.getInt("order");

				Widget widget = new Widget(id, name, width, height, css_class, css_style, text, order);
				widgets.add(widget);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return widgets;
	}

	public Widget findWidgetById(int widgetId) {
		Widget widget = new Widget();
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(FIND_WIDGET_BYID);
			pstatement.setInt(1, widgetId);

			results = pstatement.executeQuery();

			if (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String css_class = results.getString("css_class");
				String css_style = results.getString("css_style");
				String text = results.getString("text");
				int order = results.getInt("order");

				widget.setId(id);
				widget.setName(name);
				widget.setWidth(width);
				widget.setHeight(height);
				widget.setCss_class(css_class);
				widget.setCss_style(css_style);
				widget.setText(text);
				widget.setOrder(order);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return widget;
	}

	public Collection<Widget> findWidgetsForPage(int pageId) {
		PreparedStatement pstatement = null;
		Collection<Widget> widgets = new ArrayList<Widget>();
		try {
			pstatement = conn.prepareStatement(FIND_WIDGETS_FORPAGE);
			pstatement.setInt(1, pageId);

			results = pstatement.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String css_class = results.getString("css_class");
				String css_style = results.getString("css_style");
				String text = results.getString("text");
				int order = results.getInt("order");

				Widget widget = new Widget(id, name, width, height, css_class, css_style, text, order);
				widgets.add(widget);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return widgets;
	}

	public int updateWidget(int widgetId, Widget widget) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			if (widget instanceof HeadingWidget) {
				pstatement = conn.prepareStatement(UPDATE_WIDGET_HEADING);
				setWidgetDims(pstatement, widget);
				pstatement.setObject(8, ((HeadingWidget) widget).getSize(), JDBCType.INTEGER);
				pstatement.setString(9, "heading");
				pstatement.setInt(10, widgetId);
				result = pstatement.executeUpdate();

			} else if (widget instanceof HtmlWidget) {
				pstatement = conn.prepareStatement(UPDATE_WIDGET_HTML);
				setWidgetDims(pstatement, widget);
				pstatement.setString(8, ((HtmlWidget) widget).getHtml());
				pstatement.setString(9, "html");
				pstatement.setInt(10, widgetId);
				result = pstatement.executeUpdate();
			} else if (widget instanceof ImageWidget) {
				pstatement = conn.prepareStatement(UPDATE_WIDGET_IMAGE);
				setWidgetDims(pstatement, widget);
				pstatement.setString(8, ((ImageWidget) widget).getSrc());
				pstatement.setString(9, "image");
				pstatement.setInt(10, widgetId);
				result = pstatement.executeUpdate();
			} else if (widget instanceof YouTubeWidget) {
				pstatement = conn.prepareStatement(UPDATE_WIDGET_YOUTUBE);
				setWidgetDims(pstatement, widget);
				pstatement.setString(8, ((YouTubeWidget) widget).getUrl());
				pstatement.setObject(9, ((YouTubeWidget) widget).getShareable(), JDBCType.BOOLEAN);
				pstatement.setObject(10, ((YouTubeWidget) widget).getExpandable(), JDBCType.BOOLEAN);
				pstatement.setString(11, "youtube");
				pstatement.setInt(12, widgetId);
				result = pstatement.executeUpdate();
			} else {
				pstatement = conn.prepareStatement(UPDATE_WIDGET);
				pstatement.setString(1, widget.getName());
				setWidgetDims(pstatement, widget);
				pstatement.setInt(8, widgetId);
				result = pstatement.executeUpdate();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deleteWidget(int widgetId) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_WIDGET);
			pstatement.setInt(1, widgetId);
			result = pstatement.executeUpdate();
			pstatement = null;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}
}
