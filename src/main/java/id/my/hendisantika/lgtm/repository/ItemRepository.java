package id.my.hendisantika.lgtm.repository;

import id.my.hendisantika.lgtm.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-lgtm
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 26/07/25
 * Time: 07.59
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
