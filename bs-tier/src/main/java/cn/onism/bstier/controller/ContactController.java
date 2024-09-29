package cn.onism.bstier.controller;

import cn.onism.bstier.entity.Contacts;
import cn.onism.bstier.service.ContactService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 联系人控制器
 *
 * @author Onism
 * @date 2024/09/29
 */
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Resource
    private ContactService contactService;

    /**
     * 获取所有联系人
     *
     * @return {@link List }<{@link Contacts }>
     */
    @GetMapping
    public List<Contacts> getAllContacts() {
        return contactService.getAllContacts();
    }

    /**
     * 添加联系人
     *
     * @param contact 联系
     */
    @PostMapping
    public List<String> addContact(@RequestBody Contacts contact) {
        if (contactService.addContact(contact)) {
            return List.of("添加成功！");
        }
        return List.of("添加失败！");
    }

    /**
     * 根据名称查询联系人
     *
     * @param name 名字
     * @return {@link List }<{@link Contacts }>
     */
    @GetMapping("/search")
    public List<Contacts> getContactsByName(@RequestParam String name) {
        return contactService.getContactByName(name);
    }

    /**
     * 按 ID 删除联系人
     *
     * @param id 身份证
     * @throws SQLException sql异常
     */
    @DeleteMapping
    public void deleteContactById(@RequestParam Long id) throws SQLException {
        contactService.deleteContact(id);
    }

    /**
     * 更新联系人
     *
     * @param contacts 接触
     * @throws SQLException sql异常
     */
    @PutMapping
    public void updateContact(@RequestBody Contacts contacts) throws SQLException {
        contactService.updateContact(contacts);
    }

    /**
     * 按 ID 获取联系人
     *
     * @param id 身份证
     * @return {@link Contacts }
     */
    @GetMapping("/get")
    public Contacts getContactById(@RequestParam Long id) {
        return contactService.getContactById(id);
    }
}
