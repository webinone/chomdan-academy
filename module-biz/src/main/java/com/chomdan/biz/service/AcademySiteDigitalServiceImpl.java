package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteDigital;
import com.chomdan.biz.model.domain.enums.AcademySiteDigitalType;
import com.chomdan.biz.model.transfer.AcademySiteDigitalDTO;
import com.chomdan.biz.repository.AcademySiteDigitalRepository;
import com.chomdan.biz.repository.AcademySiteDigitalRepositorySupport;
import com.chomdan.biz.repository.AcademySiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

/**
 * Created by foresight on 17. 7. 24.
 */
@Service
public class AcademySiteDigitalServiceImpl implements AcademySiteDigitalService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AcademySiteRepository academySiteRepository;

    @Autowired
    AcademySiteDigitalRepository academySiteDigitalRepository;

    @Autowired
    AcademySiteDigitalRepositorySupport academySiteDigitalRepositorySupport;

    @Transactional(readOnly = true)
    public Page<AcademySiteDigital> getList(String _academySiteId, String _type, String _title, int _pageIndex, int _pageSize) {
        return this.academySiteDigitalRepositorySupport.findSearch(_academySiteId, _type, _title, _pageIndex, _pageSize);
    }

    @Transactional
    public void add(AcademySiteDigitalDTO dto) {

        AcademySite academySite = this.academySiteRepository.findByAcademySiteId(dto.getAcademySiteId());

        AcademySiteDigital academySiteDigital = new AcademySiteDigital();
        academySiteDigital.setAcademySite(academySite);
        academySiteDigital.setDigitalId(UUID.randomUUID().toString());

        if ("0".equals(dto.getType())) {
            academySiteDigital.setType(AcademySiteDigitalType.IMAGE);
        }

        if ("1".equals(dto.getType())) {
            academySiteDigital.setType(AcademySiteDigitalType.VIDEO);
        }

        academySiteDigital.setTitle(dto.getTitle());
        academySiteDigital.setContent(dto.getContent());

        academySiteDigital.setVideoUrl(dto.getVideoUrl());
        academySiteDigital.setOriginalName(dto.getOriginalName());
        academySiteDigital.setSavePath(dto.getSavePath());
        academySiteDigital.setWebPath(dto.getWebPath());

        this.academySiteDigitalRepository.save(academySiteDigital);

    }

    @Transactional
    public void modify(Long _idx, AcademySiteDigitalDTO dto) {

        AcademySiteDigital academySiteDigital = this.academySiteDigitalRepository.findOne(_idx);

        if ("0".equals(dto.getType())) {
            academySiteDigital.setType(AcademySiteDigitalType.IMAGE);
        }

        if ("1".equals(dto.getType())) {
            academySiteDigital.setType(AcademySiteDigitalType.VIDEO);
        }

        academySiteDigital.setTitle(dto.getTitle());
        academySiteDigital.setContent(dto.getContent());

        academySiteDigital.setVideoUrl(dto.getVideoUrl());
        academySiteDigital.setOriginalName(dto.getOriginalName());
        academySiteDigital.setSavePath(dto.getSavePath());
        academySiteDigital.setWebPath(dto.getWebPath());

        this.academySiteDigitalRepository.save(academySiteDigital);
    }

    @Transactional
    public void remove(Long _idx) {
        this.academySiteDigitalRepository.delete(_idx);
    }

}
