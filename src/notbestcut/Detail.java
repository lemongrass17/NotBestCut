package notbestcut;

/**
 * Created by Наташа on 06.05.2018.
 */
public class Detail {
    private int id;
    private int height;
    private int width;
    private int top;
    private int left;
    private boolean rotatable;

    public Detail(int height, int width){
        this.height = height;
        this.width = width;
        this.top = 0;
        this.left = 0;
        this.rotatable = true;
    }

    public Detail(int height, int width, boolean rotatable){
        this.height = height;
        this.width = width;
        this.top = 0;
        this.left = 0;
        this.rotatable = rotatable;
    }
    
    public Detail(int id, int height, int width, boolean rotatable){
        this.id = id;
        this.height = height;
        this.width = width;
        this.top = 0;
        this.left = 0;
        this.rotatable = rotatable;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public boolean isRotatable() {
        return rotatable;
    }

    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }
}