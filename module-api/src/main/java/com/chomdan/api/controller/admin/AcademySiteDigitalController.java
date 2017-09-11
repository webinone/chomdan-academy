package com.chomdan.api.controller.admin;

import com.chomdan.biz.model.domain.AcademySiteDigital;
import com.chomdan.biz.model.transfer.AcademySiteDigitalDTO;
import com.chomdan.biz.service.AcademySiteDigitalService;
import com.chomdan.biz.service.AcademySiteService;
import com.chomdan.shared.http.ResponseEntityUtil;
import com.chomdan.shared.http.RestResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by foresight on 17. 7. 26.
 */
@RestController
@RequestMapping("/api/v1/academy/admin")
public class AcademySiteDigitalController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value( "${api.upload.file.path}" )
    private String uploadPath;

    @Autowired
    AcademySiteDigitalService academySiteDigitalService;

    @Autowired
    AcademySiteService academySiteService;

    @GetMapping("/digitals")
    public ResponseEntity<RestResultData> getDigitals(
                    @RequestHeader(value = "Authorization") String accessToken,
                    @RequestParam(value = "academy_site_id") String academySiteId,
                    @RequestParam(value = "type")   String type,
                    @RequestParam(value = "title")  String title,
                    @RequestParam(value = "pageIndex") int pageIndex,
                    @RequestParam(value = "pageSize") int pageSize) {

        Page<AcademySiteDigital> pagingList = this.academySiteDigitalService.getList(academySiteId, type, title, pageIndex, pageSize);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, pagingList, HttpStatus.OK), HttpStatus.OK);

    }

    @PostMapping("/digital")
    public ResponseEntity<RestResultData> postDigital(
                    @RequestHeader(value = "Authorization") String accessToken,
                    @RequestBody AcademySiteDigitalDTO academySiteDigitalDTO
                ) {

        this.academySiteDigitalService.add(academySiteDigitalDTO);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "CREATED", HttpStatus.CREATED), HttpStatus.CREATED);

    }

    @PutMapping("/digital/{idx}")
    public ResponseEntity<RestResultData> putDigital(
                    @RequestHeader(value = "Authorization") String accessToken,
                    @PathVariable Long idx,
                    @RequestBody AcademySiteDigitalDTO academySiteDigitalDTO
                ) {

        this.academySiteDigitalService.modify(idx, academySiteDigitalDTO);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "UPDATED", HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/digital/{idx}")
    public ResponseEntity<RestResultData> deleteDigital(
            @RequestHeader(value = "Authorization") String accessToken,
            @PathVariable Long idx
        ) {

        this.academySiteDigitalService.remove(idx);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "DELETED", HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("/digital/upload")
    public ResponseEntity<RestResultData> uploadDigital(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam("files") MultipartFile[] files) {

        List<Map<String,String>> fileList = new ArrayList<Map<String,String>>();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }

            String originFileName = file.getOriginalFilename();

            String extension    = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String newFileName  = "academy_site_digital_" + UUID.randomUUID().toString() + "." + extension;
            String savePath     = this.uploadPath + "digital" + File.separator + newFileName;
            String webPath      = "/statics/digital/" + newFileName;

            logger.info(">>>>>>>>>>>>>>>> extension : " + extension);

            Map<String,String> fileInfoMap = new HashMap<String,String>();
            fileInfoMap.put("original_name", originFileName);
            fileInfoMap.put("save_path",    savePath);
            fileInfoMap.put("web_path",     webPath);

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(savePath);
                Files.write(path, bytes);
            } catch (IOException e) {
                //TODO: handle exception
                return new ResponseEntity<RestResultData>(
                        ResponseEntityUtil.resultData(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            fileList.add(fileInfoMap);
        }

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, fileList, HttpStatus.OK), HttpStatus.OK);

    }
}