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

    public List<Material> selectMaterial(String description, int height, int width) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Material> mats = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT description, height, width FROM material";

            boolean prev = false;
            if ((description == null) && (height == -1) && (width == -1))
                sql = sql + ";";
            else{
                sql = sql + " WHERE ";
                if (description != null) {
                    sql = sql + "description = " + description;
                    prev = true;
                }
                if (height != -1) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "height = " + height;
                    prev = true;
                }
                if (width != -1) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "width = " + width;
                }
                sql = sql + ";";
            }
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Material mat = new Material();
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

    public List<Detail> selectDetail(int id_orders, int id_material, int height, int width, int count, boolean isRotated) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Detail> dets = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT id_orders, id_material, height, width, count, isRotated FROM detail";

            boolean prev = false;
            if ((id_orders == -1) && (id_material == -1) && (height == -1) && (width == -1) && (count == -1) && (isRotated == false))
                sql = sql + ";";
            else{
                sql = sql + " WHERE ";
                if (id_orders != -1) {
                    sql = sql + "id_orders = " + id_orders;
                    prev = true;
                }
                if (id_material != -1) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "id_material = " + id_material;
                    prev = true;
                }
                if (height != -1) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "height = " + height;
                    prev = true;
                }
                if (width != -1) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "width = " + width;
                    prev = true;
                }
                if (count != -1) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "count = " + count;
                    prev = true;
                }
                if (prev)
                    sql = sql + " AND ";
                sql = sql + "isRotated = " + isRotated;

                sql = sql + ";";
            }
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Detail det = new Detail();
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

    public List<Orders> selectOrders(String client, Date dateStart, Date dateFinish) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Orders> ords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT client, dateStart, dateFinish FROM orders";

            boolean prev = false;
            if ((client == null) && (dateStart == null) && (dateFinish == null))
                sql = sql + ";";
            else{
                sql = sql + " WHERE ";
                if (client != null) {
                    sql = sql + "client = " + client;
                    prev = true;
                }
                if (dateStart != null) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "dateStart = " + new SimpleDateFormat("yyyy-MM-dd").format(dateStart).toString();
                    prev = true;
                }
                if (dateFinish != null) {
                    if (prev)
                        sql = sql + " AND ";
                    sql = sql + "dateFinish = " + new SimpleDateFormat("yyyy-MM-dd").format(dateFinish).toString();
                }
                sql = sql + ";";
            }
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Orders ord = new Orders();
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
    /*

    public int countPosts() throws SQLException {
        int postNumber = 0;
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(("SELECT COUNT (*) AS number FROM posts;"));
            postNumber = rs.next()?rs.getInt("number"):0;
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return postNumber;
    }*/
}
