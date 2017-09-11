package com.chomdan.api.controller.admin;

import com.chomdan.biz.model.domain.AcademySiteBanner;
import com.chomdan.biz.model.transfer.AcademySiteBannerDTO;
import com.chomdan.biz.service.AcademySiteBannerService;
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
public class AcademySiteBannerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value( "${api.upload.file.path}" )
    private String uploadPath;

    @Autowired
    AcademySiteBannerService academySiteBannerService;

    @Autowired
    AcademySiteService academySiteService;

    @GetMapping("/banners")
    public ResponseEntity<RestResultData> getBanners(
                    @RequestHeader(value = "Authorization") String accessToken,
                    @RequestParam(value = "academy_site_id") String academySiteId,
                    @RequestParam(value = "pageIndex") int pageIndex,
                    @RequestParam(value = "pageSize") int pageSize) {

        Page<AcademySiteBanner> pagingList = this.academySiteBannerService.getList(academySiteId, pageIndex, pageSize);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, pagingList, HttpStatus.OK), HttpStatus.OK);

    }

    @PostMapping("/banner")
    public ResponseEntity<RestResultData> postBanner(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam(value = "academy_site_id") String academySiteId,
            @RequestBody AcademySiteBannerDTO[] academySiteBannerDTOS
        ) {

        this.academySiteBannerService.add(academySiteId, academySiteBannerDTOS);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "CREATED", HttpStatus.CREATED), HttpStatus.CREATED);

    }

    @PutMapping("/banner")
    public ResponseEntity<RestResultData> putBanner(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam(value = "academy_site_id") String academySiteId,
            @RequestBody AcademySiteBannerDTO[] academySiteBannerDTOS
        ) {

        this.academySiteBannerService.modify(academySiteId, academySiteBannerDTOS);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "UPDATED", HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("/banner/upload")
    public ResponseEntity<RestResultData> uploadBanner(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam("files") MultipartFile[] files) {

        List<Map<String,String>> fileList = new ArrayList<Map<String,String>>();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }

            String originFileName = file.getOriginalFilename();

            String extension    = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String newFileName  = "academy_site_banner_" + UUID.randomUUID().toString() + "." + extension;
            String savePath     = this.uploadPath + "banner" + File.separator + newFileName;
            String webPath      = "/statics/banner/" + newFileName;

            logger.info(">>>>>>>>>>>>>>>> extension : " + extension);

            Map<String,String> fileInfoMap = new HashMap<String,String>();
            fileInfoMap.put("originalName", originFileName);
            fileInfoMap.put("savePath",    savePath);
            fileInfoMap.put("webPath",     webPath);

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(savePath);
                Files.write(path, bytes);
            } catch (IOException e) {
                return new ResponseEntity<RestResultData>(
                        ResponseEntityUtil.resultData(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            fileList.add(fileInfoMap);
        }

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, fileList, HttpStatus.OK), HttpStatus.OK);

    }
}