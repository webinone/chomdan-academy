package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySiteThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;


/**
 * Created by foresight on 17. 7. 26.
 */
public interface AcademySiteThumbnailRepository extends JpaRepository<AcademySiteThumbnail, Long>, QueryDslPredicateExecutor <AcademySiteThumbnail> {

    @Modifying
    @Query(value = "DELETE FROM TB_ACADEMY_SITE_THUMBNAIL WHERE thumbnail_id = :thumbnail_id", nativeQuery=true)
    void deleteByThumbnailId(@Param("thumbnail_id") String thumbnailId);

}
