package edu.northeastern.cs5200.models;

public class HtmlWidget extends Widget {

	private String html;

	public HtmlWidget() {
		super();
		
	}

	public HtmlWidget(String name, String css_class, String css_style, String text, int order, String html) {
		super(name, css_class, css_style, text, order);
		this.html = html;
	}

	public HtmlWidget(String name, String css_class, String css_style, String text, int order) {
		super(name, css_class, css_style, text, order);
	}

	public HtmlWidget(String name, int width, int height, String css_class, String css_style, String text, int order) {
		super(name, width, height, css_class, css_style, text, order);
	}

	public HtmlWidget(String html) {
		super();
		this.html = html;
	}
	

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
