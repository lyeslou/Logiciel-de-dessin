package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private double left=Double.MAX_VALUE;
	private double top=Double.MAX_VALUE;
	private double right=Double.MIN_VALUE;
	private double bottom=Double.MIN_VALUE;
	private List<Clip> select=new ArrayList<Clip>();
	
	public void select(Board board, double x, double y){
		clear();
		for(Clip c:board.getContents()){
			if(c.isSelected(x, y)){
				select.add(c);
				break;
			}
		}
		
	}
	
	public void toogleSelect(Board board, double x, double y){
		for(Clip c:board.getContents()){
			if(c.isSelected(x, y)){
				if(select.contains(c)){
					select.remove(c);
				}
				else{
					select.add(c);
				}
			}
		}
	}
	public void clear(){
		select.clear();
	}
	
	public List<Clip> getContents(){
		return select;
	}
	
	public void drawFeedback(GraphicsContext gc){
		gc.setStroke(Color.BLACK);
		if (!select.isEmpty()){
		for(Clip c:select){
			
			if (left>c.getLeft()){
				left=c.getLeft();
			}
			if (top>c.getTop()){
				top=c.getTop();
			}
			if (right<c.getRight()){
				right=c.getRight();
			}
			if (bottom<c.getBottom()){
				bottom=c.getBottom();
			}
		}
		
		gc.strokeRect(left-5, top-5, right-left+10, bottom-top+10);
		}
		left=Double.MAX_VALUE;
		top=Double.MAX_VALUE;
		right=Double.MIN_VALUE;
		bottom=Double.MIN_VALUE;
	}

}
