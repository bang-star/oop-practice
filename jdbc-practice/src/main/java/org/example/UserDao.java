package org.example;

import java.sql.*;

public class UserDao {

    // AS-IS
//    public void create(User user) throws SQLException{
//        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        try{
//            con = ConnectionManager.getConnection();
//            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";   // 변경될 수 있는 요소
//
//            pstmt = con.prepareStatement(sql);
//
//            pstmt.setString(1, user.getUserId());
//            pstmt.setString(2, user.getPassword());
//            pstmt.setString(3, user.getName());
//            pstmt.setString(4, user.getEmail());
//
//            pstmt.executeUpdate();
//        }finally {
//            if (pstmt != null)
//                pstmt.close();
//
//            if (con != null)
//                con.close();
//        }
//    }

    // TO-BE
    public void create(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(user, sql, pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    // AS-IS
//    public User findByUserId(String userId) throws SQLException {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectionManager.getConnection();
//            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid = ?";
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, userId);
//            rs = pstmt.executeQuery();
//
//            User user = null;
//            if (rs.next()) {
//                user = new User(
//                        rs.getString("userId"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email"));
//            }
//
//            return user;
//        } finally {
//            if (rs != null)
//                rs.close();
//
//            if (pstmt != null)
//                pstmt.close();
//
//            if (con != null)
//                con.close();
//        }
//    }

    // TO-BE : 변경되는 부분은 API 호출자 입장에서 전달하면 된다.
    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid = ?";

        return (User) jdbcTemplate.executeQuery(sql,
                pstmt -> pstmt.setString(1, userId),

                resultSet -> new User(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                ));
    }
}
