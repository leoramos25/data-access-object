package application;

import dao.DaoFactory;
import dao.SellerDao;
import entities.Department;
import entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        Department department = new Department(2, null);
        List<Seller> sellers = sellerDao.findByDepartment(department);
        System.out.println(sellers);
    }
}
