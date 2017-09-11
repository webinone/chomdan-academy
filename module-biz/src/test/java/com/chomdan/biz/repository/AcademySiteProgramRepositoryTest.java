package com.chomdan.biz.repository;

import com.chomdan.biz.config.BizConfig;
import com.chomdan.biz.model.domain.AcademySiteProgram;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;
//import org.springframework.test.

/**
 * Created by foresight on 17. 7. 26.
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BizConfig.class)
public class AcademySiteProgramRepositoryTest {

    @Autowired
    AcademySiteProgramRepository academySiteProgramRepository;

    @Before
    public void setUp() {}

    @Test
    public void addAcademyProgram() {

        String program_id = UUID.randomUUID().toString();

        AcademySiteProgram academySiteProgram = new AcademySiteProgram();
        academySiteProgram.setProgramId(program_id);
        academySiteProgram.setTitle("제목");
        academySiteProgram.setContent("내용");
        academySiteProgram.setDescription("설명이다!!!!");

        this.academySiteProgramRepository.save(academySiteProgram);
    }
}
