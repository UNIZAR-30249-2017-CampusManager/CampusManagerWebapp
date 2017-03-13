package es.unizar.campusManager.repository;

import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import es.unizar.campusManager.model.*;

import java.sql.*;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {

    private static final RowMapper<User> rowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("EMAIL"),rs.getString("NAME"));
        }
    };

    @Autowired
    protected JdbcTemplate jdbc;

    public UserRepositoryImp() {

    }

    public UserRepositoryImp(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public User findByEmail(String email) {
        try {
            List<User> l = jdbc.query("SELECT * FROM CAMPUS_USER WHERE EMAIL=?",
                    new Object[] { email }, rowMapper);
            if(l.size()==1){
                return l.get(0);
            }else{
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }


    public User save(User u) {
        try {
            jdbc.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection conn)
                        throws SQLException {
                    PreparedStatement ps = conn
                            .prepareStatement(
                                    "INSERT INTO CAMPUS_USER VALUES (?, ?)",
                                    Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, u.getEmail());
                    ps.setString(2, u.getName());
                    return ps;
                }
            });
        } catch (DuplicateKeyException e) {
            //log.debug("When insert for click with id " + cl.getId(), e);
            return null;
        } catch (Exception e) {
            //log.debug("When insert a click", e);
            return null;
        }
        return u;
    }

}
