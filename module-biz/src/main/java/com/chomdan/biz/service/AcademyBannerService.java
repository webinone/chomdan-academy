package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySiteBanner;
import com.chomdan.biz.model.transfer.AcademySiteDTO;
import org.springframework.data.domain.Page;

/**
 * Created by foresight on 17. 7. 24.
 */
public interface AcademyBannerService {

    Page<AcademySiteBanner> getList(String _academySiteId, int _pageIndex, int _pageSize);

    void add(AcademySiteDTO _academySite);

    void modify(AcademySiteDTO _academySite);

    void remove(Long idx);

}
