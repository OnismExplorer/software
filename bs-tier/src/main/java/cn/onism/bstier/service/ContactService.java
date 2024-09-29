package cn.onism.bstier.service;

import cn.onism.bstier.entity.Contacts;
import cn.onism.bstier.repository.ContactRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 联系人服务
 *
 * @author Onism
 * @date 2024/09/29
 */
@Service
public class ContactService {
    @Resource
    private ContactRepository contactRepository;

    /**
     * 获取所有联系人
     *
     * @return {@link List }<{@link Contacts }>
     */
    public List<Contacts> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * 添加联系人
     *
     * @param contact 联系人
     */
    public boolean addContact(Contacts contact) {
        return contactRepository.save(contact) != null;
    }

    /**
     * 根据 ID 获取联系人
     *
     * @param id ID
     * @return {@link Contacts }
     */
    public Contacts getContactById(Long id){
        return contactRepository.findById(id).orElse(null);
    }

    /**
     * 根据用户名称模糊查询联系人
     *
     * @param name 名字
     * @return {@link List }<{@link Contacts }>
     */
    public List<Contacts> getContactByName(String name){
        return contactRepository.findByNameLike(name);
    }

    /**
     * 更新联系人
     *
     * @param contact 联系人
     * @throws SQLException sql异常
     */
    public void updateContact(Contacts contact) throws SQLException {
        if (contactRepository.existsById(contact.getId())) {
            contactRepository.save(contact); // Save 方法可用于更新
        } else {
            throw new SQLException("联系人未找到");
        }
    }

    /**
     * 删除联系人
     *
     * @param id ID
     * @throws SQLException sql异常
     */
    public void deleteContact(Long id) throws SQLException {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        } else {
            throw new SQLException("联系人未找到");
        }
    }
}

