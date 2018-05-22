package database;

import entities.Client;
import entities.Order;
import entities.Status;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLite_OrderClient 
{
    private static Connection openConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:sqlite/databases/justCut.sqlite3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    protected static List<Status> selectAllStatuses() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Status> stats = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Status;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Status stat = new Status();
                stat.setId(rs.getInt("id"));
                stat.setDescription(rs.getString("description"));
                stats.add(stat);
            }
            rs.close();
            stmt.close();
            return stats;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<Order> selectAllOrdersOfOneClient(int id_client) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Order> ords = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT a.id AS id, a.surname AS surname, a.description AS description, b.description AS status FROM (SELECT ord.id as id, cln.id as id_client, cln.surname, ord.description\n" +
                    "FROM \"Order\" As ord INNER JOIN Client AS cln ON ord.id_client = cln.id\n" +
                    "WHERE id_client = " + id_client +") AS a INNER JOIN\n" +
                    "(SELECT ordStat.id_order, stat.description, MAX(ordStat.id) FROM\n" +
                    "OrderStatus as ordStat INNER JOIN Status as stat on ordStat.id_status = stat.id\n" +
                    "GROUP BY ordStat.id_order) AS b ON\n" +
                    "a.id = b.id_order;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Order ord = new Order();
                ord.setID(Integer.parseInt(rs.getString("id")));
                ord.setSurname(rs.getString("surname"));
                ord.setDescription(rs.getString("description"));
                ord.setStatus(rs.getString("status"));
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
    
    protected static List<Order> selectAllOrdersByStatus(int id_status) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Order> ords = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT a.id AS id, a.surname AS surname, a.description AS description, b.description AS status , b.id AS id_status FROM (SELECT ord.id as id, cln.surname, ord.description\n" +
                    "FROM \"Order\" As ord INNER JOIN Client AS cln ON ord.id_client = cln.id) AS a INNER JOIN\n" +
                    "(SELECT ordStat.id_order, stat.id, stat.description, MAX(ordStat.id) FROM\n" +
                    "(OrderStatus as ordStat INNER JOIN Status as stat on ordStat.id_status = stat.id)\n" +
                    "GROUP BY ordStat.id_order) AS b ON a.id = b.id_order\n" +
                    "WHERE id_status = " + id_status +";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Order ord = new Order();
                ord.setID(Integer.parseInt(rs.getString("id")));
                ord.setSurname(rs.getString("surname"));
                ord.setDescription(rs.getString("description"));
                ord.setStatus(rs.getString("status"));
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
    
    protected static List<Order> selectAllOrdersByDateStatus(String dateFrom, String dateTo) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Order> ords = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT a.id AS id, a.surname AS surname, a.description AS description, b.description AS status , b.id AS id_status FROM (SELECT ord.id as id, cln.surname, ord.description\n" +
                    "FROM \"Order\" As ord INNER JOIN Client AS cln ON ord.id_client = cln.id) AS a INNER JOIN\n" +
                    "(SELECT ordStat.id_order, stat.id, stat.description, MAX(ordStat.id) FROM\n" +
                    "(OrderStatus as ordStat INNER JOIN Status as stat on ordStat.id_status = stat.id)\n" +
                    "WHERE EXISTS(SELECT OrderStatus.id From OrderStatus WHERE (OrderStatus.dateS <= '" + dateTo + "') AND (OrderStatus.dateS >= '"+ dateFrom +"'))\n" +
                    "GROUP BY ordStat.id_order) AS b ON a.id = b.id_order;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Order ord = new Order();
                ord.setID(Integer.parseInt(rs.getString("id")));
                ord.setSurname(rs.getString("surname"));
                ord.setDescription(rs.getString("description"));
                ord.setStatus(rs.getString("status"));
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
    
    protected static List<Integer> selectAllClientsId() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Integer> ids = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM Client;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                ids.add(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }

    protected static List<Client> selectAllClientsInfo() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Client> clients = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Client;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Client cl = new Client();
                cl.setID(Integer.parseInt(rs.getString("id")));
                cl.setSurname(rs.getString("surname"));
                cl.setNameC(rs.getString("nameC"));
                cl.setTelephone(rs.getString("telephone"));
                cl.setAddress(rs.getString("address"));
                clients.add(cl);
            }
            rs.close();
            stmt.close();
            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<Integer> selectAllOrdersID() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Integer> ordsID = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM 'Order';";

            ResultSet rs = stmt.executeQuery(sql);
            
            int id;
            while(rs.next()){
                id = rs.getInt("id");
                ordsID.add(id);
            }
            rs.close();
            stmt.close();
            return ordsID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<Order> selectAllOrders() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Order> ords = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT a.id AS id, a.surname AS surname, a.description AS description, b.description AS status FROM \n" +
                    "(SELECT ord.id as id, cln.surname, ord.description\n" +
                    "FROM \"Order\" As ord INNER JOIN Client AS cln ON ord.id_client = cln.id) AS a INNER JOIN\n" +
                    "(SELECT ordStat.id_order, stat.description, MAX(ordStat.id) FROM\n" +
                    "OrderStatus as ordStat INNER JOIN Status as stat on ordStat.id_status = stat.id\n" +
                    "GROUP BY ordStat.id_order) AS b ON\n" +
                    "a.id = b.id_order;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Order ord = new Order();
                ord.setID(Integer.parseInt(rs.getString("id")));
                ord.setSurname(rs.getString("surname"));
                ord.setDescription(rs.getString("description"));
                ord.setStatus(rs.getString("status"));
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
    
    //Статусы заказов по датам
    protected static List<String []> selectOrdersDateDescript(int id_order) throws SQLException {
        Connection conn = null;
            
        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String[]> dateDescs = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT OrderStatus.id, OrderStatus.dateS, Status.description From OrderStatus INNER JOIN Status ON OrderStatus.id_status = Status.id WHERE OrderStatus.id_order = " + id_order +";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] dateDesc = new String[3];
                dateDesc[0] = rs.getString("id");
                dateDesc[1] = rs.getString("dateS");
                dateDesc[2] = rs.getString("description");
                dateDescs.add(dateDesc);
            }
            rs.close();
            stmt.close();
            return dateDescs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
        //INSERTS
    
    protected static void insertIntoStatus(int id, String status) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0) {
                sql = "INSERT INTO Status (description) VALUES ('" + status + "');";
            }
            else{
                sql = "UPDATE Status SET description = '" + status + "' WHERE id = " + id + ";";
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
    
    protected static void insertIntoClient(int id, String surname, String nameC, String telephone, String address) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0) {
                sql = "INSERT INTO Client (surname, nameC, telephone, address) VALUES ('" + surname + "', '" + nameC + "', '" + telephone + "', '" + address + "');";
            }
            else{
                sql = "UPDATE Client SET surname = '" + surname + "', nameC = '" + nameC + "', telephone = '" + telephone + "', address = '" + address + "' WHERE id = " + id + ";";
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
    
    protected static void insertIntoOrder(int id, int id_client, String description) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0) {
                sql = "INSERT INTO 'Order' (id_client, description) VALUES (" + id_client + ", '" + description + "');";
            }
            else{
                sql = "UPDATE 'Order' SET id_client = " + id_client + ", description = '" + description + "' WHERE id = " + id + ";";
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
    
    protected static void insertIntoOrderStatus(int id, int id_order, int id_status, Date dateS) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0) {
                sql = "INSERT INTO OrderStatus (id_order, id_status, dateS) VALUES (" + id_order + ", " + id_status + ", '" + new SimpleDateFormat("yyyy-MM-dd").format(dateS) + "');";
            }
            else{
                sql = "UPDATE OrderStatus SET id_order = " + id_order + ", id_status = " + id_status + ", dateS = '" + new SimpleDateFormat("yyyy-MM-dd").format(dateS) + "' WHERE id = " + id + ";";
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
    
    //DELETE
    
    protected static void deleteStatus(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Status WHERE id = " + id + ";";
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
    
    protected static void deleteClient(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Client WHERE id = " + id + ";";
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
    
    protected static void deleteOrder(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Order WHERE id = " + id + ";";
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
    
    protected static void deleteOrderStatus(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM OrderStatus WHERE id = " + id + ";";
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
