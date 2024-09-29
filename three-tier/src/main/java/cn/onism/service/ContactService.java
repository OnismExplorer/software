package cn.onism.service;

import cn.onism.dao.ContactDAO;
import cn.onism.entity.Contact;

import java.sql.SQLException;
import java.util.List;

/**
 * 联系人服务层
 *
 * @author Onism
 * @date 2024/09/29
 */
public class ContactService {
    private final ContactDAO contactDAO;

    public ContactService(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    /**
     * 添加联系人
     *
     * @param contact 联系
     * @throws SQLException sql异常
     */
    public void addContact(Contact contact) throws SQLException {
        contactDAO.addContact(contact);
    }

    /**
     * 获取所有联系人
     *
     * @return {@link List }<{@link Contact }>
     * @throws SQLException sql异常
     */
    public List<Contact> getAllContacts() throws SQLException {
        return contactDAO.getAllContacts();
    }

    /**
     * 根据 ID 获取联系人
     *
     * @param id ID
     * @return {@link Contact }
     * @throws SQLException sql异常
     */
    public Contact getContactById(Long id) throws SQLException {
        return contactDAO.getContactById(id);
    }

    /**
     * 根据用户名称获取联系人
     *
     * @param name 名字
     * @return {@link List }<{@link Contact }>
     * @throws SQLException sql异常
     */
    public List<Contact> getContactByName(String name) throws SQLException {
        return contactDAO.getContactByName(name);
    }

    /**
     * 更新联系人
     *
     * @param contact 联系人
     * @throws SQLException sql异常
     */
    public void updateContact(Contact contact) throws SQLException {
        contactDAO.updateContact(contact);
    }

    /**
     * 删除联系人
     *
     * @param contact 联系人
     * @throws SQLException sql异常
     */
    public void deleteContact(Contact contact) throws SQLException {
        contactDAO.deleteContact(contact);
    }
}
