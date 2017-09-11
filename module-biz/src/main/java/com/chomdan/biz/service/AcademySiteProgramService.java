package com.chomdan.biz.service;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import com.chomdan.biz.model.transfer.AcademySiteProgramDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by foresight on 17. 7. 24.
 */
public interface AcademySiteProgramService {

    Page<AcademySiteProgram> getList(String _acadmeySiteId, int _pageIndex, int _pageSize);

    void add(List<AcademySiteProgramDTO> _academySiteProgramDTOs);

    void modify(String _academySiteId, List<AcademySiteProgramDTO> _academySiteProgramDTOs);

    void remove(Long idx);

    AcademySiteProgram getByIdx(Long idx);

}
