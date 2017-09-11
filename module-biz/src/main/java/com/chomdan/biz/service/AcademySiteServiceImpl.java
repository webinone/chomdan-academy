package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteTag;
import com.chomdan.biz.model.domain.AcademySiteThumbnail;
import com.chomdan.biz.model.transfer.AcademySiteDTO;
import com.chomdan.biz.model.transfer.AcademySiteTagDTO;
import com.chomdan.biz.repository.*;
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
public class AcademySiteServiceImpl implements AcademySiteService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AcademySiteRepository academySiteRepository ;

    @Autowired
    AcademySiteLogoRepository academySiteLogoRepository;

    @Autowired
    AcademySiteTagRepository academySiteTagRepository;

    @Autowired
    AcademySiteRepositorySupport academySiteRepositorySupport;

    @Autowired
    AcademySiteThumbnailRepository academySiteThumbnailRepository;

    @Transactional(readOnly = true)
    public Page<AcademySite> getList(String _type, String _title, String _status, int _pageIndex, int _pageSize) {
        return this.academySiteRepositorySupport.findSearch(_type, _title, _status, _pageIndex, _pageSize);
    }


    @Transactional(readOnly = true)
    public AcademySite findByAcademySiteId(String academySiteId) {

//        return this.academySiteRepository.findByAcademySiteId(academySiteId);
        return null;
    }

    @Transactional(readOnly = true)
    public AcademySite findByIdx(Long idx) {
        return this.academySiteRepository.findOne(idx);
    }

    @Transactional
    public AcademySite addAcademySite(AcademySiteDTO _academySite) {

        String academySiteId = UUID.randomUUID().toString();

        logger.debug(_academySite.toString());

        AcademySite academySite = new AcademySite();
        academySite.setAcademySiteId(academySiteId);
        academySite.setType(Integer.parseInt(_academySite.getType()));
        academySite.setTitle(_academySite.getTitle());
        academySite.setSubTitle(_academySite.getSubTitle());
        academySite.setStartDate(_academySite.getStartDate());
        academySite.setEndDate(_academySite.getEndDate());
        academySite.setStartTime(_academySite.getStartTime());
        academySite.setEndTime(_academySite.getEndTime());
        academySite.setHostInfo(_academySite.getHostInfo());

        if (_academySite.getAcademySiteThumbnailDTO() != null) {

            AcademySiteThumbnail academySiteThumbnail = new AcademySiteThumbnail();
            academySiteThumbnail.setOriginalName(_academySite.getAcademySiteThumbnailDTO().getOriginalName());
            academySiteThumbnail.setSavePath(_academySite.getAcademySiteThumbnailDTO().getSavePath());
            academySiteThumbnail.setWebPath(_academySite.getAcademySiteThumbnailDTO().getWebPath());
            academySiteThumbnail.setThumbnailId(UUID.randomUUID().toString());

            academySite.setAcademySiteThumbnail(academySiteThumbnail);
        }

        academySite.setDescription(_academySite.getDescription());
        academySite.setAddress(_academySite.getAddress());
        academySite.setLocation(_academySite.getLocation());
        academySite.setLatitude(_academySite.getLatitude());
        academySite.setLongitude(_academySite.getLongitude());
        academySite.setTransportation(_academySite.getTransportation());
        academySite.setCar(_academySite.getCar());

        academySite.setPreRegistStartDate(_academySite.getPreRegistStartDate());
        academySite.setPreRegistEndDate(_academySite.getPreRegistEndDate());
        academySite.setPreRegistAmount(_academySite.getPreRegistAmount());
        academySite.setOffLineAmount(_academySite.getOffLineAmount());

        academySite.setAdminName(_academySite.getAdminName());
        academySite.setAdminPosition(_academySite.getAdminPosition());
        academySite.setAdminPhone(_academySite.getAdminPhone());
        academySite.setAdminEmail(_academySite.getAdminEmail());

        List<AcademySiteTag> academySiteTags = new ArrayList<AcademySiteTag>();

        for (AcademySiteTagDTO academySiteTagDTO : _academySite.getAcademySiteTagDTOs()) {

            AcademySiteTag academySiteTag = new AcademySiteTag();
            academySiteTag.setTagId(UUID.randomUUID().toString());
            academySiteTag.setName(academySiteTagDTO.getName());
            academySiteTag.setAcademySite(academySite);

            academySiteTags.add(academySiteTag);
        }

        academySite.setAcademySiteTags(academySiteTags);

        return this.academySiteRepository.save(academySite);

    }

    @Transactional
    public AcademySite modifyAcademySite(AcademySiteDTO _academySite) {

        AcademySite academySite = this.academySiteRepository.findOne(_academySite.getIdx());

        // 태그는 일단 모두 삭제하고 시작
        String academySiteId = academySite.getAcademySiteId();

        // 사이트 id로 모두 삭제한다.
        this.academySiteTagRepository.deleteByAcademySiteId(academySiteId);

        if (_academySite.getAcademySiteThumbnailDTO() != null) {

            // 만약 썸네일이 존재한다면..저장하는데 기존 것은 일단 모두 삭제한다.
            this.academySiteThumbnailRepository.delete(academySite.getAcademySiteThumbnail());

            AcademySiteThumbnail academySiteThumbnail = new AcademySiteThumbnail();
            academySiteThumbnail.setOriginalName(_academySite.getAcademySiteThumbnailDTO().getOriginalName());
            academySiteThumbnail.setSavePath(_academySite.getAcademySiteThumbnailDTO().getSavePath());
            academySiteThumbnail.setWebPath(_academySite.getAcademySiteThumbnailDTO().getWebPath());
            academySiteThumbnail.setThumbnailId(UUID.randomUUID().toString());

            academySite.setAcademySiteThumbnail(academySiteThumbnail);
        }

        academySite.setType(Integer.parseInt(_academySite.getType()));
        academySite.setTitle(_academySite.getTitle());
        academySite.setSubTitle(_academySite.getSubTitle());
        academySite.setStartDate(_academySite.getStartDate());
        academySite.setEndDate(_academySite.getEndDate());
        academySite.setStartTime(_academySite.getStartTime());
        academySite.setEndTime(_academySite.getEndTime());
        academySite.setHostInfo(_academySite.getHostInfo());

        academySite.setDescription(_academySite.getDescription());
        academySite.setAddress(_academySite.getAddress());
        academySite.setLocation(_academySite.getLocation());
        academySite.setLatitude(_academySite.getLatitude());
        academySite.setLongitude(_academySite.getLongitude());
        academySite.setTransportation(_academySite.getTransportation());
        academySite.setCar(_academySite.getCar());

        academySite.setPreRegistStartDate(_academySite.getPreRegistStartDate());
        academySite.setPreRegistEndDate(_academySite.getPreRegistEndDate());
        academySite.setPreRegistAmount(_academySite.getPreRegistAmount());
        academySite.setOffLineAmount(_academySite.getOffLineAmount());

        academySite.setAdminName(_academySite.getAdminName());
        academySite.setAdminPosition(_academySite.getAdminPosition());
        academySite.setAdminPhone(_academySite.getAdminPhone());
        academySite.setAdminEmail(_academySite.getAdminEmail());

        List<AcademySiteTag> academySiteTags = new ArrayList<AcademySiteTag>();

        for (AcademySiteTagDTO academySiteTagDTO : _academySite.getAcademySiteTagDTOs()) {

            AcademySiteTag academySiteTag = new AcademySiteTag();
            academySiteTag.setTagId(UUID.randomUUID().toString());
            academySiteTag.setName(academySiteTagDTO.getName());
            academySiteTag.setAcademySite(academySite);

            this.academySiteTagRepository.save(academySiteTag);
            academySiteTags.add(academySiteTag);
        }

        academySite.setAcademySiteTags(academySiteTags);

        return this.academySiteRepository.save(academySite);
    }

    @Transactional
    public void remove(Long idx) {
        this.academySiteRepository.delete(idx);
    }

}
