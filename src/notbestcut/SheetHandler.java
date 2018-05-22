/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notbestcut;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Наташа on 06.05.2018.
 */
public class SheetHandler {
    private ArrayList<Detail> detailList;
    private ArrayList<Sheet> sheetList;
    private int current;

    public SheetHandler(){
        current = 0;

        detailList = new ArrayList();
        sheetList = new ArrayList();

        sheetList.add(new Sheet());
        detailList.add(new Detail(20000,20000));
        detailList.add(new Detail(3000,4000));
        detailList.add(new Detail(5000,10000));
        detailList.add(new Detail(4500,5000));
        detailList.add(new Detail(3000,11000));
        detailList.add(new Detail(4500,5000));
        detailList.add(new Detail(9500,2500));
        detailList.add(new Detail(13500,4000));
        detailList.add(new Detail(4000,5500));
        detailList.add(new Detail(3500,3000));
        detailList.add(new Detail(2500,7000));


        rotateDetails();
        detailList.sort(new DetailComparator());
    }

    public SheetHandler(int height, int width) {
        current = 0;

        detailList = new ArrayList();
        sheetList = new ArrayList();

        sheetList.add(new Sheet(height, width));
    }
    
    public ArrayList<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<Detail> detailList) {
        this.detailList = detailList;
    }

    public ArrayList<Sheet> getSheetList() {
        return sheetList;
    }

    public void setSheetList(ArrayList<Sheet> sheetList) {
        this.sheetList = sheetList;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    private void rotateDetails(){
        for (Detail detail: detailList){
            if (detail.isRotatable())
                if (detail.getHeight() > detail.getWidth()){
                    int temp = detail.getHeight();
                    detail.setHeight(detail.getWidth());
                    detail.setWidth(temp);
                }
        }
    }

    private void findLeftAngles(ArrayList<Point> points){
        ArrayList<Detail> details = sheetList.get(current).getDetailList();
        for (int i = 0; i < details.size(); i++){
                points.add(new Point(details.get(i).getTop() + details.get(i).getHeight(), details.get(i).getLeft()));
        }
        points.sort(new PointComparator());
    }

    private void findRightAngles(ArrayList<Point> points){
        ArrayList<Detail> details = sheetList.get(current).getDetailList();
        for (int i = 0; i < details.size(); i++){
                points.add(new Point(details.get(i).getTop(), details.get(i).getLeft() + details.get(i).getWidth()));
        }
    }

     /*private ArrayList<Point> sortAngles(ArrayList<Point> angles){
        int min;
        for (int i = 0; i < detailList.size()-1; i++){
            min = i;
            for (int j = i; j < detailList.size()-1; j++){
                angles.sort();
            }
        }
        return angles;
    }*/

    private void addDetail(ArrayList<Point> points, Detail detail){
        sheetList.get(current).getDetailList().add(detail); //добавляем деталь в лист
        detailList.remove(detail); //убираем из общего списка
        points.add(new Point(detail.getLeft(), detail.getTop() + detail.getHeight())); //добавляем в список точек точки добавленной
        points.add(new Point(detail.getLeft() + detail.getWidth(), detail.getTop()));
        points.sort(new PointComparator());
    }

     public void distribute(){ //распределение деталей на листе
         int ind, indDet;
         boolean flag;

         detailList.sort(new DetailComparator());
         rotateDetails();

         do {

         ArrayList<Point> points = new ArrayList(); //точки, в которые можно поставить деталь

         detailList.get(0).setLeft(0); //первую деталь слева вверх сразу
         detailList.get(0).setTop(0);
         addDetail(points, detailList.get(0));


         indDet = 0; //текущая деталь

         while (indDet < detailList.size()){ //ходим по всем деталям
             ind = 0;
             flag = false;
             while ((flag == false)&&(ind < points.size())) { //проверяем все точки, в которые можно ставить
                 flag = true;
                 for (Detail otherDetail : sheetList.get(current).getDetailList()) { //проверяем, не мешаем ли другим деталям
                     if ((((points.get(ind).getX() < (otherDetail.getLeft() + otherDetail.getWidth()) && (points.get(ind).getX() >= otherDetail.getLeft())) || (((points.get(ind).getX() + detailList.get(indDet).getWidth()) > otherDetail.getLeft()) && ((points.get(ind).getX() + detailList.get(indDet).getWidth()) <= (otherDetail.getLeft() + otherDetail.getWidth()))))) && (((points.get(ind).getY() < (otherDetail.getTop() + otherDetail.getHeight())) && (points.get(ind).getY() >= otherDetail.getTop())) || (((points.get(ind).getY() + detailList.get(indDet).getHeight()) > otherDetail.getTop()) && ((points.get(ind).getY() + detailList.get(indDet).getHeight()) <= (otherDetail.getTop() + otherDetail.getHeight())))) || ((points.get(ind).getX() + detailList.get(indDet).getWidth()) > sheetList.get(current).getWidth()) || ((points.get(ind).getY() + detailList.get(indDet).getHeight()) > sheetList.get(current).getHeight())) {
                         flag = false; //мешаем
                         ind++; //берем другую точку
                         break;
                     }
                 }
             }
             if (flag == true) { //никому не помешали и нашли свою точку
                 detailList.get(indDet).setLeft(points.get(ind).getX()); //забираем себе точку
                 detailList.get(indDet).setTop(points.get(ind).getY());
                 points.remove(ind); //убираем ее из общего списка
                 addDetail(points, detailList.get(indDet));
                 sheetList.get(current).getDetailList().sort(new DetailComparator());


                /* for (int i = 0; i < points.size(); i++)
                     System.out.println(points.get(i).getX() + "x" + points.get(i).getY());
                 System.out.println("---------------");*/
             }
             else
                 indDet++; //не нашли себе место

             if (indDet >= detailList.size() && detailList.size()> 0) { //прошли все детали, но все поместились на этом листе
                 sheetList.add(new Sheet(sheetList.get(0).getHeight(), sheetList.get(0).getWidth()));
                 current++;
             }

         }} while (0 < detailList.size());
    }

    /*private void clear(){ //заполнить лист нулями
        for (int i = 0; i < sheet.length; i++){
            for (int j = 0; j < sheet[0].length; j++){
                sheet[i][j] = 0;
            }
        }
    }*/

    public void print(){ //вывести на экран
        for (Detail detail: sheetList.get(current).getDetailList()){
                System.out.print("(" + detail.getLeft() + ";" + detail.getTop() + ") " + detail.getHeight() + 'x' + detail.getWidth()+ '\n');
            }
        System.out.println("----------------------------------");
    }
}

