package database;

import entities.Details;
import entities.Status;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLite_OrderMaterial {
    private static Connection openConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:sqlite/databases/justCut.sqlite3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    protected static List<String> selectSheetsID() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String> sheetsID = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM Sheet;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String sheetId = new String();
                sheetId = rs.getString("id");
                sheetsID.add(sheetId);
            }
            rs.close();
            stmt.close();
            return sheetsID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String[]> selectMaterial() throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String[]> materials = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Material;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] mater = new String[2];
                mater[0] = rs.getString("id");
                mater[1] = rs.getString("description");
                materials.add(mater);
            }
            rs.close();
            stmt.close();
            return materials;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<String []> selectSheetsOfOrder(int id_order) throws SQLException { // Листы заказа
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            List<String []> sheetsOfOrders = new ArrayList<String[]>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT Sheets.id AS id, Material.Description AS description, Sheet.WidthS || \"x\" || Sheet.LengthS AS " +
                    "Dimension, Sheets.countS AS countS, (Sheets.countS*Sheet.priceS) " +
                    " AS Price FROM Sheets INNER JOIN " +
                    "(Sheet INNER JOIN Material ON Sheet.id_material = Material.id) ON " +
                    "Sheets.id_sheet = Sheet.id WHERE Sheets.id_order =" + id_order + ";";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String[] sheetsOfOrder = new String[5];
                sheetsOfOrder[0] = rs.getString("id");
                sheetsOfOrder[1] = rs.getString("description");
                sheetsOfOrder[2] = rs.getString("Dimension");
                sheetsOfOrder[3] = rs.getString("countS");
                sheetsOfOrder[4] = rs.getString("Price");
                sheetsOfOrders.add(sheetsOfOrder);
            }
            rs.close();
            stmt.close();
            return sheetsOfOrders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return null;
    }
    
    protected static List<Integer> getUniqMat(int idOrder) throws SQLException {
        Connection conn = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);

            List<Integer> uniqMat = new ArrayList();
            Statement stmt = conn.createStatement();
            String sql = "SELECT DISTINCT id_material FROM Sheet Inner Join (Detail INNER Join Sheets On Detail.id_sheets = Sheets.id) AS a ON Sheet.id = a.id_sheet\n" +
                    "WHERE id_order = " + idOrder + ";";

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

    protected static List<Details>[] selectDetailByMat(int idOrder) throws SQLException {
        Connection conn = null;
        List<Integer> listMatId = getUniqMat(idOrder);

        try{
            conn = openConnection();
            conn.setAutoCommit(false);

            ArrayList<Details>[] arrayOfArrayList = new ArrayList[listMatId.size()];

            Statement stmt = conn.createStatement();

            for (int i = 0; i < listMatId.size(); i++) {
                String sql = "SELECT Detail.id, Detail.lengthD, Detail.widthD, Detail.countD, "
                        + "Cast(Detail.isRotated as nvarchar(10)) FROM Sheet INNER JOIN (Detail INNER JOIN Sheets On Detail.id_sheets = Sheets.id) AS a ON Sheet.id = a.id_sheet WHERE id_order = " + idOrder
                        + " AND id_material = " + listMatId.get(i) +";";

                ResultSet rs = stmt.executeQuery(sql);
                arrayOfArrayList[i] = new ArrayList();

                while (rs.next()) {
                    Details det = new Details();
                    det.setId(rs.getInt("id"));
                    det.setHeight(rs.getInt("lengthD"));
                    det.setWidth(rs.getInt("widthD"));
                    det.setCount(rs.getInt("countD"));
                    if ((rs.getString("Cast(Detail.isRotated as nvarchar(10))")).equals("false"))
                        det.setRotated(false);
                    else if ((rs.getString("Cast(Detail.isRotated as nvarchar(10))")).equals("true"))
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
    
    protected static void insertIntoMaterial(int id, String material) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO Material (description) VALUES ('" + material +"');";
            }
            else{
                sql = "UPDATE Material SET description = '" + material + "' WHERE id = " + id + ";";
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
    
    protected static void insertIntoSheet(int id, int id_material, int lengthS, int widthS, int priceS) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO Sheet (id_material, lengthS, widthS, priceS) VALUES (" + id_material + ", " + lengthS + ", " + widthS + ", " + priceS +");";
            }
            else{
                sql = "UPDATE Sheet SET id_material = " + id_material + ", lengthS = " + lengthS + ", widthS = " + widthS + ", prices = " + priceS + " WHERE id = " + id + ";";
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
    
    protected static void insertIntoSheets(int id, int id_order, int id_sheet, int countS) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO Sheets (id_order, id_sheet, countS) VALUES (" + id_order + ", " + id_sheet + ", " + countS + ");";
            }
            else{
                sql = "UPDATE Sheets SET id_order = " + id_order + ", id_sheet = " + id_sheet + ", countS = " + countS + " WHERE id = " + id + ";";
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

    protected static void insertIntoDetail(int id, int id_sheets, int lengthD, int widthD, int countD, boolean isRotated) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql;

            if (id < 0){
                sql = "INSERT INTO Detail (id_sheets, lengthD, widthD, countD, isRotated) VALUES (" + id_sheets + ", " + lengthD + ", " + widthD + ", " + countD + ", '" + isRotated +"');";
            }
            else{
                sql = "UPDATE Detail SET id_sheets = " + id_sheets + ", lengthD = " + lengthD + ", widthD = " + widthD + ", countD = " + countD + ", isRotated = '" + isRotated + "' WHERE id = " + id + ";";
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
    
    protected static void deleteMaterial(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Material WHERE id = " + id + ";";
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
    
    protected static void deleteSheet(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Sheet WHERE id = " + id + ";";
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
    
    protected static void deleteSheets(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Sheets WHERE id = " + id + ";";
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
    
    protected static void deleteDetail(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = openConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String sql = "DELETE FROM Detail WHERE id = " + id + ";";
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
