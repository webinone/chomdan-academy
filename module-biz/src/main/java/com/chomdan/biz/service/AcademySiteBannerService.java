package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySiteBanner;
import com.chomdan.biz.model.transfer.AcademySiteBannerDTO;
import org.springframework.data.domain.Page;

/**
 * Created by foresight on 17. 7. 24.
 */
public interface AcademySiteBannerService {

    Page<AcademySiteBanner> getList(String _academySiteId, int _pageIndex, int _pageSize);

    void add(String _academySiteId, AcademySiteBannerDTO[] _academySiteBannerDTO);

    void modify(String _academySiteId, AcademySiteBannerDTO[] _academySiteBannerDTO);

    void remove(Long idx);

}
