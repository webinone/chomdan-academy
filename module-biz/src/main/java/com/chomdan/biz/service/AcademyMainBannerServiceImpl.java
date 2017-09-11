package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademyMain;
import com.chomdan.biz.model.domain.AcademyMainBanner;
import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteBanner;
import com.chomdan.biz.model.transfer.AcademyMainBannerDTO;
import com.chomdan.biz.repository.AcademyMainBannerRepository;
import com.chomdan.biz.repository.AcademyMainBannerRepositorySupport;
import com.chomdan.biz.repository.AcademyMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
 
/**
 * Created by foresight on 17. 7. 24.
 */
@Service
public class AcademyMainBannerServiceImpl implements AcademyMainBannerService {

    @Autowired
    AcademyMainRepository academyMainRepository;
    
    @Autowired
    AcademyMainBannerRepository academyMainBannerRepository;

    @Autowired
    AcademyMainBannerRepositorySupport academyMainBannerRepositorySupport;

    @Transactional
    public void addAcademyMainBanner (AcademyMainBannerDTO[] _academyMainBannerDTOs) {

        String academyMainId    = "chomdan";

        // 먼저 삭제한다.
        this.academyMainBannerRepository.deleteByAcademyMainId(academyMainId);

        int bannerOrder = 1;
        List<AcademyMainBanner> saveAcademyMainBanners = new ArrayList<AcademyMainBanner>();

        for (AcademyMainBannerDTO bannerDTO : _academyMainBannerDTOs) {

            AcademyMainBanner _banner = new AcademyMainBanner();

//            _banner.setAcademyMain(academyMain);
            _banner.setAcademyMainId(academyMainId);
            _banner.setBannerId(UUID.randomUUID().toString());
            _banner.setBannerType(bannerDTO.getBannerType());
            _banner.setTitle(bannerDTO.getTitle());
            _banner.setDescription(bannerDTO.getDescription());
            _banner.setBannerOrder(bannerOrder);
            _banner.setOriginalName(bannerDTO.getOriginalName());
            _banner.setSavePath(bannerDTO.getSavePath());
            _banner.setWebPath(bannerDTO.getWebPath());
            _banner.setLinkUrl(bannerDTO.getLinkUrl());

            saveAcademyMainBanners.add(_banner);

            bannerOrder ++;
        }

        this.academyMainBannerRepository.save(saveAcademyMainBanners);
    }

    @Transactional
    public void modifyAcademyMainBanner (AcademyMainBannerDTO[] _academyMainBannerDTOs) {

        String academyMainId    = "chomdan";

        AcademyMain academyMain = this.academyMainRepository.findByAcademyMainId(academyMainId);

        // 먼저 삭제한다.
        this.academyMainBannerRepository.deleteByAcademyMainId(academyMainId);

        int bannerOrder = 1;
        List<AcademyMainBanner> saveAcademyMainBanners = new ArrayList<AcademyMainBanner>();

        for (AcademyMainBannerDTO bannerDTO : _academyMainBannerDTOs) {

            AcademyMainBanner _banner = new AcademyMainBanner();

//            _banner.setAcademyMain(academyMain);
            _banner.setAcademyMainId(academyMainId);
            _banner.setBannerId(UUID.randomUUID().toString());
            _banner.setBannerType(bannerDTO.getBannerType());
            _banner.setTitle(bannerDTO.getTitle());
            _banner.setDescription(bannerDTO.getDescription());
            _banner.setBannerOrder(bannerOrder);
            _banner.setOriginalName(bannerDTO.getOriginalName());
            _banner.setSavePath(bannerDTO.getSavePath());
            _banner.setWebPath(bannerDTO.getWebPath());
            _banner.setLinkUrl(bannerDTO.getLinkUrl());

            saveAcademyMainBanners.add(_banner);

            bannerOrder++;
        }

        this.academyMainBannerRepository.save(saveAcademyMainBanners);
    }

    @Transactional(readOnly = true)
    public Page<AcademyMainBanner> getList(String _type, int _pageIndex, int _pageSize) {
        return this.academyMainBannerRepositorySupport.listBanners(_type, _pageIndex, _pageSize);
    }
}
