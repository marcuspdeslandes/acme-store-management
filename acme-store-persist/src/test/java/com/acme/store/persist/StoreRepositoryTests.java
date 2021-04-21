package com.acme.store.persist;

import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.model.enums.StoreTypeEnum;
import com.acme.store.persist.repository.StoreRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class StoreRepositoryTests {

	@Autowired
	private StoreRepository repo;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateStore() {
		Store savedStore = repo.save(new Store(1L,"SlaLY-pZJJL469fz1jRoXeD1onFdNYV:556","Kmypdj Pejm","Store 923315",new Date(), StoreTypeEnum.RETAIL, null, null));
		
		assertThat(savedStore.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void testFindStoreByName() {
    Pageable pageable = PageRequest.of(0, 1);
    Page<Store> store = repo.findByNameLike("923", pageable);
		assertThat(store.getContent().get(0).getName()).isEqualTo("Store 923315");
	}
	
	@Test
	@Order(3)
	public void testListStores() {
		List<Store> stores = (List<Store>) repo.findAll();
		assertThat(stores).size().isGreaterThan(0);
	}	
	
	@Test
	@Rollback(false)
	@Order(4)
	public void testUpdateStore() {
    Pageable pageable = PageRequest.of(0, 1);
		Page<Store> pageStore = repo.findByNameLike("923", pageable);
    Store store = pageStore.getContent().get(0);
    store.setName("New Store Name");
		
		repo.save(store);

    Page<Store> updatedStore = repo.findByNameLike("New", pageable);
		
		assertThat(updatedStore.getContent().get(0).getName()).isEqualTo("New Store Name");
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeleteStore() {
    Pageable pageable = PageRequest.of(0, 1);
    Page<Store> pageStore = repo.findByNameLike("New", pageable);
		
		repo.deleteById(pageStore.getContent().get(0).getId());
		
		Page<Store> deletedStore = repo.findByNameLike("New", pageable);
		
		assertThat(deletedStore.getTotalElements()).isEqualTo(0);
		
	}
}
