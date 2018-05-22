/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notbestcut;

import database.SQLiteManager;
import entities.Client;
import entities.Status;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxHandler {
    private static ArrayList<String> arrID;
    private static ArrayList<String> arrVal;
    
    public ComboBoxHandler(){
        arrID = new ArrayList();
        arrVal = new ArrayList();
    }
    
    public void setArrID(ArrayList<String> arr){
        this.arrID = arr;
    }
    
    public ArrayList<String> getArrID(){
        return arrID;
    }
    
    public void addID(String id){
        arrID.add(id);
    }
    
    public static String getID(int ind){
        return arrID.get(ind);
    }
    
    
    public void setArrVal(ArrayList<String> arr){
        this.arrVal = arr;
    }
    
    public ArrayList<String> getArrVal(){
        return arrVal;
    }
    
    public void addVal(String val){
        arrVal.add(val);
    }
    
    public static String getVal(int ind){
        return arrVal.get(ind);
    }
    
    public void clear(){
        arrID = new ArrayList();
        arrVal = new ArrayList();
    }
    
    protected static int getInd(String val){
        for (int i = 0; i < arrVal.size(); i++)
            if (arrVal.get(i).equals(val))
                return i;
        return -1;
    } 
    
    protected static void setStaffPostComboBox() throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<String[]> allPosts = man.selectAllPosts();
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allPosts.size(); i++){
            arrID.add(allPosts.get(i)[0]);
            arrVal.add(allPosts.get(i)[1]);
        }
        arrID.add("-1");
        arrVal.add("Новая должность...");
    }
    
    protected static void refreshStaffPostComboBox(JComboBox box) throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<String[]> allPosts = man.selectAllPosts();
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allPosts.size(); i++){
            arrID.add(allPosts.get(i)[0]);
            arrVal.add(allPosts.get(i)[1]);
        }
        arrID.add("-1");
        arrVal.add("Новая должность...");
        
        String[] ans = new String[arrVal.size()];
        
        box.removeAllItems();
        for (int i = 0; i < ans.length; i++)
            box.addItem(arrVal.get(i));
    }
    
    protected static void setStatsComboBox() throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<Status> allStats = man.selectAllStatuses();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allStats.size(); i++){
            arrID.add(String.valueOf(allStats.get(i).getId()));
            arrVal.add(allStats.get(i).getDescription());
        }
        arrID.add("-1");
        arrVal.add("Новый статус...");
    }
    
    protected static void refreshStatsComboBox(JComboBox box) throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<Status> allStats = man.selectAllStatuses();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allStats.size(); i++){
            arrID.add(String.valueOf(allStats.get(i).getId()));
            arrVal.add(allStats.get(i).getDescription());
        }
        arrID.add("-1");
        arrVal.add("Новый статус...");
        
        String[] ans = new String[arrVal.size()];
        
        box.removeAllItems();
        for (int i = 0; i < ans.length; i++)
            box.addItem(arrVal.get(i));
    }
    
    protected static void setClientsComboBox() throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<Client> allClients = man.selectAllClientsInfo();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allClients.size(); i++){
            arrID.add(String.valueOf(allClients.get(i).getID()));
            arrVal.add(allClients.get(i).getSurname() + " " + allClients.get(i).getNameC().substring(0, 1) + ".");
        }
        arrID.add("-1");
        arrVal.add("Новый клиент...");
    }
    
    protected static void refreshClientsComboBox(JComboBox box) throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<Client> allClients = man.selectAllClientsInfo();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allClients.size(); i++){
            arrID.add(String.valueOf(allClients.get(i).getID()));
            arrVal.add(allClients.get(i).getSurname() + " " + allClients.get(i).getNameC().substring(0, 1) + ".");
        }
        arrID.add("-1");
        arrVal.add("Новый клиент...");
        
        String[] ans = new String[arrVal.size()];
        
        box.removeAllItems();
        for (int i = 0; i < ans.length; i++)
            box.addItem(arrVal.get(i));
    }
    
    protected static void setStaffComboBox() throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<String[]> allStaff = man.selectAllStaff();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allStaff.size(); i++){
            arrID.add(String.valueOf(allStaff.get(i)[0]));
            arrVal.add(allStaff.get(i)[1] + " " + allStaff.get(i)[2].substring(0, 1));
        }
        arrID.add("-1");
        arrVal.add("Новый статус...");
    }
    
    protected static void refreshStaffComboBox(JComboBox box) throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<String[]> allStaff = man.selectAllStaff();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allStaff.size(); i++){
            arrID.add(String.valueOf(allStaff.get(i)[0]));
            arrVal.add(allStaff.get(i)[1] + " " + allStaff.get(i)[2].substring(0, 1));
        }
        arrID.add("-1");
        arrVal.add("Новый статус...");
        
        String[] ans = new String[arrVal.size()];
        
        box.removeAllItems();
        for (int i = 0; i < ans.length; i++)
            box.addItem(arrVal.get(i));
    }
    
    protected static void setServiceComboBox() throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<String[]> allSevices = man.selectAllServices();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allSevices.size(); i++){
            arrID.add(String.valueOf(allSevices.get(i)[0]));
            arrVal.add(allSevices.get(i)[1] + " (" + allSevices.get(i)[2] + ")");
        }
        arrID.add("-1");
        arrVal.add("Новая услуга...");
    }
    protected static void refreshServiceComboBox(JComboBox box) throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<String[]> allSevices = man.selectAllServices();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allSevices.size(); i++){
            arrID.add(String.valueOf(allSevices.get(i)[0]));
            String price = allSevices.get(i)[2];
            arrVal.add(allSevices.get(i)[1] + " (" + price.substring(0, price.length()-3) + "." + price.substring(price.length()-2) + ")");
        }
        arrID.add("-1");
        arrVal.add("Новая услуга...");
        
        String[] ans = new String[arrVal.size()];
        
        box.removeAllItems();
        for (int i = 0; i < ans.length; i++)
            box.addItem(arrVal.get(i));
    }
    
    protected static void refresMaterialComboBox(JComboBox box) throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<String[]> allMat = man.selectMaterial();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allMat.size(); i++){
            arrID.add(String.valueOf(allMat.get(i)[0]));
            arrVal.add(allMat.get(i)[1]);
        }
        arrID.add("-1");
        arrVal.add("Новый материал...");
        
        String[] ans = new String[arrVal.size()];
        
        box.removeAllItems();
        for (int i = 0; i < ans.length; i++)
            box.addItem(arrVal.get(i));
    }
    
    protected static void refreshStatusComboBox(JComboBox box) throws SQLException{
        SQLiteManager man = new SQLiteManager();
        List<Status> allMat = man.selectAllStatuses();
        
        arrID = new ArrayList();
        arrVal = new ArrayList();
        
        for (int i = 0; i < allMat.size(); i++){
            arrID.add(String.valueOf(allMat.get(i).getId()));
            arrVal.add(allMat.get(i).getDescription());
        }
        arrID.add("-1");
        arrVal.add("Новый статус...");
        
        String[] ans = new String[arrVal.size()];
        
        box.removeAllItems();
        for (int i = 0; i < ans.length; i++)
            box.addItem(arrVal.get(i));
    }
}
