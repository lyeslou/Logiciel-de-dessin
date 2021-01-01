package pobj.pinboard.editor;



import java.io.File;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface,ClipboardListener{
	
	private Board board;
	private Tool tool=new ToolRect();
	private Canvas canvas;
	private File f;
	private Selection select=new Selection();
	private MenuItem paste;
	private CommandStack stack=new CommandStack();
	//private ClipGroup clipGroup=new ClipGroup();
	public EditorWindow(Stage stage ){
		board=new Board();
		Clipboard.getInstance().addListener(this);
		stage.setTitle("dessin");
		//menu file
		Menu file=new Menu("File");
		//new
		MenuItem New=new MenuItem("new");
		New.setOnAction((e)->{new EditorWindow(new Stage());});
		//close
		MenuItem Close=new MenuItem("close");
		Close.setOnAction((e)->{stage.close();
		//Clipboard.getInstance().removeListener(this);
		});
		file.getItems().addAll(New,Close);
		//menu edit
		Menu edit=new Menu("Edit");
		//copy
		MenuItem copy=new MenuItem("Copy");
		copy.setOnAction((e)->{
			Clipboard.getInstance().clear();
			Clipboard.getInstance().copyToClipboard(select.getContents());
			});
		//paste
		paste=new MenuItem("Paste");
		paste.setDisable(true);
		paste.setOnAction((e)->{
			board.addClip(Clipboard.getInstance().copyFromClipboard());
			draw();
			Clipboard.getInstance().clear();
		});
		//delete
		MenuItem delete=new MenuItem("Delete");
		delete.setOnAction((e)->{
			board.removeClip(select.getContents());
			draw();
		});
		
		
		//Groupe
		MenuItem groupe=new MenuItem("Groupe");
		groupe.setOnAction((e)->{
			/*ClipGroup clipGroup=new ClipGroup();
			List<Clip> gr=select.getContents();
			board.removeClip(gr);
			clipGroup.addClip(gr);
			board.addClip(clipGroup);*/
			Command c= new CommandGroup(this,select.getContents());
			stack.addCommand(c);
			c.execute();
		});
		//UnGroup
		MenuItem UnGroup=new MenuItem("UnGroupe");
		UnGroup.setOnAction((e)->{
			for(Clip c:select.getContents()){
				if (c instanceof ClipGroup){
					ClipGroup o=(ClipGroup)c;
					/*List<Clip> gr=o.getClips();
					board.removeClip(o);
					board.addClip(gr);
					draw();*/
					Command cmd= new CommandUngroup(this,o);
					stack.addCommand(cmd);
					cmd.execute();
				}
			}
			
		
		});
		
		//Undo
		MenuItem undo=new MenuItem("Undo");
		undo.setOnAction((e)->{
			
			stack.undo();
			draw();
		});
		
		//Redo
		MenuItem redo=new MenuItem("Redo");
		redo.setOnAction((e)->{
			
			stack.redo();
			draw();
		});
		edit.getItems().addAll(copy,paste,delete,groupe,UnGroup,undo,redo);
		
		Menu tools=new Menu("Tools");
		
		//menu bar
		MenuBar menuBar = new MenuBar(file,edit,tools);
		Label label=new Label("Filled rectangle tool");
		
		//box
		Button box=new Button("Box");
		box.setOnAction((e)->{
			tool=new ToolRect();
			label.setText("Filled rectangle tool");});
		//ellipse
		Button ellipse=new Button("Ellipse");
		ellipse.setOnAction((e)->{
			tool=new ToolEllipse();
			label.setText("Filled ellipse tool");});
		//image
		Button img=new Button("Img..");
		img.setOnAction((e)->{
			FileChooser fileCh=new FileChooser(); 
			f=fileCh.showOpenDialog(stage);
			Clip i=new ClipImage(50,50,f);
			i.draw(canvas.getGraphicsContext2D());
			});
		//select
		Button select=new Button("Select");
		select.setOnAction((e)->{
			tool=new ToolSelection();
			label.setText("Filled selection tool");});
		ToolBar toolBar=new ToolBar(box,ellipse,img,select);
		
		canvas = new Canvas(600,600);
		canvas.setOnMouseDragged((e)->{drag(e);});
		canvas.setOnMousePressed((e)->{press(e);});
		canvas.setOnMouseReleased((e)->{release(e);});
		Separator sep=new Separator();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(menuBar,toolBar,canvas,sep,label);
		stage.setScene(new Scene(vbox));
		stage.show();
	}

	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}

	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return select;
		
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return stack;
		
	}
	private void drag(MouseEvent e){
		tool.drag(this, e);
		draw();
	}
	
	private void press(MouseEvent e){
		tool.press(this, e);
		draw();
	}
	
	public void draw(){
		board.draw(canvas.getGraphicsContext2D());
		tool.drawFeedback(this, canvas.getGraphicsContext2D());
	}
	
	private void release(MouseEvent e){
		tool.release(this, e);
		draw();
	}
	
	public File getFile(){
		return f;
		
	}

	@Override
	public void clipboardChanged() {
		// TODO Auto-generated method stub
		if(Clipboard.getInstance().isEmpty()){
			paste.setDisable(true);
		}
		else{
			paste.setDisable(false);
		}
	}
	
	public Selection getSelect(){
		return select;
	}
	
	
	
}
