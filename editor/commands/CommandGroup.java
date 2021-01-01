package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command{
	
	private List<Clip> clips=new ArrayList();
	private EditorInterface editor;
	private ClipGroup clipGroup;
	
	public CommandGroup(EditorInterface editor, Clip toAdd){
		clips.add(toAdd);
		this.editor=editor;
	}
	
	public CommandGroup(EditorInterface editor, List<Clip> toAdd){
		clips.addAll(toAdd);
		this.editor=editor;
	}
	@Override
	public void execute() {
		//if (editor.getBoard().getContents().contains(clips)){
			editor.getBoard().removeClip(clips);
			clipGroup=new ClipGroup();
			clipGroup.addClip(clips);
			editor.getBoard().addClip(clipGroup);
		//}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if(clipGroup!=null){
			editor.getBoard().removeClip(clipGroup);
			editor.getBoard().addClip(clips);
		}
		
	}

}
