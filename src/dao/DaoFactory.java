package dao;

public class DaoFactory {
    private DaoFactory() {
    }

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC();
    }
}
