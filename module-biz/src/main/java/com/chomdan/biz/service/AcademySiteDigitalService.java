package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySiteBanner;
import com.chomdan.biz.model.domain.AcademySiteDigital;
import com.chomdan.biz.model.transfer.AcademySiteBannerDTO;
import com.chomdan.biz.model.transfer.AcademySiteDigitalDTO;
import org.springframework.data.domain.Page;

/**
 * Created by foresight on 17. 7. 24.
 */
public interface AcademySiteDigitalService {

    Page<AcademySiteDigital> getList(String _academySiteId, String _type, String _title, int _pageIndex, int _pageSize);

    void add(AcademySiteDigitalDTO dto);

    void modify(Long _idx, AcademySiteDigitalDTO dto) ;

    void remove(Long idx);

}
