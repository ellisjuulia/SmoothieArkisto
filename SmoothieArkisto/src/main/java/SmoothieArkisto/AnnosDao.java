package SmoothieArkisto;

import java.util.*;
import java.sql.*;

public class AnnosDao implements Dao<Annos, Integer> {
    
    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }
    
    @Override
    public Annos findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Annos annos = new Annos(rs.getInt("id"), rs.getString("nimi"));
  
        stmt.close();
        rs.close();

        connection.close();

        return annos;
    }

    @Override
    public List<Annos> findAll() throws SQLException {
        
	List<Annos> annokset = new ArrayList<>();
	Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Annos");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Annos a = new Annos(rs.getInt("id"), rs.getString("nimi"));
  
            annokset.add(a);
        }

        stmt.close();
        rs.close();

        conn.close();
        return annokset;
    }

    @Override
    public Annos saveOrUpdate(Annos object) throws SQLException {
        Annos byName = findByName(object.getNimi());

        if (byName != null) {
            return byName;
        } 

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Annos (id, nimi) VALUES (?, ?)");
            stmt.setInt(1, object.getId());
            stmt.setString(2, object.getNimi());
            stmt.executeUpdate();
        }

        return findByName(object.getNimi());
    }
    
    private Annos findByName(String nimi) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, nimi FROM Annos WHERE nimi = ?");
            stmt.setString(1, nimi);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Annos(result.getInt("id"), result.getString("nimi"));
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
