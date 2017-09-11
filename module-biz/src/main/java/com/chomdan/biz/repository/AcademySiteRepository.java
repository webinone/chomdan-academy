package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * Created by foresight on 17. 7. 26.
 */
public interface AcademySiteRepository extends JpaRepository<AcademySite, Long>, QueryDslPredicateExecutor <AcademySite> {

    AcademySite findByAcademySiteId(String academySiteId);


}
