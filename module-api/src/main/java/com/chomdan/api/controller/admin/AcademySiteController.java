package com.chomdan.api.controller.admin;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.transfer.AcademySiteDTO;
import com.chomdan.biz.service.AcademySiteService;
import com.chomdan.shared.http.ResponseEntityUtil;
import com.chomdan.shared.http.RestResultData;
import com.chomdan.shared.security.jwt.TokenAdminProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
public class AcademySiteController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value( "${api.upload.file.path}" )
    private String uploadPath;

    @Autowired
    private AcademySiteService academySiteService;

    @Autowired
    private TokenAdminProvider tokenAdminProvider;

    @GetMapping("/sites")
    public ResponseEntity<RestResultData> getSites(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "title",  defaultValue = "", required = false) String title,
            @RequestParam(value = "pageIndex") int pageIndex,
            @RequestParam(value = "pageSize") int pageSize) {

        System.out.println(this.tokenAdminProvider.getUserId(accessToken));

        Page<AcademySite> pagingList = this.academySiteService.getList(type, title, status, pageIndex, pageSize);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, pagingList, HttpStatus.OK), HttpStatus.OK );
    }

    @PostMapping("/site")
    public ResponseEntity<RestResultData> postSite(
            @RequestHeader(value = "Authorization") String accessToken,
            @Valid @RequestBody AcademySiteDTO academySiteDTO) {

        logger.debug(academySiteDTO.toString());

        this.academySiteService.addAcademySite(academySiteDTO);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "CREATED", HttpStatus.CREATED), HttpStatus.CREATED);

    }

    @PutMapping("/site/{idx}")
    public ResponseEntity<RestResultData> putSite(@PathVariable Long idx, RequestEntity<AcademySiteDTO> requestEntity) {

        AcademySiteDTO academySiteDTO = requestEntity.getBody();
        academySiteDTO.setIdx(idx);
        logger.debug(academySiteDTO.toString());

        this.academySiteService.modifyAcademySite(academySiteDTO);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "UPDATED", HttpStatus.OK), HttpStatus.OK);

    }

    @DeleteMapping("/site/{idx}")
    public ResponseEntity<RestResultData> deleteSite(@PathVariable Long idx) {

        this.academySiteService.remove(idx);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "DELETED", HttpStatus.OK), HttpStatus.OK);

    }

    @GetMapping("/site/{idx}")
    public ResponseEntity<RestResultData> getSite(@PathVariable Long idx) {

        AcademySite academySite = this.academySiteService.findByIdx(idx);

        logger.debug(academySite.toString());

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, academySite, HttpStatus.OK), HttpStatus.OK);

    }

    @PostMapping("/site/upload/thumbnail")
    public ResponseEntity<RestResultData> uploadThumbnail (
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam("files") MultipartFile[] files) {

        List<Map<String,String>> fileList = new ArrayList<Map<String,String>>();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }

            String originFileName = file.getOriginalFilename();

            String extension    = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String newFileName  = "academy_site_thumb_" + UUID.randomUUID().toString() + "." + extension;
            String savePath     = this.uploadPath + "thumbnail" + File.separator + newFileName;
            String webPath      = "/statics/thumbnail/" + newFileName;

            logger.info(">>>>>>>>>>>>>>>> extension : " + extension);

            Map<String,String> fileInfoMap = new HashMap<String,String>();
            fileInfoMap.put("originialName", originFileName);
            fileInfoMap.put("savePath",    savePath);
            fileInfoMap.put("webPath",     webPath);

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
