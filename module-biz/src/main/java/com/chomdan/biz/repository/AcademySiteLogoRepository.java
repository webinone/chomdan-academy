package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * Created by foresight on 17. 7. 26.
 */
public interface AcademySiteLogoRepository extends JpaRepository<AcademySiteLogo, Long>, QueryDslPredicateExecutor <AcademySiteLogo> {

}
