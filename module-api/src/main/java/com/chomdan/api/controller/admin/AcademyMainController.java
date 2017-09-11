package com.chomdan.api.controller.admin;

import com.chomdan.biz.model.domain.AcademyMainBanner;
import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.transfer.AcademyMainBannerDTO;
import com.chomdan.biz.model.transfer.AcademySiteBannerDTO;
import com.chomdan.biz.model.transfer.AcademySiteDTO;
import com.chomdan.biz.service.AcademyMainBannerService;
import com.chomdan.biz.service.AcademySiteService;
import com.chomdan.shared.http.ResponseEntityUtil;
import com.chomdan.shared.http.RestResultData;
import com.chomdan.shared.security.jwt.TokenAdminProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by foresight on 17. 7. 26.
 */
@RestController
@RequestMapping("/api/v1/academy/admin")
public class AcademyMainController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value( "${api.upload.file.path}" )
    private String bannerPath;

    @Autowired
    private AcademyMainBannerService academyMainBannerService;

    @Autowired
    private TokenAdminProvider tokenAdminProvider;

    @PostMapping("/main/upload/banner")
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
            String newFileName  = "academy_banner_" + UUID.randomUUID().toString() + "." + extension;
            String savePath     = this.bannerPath + "banner" + File.separator + newFileName;
            String webPath      = "/statics/banner/" + newFileName;
            

            logger.info(">>>>>>>>>>>>>>>> extension : " + extension);

            Map<String,String> fileInfoMap = new HashMap<String,String>();
            fileInfoMap.put("save_path", savePath);
            fileInfoMap.put("web_path", webPath);

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

    @GetMapping("/main/download/banner")
    public ResponseEntity<InputStreamResource> downloadFile(
                @RequestHeader(value = "Authorization") String accessToken,
                @RequestParam("file_idx") String bannerType) throws FileNotFoundException {

        // String filePath = this.bannerPath + "banner" + File.separator + "academy_banner_2be4ca4b-f06c-4e6b-b429-e3769a6a1e72.png";
        String filePath = this.bannerPath + "banner" + File.separator + "academy_banner_2be4ca4b-f06c-4e6b-b429-e3769a6a1e723.png";
        
        logger.debug("########################## filePath : " + filePath);
        File file = new File (filePath);

        if (!file.exists()) {
            // 존재하지 않는경우.. .
            throw new FileNotFoundException(" FILE NOT FOUND !!");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "filename=" + file.getName());

        return new ResponseEntity<InputStreamResource>(
            new InputStreamResource(new FileInputStream(file)), headers, HttpStatus.OK);
    }

    @PostMapping("/main/banner")
    public ResponseEntity<RestResultData> postMainBanner(@RequestHeader(value = "Authorization") String accessToken,
                                                         RequestEntity<AcademyMainBannerDTO[]> requestEntity) {
        
        this.academyMainBannerService.addAcademyMainBanner(requestEntity.getBody());
            
        return new ResponseEntity<RestResultData>(
            ResponseEntityUtil.resultData(true, "CREATED", HttpStatus.CREATED), HttpStatus.CREATED);
    }


    @PutMapping("/main/banner")
    public ResponseEntity<RestResultData> putBanner(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestBody AcademyMainBannerDTO[] academyMainBannerDTOS
    ) {

        this.academyMainBannerService.modifyAcademyMainBanner(academyMainBannerDTOS);

        return new ResponseEntity<RestResultData>(
                ResponseEntityUtil.resultData(true, "UPDATED", HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/main/banners")
    public ResponseEntity<RestResultData> getMainBanners(
            @RequestHeader(value = "Authorization") String accessToken,
            @RequestParam(value = "type",  defaultValue = "", required = false) String type,
            @RequestParam(value = "pageIndex") int pageIndex,
            @RequestParam(value = "pageSize") int pageSize) {

        Page<AcademyMainBanner> pagingList = this.academyMainBannerService.getList(type, pageIndex, pageSize);

        return new ResponseEntity<RestResultData>(
                    ResponseEntityUtil.resultData(true, pagingList, HttpStatus.OK), HttpStatus.OK);
    }


}
