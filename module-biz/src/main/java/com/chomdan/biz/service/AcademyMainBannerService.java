package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademyMainBanner;
import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.transfer.AcademyMainBannerDTO;
import com.chomdan.biz.model.transfer.AcademySiteDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by foresight on 17. 7. 24.
 */
public interface AcademyMainBannerService {

    // Page<AcademySite> getList(String _title, int _pageIndex, int _pageSize);

    // AcademySite addAcademySite (AcademySiteDTO _academySite);

    // AcademySite modifyAcademySite(AcademySiteDTO _academySite);

    // AcademySite findByAcademySiteId(String academySiteId);

    // AcademySite findByIdx(Long idx);

    // void remove(Long idx);

    void addAcademyMainBanner (AcademyMainBannerDTO[] _academyMainBannerDTOs);

    void modifyAcademyMainBanner (AcademyMainBannerDTO[] _academyMainBannerDTOs);


    Page<AcademyMainBanner> getList(String _type, int _pageIndex, int _pageSize);
}
