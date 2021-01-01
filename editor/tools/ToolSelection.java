package pobj.pinboard.editor.tools;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool{
	
	private double xi;
	private double yi;
	private double x;
	private double y=0;
	private double x0=0,y0=0;
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		if (!e.isShiftDown()){
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());	
		}
		else{
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY());
		}
		if(!i.getSelection().getContents().isEmpty()){
			x0=e.getX();
			y0=e.getY();
			xi=e.getX();
			yi=e.getY();
			x=e.getX();
			y=e.getY();
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		if(!i.getSelection().getContents().isEmpty()){
			xi=x;
			yi=y;
			x=e.getX();
			y=e.getY();
			for(Clip c:i.getSelection().getContents()){
				c.move((x-xi), (y-yi));
			}
			
		}
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		//i.getSelection().clear();
		if(!i.getSelection().getContents().isEmpty()){
			Command c= new CommandMove(i, i.getSelection().getContents(), x-x0, y-y0);
			i.getUndoStack().addCommand(c);
			//c.execute();
		}
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
		
		//i.getBoard().draw(gc);
		i.getSelection().drawFeedback(gc);
		
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return null;
	}

}
