package notbestcut;

import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import notbestcut.Sheet;

public class JSheetPaintPanel extends javax.swing.JPanel {

    private ArrayList<Sheet> sheet;
    private int ind;
    private double k;
    
    public JSheetPaintPanel() {
        initComponents();
        ind = -1;
        k = 1;
        sheet = new ArrayList();
    }

    /*public JSheetPaintPanel(Sheet sheet){
	initComponents();
	this.sheet = sheet;
	repaint();
    }*/
    
    private void calcK(){
		boolean flag = false;
		if (sheet.get(ind).getWidth() > this.getWidth()){
			k = (double)(sheet.get(ind).getWidth())/((double)(this.getWidth()));
			flag = true;
		}
		if (sheet.get(ind).getHeight() > this.getHeight()){
		    k = (double)(sheet.get(ind).getHeight())/((double)(this.getHeight()));
		    flag = true;
		}
		
	    if (flag == false)
			k = 1;
	}
	
	public void setSheet(ArrayList<Sheet> sheet){
		this.sheet = sheet;
		repaint();
	}	
        
        public void setInd(int ind){
            this.ind = ind;
            repaint();
        }
        
        public int getInd(){
            return ind;
        }
        
        public int getSheetSize(){
            return sheet.size();
        }
	public void paintSheet(Graphics g){
		calcK();
		
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.BLACK);
		int w = 2;
		g2D.setStroke(new BasicStroke(2.0f));
		if (sheet.size() > 0)
                    g2D.drawRect(0, 0, (int)((sheet.get(ind).getWidth()/k)), (int)((sheet.get(ind).getHeight()/k)));
		
		for (Detail d: sheet.get(ind).getDetailList()){
			g2D.setColor(Color.BLACK);
			g2D.drawRect((int)(d.getLeft()/k), (int)(d.getTop()/k), (int)(d.getWidth()/k), (int)(d.getHeight()/k));
			g2D.setColor(Color.WHITE);
			g2D.fillRect((int)(d.getLeft()/k)+w, (int)(d.getTop()/k)+w, (int)(d.getWidth()/k)-2*w, (int)(d.getHeight()/k)-2*w);
			g2D.setColor(Color.BLACK);
			g2D.setFont(new Font("Century Gothic", Font.PLAIN, 16));
			g2D.drawString(d.getHeight()+"", (int)(d.getLeft()/k)+w, ((int)(d.getTop()/k)+w)+((int)(d.getHeight()/k)/2));
			g2D.drawString(d.getWidth()+"", (int)(d.getLeft()/k)+w+(((int)(d.getWidth()/k)/2)-18), ((int)(d.getTop()/k)+w)+((int)(d.getHeight()/k)-5));
			g2D.setColor(new Color(84, 149, 215));
			g2D.setFont(new Font("Century Gothic", Font.BOLD, 16));
			g2D.drawString(d.getId()+"", (int)(d.getLeft()/k)+w+(((int)(d.getWidth()/k))-25), ((int)(d.getTop()/k)+17));
		}
	}
	
        public void saveImage(String name,String type) {
		BufferedImage image = new BufferedImage((int)((sheet.get(ind).getWidth()/k)), (int)((sheet.get(ind).getHeight()/k)), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		paint(g2);
		try {
			ImageIO.write(image, type, new File(name + "." + type));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
	@Override
	public void paint(Graphics g){
            g.setColor(new Color(100, 176, 255));
            if (sheet.size() > 0){
                g.fillRect(0, 0, (int)(sheet.get(ind).getWidth()/k), (int)(sheet.get(ind).getHeight()/k));
                paintSheet(g);
            }
	}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
