package com.acme.store.persist.repository;

import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.entity.StoreSeason;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreSeasonsRepository
    extends PagingAndSortingRepository<StoreSeason, Long>, JpaSpecificationExecutor<StoreSeason> {

  @Query(value = "SELECT * FROM store_seasons WHERE season LIKE %:season%", nativeQuery = true)
  Page<StoreSeason> findBySeason(String season, Pageable pageable);
}
