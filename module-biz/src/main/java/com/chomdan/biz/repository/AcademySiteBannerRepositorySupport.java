package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySiteBanner;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import com.chomdan.biz.model.domain.QAcademySiteBanner;
import com.chomdan.biz.model.domain.QAcademySiteProgram;
import com.querydsl.jpa.JPQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by foresight on 17. 7. 26.
 */
@Repository
public class AcademySiteBannerRepositorySupport extends QueryDslRepositorySupport {

    public AcademySiteBannerRepositorySupport() {
        super(AcademySiteBanner.class);
    }

    public Page<AcademySiteBanner> findSearch(String _academySiteId, int _pageIndex, int _pageSize) {

        QAcademySiteBanner academySiteBanner = QAcademySiteBanner.academySiteBanner;

        JPQLQuery query = from(academySiteBanner);

        if (!"".equals(StringUtils.trimToEmpty(_academySiteId))) {
            query.where(academySiteBanner.academySite.academySiteId.eq(_academySiteId));
        }

        query.orderBy(academySiteBanner.bannerOrder.asc())
                .offset(_pageIndex * _pageSize).limit(_pageSize);


        List<AcademySiteBanner> sites = query.fetch();
        long totalCount = query.fetchCount();

        PageRequest pageRequest = new PageRequest(_pageIndex, _pageSize);
        return new PageImpl<>(sites, pageRequest, totalCount);
    }
}
