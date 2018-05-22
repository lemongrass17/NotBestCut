package entities;

public class Details {

    private int id;
    private int id_orders;
    private int id_material;
    private int height;
    private int width;
    private int count;
    private boolean isRotated;

    public void setId(int id){
        this.id = id;
    }

    public void setId_orders(int id_orders){
        this.id_orders = id_orders;
    }

    public void setId_material(int id_material){
        this.id_material = id_material;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setRotated(boolean isRotated){
        this.isRotated = isRotated;
    }

    public int getId(){
        return id;
    }

    public int getId_orders(){
        return id_orders;
    }

    public int getId_material(){
        return id_material;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getCount(){
        return count;
    }

    public boolean getRotated(){
        return isRotated;
    }
}
