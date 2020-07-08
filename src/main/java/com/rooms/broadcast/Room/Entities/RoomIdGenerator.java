package com.rooms.broadcast.Room.Entities;

import org.apache.commons.text.RandomStringGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Connection connection = session.connection();
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z').filteredBy(Character::isLetterOrDigit).build();
        String id = "";
        int idCount = 1;
        try {
            PreparedStatement statement=connection.prepareStatement("select count(id) from Room where id=?");
            while(idCount!=0) {
                id = generator.generate(6,7);
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    idCount = rs.getInt(1);
                }
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}