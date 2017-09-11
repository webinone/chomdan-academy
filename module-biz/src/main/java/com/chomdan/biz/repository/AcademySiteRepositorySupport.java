package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.QAcademySite;
import com.chomdan.biz.model.domain.enums.AcademySiteType;
import com.querydsl.jpa.JPQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by foresight on 17. 8. 1.
 */
@Repository
public class AcademySiteRepositorySupport extends QueryDslRepositorySupport {

    public AcademySiteRepositorySupport() {
        super(AcademySite.class);
    }

    public Page<AcademySite> findSearch(String _type, String _title, String _status, int _pageIndex, int _pageSize) {

        QAcademySite academySite = QAcademySite.academySite;

        JPQLQuery query = from(academySite);

        if (!"".equals(StringUtils.trimToEmpty(_type))) {
            query.where(academySite.type.eq(Integer.parseInt(_type)));
        }

        if (!"".equals(StringUtils.trimToEmpty(_title))) {
            query.where(academySite.title.like("%" + _title + "%"));
        }

        if (!"".equals(StringUtils.trimToEmpty(_status))) {
            // -1 : 대기중
            // 0  : 진행 중인 것
            // 1  : 완료 된 것
            Date nowDate = new Date();

            if ("-1".equals(_status)) {
                query.where(academySite.startDate.gt(nowDate));
            }

            if ("0".equals(_status)) {
                query.where(academySite.startDate.gt(nowDate).and(academySite.endDate.lt(nowDate)));
            }

            if ("1".equals(_status)) {
                query.where(academySite.endDate.lt(nowDate));
            }

        }

        query.orderBy(academySite.idx.desc()).offset(_pageIndex * _pageSize).limit(_pageSize);


        List<AcademySite> sites = query.fetch();
        long totalCount = query.fetchCount();

        PageRequest pageRequest = new PageRequest(_pageIndex, _pageSize);
        return new PageImpl<>(sites, pageRequest, totalCount);
    }

}
