package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademyMainBanner;

import javax.jdo.annotations.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Created by foresight on 17. 7. 26.
 */
public interface AcademyMainBannerRepository extends JpaRepository<AcademyMainBanner, Long> {

//    AcademySiteProgram findBy(String academySiteId);

    @Modifying
    @Query(value = "DELETE FROM TB_ACADEMY_MAIN_BANNER WHERE academy_main_id = :academy_main_id", nativeQuery=true)
    void deleteByAcademyMainId(@Param("academy_main_id") String academy_main_id);
}
