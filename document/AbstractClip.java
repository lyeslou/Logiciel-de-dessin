package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip {
	
	private double left,top,right,bottom;
	private Color color;
	
	
	public double getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	
	public double getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	
	public double getBottom() {
		// TODO Auto-generated method stub
		return bottom;
	}

	
	public double getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
	}

	
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		left+=x;
		right+=x;
		top+=y;
		bottom+=y;
	}
	
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		color=c;
	}

	
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		
		return (x<=right && x>=left && y>=top && y<=bottom);
	}
}
