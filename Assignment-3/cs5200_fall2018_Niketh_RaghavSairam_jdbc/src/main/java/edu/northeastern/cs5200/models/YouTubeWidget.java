package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget {

	private String url;
	private Boolean shareable;
	private Boolean expandable;

	public YouTubeWidget() {
		super();
		
	}

	public YouTubeWidget(int id, String name, int width, int height, String css_class, String css_style, String text,
			int order) {
		super(id, name, width, height, css_class, css_style, text, order);
	}

	public YouTubeWidget(String name, int width, int height, String css_class, String css_style, String text, int order,
			String url, Boolean shareable, Boolean expandable) {
		super(name, width, height, css_class, css_style, text, order);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getShareable() {
		return shareable;
	}

	public void setShareable(Boolean shareable) {
		this.shareable = shareable;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

}
