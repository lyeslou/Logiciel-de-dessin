package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool {
	
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		
		x1 = e.getX();
		y1 = e.getY();
		x2=e.getX();
		y2=e.getY();

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		
		x2=e.getX();
		y2=e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		if(x1!=x2 || y1!=y2){
			Command c=new CommandAdd(i,(new ClipEllipse(Math.min(x1,x2),Math.min(y1, y2),Math.max(x1, x2),Math.max(y1, y2),Color.BLUE)));
			c.execute();
			i.getUndoStack().addCommand(c);
		}
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setStroke(Color.RED);
		double xi=Math.min(x1, x2);
		double yi=Math.min(y1, y2);
		double xf=Math.max(x1,x2 );
		double yf=Math.max(y1, y2);
		gc.strokeOval(xi, yi, xf-xi, yf-yi);
		
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return "Ellipse";
	}

}
