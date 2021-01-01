package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements  Tool{
	
	private double x1;
	private double y1;
	private File f;
	Clip img;
	public ToolImage(File f){
		this.f=f;
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();
		img=new ClipImage(x1,y1,f);
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x1=e.getX();
		y1=e.getY();
		img=new ClipImage(x1,y1,f);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		//Clip img=new ClipImage(x1,y1,f);
		i.getBoard().addClip(img);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
		img.draw(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return null;
	}

}
