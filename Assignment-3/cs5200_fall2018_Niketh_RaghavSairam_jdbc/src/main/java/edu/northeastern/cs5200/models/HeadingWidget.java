package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget {

	private int size;

	public HeadingWidget() {
		super();
		
	}

	public HeadingWidget(String name, String css_class, String css_style, String text, int order, int size) {
		super(name, css_class, css_style, text, order);
		this.size = size;
	}

	public HeadingWidget(String name, String css_class, String css_style, String text, int order) {
		super(name, css_class, css_style, text, order);
	}

	public HeadingWidget(String name, int width, int height, String css_class, String css_style, String text,
			int order) {
		super(name, width, height, css_class, css_style, text, order);
	}

	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
