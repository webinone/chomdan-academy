package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.transfer.AcademySiteDTO;
import org.springframework.data.domain.Page;

/**
 * Created by foresight on 17. 7. 24.
 */
public interface AcademySiteService {

    Page<AcademySite> getList(String _type, String _title, String _status, int _pageIndex, int _pageSize);

    AcademySite addAcademySite (AcademySiteDTO _academySite);

    AcademySite modifyAcademySite(AcademySiteDTO _academySite);

    AcademySite findByAcademySiteId(String academySiteId);

    AcademySite findByIdx(Long idx);

    void remove(Long idx);
}
