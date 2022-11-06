package si.fri.prpo;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UporabnikDaoImpl implements BaseDao {
    private static UporabnikDaoImpl singleton = null;
    private Connection con = null;

    private UporabnikDaoImpl(){
        if(con == null)
            con = getConnection();
    }

    public static UporabnikDaoImpl getInstance(){
        if(singleton == null)
            singleton = new UporabnikDaoImpl();

        return singleton;
    }

    @Override
    public Connection getConnection() {
        try{
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            return ds.getConnection();
        } catch (SQLException e) {
            System.out.println("SQLEXception alert");
        }
        catch(NamingException e){
            System.out.println("NamingException alert");
        }
        return null;
    }

    @Override
    public Entiteta vrni(int id) {

        PreparedStatement ps = null;
        try {

            if (singleton.con == null) {
                singleton.con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik WHERE id = ?";
            ps = singleton.con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getUporabnikFromRS(rs);
            }else{
                //log.info("Ne vraca uporabnikov");
            }

        } catch (SQLException e) {
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }

        return null;
    }

    @Override
    public void vstavi(Entiteta ent) {

        Uporabnik u = (Uporabnik)ent;
        PreparedStatement ps = null;
        try {

            if (singleton.con == null) {
                singleton.con = getConnection();
            }

            String sql = "INSERT INTO uporabnik (ime, priimek, uporabnisko_ime) VALUES (?, ?, ?)";
            ps = singleton.con.prepareStatement(sql);
            ps.setString(1, u.getIme());
            ps.setString(2, u.getPriimek());
            ps.setString(3, u.getUporabnisko());
            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    @Override
    public void odstrani(int id){
        PreparedStatement ps = null;
        try {

            if (singleton.con == null) {
                singleton.con = getConnection();
            }

            String sql = "DELETE FROM uporabnik WHERE id = ?;";
            ps = singleton.con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    @Override
    public void posodobi(Entiteta ent){
        Uporabnik u = (Uporabnik)ent;
        PreparedStatement ps = null;
        try {

            if (singleton.con == null) {
                singleton.con = getConnection();
            }

            String sql = "UPDATE uporabnik SET ime = ?, priimek = ?, uporabnisko_ime = ? WHERE id = ?";
            ps = singleton.con.prepareStatement(sql);
            ps.setString(1, u.getIme());
            ps.setString(2, u.getPriimek());
            ps.setString(3, u.getUporabnisko());
            ps.setInt(4, ent.getId());

            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    @Override
    public List<Entiteta> vrniVse(){
        ArrayList<Entiteta> uporabniki = new ArrayList<>();
        PreparedStatement ps = null;
        try {

            if (singleton.con == null) {
                singleton.con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik";
            ps = singleton.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                uporabniki.add(getUporabnikFromRS(rs));
            }
            return uporabniki;

        } catch (SQLException e) {
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }

        return null;
    }

    private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException {
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        String uporabniskoIme = rs.getString("uporabnisko_ime");
        return new Uporabnik(ime, priimek, uporabniskoIme);
    }


}
