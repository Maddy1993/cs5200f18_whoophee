package edu.northeastern.cs5200.models;

public class ImageWidget extends Widget {

	private String src;

	public ImageWidget() {
		super();
		
	}

	public ImageWidget(String name, int width, int height, String css_class, String css_style, String text, int order,
			String src) {
		super(name, width, height, css_class, css_style, text, order);
		this.src = src;
	}

	public ImageWidget(String src) {
		super();
		this.src = src;
	}

	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
