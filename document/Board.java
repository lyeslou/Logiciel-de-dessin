package pobj.pinboard.document;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	
	private List<Clip>contents ;
	
	public Board() {
		contents=new ArrayList<Clip>();
	}
	
	public List<Clip> getContents(){
		return contents;
	}
	
	public void addClip(Clip clip) {
		contents.add(clip);
	}
	
	public void addClip(List<Clip> clip) {
		for(Clip c:clip) {
			contents.add(c);
		}
	}
	
	public void removeClip(Clip clip) {
		contents.remove(clip);
	}
	
	public void removeClip(List<Clip> clip) {
		for(Clip c:clip) {
			contents.remove(c);
		}
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());
		for(Clip c:contents) {
			c.draw(gc);
		}
	}
	
	public void clear(){
		contents.clear();
	}
	
	
}