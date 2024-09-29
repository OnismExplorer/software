package cn.onism.dao;

import cn.onism.entity.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 联系 人DAO层
 *
 * @author Onism
 * @date 2024/09/29
 */
public class ContactDAO {
    private final Connection connection;

    public ContactDAO(String dbUrl, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(dbUrl, user, password);
    }

    /**
     * 添加联系人
     *
     * @param contact 联系
     * @throws SQLException sql异常
     */
    public void addContact(Contact contact) throws SQLException {
        String query = "INSERT INTO contacts (name, address, phone) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getAddress());
            statement.setString(3, contact.getPhone());
            statement.executeUpdate();
        }
    }

    /**
     * 获取全部联系人
     *
     * @return {@link List }<{@link Contact }>
     * @throws SQLException sql异常
     */
    public List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM contacts";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Contact contact = new Contact(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getString("phone"));
                contacts.add(contact);
            }
            return contacts;
        }
    }

    /**
     * 根据用户名称获取联系人
     *
     * @param name 名字
     * @return {@link List }<{@link Contact }>
     * @throws SQLException sql异常
     */
    public List<Contact> getContactByName(String name) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM contacts WHERE name like ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"));
                contacts.add(contact);
            }
            return contacts;
        }
    }

    /**
     * 按 ID 获取联系人
     *
     * @param id 身份证
     * @return {@link Contact }
     * @throws SQLException sql异常
     */
    public Contact getContactById(Long id) throws SQLException {
        String query = "SELECT * FROM contacts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Contact(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"));
            }
            return null;
        }
    }

    /**
     * 更新联系人
     *
     * @param contact 联系人
     * @throws SQLException sql异常
     */
    public void updateContact(Contact contact) throws SQLException {
        String query = "UPDATE contacts SET address = ?, phone = ? ,name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getAddress());
            statement.setString(2, contact.getPhone());
            statement.setString(3, contact.getName());
            statement.setLong(4, contact.getId());
            statement.executeUpdate();
        }
    }

    /**
     * 删除联系人
     *
     * @param contact 联系人
     * @throws SQLException sql异常
     */
    public void deleteContact(Contact contact) throws SQLException {
        String query = "DELETE FROM contacts WHERE name = ? AND phone = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhone()); // 确保通过 name 和 phone 一起定位联系人
            statement.executeUpdate();
        }
    }

    /**
     * 关闭数据库链接
     *
     * @throws SQLException sql异常
     */
    public void close() throws SQLException {
        connection.close();
    }
}
