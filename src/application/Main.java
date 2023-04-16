package application;

import dao.DaoFactory;
import dao.SellerDao;
import entities.Department;
import entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(3));

        Department department = new Department(2, null);
        System.out.println(sellerDao.findByDepartment(department));

        System.out.println(sellerDao.findAll());
    }
}
