/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class ChangeSaver {
    private List<Integer> idUpdatedList;
    private List<Integer> idDeletedList;
    private int countOfinserted;
    
    public ChangeSaver(){
        idUpdatedList = new ArrayList();
        idDeletedList = new ArrayList();
        countOfinserted = 0;
    }
    
    public void clear(){
        idUpdatedList = new ArrayList();
        idDeletedList = new ArrayList();
        countOfinserted = 0;
    }
    public List<Integer> getUpdatedList(){
        return idUpdatedList;
    }
    
    public void addUpdatedList(int ind){
        idUpdatedList.add(ind);
    }
    
    public List<Integer> getDeletedList(){
        return idDeletedList;
    }
    
    public void addDeletedList(int ind){
        idDeletedList.add(ind);
    }
    
    public int getCountOfinserted(){
        return countOfinserted;
    }
    
    public void setCountOfinserted(int val){
        countOfinserted = val;
    }
    
    public void incCountOfinserted(){
        countOfinserted++;
    }
}
