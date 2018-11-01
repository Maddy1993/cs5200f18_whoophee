package edu.northeastern.cs5200.models;

public class Widget {

	private int id;
	private String name;
	private int width;
	private int height;
	private String css_class;
	private String css_style;
	private String text;
	private int order;

	public Widget() {
		super();
		
	}

	public Widget(int id, String name, int width, int height, String css_class, String css_style, String text,
			int order) {
		super();
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_class = css_class;
		this.css_style = css_style;
		this.text = text;
		this.order = order;
	}

	public Widget(String name, String css_class, String css_style, String text, int order) {
		super();
		this.name = name;
		this.css_class = css_class;
		this.css_style = css_style;
		this.text = text;
		this.order = order;
	}

	public Widget(String name, int width, int height, String css_class, String css_style, String text, int order) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.css_class = css_class;
		this.css_style = css_style;
		this.text = text;
		this.order = order;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getCss_class() {
		return css_class;
	}

	public void setCss_class(String css_class) {
		this.css_class = css_class;
	}

	public String getCss_style() {
		return css_style;
	}

	public void setCss_style(String css_style) {
		this.css_style = css_style;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}