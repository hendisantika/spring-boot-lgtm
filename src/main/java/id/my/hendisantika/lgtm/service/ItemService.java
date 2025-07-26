package id.my.hendisantika.lgtm.service;

import id.my.hendisantika.lgtm.entity.Item;
import id.my.hendisantika.lgtm.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        log.info("Fetching all items from service");
        return itemRepository.findAll();
    }
}
