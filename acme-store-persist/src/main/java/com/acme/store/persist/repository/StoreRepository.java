package com.acme.store.persist.repository;

import com.acme.store.persist.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository  extends PagingAndSortingRepository<Store, Long>,
  JpaSpecificationExecutor<Store> {

  @Query(value = "SELECT * FROM Store WHERE name LIKE %:name%",
    nativeQuery = true)
  Page<Store> findByNameLike(String name, Pageable pageable);

  Optional<Store> findById(Long id);

}
