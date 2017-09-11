package com.chomdan.biz.model.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foresight on 17. 7. 24.
 */
@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idx")
@Table(name = "TB_ACADEMY_SITE_MAIN")
public class AcademyMain implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "academy_main_id")
    private String academyMainId;

    @OneToMany
    @JoinColumn(name="academy_main_id", referencedColumnName = "academy_main_id")
    private List<AcademyMainBanner> academyMainBanners = new ArrayList<AcademyMainBanner>();
   


}
