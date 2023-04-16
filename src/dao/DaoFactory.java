package dao;

import database.DB;

public class DaoFactory {
    private DaoFactory() {
    }

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
