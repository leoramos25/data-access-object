package dao;

import database.DB;
import database.DbException;
import entities.Department;
import entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    public static final String FIND_BY_ID =
            "SELECT seller.*, department.name as DepName FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id WHERE seller.Id = ?";

    public static final String FIND_BY_DEPARTMENT =
            "SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name";
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, dep);
                return seller;
            }
            return null;
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(FIND_BY_DEPARTMENT);
            ps.setInt(1, department.getId());
            rs = ps.executeQuery();
            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();
            while (rs.next()) {
                Department dep = departmentMap.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    departmentMap.put(rs.getInt("DepartmentId"), dep);
                }
                Seller seller = instantiateSeller(rs, dep);
                sellers.add(seller);
            }
            return sellers;
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        seller.setDepartment(dep);
        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
