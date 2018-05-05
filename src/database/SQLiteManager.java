package database;

import entities.Detail;
import entities.Material;
import entities.Orders;
import javafx.scene.transform.MatrixType;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class SQLiteManager {

    //I'm here!!!
    //I'm glad!!!

    private Connection openConnection(){

        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:sqlite/databases/notbestcut.sqlite3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //INSERT

    public void insertIntoOrders(String client, String dateStart, String dateFinish) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "INSERT INTO orders (client, dateStart, dateFinish) VALUES (" + client + ", " + dateStart + ", " + dateFinish + ");";
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

    public void insertIntoMaterial(String description, String height, String width) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "INSERT INTO material (description, height, width) VALUES (" + description + ", " + height + ", " + width + ");";
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

    public void insertIntoDetail(String id_orders, String id_material, String height, String width, String count, String isRotated) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "INSERT INTO detail (id_orders, id_material, height, width, count, isRotated) VALUES (" + id_orders + ", " + id_material + ", " + height + ", " + width + ", " + count + ", " + isRotated + ");";
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

    //SELECT

    public List<Material> selectMaterial() throws SQLException {
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
    }

    public List<Detail> selectDetail(int id_orders, int id_material) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Detail> dets = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM detail WHERE id_orders = " + id_orders +" AND id_material = " + id_material +";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Detail det = new Detail();
                det.setId(rs.getInt("id"));
                det.setId_orders(rs.getInt("id_orders"));
                det.setId_material(rs.getInt("id_material"));
                det.setHeight(rs.getInt("height"));
                det.setWidth(rs.getInt("width"));
                det.setCount(rs.getInt("count"));
                det.setRotated(rs.getBoolean("isRotated"));
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

    public void deleteMaterial(int id) throws SQLException {
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
    }

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

    public void deleteDetail(int id) throws SQLException {
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
    }

    //UPDATE

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

    public void updateDetail(List<Integer> idList, List<Detail> detList) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            for (int i = 0; i < idList.size(); i++) {
                String sql = "UPDATE detail SET id_orders = " + detList.get(i).getId_orders() +
                        ", id_material = " + detList.get(i).getId_material() + ", height = " + detList.get(i).getHeight() +
                        ", width = " + detList.get(i).getWidth() + ", count = " + detList.get(i).getCount() + ", isRotated = " + detList.get(i).getRotated() +
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
}
