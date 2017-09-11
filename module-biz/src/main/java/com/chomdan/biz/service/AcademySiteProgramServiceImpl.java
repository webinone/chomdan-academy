package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import com.chomdan.biz.model.transfer.AcademySiteProgramDTO;
import com.chomdan.biz.repository.AcademySiteProgramRepository;
import com.chomdan.biz.repository.AcademySiteProgramRepositorySupport;
import com.chomdan.biz.repository.AcademySiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
 
/**
 * Created by foresight on 17. 7. 24.
 */
@Service
public class AcademySiteProgramServiceImpl implements AcademySiteProgramService {

    @Autowired
    AcademySiteRepository academySiteRepository;

    @Autowired
    AcademySiteProgramRepository academySiteProgramRepository;

    @Autowired
    AcademySiteProgramRepositorySupport academySiteProgramRepositorySupport;

    @Transactional(readOnly = true)
    public Page<AcademySiteProgram> getList(String _acadmeySiteId, int _pageIndex, int _pageSize) {

        return this.academySiteProgramRepositorySupport.findSearch(_acadmeySiteId, _pageIndex, _pageSize);
    }

    @Transactional
    public void add(List<AcademySiteProgramDTO> _academySiteProgramDTOs) {

        if (_academySiteProgramDTOs != null && _academySiteProgramDTOs.size() > 0) {

            AcademySite academySite = this.academySiteRepository.findByAcademySiteId(_academySiteProgramDTOs.get(0).getAcademySiteId());

            int programOrder = 1;

            for (AcademySiteProgramDTO academySiteProgramDTO : _academySiteProgramDTOs) {

                AcademySiteProgram academySiteProgram = new AcademySiteProgram();
                academySiteProgram.setProgramId(UUID.randomUUID().toString());
                academySiteProgram.setTitle(academySiteProgramDTO.getTitle());
                academySiteProgram.setContent(academySiteProgramDTO.getContent());
                academySiteProgram.setProgramOrder(programOrder);

                academySiteProgram.setAcademySite(academySite);

                this.academySiteProgramRepository.save(academySiteProgram);

                programOrder++;

            }
        }

    }

    @Transactional
    public void modify(String _academySiteId, List<AcademySiteProgramDTO> _academySiteProgramDTOs) {

        // 모두 삭제후 재입력
        this.academySiteProgramRepository.deleteByAcademySiteId(_academySiteId);

        if (_academySiteProgramDTOs != null && _academySiteProgramDTOs.size() > 0) {

            AcademySite academySite = this.academySiteRepository.findByAcademySiteId(_academySiteId);

            int programOrder = 1;

            for (AcademySiteProgramDTO academySiteProgramDTO : _academySiteProgramDTOs) {

                AcademySiteProgram academySiteProgram = new AcademySiteProgram();
                academySiteProgram.setProgramId(UUID.randomUUID().toString());
                academySiteProgram.setTitle(academySiteProgramDTO.getTitle());
                academySiteProgram.setContent(academySiteProgramDTO.getContent());
                academySiteProgram.setProgramOrder(programOrder);

                academySiteProgram.setAcademySite(academySite);

                this.academySiteProgramRepository.save(academySiteProgram);

                programOrder++;

            }
        }
    }

    @Transactional
    public void remove(Long idx) {
        this.academySiteProgramRepository.delete(idx);

    }

    @Transactional(readOnly = true)
    public AcademySiteProgram getByIdx(Long idx) {
        return this.academySiteProgramRepository.findOne(idx);
    }
}
