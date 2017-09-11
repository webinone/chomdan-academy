package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteBanner;
import com.chomdan.biz.model.transfer.AcademySiteBannerDTO;
import com.chomdan.biz.repository.AcademySiteBannerRepository;
import com.chomdan.biz.repository.AcademySiteBannerRepositorySupport;
import com.chomdan.biz.repository.AcademySiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AcademySiteBannerServiceImpl implements AcademySiteBannerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AcademySiteRepository academySiteRepository;

    @Autowired
    AcademySiteBannerRepository academySiteBannerRepository;

    @Autowired
    AcademySiteBannerRepositorySupport academySiteBannerRepositorySupport;

    @Transactional(readOnly = true)
    public Page<AcademySiteBanner> getList(String _academySiteId, int _pageIndex, int _pageSize) {
        return this.academySiteBannerRepositorySupport.findSearch(_academySiteId, _pageIndex, _pageSize);
    }

    @Transactional
    public void add(String _academySiteId, AcademySiteBannerDTO[] _academySiteBannerDTOs) {

        if (_academySiteBannerDTOs != null && _academySiteBannerDTOs.length > 0) {

            AcademySite academySite = this.academySiteRepository.findByAcademySiteId(_academySiteId);

            int bannerOrder = 1;

            List<AcademySiteBanner> saveAcademySiteBanners = new ArrayList<AcademySiteBanner>();

            for (AcademySiteBannerDTO academySiteBannerDTO : _academySiteBannerDTOs) {

                AcademySiteBanner academySiteBanner = new AcademySiteBanner();
                academySiteBanner.setBannerId(UUID.randomUUID().toString());
                academySiteBanner.setTitle(academySiteBannerDTO.getTitle());
                academySiteBanner.setDescription(academySiteBannerDTO.getDescription());
                academySiteBanner.setLinkUrl(academySiteBannerDTO.getLinkUrl());
                academySiteBanner.setOriginalName(academySiteBannerDTO.getOriginalName());
                academySiteBanner.setSavePath(academySiteBannerDTO.getSavePath());
                academySiteBanner.setWebPath(academySiteBannerDTO.getWebPath());

                academySiteBanner.setBannerOrder(bannerOrder);
                academySiteBanner.setAcademySite(academySite);

                saveAcademySiteBanners.add(academySiteBanner);

                bannerOrder++;
            }

            this.academySiteBannerRepository.save(saveAcademySiteBanners);
        }

    }

    @Transactional
    public void modify(String _academySiteId, AcademySiteBannerDTO[] _academySiteBannerDTOs) {

        // 일단 모두 삭제
        this.academySiteBannerRepository.deleteByAcademySiteId(_academySiteId);

        // 삭제 후 다시 입력
        if (_academySiteBannerDTOs != null && _academySiteBannerDTOs.length > 0) {

            AcademySite academySite = this.academySiteRepository.findByAcademySiteId(_academySiteId);

            int bannerOrder = 1;

            List<AcademySiteBanner> saveAcademySiteBanners = new ArrayList<AcademySiteBanner>();

            for (AcademySiteBannerDTO academySiteBannerDTO : _academySiteBannerDTOs) {

                AcademySiteBanner academySiteBanner = new AcademySiteBanner();
                academySiteBanner.setBannerId(UUID.randomUUID().toString());
                academySiteBanner.setTitle(academySiteBannerDTO.getTitle());
                academySiteBanner.setDescription(academySiteBannerDTO.getDescription());
                academySiteBanner.setLinkUrl(academySiteBannerDTO.getLinkUrl());
                academySiteBanner.setOriginalName(academySiteBannerDTO.getOriginalName());
                academySiteBanner.setSavePath(academySiteBannerDTO.getSavePath());
                academySiteBanner.setWebPath(academySiteBannerDTO.getWebPath());

                academySiteBanner.setBannerOrder(bannerOrder);
                academySiteBanner.setAcademySite(academySite);

                saveAcademySiteBanners.add(academySiteBanner);

                bannerOrder++;
            }

            this.academySiteBannerRepository.save(saveAcademySiteBanners);
        }

    }

    @Transactional
    public void remove(Long idx) {

    }

}
