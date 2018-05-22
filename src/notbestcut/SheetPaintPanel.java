package notbestcut;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class SheetPaintPanel extends JPanel{
	private Sheet sheet;
	private double k;
	
	public SheetPaintPanel(){
		super();
	}
	
	public SheetPaintPanel(Sheet sheet){
		super();
		this.sheet = sheet;
		repaint();
	}
	
	private void calcK(){
		boolean flag = false;
		if (sheet.getWidth() > this.getWidth()){
			k = (double)(sheet.getWidth())/((double)(this.getWidth()));
			flag = true;
		}
		if (sheet.getHeight() > this.getHeight()){
		    k = (double)(sheet.getHeight())/((double)(this.getHeight()));
		    flag = true;
		}
		
	    if (flag == false)
			k = 1;
	}
	
	public void setSheet(Sheet sheet){
		this.sheet = sheet;
		repaint();
	}	
	
	public void paintSheet(Graphics g){
		calcK();
		
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.BLACK);
		g2D.setStroke(new BasicStroke(2.0f));
		g2D.drawRect(0, 0, (int)((sheet.getWidth()/k)), (int)((sheet.getHeight()/k)));
		
		for (Detail d: sheet.getDetailList()){
			g2D.drawRect((int)(d.getLeft()/k), (int)(d.getTop()/k), (int)(d.getWidth()/k), (int)(d.getHeight()/k));
		}
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2D = (Graphics2D)g;
        
        paintSheet(g);
	}
}
