package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip{
	
	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		setGeometry(left, top, right, bottom);
		setColor(color);
	}
	
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.setFill(getColor());
		ctx.fillOval(getLeft(), getTop(), getRight()-getLeft(), getBottom()-getTop());
		
	}
	
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		
		double cx=(getLeft()+getRight())/2;
		double cy=(getTop()+getBottom())/2;
		double rx=(getRight()-getLeft())/2;
		double ry=(getBottom()-getTop())/2;
		return ((Math.pow((x-cx)/rx,2)+(Math.pow((y-cy)/ry,2))))<=1;
	}
	
	public Clip copy() {
		return new ClipEllipse(getLeft(),getTop(),getRight(),getBottom(),getColor());
	}

}
