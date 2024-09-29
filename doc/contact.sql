DROP DATABASE IF EXISTS contact;
# 创建数据库
CREATE DATABASE contact CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# 使用数据库
USE contact;

# 创建表
CREATE TABLE IF NOT EXISTS contacts (
                                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(20),
                                        address VARCHAR(255),
                                        phone VARCHAR(25)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;