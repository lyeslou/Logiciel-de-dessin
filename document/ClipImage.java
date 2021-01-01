package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage extends AbstractClip implements Clip{
	
	Image img;
	public ClipImage(double left, double top,File filename){
		img= new Image("file://" + filename.getAbsolutePath());
		setGeometry(left, top, img.getWidth()+left, img.getHeight()+top);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
	
			//ctx.fillRect(20, 20, 20, 20);
			if (img==null){
				ctx.fillRect(20, 20, 20, 20);
			}
			else{
			ctx.drawImage(img, getLeft(),getTop() );
			}
	}


	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
