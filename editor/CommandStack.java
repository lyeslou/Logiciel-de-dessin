package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	
	private List<Command> undo=new ArrayList();
	private List<Command> redo=new ArrayList();
	
	public void addCommand(Command c){
		undo.add(0, c);
		redo.clear();
	}
	
	public void undo(){
		if(!undo.isEmpty()){
			Command c=undo.get(0);
			c.undo();
			undo.remove(0);
			redo.add(0,c);
		}
	}
	
	public void redo(){
		if(!redo.isEmpty()){
			Command c=redo.get(0);
			c.execute();
			redo.remove(0);
			undo.add(0,c);
		}
	}
	
	public boolean isUndoEmpty(){
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty(){
		return redo.isEmpty();
	}
}
