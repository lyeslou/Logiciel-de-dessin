package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command{
	
	private ClipGroup clip;
	private EditorInterface editor;
	private ClipGroup clipGroup;
	
	public CommandUngroup(EditorInterface editor, ClipGroup toAdd){
		clip=toAdd;
		this.editor=editor;
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(clip);
		editor.getBoard().addClip(clip.getClips());
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(clip.getClips());
		editor.getBoard().addClip(clip);
	}
	

}
