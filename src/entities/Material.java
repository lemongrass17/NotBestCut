package entities;

public class Material {

    private String description;
    private int height;
    private int width;

    public void setDescription(String description){
        this.description = description;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public String getDescription(){
        return description;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}
