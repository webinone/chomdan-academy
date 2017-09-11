package com.chomdan.api.controller.admin;

import com.chomdan.biz.model.domain.AcademySiteProgram;
import com.chomdan.biz.model.transfer.AcademySiteProgramDTO;
import com.chomdan.biz.service.AcademySiteProgramService;
import com.chomdan.biz.service.AcademySiteService;
import com.chomdan.shared.http.ResponseEntityUtil;
import com.chomdan.shared.http.RestResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by foresight on 17. 7. 26.
 */
@RestController
@RequestMapping("/api/v1/academy/admin")
public class AcademySiteProgramController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AcademySiteProgramService academySiteProgramService;

    @Autowired
    AcademySiteService academySiteService;

    @GetMapping("/programs")
    public ResponseEntity<RestResultData> getPrograms(
                    @RequestHeader(value = "Authorization") String accessToken,
                    @RequestParam(value = "academy_site_id") String academySiteId,
                    @RequestParam(value = "pageIndex") int pageIndex,
                    @RequestParam(value = "pageSize") int pageSize) {

        Page<AcademySiteProgram> programList = this.academySiteProgramService.getList(academySiteId, pageIndex, pageSize);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, programList, HttpStatus.OK), HttpStatus.OK);

    }

    @PostMapping("/program")
    public ResponseEntity<RestResultData> postProgram(RequestEntity<List<AcademySiteProgramDTO>> requestEntity) {

        List<AcademySiteProgramDTO> academySiteProgramDTOs = requestEntity.getBody();

        this.academySiteProgramService.add(academySiteProgramDTOs);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "CREATED", HttpStatus.CREATED), HttpStatus.CREATED);

    }


    @GetMapping("/program/{idx}")
    public ResponseEntity<RestResultData> getSite(@PathVariable Long idx) {

        AcademySiteProgram academySiteProgram = this.academySiteProgramService.getByIdx(idx);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, academySiteProgram, HttpStatus.OK), HttpStatus.OK);

    }

    @PutMapping("/program")
    public ResponseEntity<RestResultData> putProgram(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam(value = "academy_site_id") String academySiteId,
            RequestEntity<List<AcademySiteProgramDTO>> requestEntity) {

        List<AcademySiteProgramDTO> academySiteProgramDTOs = requestEntity.getBody();

        this.academySiteProgramService.modify(academySiteId, academySiteProgramDTOs);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "UPDATED", HttpStatus.OK), HttpStatus.OK);

    }
}