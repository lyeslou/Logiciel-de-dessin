package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite{
	
	
	
	private List<Clip> groupe;
	
	
	public ClipGroup(){
		groupe=new ArrayList<Clip>();
		super.setGeometry(Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
	}
	
	private ClipGroup(List<Clip> g){
		groupe=g;
		
	}
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		for(Clip c:groupe){
			c.draw(ctx);
		}
		ctx.setStroke(Color.GREEN);
		ctx.strokeRect(getLeft(), getTop(), getRight()-getLeft(), getBottom()-getTop());
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		List<Clip> g=new ArrayList<Clip>();
		for(Clip c:groupe){
			g.add(c.copy());
		}
		return new ClipGroup(g);
		
	}

	@Override
	public List<Clip> getClips() {
		// TODO Auto-generated method stub
		
		return groupe;
	}

	@Override
	public void addClip(Clip toAdd) {
		// TODO Auto-generated method stub
		double left=0;
		double right=0;
		double top=0;
		double bottom=0;
		groupe.add(toAdd);
		if (getLeft()>toAdd.getLeft()){
			left=toAdd.getLeft();
		}
		else{
			left=getLeft();
		}
		if (getTop()>toAdd.getTop()){
			top=toAdd.getTop();
		}
		else{
			top=getTop();
		}
		if (getRight()<toAdd.getRight()){
			right=toAdd.getRight();
		}
		else{
			right=getRight();
		}
		if (getBottom()<toAdd.getBottom()){
			bottom=toAdd.getBottom();
		}
		else{
			bottom=getBottom();
		}
		super.setGeometry(left, top, right, bottom);
		
	
	}

	@Override
	public void removeClip(Clip toRemove) {
		// TODO Auto-generated method stub
		groupe.remove(toRemove);
		double left=0;
		double right=0;
		double top=0;
		double bottom=0;
		super.setGeometry(Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
		for(Clip c:groupe){
			if (getLeft()>c.getLeft()){
				left=c.getLeft();
			}
			else{
				left=getLeft();
			}
			if (getTop()>c.getTop()){
				top=c.getTop();
			}
			else{
				top=getTop();
			}
			if (getRight()<c.getRight()){
				right=c.getRight();
			}
			else{
				right=getRight();
			}
			if (getBottom()<c.getBottom()){
				bottom=c.getBottom();
			}
			else{
				bottom=getBottom();
			}
			
		}
		super.setGeometry(left, top, right, bottom);
	}
	@Override
	public void move(double x, double y){
		for(Clip c:groupe){
			c.move(x, y);
		}
		super.move(x, y);
	}
	
	@Override
	public void setGeometry(double left, double top, double right, double bottom){
		move(right-left,bottom-top);
	}
	
	public void addClip(List<Clip> g){
		for(Clip c:g){
			addClip(c);
		}
	}
	
	public void clear(){
		groupe.clear();
	}
}
