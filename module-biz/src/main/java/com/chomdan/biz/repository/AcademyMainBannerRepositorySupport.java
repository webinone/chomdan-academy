package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademyMainBanner;
import com.chomdan.biz.model.domain.QAcademyMainBanner;
import com.querydsl.jpa.JPQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foresight on 17. 8. 1.
 */
@Repository
public class AcademyMainBannerRepositorySupport extends QueryDslRepositorySupport {

    public AcademyMainBannerRepositorySupport() {
        super(AcademyMainBanner.class);
    }

    public Page<AcademyMainBanner> listBanners(String _type, int _pageIndex, int _pageSize) {

        QAcademyMainBanner academyMainBanner = QAcademyMainBanner.academyMainBanner;

        JPQLQuery query = from(academyMainBanner);

        if (!"".equals(StringUtils.trimToEmpty(_type))) {
            query.where(academyMainBanner.bannerType.eq(_type));
        }

        query.orderBy(academyMainBanner.bannerOrder.asc());
        query.offset(_pageIndex * _pageSize).limit(_pageSize);

        List<AcademyMainBanner> banners = query.fetch();
        long totalCount = query.fetchCount();

        PageRequest pageRequest = new PageRequest(_pageIndex, _pageSize);
        return new PageImpl<>(banners, pageRequest, totalCount);

    }

}
