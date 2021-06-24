/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserDB {

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user";

        try {
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);

                User user = new User(email, active, firstName, lastName, password, role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }

//    public User get(String email) throws Exception {
//        User note = null;
//        ConnectionPool conPool = ConnectionPool.getInstance();
//        Connection con = conPool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "SELECT * FROM note WHERE note_id=?";
//
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, noteId);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                String email = rs.getString(1);
//                boolean active = rs.getBoolean(2);
//                String firstName = rs.getString(3);
//                String lastName = rs.getString(4);
//                String password = rs.getString(5);
//                int role = rs.getInt(6);
//
//                User user = new User(email, active, firstName, lastName, password, role);
//            }
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            conPool.freeConnection(con);
//        }
//
//        return note;
//    }

//    public void insert(User user) throws Exception {
//        ConnectionPool cp = ConnectionPool.getInstance();
//        Connection con = cp.getConnection();
//        PreparedStatement ps = null;
//        String sql = "INSERT INTO note (title, contents, owner) VALUES (?, ?, ?)";
//
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, user.getTitle());
//            ps.setString(2, user.getContents());
//            ps.setString(3, user.getOwner());
//            ps.executeUpdate();
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            cp.freeConnection(con);
//        }
//    }

//    public void update(User note) throws Exception {
//        ConnectionPool cp = ConnectionPool.getInstance();
//        Connection con = cp.getConnection();
//        PreparedStatement ps = null;
//        String sql = "UPDATE note SET title=?, contents=? WHERE note_id=?";
//
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, note.getTitle());
//            ps.setString(2, note.getContents());
//            ps.setInt(3, note.getNoteId());
//            ps.executeUpdate();
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            cp.freeConnection(con);
//        }
//    }
//
//    public void delete(User user) throws Exception {
//        ConnectionPool cp = ConnectionPool.getInstance();
//        Connection con = cp.getConnection();
//        PreparedStatement ps = null;
//        String sql = "DELETE FROM note WHERE note_id=?";
//
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, note.getNoteId());
//            ps.executeUpdate();
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            cp.freeConnection(con);
//        }
//    }
}