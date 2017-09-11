package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademyMain;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by foresight on 17. 7. 26.
 */
public interface AcademyMainRepository extends JpaRepository<AcademyMain, Long> {

    AcademyMain findByAcademyMainId(String academyMainId);
}
