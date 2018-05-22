package database;

import entities.Client;
import entities.Details;
import entities.Material;
import entities.Order;
import entities.Orders;
import entities.Status;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class SQLiteManager {

    private Connection openConnection(){

        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:sqlite/databases/notbestcut.sqlite3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //OrderClient
    
    public List<Order> selectAllOrdersOfOneClient(int id_client) throws SQLException {
        return SQLite_OrderClient.selectAllOrdersOfOneClient(id_client);
    }
    
    public List<Order> selectAllOrdersByStatus(int id_status) throws SQLException {
        return SQLite_OrderClient.selectAllOrdersByStatus(id_status);
    }
    
    public List<Order> selectAllOrdersByDateStatus(String dateFrom, String dateTo) throws SQLException {
        return SQLite_OrderClient.selectAllOrdersByDateStatus(dateFrom, dateTo);
    }
    
    public List<Integer> selectAllOrdersID() throws SQLException {
        return SQLite_OrderClient.selectAllOrdersID();
    }
    
    public List<Status> selectAllStatuses() throws SQLException {
        return SQLite_OrderClient.selectAllStatuses();
    }
    
    public List<Integer> selectAllClientsId() throws SQLException {
        return SQLite_OrderClient.selectAllClientsId();
    }

    public List<Client> selectAllClientsInfo() throws SQLException {
        return SQLite_OrderClient.selectAllClientsInfo();
    }
    
    public List<Order> selectAllOrders() throws SQLException {
        return SQLite_OrderClient.selectAllOrders();
    }
    
    public List<String[]> selectOrdersDateDescript(int id_order) throws SQLException {
        return SQLite_OrderClient.selectOrdersDateDescript(id_order);
    }
    
    public void deleteStatus(int id) throws SQLException {
        SQLite_OrderClient.deleteStatus(id);
    }
    
    public void deleteClient(int id) throws SQLException {
        SQLite_OrderClient.deleteClient(id);
    }
    
    public void deleteOrder(int id) throws SQLException {
        SQLite_OrderClient.deleteOrder(id);
    }
    
    public void deleteOrderStatus(int id) throws SQLException {
        SQLite_OrderClient.deleteOrderStatus(id);
    }
    
    public void insertIntoStatus(int id, String status) throws SQLException {
        SQLite_OrderClient.insertIntoStatus(id, status);
    }
    
    public void insertIntoClient(int id, String surname, String nameC, String telephone, String address) throws SQLException {
        SQLite_OrderClient.insertIntoClient(id, surname, nameC, telephone, address);
    }
    
    public void insertIntoOrder(int id, int id_client, String description) throws SQLException {
        SQLite_OrderClient.insertIntoOrder(id, id_client, description);
    }
    
    public void insertIntoOrderStatus(int id, int id_order, int id_status, Date dateS) throws SQLException {
        SQLite_OrderClient.insertIntoOrderStatus(id, id_order, id_status, dateS);
    }
    
    
    //OrderStaff
    
    public List<String []> selectAllStaff() throws SQLException {
        return SQLite_OrderStaff.selectAllStaff();
    }
    
    public List<String[]> selectAllServices() throws SQLException {
        return SQLite_OrderStaff.selectAllServices();
    }
    
    public List<String[]> selectAllPosts() throws SQLException {
        return SQLite_OrderStaff.selectAllPosts();
    }
    
    public List<String[]> selectStaffPost() throws SQLException {
        return SQLite_OrderStaff.selectStaffPost();
    }
    
    public List<String []> selectStaffByPost(int id_post) throws SQLException {
        return SQLite_OrderStaff.selectStaffByPost(id_post);
    }
    
    public List<String []> selectPostsOfStaff(int id_staff) throws SQLException {
        return SQLite_OrderStaff.selectPostsOfStaff(id_staff);
    }
    
    public List<String []> selectServicesOfOrder(int id_order) throws SQLException {
        return SQLite_OrderStaff.selectServicesOfOrder(id_order);
    }

    public List<String> selectStaffID() throws SQLException{
        return SQLite_OrderStaff.selectStaffID();
    }
    
    public void insertIntoPost(int id, String post) throws SQLException {
        SQLite_OrderStaff.insertIntoPost(id, post);
    }
    
    public void insertIntoStaff(int id, String surname, String nameS, String telephone, String address, Date birthday) throws SQLException {
        SQLite_OrderStaff.insertIntoStaff(id, surname, nameS, telephone, address, birthday);
    }
    
    public void insertIntoStaffPost(int id, int id_staff, int id_post, Date date_join) throws SQLException {
        SQLite_OrderStaff.insertIntoStaffPost(id, id_staff, id_post, date_join);
    }
    
    public void insertIntoService(int id, String description, int price) throws SQLException {
        SQLite_OrderStaff.insertIntoService(id, description, price);
    }
    
    public void insertIntoServiceOrder(int id, int id_service, int id_order, int id_staff) throws SQLException {
        SQLite_OrderStaff.insertIntoServiceOrder(id, id_service, id_order, id_staff);
    }
    
    public void deletePost(int id) throws SQLException {
        SQLite_OrderStaff.deletePost(id);
    }
    
    public void deleteStaff(int id) throws SQLException {
        SQLite_OrderStaff.deleteStaff(id);
    }
    
    public void deleteService(int id) throws SQLException {
        SQLite_OrderStaff.deleteService(id);
    }
    
    public void deleteStaffPost(int id) throws SQLException {
        SQLite_OrderStaff.deleteStaffPost(id);
    }
    
    public void deleteOrderService(int id) throws SQLException {
        SQLite_OrderStaff.deleteOrderService(id);
    }
    
    //OrderMaterial
    
    public List<String[]> selectMaterial() throws SQLException {
        return SQLite_OrderMaterial.selectMaterial();
    }
    
    public List<String> selectSheetsID() throws SQLException {
        return SQLite_OrderMaterial.selectSheetsID();
    }
    
    public List<String []> selectSheetsOfOrder(int id_order) throws SQLException {
        return SQLite_OrderMaterial.selectSheetsOfOrder(id_order);
    }
    
    public List<Details>[] selectDetailByMat(int idOrder) throws SQLException {
        return SQLite_OrderMaterial.selectDetailByMat(idOrder);
    }
    
    public void insertIntoMaterial(int id, String material) throws SQLException {
        SQLite_OrderMaterial.insertIntoMaterial(id, material);
    }
    
    public void insertIntoSheet(int id, int id_material, int lengthS, int widthS, int priceS) throws SQLException {
        SQLite_OrderMaterial.insertIntoSheet(id, id_material, lengthS, widthS, priceS);
    }
    
    public void insertIntoSheets(int id, int id_order, int id_sheet, int countS) throws SQLException {
        SQLite_OrderMaterial.insertIntoSheets(id, id_order, id_sheet, countS);
    }
    
    public void insertIntoDetail(int id, int id_sheets, int lengthD, int widthD, int countD, boolean isRotated) throws SQLException {
        SQLite_OrderMaterial.insertIntoDetail(id, id_sheets, lengthD, widthD, countD, isRotated);
    }
    
    public void deleteMaterial(int id) throws SQLException {
        SQLite_OrderMaterial.deleteMaterial(id);
    }
    
    public void deleteSheet(int id) throws SQLException {
        SQLite_OrderMaterial.deleteSheet(id);
    }
    
    public void deleteSheets(int id) throws SQLException {
        SQLite_OrderMaterial.deleteSheets(id);
    }
    
    public void deleteDetail(int id) throws SQLException {
        SQLite_OrderMaterial.deleteDetail(id);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    public void insertIntoOrders(String client, Date dateStart, Date dateFinish) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "INSERT INTO orders (client, dateStart, dateFinish) VALUES ('" + client + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(dateStart) + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFinish) + "');";
            System.out.println(sql);
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

    public void insertIntoMaterial(String description, int height, int width) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "INSERT INTO material (description, height, width) VALUES ('" + description + "', " + height + ", " + width + ");";
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

    /*public void insertIntoDetail(int id_orders, int id_material, int height, int width, int count, boolean isRotated) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "INSERT INTO detail (id_orders, id_material, height, width, count, isRotated) VALUES (" + id_orders + ", " + id_material + ", " + height + ", " + width + ", " + count + ", '" + isRotated + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }*/

    //SELECT

   /* public List<Material> selectMaterial() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Material> mats = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM material;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Material mat = new Material();
                mat.setId(rs.getInt("id"));
                mat.setDescription(rs.getString("description"));
                mat.setHeight(rs.getInt("height"));
                mat.setWidth(rs.getInt("width"));
                mats.add(mat);
            }
            rs.close();
            stmt.close();
            return mats;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }*/

    public Material selectMaterial(int id) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Details> dets = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM material WHERE id = " + id + ";";

            ResultSet rs = stmt.executeQuery(sql);

            Material mat = new Material();
            mat.setId(rs.getInt("id"));
            mat.setHeight(rs.getInt("height"));
            mat.setWidth(rs.getInt("width"));
            mat.setDescription(rs.getString("description"));
            
            rs.close();
            stmt.close();
            return mat;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    public List<Details> selectDetail(int id_orders, int id_material) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Details> dets = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, id_orders, id_material, height, width, count, "
                    + "Cast(isRotated as nvarchar(10)) FROM detail WHERE id_orders = " + id_orders
                    + " AND id_material = " + id_material +";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Details det = new Details();
                det.setId(rs.getInt("id"));
                det.setId_orders(rs.getInt("id_orders"));
                det.setId_material(rs.getInt("id_material"));
                det.setHeight(rs.getInt("height"));
                det.setWidth(rs.getInt("width"));
                det.setCount(rs.getInt("count"));
                if ((rs.getString("Cast(isRotated as nvarchar(10))")).equals("false"))
                    det.setRotated(false);
                else if ((rs.getString("Cast(isRotated as nvarchar(10))")).equals("true"))
                    det.setRotated(true);
                dets.add(det);
            }
            rs.close();
            stmt.close();
            return dets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }

    public List<Orders> selectOrders() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Orders> ords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM orders";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Orders ord = new Orders();
                ord.setId(rs.getInt("id"));
                ord.setClient(rs.getString("client"));
                ord.setDateStart(rs.getString("dateStart"));
                ord.setDateFinish(rs.getString("dateFinish"));
                ords.add(ord);
            }
            rs.close();
            stmt.close();
            return ords;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }

    //DELETE

    /*public void deleteMaterial(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM material WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }*/
    
    public void deleteOrders(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM orders WHERE id = " + id + ";";
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

    /*public void deleteDetail(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM detail WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }*/

    //UPDATE
/*
    public void updateMaterial(List<Integer> idList, List<Material> matList) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            for (int i = 0; i < idList.size(); i++) {
                String sql = "UPDATE material SET description = " + matList.get(i).getDescription() +
                        ", height = " + matList.get(i).getHeight() + ", width = " + matList.get(i).getWidth() +
                        " WHERE id = " + idList.get(i) + ";";
                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }

    public void updateOrders(List<Integer> idList, List<Orders> ordList) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            for (int i = 0; i < idList.size(); i++) {
                String sql = "UPDATE orders SET client = " + ordList.get(i).getClient() +
                        ", dateStart = " + ordList.get(i).getDateStart() + ", dateFinish = " + ordList.get(i).getDateFinish() +
                        " WHERE id = " + idList.get(i) + ";";
                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }

    public void updateDetail(List<Integer> idList, List<Details> detList) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            for (int i = 0; i < idList.size(); i++) {
                String sql = "UPDATE detail SET id_orders = " + detList.get(i).getId_orders() +
                        ", id_material = " + detList.get(i).getId_material() + ", height = " + detList.get(i).getHeight() +
                        ", width = " + detList.get(i).getWidth() + ", count = " + detList.get(i).getCount() + ", isRotated = '" + detList.get(i).getRotated() +
                        "' WHERE id = " + idList.get(i) + ";";
                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
    }
    
    public List<Integer> getUniqMat(int idOrder) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            
            List<Integer> uniqMat = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT DISTINCT id_material FROM detail WHERE id_orders = " + idOrder + ";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
                uniqMat.add(rs.getInt("id_material"));
            rs.close();
            stmt.close();
            return uniqMat;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }

    public List<Details>[] getDet(int idOrder) throws SQLException {
        Connection conn = null;
        List<Integer> listMatId = getUniqMat(idOrder);

        try{
            conn = openConnection();
            conn.setAutoCommit(false);

            ArrayList<Details>[] arrayOfArrayList = new ArrayList[listMatId.size()];
            
            Statement stmt = conn.createStatement();

            for (int i = 0; i < listMatId.size(); i++) {
                String sql = "SELECT id, id_orders, id_material, height, width, count, "
                    + "Cast(isRotated as nvarchar(10)) FROM detail WHERE id_orders = " + idOrder
                    + " AND id_material = " + listMatId.get(i) +";";
                
                ResultSet rs = stmt.executeQuery(sql);
                arrayOfArrayList[i] = new ArrayList();
                
                while (rs.next()) {
                    Details det = new Details();
                    det.setId(rs.getInt("id"));
                    det.setId_orders(rs.getInt("id_orders"));
                    det.setId_material(rs.getInt("id_material"));
                    det.setHeight(rs.getInt("height"));
                    det.setWidth(rs.getInt("width"));
                    det.setCount(rs.getInt("count"));
                    if ((rs.getString("Cast(isRotated as nvarchar(10))")).equals("false"))
                        det.setRotated(false);
                    else if ((rs.getString("Cast(isRotated as nvarchar(10))")).equals("true"))
                        det.setRotated(true);
                  
                    arrayOfArrayList[i].add(det);
                }
                rs.close();
            }
                stmt.close();
            return arrayOfArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
*/
}
