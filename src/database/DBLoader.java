package database;

import entities.Detail;
import entities.Material;
import entities.Orders;

import java.sql.SQLException;
import java.util.List;

public class DBLoader {

    private SQLiteManager man = new SQLiteManager();

    private void loadMaterialToDB(List<Material> matList, List<Integer> idUpdatedList, List<Integer> idDeletedList, int countOfInserted) throws SQLException {

        if (idUpdatedList != null)
            man.updateMaterial(idUpdatedList, matList);

        if (idDeletedList != null){
            for (int i = 0; i < idDeletedList.size(); i++)
                man.deleteMaterial(idDeletedList.get(i));
        }

        if (countOfInserted != 0){
            for (int i = matList.size() - countOfInserted; i < matList.size(); i++)
                man.insertIntoMaterial(matList.get(i).getDescription(), matList.get(i).getHeight(), matList.get(i).getWidth());
        }
    }

    private void loadOrdersToDB(List<Orders> ordList, List<Integer> idUpdatedList, List<Integer> idDeletedList, int countOfInserted) throws SQLException {

        if (idUpdatedList != null)
            man.updateOrders(idUpdatedList, ordList);

        if (idDeletedList != null){
            for (int i = 0; i < idDeletedList.size(); i++)
                man.deleteOrders(idDeletedList.get(i));
        }

        if (countOfInserted != 0){
            for (int i = ordList.size() - countOfInserted; i < ordList.size(); i++)
                man.insertIntoOrders(ordList.get(i).getClient(), ordList.get(i).getDateStart(), ordList.get(i).getDateFinish());
        }
    }

    private void loadDetailToDB(List<Detail> detList, List<Integer> idUpdatedList, List<Integer> idDeletedList, int countOfInserted) throws SQLException {

        if (idUpdatedList != null)
            man.updateDetail(idUpdatedList, detList);

        if (idDeletedList != null){
            for (int i = 0; i < idDeletedList.size(); i++)
                man.deleteDetail(idDeletedList.get(i));
        }

        if (countOfInserted != 0){
            for (int i = detList.size() - countOfInserted; i < detList.size(); i++)
                man.insertIntoDetail(detList.get(i).getId_orders(), detList.get(i).getId_material(), detList.get(i).getHeight(), detList.get(i).getWidth(), detList.get(i).getCount(), detList.get(i).getRotated());
        }
    }

    public void loadToDB(List<Orders> ordList, List<Integer> idUpdOrd, List<Integer> idDelOrd, int countOfInsOrd,
                         List<Material> matList, List<Integer> idUpdMat, List<Integer> idDelMat, int countOfInsMat,
                         List<Detail> detList, List<Integer> idUpdDet, List<Integer> idDelDet, int countOfInsDet) throws SQLException {

        loadOrdersToDB(ordList, idUpdOrd, idDelOrd, countOfInsOrd);
        loadMaterialToDB(matList, idUpdMat, idDelMat, countOfInsMat);
        loadDetailToDB(detList, idUpdDet, idDelDet, countOfInsDet);
    }
}
