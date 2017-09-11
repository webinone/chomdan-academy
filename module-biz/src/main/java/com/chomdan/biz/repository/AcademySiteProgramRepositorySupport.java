package com.chomdan.biz.repository;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import com.chomdan.biz.model.domain.QAcademySite;
import com.chomdan.biz.model.domain.QAcademySiteProgram;
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
 * Created by foresight on 17. 7. 26.
 */
@Repository
public class AcademySiteProgramRepositorySupport extends QueryDslRepositorySupport {

    public AcademySiteProgramRepositorySupport() {
        super(AcademySiteProgram.class);
    }

    public Page<AcademySiteProgram> findSearch(String _academySiteId, int _pageIndex, int _pageSize) {

        QAcademySiteProgram academySiteProgram = QAcademySiteProgram.academySiteProgram;

        JPQLQuery query = from(academySiteProgram);

        if (!"".equals(StringUtils.trimToEmpty(_academySiteId))) {
            query.where(academySiteProgram.academySite.academySiteId.eq(_academySiteId));
        }

        query.orderBy(academySiteProgram.programOrder.asc())
                .offset(_pageIndex * _pageSize).limit(_pageSize);


        List<AcademySiteProgram> sites = query.fetch();
        long totalCount = query.fetchCount();

        PageRequest pageRequest = new PageRequest(_pageIndex, _pageSize);
        return new PageImpl<>(sites, pageRequest, totalCount);
    }
}
