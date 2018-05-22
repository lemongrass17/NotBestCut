/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Max
 */
public class SQLite_OrderStaff {
    private static Connection openConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:sqlite/databases/justCut.sqlite3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    protected static List<String []> selectAllStaff() throws SQLException {
        Connection conn = null;
            
        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String[]> postsOfStaffs = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT Staff.id, Staff.surname, Staff.nameS, Staff.telephone, Staff.address, Staff.birthday, Post.Description\n" +
                        "FROM Staff INNER JOIN (StaffPost INNER JOIN POST ON StaffPost.id_post = Post.id) ON Staff.id = StaffPost.id_staff\n" +
                        "WHERE (SELECT max(StaffPost.id) FROM StaffPost WHERE StaffPost.id_staff = Staff.id) = StaffPost.id;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] postsOfStaff = new String[7];
                postsOfStaff[0] = rs.getString("id");
                postsOfStaff[1] = rs.getString("surname");
                postsOfStaff[2] = rs.getString("nameS");
                postsOfStaff[3] = rs.getString("telephone");
                postsOfStaff[4] = rs.getString("address");
                postsOfStaff[5] = rs.getString("birthday");
                postsOfStaff[6] = rs.getString("Description");
                postsOfStaffs.add(postsOfStaff);
            }
            rs.close();
            stmt.close();
            System.out.println(postsOfStaffs.size());
            return postsOfStaffs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String> selectStaffID() throws SQLException {
        Connection conn = null;
            
        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String> postsOfStaffs = new ArrayList<String>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM Staff;";

            ResultSet rs = stmt.executeQuery(sql);
            String postsOfStaff;
            while(rs.next()){
                postsOfStaff = rs.getString("id");
                postsOfStaffs.add(postsOfStaff);
            }
            rs.close();
            stmt.close();
            System.out.println(postsOfStaffs.size());
            return postsOfStaffs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String[]> selectAllServices() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String[]> services = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Service;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] service = new String[3];
                service[0] = rs.getString("id");
                service[1] = rs.getString("description");
                service[2] = rs.getString("price");
                services.add(service);
            }
            rs.close();
            stmt.close();
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String[]> selectAllPosts() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String[]> posts = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Post;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] post = new String[2];
                post[0] = rs.getString("id");
                post[1] = rs.getString("description");
                posts.add(post);
            }
            rs.close();
            stmt.close();
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String []> selectStaffPost() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String []> staffPostS = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT Staff.id, Staff.surname, Staff.nameS, Staff.telephone, Staff.address, Post.Description\n" +
                    "FROM Staff INNER JOIN (StaffPost INNER JOIN Post ON StaffPost.id_post = Post.id) ON Staff.id = StaffPost.id_staff;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] staffPost = new String[6];
                staffPost[0] = rs.getString("Staff.id");
                staffPost[1] = rs.getString("Staff.surname");
                staffPost[2] = rs.getString("Staff.nameS");
                staffPost[3] = rs.getString("Staff.telephone");
                staffPost[4] = rs.getString("Staff.address");
                staffPost[5] = rs.getString("Post.description");
                staffPostS.add(staffPost);
            }
            rs.close();
            stmt.close();
            return staffPostS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String []> selectPostsOfStaff(int id_staff) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String[]> postsOfStaffs = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT StaffPost.id, Post.description, StaffPost.date_join\n" +
                    "FROM Staff INNER JOIN (StaffPost INNER JOIN Post ON StaffPost.id_post = Post.id) ON StaffPost.id_staff = Staff.id\n" +
                    "WHERE Staff.id = " + id_staff +";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] postsOfStaff = new String[3];
                postsOfStaff[0] = rs.getString("id");
                postsOfStaff[1] = rs.getString("description");
                postsOfStaff[2] = rs.getString("date_join");
                postsOfStaffs.add(postsOfStaff);
            }
            rs.close();
            stmt.close();
            return postsOfStaffs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String []> selectServicesOfOrder(int id_order) throws SQLException { // Услуги заказа
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String []> servisesOfOrders = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT OrderService.id AS id, Service.description AS description, Service.price " +
                    "AS Price, Staff.Surname || \" \" || substr(Staff.nameS,1,1) " +
                    "|| \". (\" || Post.description || \")\" AS Staff\n FROM (Staff INNER JOIN (Service " +
                    "INNER JOIN OrderService ON Service.id = OrderService.id_service) ON Staff.id = " +
                    "OrderService.id_staff) INNER JOIN (Post INNER JOIN StaffPost ON Post.id = StaffPost.id_post) " +
                    "ON Staff.id = StaffPost.id_staff WHERE ((((SELECT max(StaffPost.id) FROM StaffPost WHERE " +
                    "Staff.id = StaffPost.id_staff))=StaffPost.id)) AND (OrderService.id_order = " + id_order + ");";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String[] servisOfOrder = new String[4];
                servisOfOrder[0] = rs.getString("id");
                servisOfOrder[1] = rs.getString("description");
                servisOfOrder[2] = rs.getString("Price");
                servisOfOrder[3] = rs.getString("Staff");
                servisesOfOrders.add(servisOfOrder);
                
                System.out.println(servisOfOrder[0] + " 1: " + servisOfOrder[1] + "2: " + servisOfOrder[2] + " 3:" + servisOfOrder[3]);
            }
            rs.close();
            stmt.close();
            return servisesOfOrders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String []> selectStaffByPost(int id_post) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String[]> postsOfStaffs = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT Staff.id, Staff.surname, Staff.nameS, Staff.telephone, Staff.address, Staff.birthday, Post.Description " +
                        "FROM Staff INNER JOIN (StaffPost INNER JOIN POST ON StaffPost.id_post = Post.id) ON Staff.id = StaffPost.id_staff " +
                        "WHERE (((SELECT max(StaffPost.id) FROM StaffPost WHERE StaffPost.id_staff = Staff.id) = StaffPost.id) AND (Post.id = " + id_post + "));";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] postsOfStaff = new String[7];
                postsOfStaff[0] = rs.getString("id");
                postsOfStaff[1] = rs.getString("surname");
                postsOfStaff[2] = rs.getString("nameS");
                postsOfStaff[3] = rs.getString("telephone");
                postsOfStaff[4] = rs.getString("address");
                postsOfStaff[5] = rs.getString("birthday");
                postsOfStaff[6] = rs.getString("Description");
                postsOfStaffs.add(postsOfStaff);
            }
            rs.close();
            stmt.close();
            System.out.println(postsOfStaffs.size());
            return postsOfStaffs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static void insertIntoPost(int id, String post) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO Post (description) VALUES ('" + post +"');";
            }
            else{
                sql = "UPDATE Post SET post = '" + post + "' WHERE id = " + id + ";";
            }
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void insertIntoStaff(int id, String surname, String nameS, String telephone, String address, Date birthday) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO Staff (surname, nameS, telephone, address, birthday) VALUES ('" + surname + "', '" + nameS + "', '" + telephone + "', '" + address + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(birthday) +"');";
            }
            else{
                sql = "UPDATE Staff SET surname = '" + surname + "', nameS = '" + nameS + "', telephone = '" + telephone + "', address = '" + address + "' birthday = '" + new SimpleDateFormat("yyyy-MM-dd").format(birthday) + "' WHERE id = " + id + ";";
            }
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void insertIntoStaffPost(int id, int id_staff, int id_post, Date date_join) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO StaffPost (id_staff, id_post, date_join) VALUES (" + id_staff+ ", " + id_post +", '" + new SimpleDateFormat("yyyy-MM-dd").format(date_join) + "');";
            }
            else{
                sql = "UPDATE StaffPost SET id_staff = " + id_staff + ", id_post = " + id_post + ", date_join = '" + new SimpleDateFormat("yyyy-MM-dd").format(date_join) + "' WHERE id = " + id + ";";
            }
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void insertIntoService(int id, String description, int price) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO Service (description, price) VALUES ('" + description + "', " + price + ");";
            }
            else{
                sql = "UPDATE Service SET description = '" + description + "', price = " + price + " WHERE id = " + id + ";";
            }
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void insertIntoServiceOrder(int id, int id_service, int id_order, int id_staff) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO OrderService (id_service, id_order, id_staff) VALUES (" + id_service + ", " + id_order + ", " + id_staff + ");";
            }
            else{
                sql = "UPDATE OrderService SET id_service = " + id_service + ", id_order = " + id_order + ", id_staff = " + id_staff + " WHERE id = " + id + ";";
            }
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void deletePost(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Post WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void deleteStaff(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Staff WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void deleteService(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Service WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void deleteStaffPost(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM StaffPost WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    protected static void deleteOrderService(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM OrderService WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
}
