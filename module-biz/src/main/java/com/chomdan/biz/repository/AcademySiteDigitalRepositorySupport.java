package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySiteDigital;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import com.chomdan.biz.model.domain.QAcademySiteDigital;
import com.chomdan.biz.model.domain.QAcademySiteProgram;
import com.chomdan.biz.model.domain.enums.AcademySiteDigitalType;
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
public class AcademySiteDigitalRepositorySupport extends QueryDslRepositorySupport {

    public AcademySiteDigitalRepositorySupport() {
        super(AcademySiteDigital.class);
    }

    public Page<AcademySiteDigital> findSearch(String _academySiteId, String _type, String _title, int _pageIndex, int _pageSize) {

        QAcademySiteDigital academySiteDigital = QAcademySiteDigital.academySiteDigital;

        JPQLQuery query = from(academySiteDigital);

        if (!"".equals(StringUtils.trimToEmpty(_academySiteId))) {
            query.where(academySiteDigital.academySite.academySiteId.eq(_academySiteId));
        }

        if (!"".equals(StringUtils.trimToEmpty(_type))) {
            if ("0".equals(_type)) {
                query.where(academySiteDigital.type.eq(AcademySiteDigitalType.IMAGE));
            }

            if ("1".equals(_type)) {
                query.where(academySiteDigital.type.eq(AcademySiteDigitalType.VIDEO));
            }
        }

        if (!"".equals(StringUtils.trimToEmpty(_title))) {
            query.where(academySiteDigital.title.like("%" + _title + "%"));
        }

        query.orderBy(academySiteDigital.idx.desc())
                .offset(_pageIndex * _pageSize).limit(_pageSize);

        List<AcademySiteDigital> list = query.fetch();
        long totalCount = query.fetchCount();

        PageRequest pageRequest = new PageRequest(_pageIndex, _pageSize);
        return new PageImpl<>(list, pageRequest, totalCount);
    }
}
