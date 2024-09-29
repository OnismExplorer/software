package cn.onism.bstier.repository;

import cn.onism.bstier.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 联系人存储库
 *
 * @author Onism
 * @date 2024/09/29
 */
public interface ContactRepository extends JpaRepository<Contacts, Long> {
    /**
     * 根据用户名查询联系人
     *
     * @param name 名字
     * @return {@link List }<{@link Contacts }>
     */
    @Query("SELECT c FROM Contacts c WHERE c.name LIKE %:name%")
    List<Contacts> findByNameLike(@Param("name") String name);
}
