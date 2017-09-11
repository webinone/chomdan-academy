package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by foresight on 17. 7. 26.
 */
public interface AcademySiteProgramRepository extends JpaRepository<AcademySiteProgram, Long> {

    AcademySiteProgram findByAcademySite(AcademySite _academySite);

    @Modifying
    @Query(value = "DELETE FROM TB_ACADEMY_SITE_PROGRAM WHERE academy_site_id = :academy_site_id", nativeQuery=true)
    void deleteByAcademySiteId(@Param("academy_site_id") String academy_site_id);
}
