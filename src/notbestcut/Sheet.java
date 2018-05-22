package notbestcut;

import java.util.ArrayList;

/**
 * Created by Наташа on 12.05.2018.
 */
public class Sheet {
    private int id;
    private int height;
    private int width;
    private ArrayList<Detail> detailList = new ArrayList();

    public Sheet(){
        this.height = 20000;
        this.width = 20000;
    }

    public Sheet(int height, int width){
        this.height = height;
        this.width = width;
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

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ArrayList<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<Detail> detailList) {
        this.detailList = detailList;
    }
}
