package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.GuardianPhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GuardianPhoneNumberDaoImpl implements GuardianPhoneNumberDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(GuardianPhoneNumber guardianPhoneNumber) {
        String sql = "INSERT INTO GuardianPhoneNumber (phoneNumber, name, studentId) VALUES (?, ?, ?)";
        template.update(sql, guardianPhoneNumber.getPhoneNumber(), guardianPhoneNumber.getName(), guardianPhoneNumber.getStudentId());
    }

    @Override
    public List<GuardianPhoneNumber> getByStudentId(int studentId) {
        String sql = "SELECT * FROM GuardianPhoneNumber WHERE studentId = ?";
        List<GuardianPhoneNumber> phoneNumbers = template.query(sql, new Object[] { studentId },
                new BeanPropertyRowMapper<>(GuardianPhoneNumber.class));
        return phoneNumbers;
    }

    /**
     * Update only the phoneNumber attribute
     */
    @Override
    public void update(GuardianPhoneNumber guardianPhoneNumber, int oldPhoneNumber) {
        String sql = "UPDATE GuardianPhoneNumber SET phoneNumber = ? WHERE phoneNumber = ? AND name = ? AND studentId = ?";
        template.update(sql, guardianPhoneNumber.getPhoneNumber(), oldPhoneNumber, guardianPhoneNumber.getName(), guardianPhoneNumber.getStudentId());
    }

    @Override
    public void delete(GuardianPhoneNumber guardianPhoneNumber) {
        String sql = "DELETE FROM GuardianPhoneNumber WHERE phoneNumber = ? AND name = ? AND studentId = ?";
        template.update(sql, guardianPhoneNumber.getPhoneNumber(), guardianPhoneNumber.getName(), guardianPhoneNumber.getStudentId());
    }

}