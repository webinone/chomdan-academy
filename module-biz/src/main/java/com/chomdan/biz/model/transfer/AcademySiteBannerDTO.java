package com.chomdan.biz.model.transfer;

import lombok.Data;

/**
 * Created by foresight on 17. 7. 31.
 */
@Data
public class AcademySiteBannerDTO extends BaseDTO  {

    private String bannerId;

    private String title;

    private String description;

    private String linkUrl;

    private String originalName;

    private String savePath;

    private String webPath;

}
