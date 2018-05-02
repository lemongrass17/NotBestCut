package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Orders {

    private String client;
    private Date dateStart;
    private Date dateFinish;

    public void setClient(String client){
        this.client = client;
    }

    public void setDateStart(String dateStart){
        try {
            this.dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(dateStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDateFinish(String dateFinish){
        try {
            this.dateFinish = new SimpleDateFormat("yyyy-MM-dd").parse(dateFinish);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getClient(){
        return client;
    }

    public String getDateStart(){
        return new SimpleDateFormat("yyyy-MM-dd").format(dateStart).toString();
    }

    public String getDateFinish(){
        return new SimpleDateFormat("yyyy-MM-dd").format(dateFinish).toString();
    }
}