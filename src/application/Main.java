package application;

import dao.DaoFactory;
import dao.SellerDao;
import entities.Department;
import entities.Seller;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(sellerDao.findById(3));

        Department department = new Department(2, null);
        System.out.println(sellerDao.findByDepartment(department));

        System.out.println(sellerDao.findAll());

        Seller seller = new Seller(null, "Leonardo", "leonardo@email.com",
                LocalDate.now(), 3807.00, department);
        sellerDao.insert(seller);
        System.out.println("Inserted! New id = " + seller.getId());

        seller = sellerDao.findById(1);
        seller.setName("Marta Wayne");
        sellerDao.update(seller);
        System.out.println("Update completed");
    }
}
