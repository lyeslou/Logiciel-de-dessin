package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command{
	
	private List<Clip> clips=new ArrayList();
	private EditorInterface editor;
	public CommandAdd(EditorInterface editor, Clip toAdd){
		clips.add(toAdd);
		this.editor=editor;
	}
	
	public CommandAdd(EditorInterface editor, List<Clip> toAdd){
		clips.addAll(toAdd);
		this.editor=editor;
	}

	@Override
	public void execute() {
		editor.getBoard().addClip(clips);
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(clips);
		
	}

}
