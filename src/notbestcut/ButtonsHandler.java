/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notbestcut;

import database.SQLiteManager;
import entities.Client;
import entities.Details;
import entities.Order;
import entities.Status;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Max
 */
public class ButtonsHandler {
    protected static void refreshTableAllClients(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Client> allClientsInfo = man.selectAllClientsInfo();
            
            for (int i = model.getRowCount(); i < allClientsInfo.size(); i++){
                model.addRow(new Object[]{null, null, null, null, null, null});
            }
            
            for (int i = 0; i < allClientsInfo.size(); i++){
                model.setValueAt(allClientsInfo.get(i).getID(), i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allClientsInfo.get(i).getSurname(), i, 2);
                model.setValueAt(allClientsInfo.get(i).getNameC(), i, 3);
                model.setValueAt(allClientsInfo.get(i).getTelephone(), i, 4);
                model.setValueAt(allClientsInfo.get(i).getAddress(), i, 5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   /* protected static void refreshTableAllStaff(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Client> allStaff = man.select
            
            for (int i = model.getRowCount(); i < allClientsInfo.size(); i++){
                model.addRow(new Object[]{null, null, null, null, null, null});
            }
            
            for (int i = 0; i < allClientsInfo.size(); i++){
                model.setValueAt(allClientsInfo.get(i).getID(), i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allClientsInfo.get(i).getSurname(), i, 2);
                model.setValueAt(allClientsInfo.get(i).getNameC(), i, 3);
                model.setValueAt(allClientsInfo.get(i).getTelephone(), i, 4);
                model.setValueAt(allClientsInfo.get(i).getAddress(), i, 5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
   
    protected static void refreshTableOrders(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Order> allOrders = man.selectAllOrders();
            System.out.println(allOrders.size());
            
            for (int i = model.getRowCount(); i < allOrders.size(); i++){
                model.addRow(new Object[]{null, null, null, null, null});
            }
            
            for (int i = 0; i < allOrders.size(); i++){
                model.setValueAt(allOrders.get(i).getID(), i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allOrders.get(i).getDescription(), i, 2);
                model.setValueAt(allOrders.get(i).getSurname(), i, 3);
                model.setValueAt(allOrders.get(i).getStatus(), i, 4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableOrdersWithFilters(JTable table, int selectedRadio, int id){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Order> allOrders;
            switch (selectedRadio){
                case 1:
                    allOrders = man.selectAllOrdersOfOneClient(id);
                    break;
                default:
                    allOrders = man.selectAllOrdersByStatus(id);
                    break;
            }
            System.out.println(allOrders.size());
            
            for (int i = model.getRowCount(); i < allOrders.size(); i++){
                model.addRow(new Object[]{null, null, null, null, null});
            }
            
            for (int i = 0; i < allOrders.size(); i++){
                model.setValueAt(allOrders.get(i).getID(), i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allOrders.get(i).getDescription(), i, 2);
                model.setValueAt(allOrders.get(i).getSurname(), i, 3);
                model.setValueAt(allOrders.get(i).getStatus(), i, 4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableOrdersWithDate(JTable table, String dateFrom, String dateTo){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Order> allOrders = man.selectAllOrdersByDateStatus(dateFrom, dateTo);
        
            System.out.println(allOrders.size());
            
            for (int i = model.getRowCount(); i < allOrders.size(); i++){
                model.addRow(new Object[]{null, null, null, null, null});
            }
            
            for (int i = 0; i < allOrders.size(); i++){
                model.setValueAt(allOrders.get(i).getID(), i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allOrders.get(i).getDescription(), i, 2);
                model.setValueAt(allOrders.get(i).getSurname(), i, 3);
                model.setValueAt(allOrders.get(i).getStatus(), i, 4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableOrderService(JTable table, int id){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allServices = man.selectServicesOfOrder(id);
            
            for (int i = model.getRowCount(); i < allServices.size(); i++){
                model.addRow(new Object[]{null, null, null, null});
            }
            
            for (int i = 0; i < allServices.size(); i++){
                model.setValueAt(allServices.get(i)[0], i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allServices.get(i)[1], i, 2);
                model.setValueAt(allServices.get(i)[3], i, 3);
                String price = allServices.get(i)[2];
                model.setValueAt(price.substring(0, price.length()-3) + "." + price.substring(price.length()-2), i, 4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableOrderSheets(JTable table, int id){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allServices = man.selectSheetsOfOrder(id);
            
            for (int i = model.getRowCount(); i < allServices.size(); i++){
                model.addRow(new Object[]{null, null, null, null});
            }
            
            if (model.getColumnCount() == 4){
                for (int i = 0; i < allServices.size(); i++){
                    model.setValueAt(allServices.get(i)[0], i, 0);
                    model.setValueAt(allServices.get(i)[1], i, 1);
                    model.setValueAt(allServices.get(i)[2], i, 2);
                    String price = allServices.get(i)[4];
                    model.setValueAt(price.substring(0, price.length()-3) + "." + price.substring(price.length()-2), i, 3);
                }
            }
            else
            {
                 for (int i = 0; i < allServices.size(); i++){
                    model.setValueAt(allServices.get(i)[1], i, 0);
                    model.setValueAt(allServices.get(i)[2], i, 1);
                    model.setValueAt(allServices.get(i)[0], i, 2); 
                }
                
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableOrderStatus(JTable table, int id){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allServices = man.selectOrdersDateDescript(id);
            
            for (int i = model.getRowCount(); i < allServices.size(); i++){
                model.addRow(new Object[]{null, null, null});
            }
            
            for (int i = 0; i < allServices.size(); i++){
                model.setValueAt(allServices.get(i)[0], i, 0);
                model.setValueAt(allServices.get(i)[1], i, 1);
                model.setValueAt(allServices.get(i)[2], i, 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableStaff(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allStaff = man.selectAllStaff();
            
            
            for (int i = model.getRowCount(); i < allStaff.size(); i++){
                model.addRow(new Object[]{null, null, null, null, null, null, null, null});
            }
            
            for (int i = 0; i < allStaff.size(); i++){
                model.setValueAt(allStaff.get(i)[0], i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allStaff.get(i)[1], i, 2);
                model.setValueAt(allStaff.get(i)[2], i, 3);
                model.setValueAt(allStaff.get(i)[5], i, 4);
                model.setValueAt(allStaff.get(i)[6], i, 5);
                model.setValueAt(allStaff.get(i)[3], i, 6);
                model.setValueAt(allStaff.get(i)[4], i, 7);
                System.out.println(allStaff.get(i)[6]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableStaffWithPost(JTable table, int id_post){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allStaff = man.selectStaffByPost(id_post);
            
            for (int i = model.getRowCount(); i < allStaff.size(); i++){
                model.addRow(new Object[]{null, null, null, null, null, null, null, null});
            }
            
            for (int i = 0; i < allStaff.size(); i++){
                model.setValueAt(allStaff.get(i)[0], i, 0);
                model.setValueAt(i+1, i, 1);
                model.setValueAt(allStaff.get(i)[1], i, 2);
                model.setValueAt(allStaff.get(i)[2], i, 3);
                model.setValueAt(allStaff.get(i)[5], i, 4);
                model.setValueAt(allStaff.get(i)[6], i, 5);
                model.setValueAt(allStaff.get(i)[3], i, 6);
                model.setValueAt(allStaff.get(i)[4], i, 7);
                System.out.println(allStaff.get(i)[6]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableStaffPost(JTable table, int id){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allPosts = man.selectPostsOfStaff(id);
            
            for (int i = model.getRowCount(); i < allPosts.size(); i++){
                model.addRow(new Object[]{null, null, null});
            }
            
            for (int i = 0; i < allPosts.size(); i++){
                model.setValueAt(allPosts.get(i)[0], i, 0);
                model.setValueAt(allPosts.get(i)[2], i, 1);
                model.setValueAt(allPosts.get(i)[1], i, 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableMaterial(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allMat = man.selectMaterial();
            
            for (int i = model.getRowCount(); i < allMat.size(); i++){
                model.addRow(new Object[]{null, null});
            }
            
            for (int i = 0; i < allMat.size(); i++){
                model.setValueAt(allMat.get(i)[0], i, 0);
                model.setValueAt(allMat.get(i)[1], i, 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static void refreshTablePosts(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allPosts = man.selectAllPosts();
            
            for (int i = model.getRowCount(); i < allPosts.size(); i++){
                model.addRow(new Object[]{null, null});
            }
            
            for (int i = 0; i < allPosts.size(); i++){
                model.setValueAt(allPosts.get(i)[0], i, 0);
                model.setValueAt(allPosts.get(i)[1], i, 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    protected static void refreshTableServices(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<String[]> allServices = man.selectAllServices();
            
            for (int i = model.getRowCount(); i < allServices.size(); i++){
                model.addRow(new Object[]{null, null});
            }
            
            for (int i = 0; i < allServices.size(); i++){
                model.setValueAt(allServices.get(i)[0], i, 0);
                model.setValueAt(allServices.get(i)[1], i, 1);
                String price = allServices.get(i)[2]; 
                model.setValueAt(price.substring(0, price.length()-3) + "." + price.substring(price.length()-2), i, 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static void refreshTableStats(JTable table){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Status> allStats = man.selectAllStatuses();
            System.out.println("size: " + allStats.size());
            
            for (int i = model.getRowCount(); i < allStats.size(); i++){
                model.addRow(new Object[]{null, null});
            }
            
            for (int i = 0; i < allStats.size(); i++){
                model.setValueAt(allStats.get(i).getId(), i, 0);
                model.setValueAt(allStats.get(i).getDescription(), i, 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableDetails(JTable table, int id_order, int ind){
        SQLiteManager man = new SQLiteManager();
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Details>[] allDetails = man.selectDetailByMat(id_order);
            if (allDetails.length > 0)
            {
                for (int i = model.getRowCount(); i < allDetails[ind].size(); i++){
                    model.addRow(new Object[]{null, null, null, null, null, null, null});
                }

                for (int i = 0; i < allDetails[ind].size(); i++){
                    model.setValueAt(i+1, i, 0);
                    model.setValueAt(allDetails[ind].get(i).getWidth(), i, 1);
                    model.setValueAt(allDetails[ind].get(i).getHeight(), i, 2);
                    model.setValueAt(allDetails[ind].get(i).getCount(), i, 3);
                    model.setValueAt(allDetails[ind].get(i).getRotated(), i, 4);
                    model.setValueAt(allDetails[ind].get(i).getId(), i, 6);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static void refreshTableDetails(DefaultTableModel model, int id_order, int ind){
        SQLiteManager man = new SQLiteManager();
        
        for (int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        
        try {
            List<Details>[] allDetails = man.selectDetailByMat(id_order);
            if (allDetails.length > 0)
            {
                for (int i = model.getRowCount(); i < allDetails[ind].size(); i++){
                    model.addRow(new Object[]{null, null, null, null, null, null, null});
                }

                for (int i = 0; i < allDetails[ind].size(); i++){
                    model.setValueAt(i+1, i, 0);
                    model.setValueAt(allDetails[ind].get(i).getWidth(), i, 1);
                    model.setValueAt(allDetails[ind].get(i).getHeight(), i, 2);
                    model.setValueAt(allDetails[ind].get(i).getCount(), i, 3);
                    model.setValueAt(allDetails[ind].get(i).getRotated(), i, 4);
                    model.setValueAt(allDetails[ind].get(i).getId(), i, 6);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


