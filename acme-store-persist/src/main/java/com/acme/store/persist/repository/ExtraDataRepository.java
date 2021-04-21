package com.acme.store.persist.repository;

import com.acme.store.persist.domain.entity.ExtraData;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraDataRepository
    extends PagingAndSortingRepository<ExtraData, Long>, JpaSpecificationExecutor<ExtraData> {}
