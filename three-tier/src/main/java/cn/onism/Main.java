package cn.onism;

import cn.onism.dao.ContactDAO;
import cn.onism.service.ContactService;
import cn.onism.ui.ContactUI;

import java.sql.SQLException;

/**
 * 主程序入口
 *
 * @author Onism
 * @date 2024/09/29
 */
public class Main {
    public static void main(String[] args) {
        ContactDAO contactDAO = null;
        try {
            contactDAO = new ContactDAO("jdbc:mysql://localhost:3306/contact", "root", "123456");
            ContactService contactService = new ContactService(contactDAO);
            ContactUI contactUI = new ContactUI(contactService);
            contactUI.start();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(contactDAO != null) {
                try {
                    contactDAO.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
