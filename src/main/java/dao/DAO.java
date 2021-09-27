package dao;


import entity.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothes", "root", "tung03102001");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public List<Product> getAll() {
        String query = "select * from product";
        List<Product> list = new ArrayList<>();
        try {
            conn = new DAO().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public Product getProduct(String txt) {
        String query = "select * from product where id = ?";
        List<Product> list = new ArrayList<>();
        try {
            conn = new DAO().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, txt);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        1);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
//    create table product
//        (
//                id          int auto_increment
//                primary key,
//                name        varchar(100) charset utf8  null,
//        image       varchar(1000) charset utf8 null,
//        price       int                        null,
//        title       varchar(50) charset utf8   null,
//        description varchar(1000) charset utf8 null,
//        cateID      int                        null,
//        sell_ID     int                        null
//        );
