package com.backEnd.serviceMarketplace.repository;

import com.backEnd.serviceMarketplace.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }

    public void userSave(Account account) {
        String query="INSERT INTO accounts (username, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, account.getUserName(), account.getEmail(), account.getPassword());

    }

    public Account findByUsername(String email) {
        String sql = "SELECT * FROM accounts WHERE email= (:email)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email",email);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, parameters, (rs, rowNum) -> {
                Account user = new Account();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            });
        } catch (Exception e) {
            return null; // If user is not found, return null
        }
    }

}
