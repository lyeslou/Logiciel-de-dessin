package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	private static Clipboard clipBoard=new Clipboard();
	private List<ClipboardListener> cibles=new ArrayList<>();
	private List<Clip> clips=new ArrayList<Clip>();
	private Clipboard(){
		
	}
	public static Clipboard getInstance(){
		return clipBoard;
	}
	public void copyToClipboard(List<Clip> clips){
		for(Clip c:clips){
			this.clips.add(c.copy());
		}
		notifier();
	}
	public List<Clip> copyFromClipboard(){
		List<Clip> copie=new ArrayList<>();
		for(Clip c:clips){
			copie.add(c.copy());
		}
		return copie;
	}
	public boolean isEmpty(){
		return clips.isEmpty();
	}
	public void clear(){
		clips.clear();
		notifier();
	}
	
	public void addListener(ClipboardListener listener){
		cibles.add(listener);
	}
	
	public void removeListener(ClipboardListener listener){
		cibles.remove(listener);
	}
	
	public void notifier(){
		for(ClipboardListener c:cibles){
			c.clipboardChanged();
		}
	}

}
