package cn.onism;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 主程序入口
 *
 * @author Onism
 * @date 2024/09/29
 */
public class Main {
    public static void main(String[] args) {
        ContactManager manager = null;
        try {
            manager = new ContactManager("jdbc:mysql://localhost:3306/contact?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("=======个人通讯录管理系统=======");
                System.out.println("1. 查看联系人信息");
                System.out.println("2. 添加新联系人");
                System.out.println("3. 修改已有联系人信息");
                System.out.println("4. 删除联系人");
                System.out.println("5. 按名字查询联系人");
                System.out.println("0. 退出系统");
                System.out.print("请选择操作：");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 读取换行符，避免干扰下一次输入

                switch (choice) {
                    case 1 -> {
                        List<Contact> contacts = manager.getAllContacts();
                        if (contacts.isEmpty()) {
                            System.out.println("通讯录为空。");
                        } else {
                            System.out.println("联系人信息如下：");
                            for (Contact contact : contacts) {
                                System.out.printf("ID: %d, 姓名: %s, 电话: %s, 地址: %s\n", contact.getId(), contact.getName(), contact.getPhone(), contact.getAddress());
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("-----------------------");
                        System.out.print("请输入联系人姓名：");
                        String name = scanner.nextLine();
                        System.out.print("请输入电话号码：");
                        String phone = scanner.nextLine();
                        System.out.print("请输入地址：");
                        String address = scanner.nextLine();
                        manager.addContact(new Contact(name, address, phone));
                        System.out.println("联系人已添加！");
                    }
                    case 3 -> {
                        System.out.println("-----------------------");
                        System.out.print("请输入需要修改的联系人ID：");
                        Long id = scanner.nextLong();
                        scanner.nextLine(); // 读取换行符
                        Contact contact = manager.getContactById(id);
                        if (contact == null) {
                            System.out.println("未找到指定ID的联系人。");
                            break;
                        }
                        System.out.println("当前信息: ");
                        System.out.printf("姓名: %s, 电话: %s, 地址: %s\n", contact.getName(), contact.getPhone(), contact.getAddress());

                        System.out.print("请输入修改后联系人姓名(直接回车保持不修改)：");
                        String newName = scanner.nextLine();
                        if (!newName.isEmpty()) {
                            contact.setName(newName);
                        }

                        System.out.print("请输入修改后电话(直接回车保持不修改)：");
                        String newPhone = scanner.nextLine();
                        if (!newPhone.isEmpty()) {
                            contact.setPhone(newPhone);
                        }

                        System.out.print("请输入修改后地址(直接回车保持不修改)：");
                        String newAddress = scanner.nextLine();
                        if (!newAddress.isEmpty()) {
                            contact.setAddress(newAddress);
                        }

                        manager.updateContact(contact);
                        System.out.println("联系人信息已更新！");
                    }
                    case 4 -> {
                        System.out.println("-----------------------");
                        System.out.print("请输入要删除的联系人ID：");
                        Long id = scanner.nextLong();
                        Contact contact = manager.getContactById(id);
                        if (contact != null) {
                            manager.deleteContact(contact);
                            System.out.println("联系人已删除！");
                        } else {
                            System.out.println("未找到指定ID的联系人。");
                        }
                    }
                    case 5 -> {
                        System.out.println("-----------------------");
                        System.out.print("请输入查询联系人姓名：");
                        String name = scanner.next();
                        List<Contact> contacts = manager.getContactByName(name);
                        if(contacts == null || contacts.isEmpty()) {
                            System.out.println("未找到 "+name+" 相关联系人");
                        } else {
                            System.out.println( "\" "+ name +"\" 相关联系人信息如下：");
                            for (Contact contact : contacts) {
                                System.out.printf("ID: %d, 姓名: %s, 电话: %s, 地址: %s\n", contact.getId(), contact.getName(), contact.getPhone(), contact.getAddress());
                            }
                        }
                    }
                    case 0 -> {
                        System.out.println("已退出系统，欢迎下次光临！");
                        return;
                    }
                    default -> System.out.println("无效选择，请重试。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭链接
            if (manager != null) {
                try {
                    manager.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

