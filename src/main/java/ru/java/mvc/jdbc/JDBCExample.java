package ru.java.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.java.mvc.bean.Guest;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JDBCExample {

    @Autowired
    DataSource dataSource; //look to application-context.xml bean id='dataSource' definition

    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Guest> queryAllGuests() {
        final String QUERY_SQL = "SELECT * FROM GUEST ORDER BY IDGUEST";
        List<Guest> guestList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<Guest>() {
            public Guest mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Guest guest = new Guest();
                guest.setIdGuest(resultSet.getInt("IDGUEST"));
                guest.setGuestName(resultSet.getString("GUESTNAME"));
                guest.setComment(resultSet.getString("COMMENT"));
                return guest;
            }
        });
        return guestList;
    }

    //JDBC TEMPLATE UPDATE EXAMPLE
    public boolean insertGuest(Guest guest)  {
        final String INSERT_SQL = "INSERT INTO GUEST (GUESTNAME, COMMENT) VALUES (?, ?)";
        jdbcTemplate.update(INSERT_SQL, guest.getGuestName(), guest.getComment());
        return true;
    }


}
