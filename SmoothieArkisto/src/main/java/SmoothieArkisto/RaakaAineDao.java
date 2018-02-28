package SmoothieArkisto;

import java.util.*;
import java.sql.*;

public class RaakaAineDao implements Dao<RaakaAine, Integer> {
    
    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }
    
    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        RaakaAine raakaAine = new RaakaAine(rs.getInt("id"), rs.getString("nimi"));
  
        stmt.close();
        rs.close();

        connection.close();

        return raakaAine;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {
        
	List<RaakaAine> raakaAineet = new ArrayList<>();
	Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM RaakaAine");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            RaakaAine r = new RaakaAine(rs.getInt("id"), rs.getString("nimi"));
  
            raakaAineet.add(r);
        }

        stmt.close();
        rs.close();

        conn.close();
        return raakaAineet;
    }
    
    @Override
    public RaakaAine saveOrUpdate(RaakaAine object) throws SQLException {
        
        RaakaAine byName = findByName(object.getNimi());

        if (byName != null) {
            return byName;
        } 

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO RaakaAine (id, nimi) VALUES (?, ?)");
            stmt.setInt(1, object.getId());
            stmt.setString(2, object.getNimi());
            stmt.executeUpdate();
        }

        return findByName(object.getNimi());
    }

    private RaakaAine findByName(String nimi) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, nimi FROM RaakaAine WHERE nimi = ?");
            stmt.setString(1, nimi);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new RaakaAine(result.getInt("id"), result.getString("nimi"));
        }
    }
  
    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Annos WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }
    
}
