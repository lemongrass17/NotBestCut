package database;

import java.sql.*;

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

    /*
    public List<Post> selectPosts(int pageNumber, int postsPerPage) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<Post> notes = new ArrayList<Post>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM posts ORDER BY post_id DESC LIMIT " + postsPerPage + " OFFSET "
                    + (pageNumber - 1)*postsPerPage + ";");

            while(rs.next()){
                Post note = new Post();
                note.setDate(rs.getString("publish_date"));
                note.setUsername(rs.getString("username"));
                note.setText(rs.getString("post_text"));
                notes.add(note);
            }
            rs.close();
            stmt.close();
            return notes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }

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
