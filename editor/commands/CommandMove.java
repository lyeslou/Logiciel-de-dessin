package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command{
	
	private List<Clip> clips=new ArrayList();
	private EditorInterface editor;
	private double dx;
	private double dy;
	public CommandMove(EditorInterface editor, Clip toAdd, double dx, double dy){
		this.dx=dx;
		this.dy=dy;
		clips.add(toAdd);
		this.editor=editor;
	}
	
	public CommandMove(EditorInterface editor, List<Clip> toAdd, double dx, double dy){
		this.dx=dx;
		this.dy=dy;
		clips.addAll(toAdd);
		this.editor=editor;
	}
	@Override
	public void execute() {
		for(Clip c:clips){
			c.move(dx, dy);
		}
	}

	@Override
	public void undo() {
		for(Clip c:clips){
			c.move(-dx, -dy);
		}
		
	}

}
