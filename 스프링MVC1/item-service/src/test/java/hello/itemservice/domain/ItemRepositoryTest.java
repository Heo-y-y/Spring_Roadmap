package hello.itemservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("ItemA", 10000, 10);
        // when
        Item saveItem = itemRepository.save(item);
        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("ItemA", 10000, 10);
        Item item2 = new Item("ItemB", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        // when
        List<Item> result = itemRepository.findAll();
        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void update() {
        // given
        Item item = new Item("Item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        // when
        Item updateItem = new Item("Item2", 20000, 30);
        itemRepository.update(itemId, updateItem);
        // then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}