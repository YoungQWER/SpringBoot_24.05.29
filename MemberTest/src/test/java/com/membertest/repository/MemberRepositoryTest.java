package com.membertest.repository;


import com.membertest.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testSample(){
        log.info("memberRepository : " + memberRepository);
    }

    //추가
    @Test
    public void memberInsert(){

        for(int i=0; i<10; i++){
            Member member = Member.builder()
                    .name("user"+i)
                    .age(i+5)
                    .phone("010-1111-2222")
                    .address("안산시 상록구"+i)
                    .build();

            memberRepository.save(member);
        }
    }

    //단건 조회
    @Test
    public void memberGetOne(){

        Long memberId = 1L;

        //Ctrl+alt+v
        //Optional<Member> result = memberRepository.findById(memberId);

        //null값일때

        Member member = memberRepository
                .findById(memberId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found : " + memberId)
                );

//        Member member = memberRepository.findById(memberId).get();
        log.info(member);
    }

    //리스트 불러오기
    @Test
    public void memberList(){
        List<Member> members = memberRepository.findAll();
        log.info(members);
    }

    //전체 데이터 페이징 처리
    @Test
    public void memberPaging(){
        Pageable pageable =  PageRequest.of(1,5);

        Page<Member> result = memberRepository.findAll(pageable);

        log.info("getTotalElements : " + result.getTotalElements());
        log.info("getTotalPages : " + result.getTotalPages());
        log.info("getSize : " + result.getSize());
        log.info("getContent : " + result.getContent());
    }

    //삭제
    @Test
    public void memberDelete(){
        Long memberId = 13L;

        memberRepository.deleteById(memberId);

    }

    //업데이트
    @Test
    public void memberUpdate(){
        Member member = Member.builder()
                .name("수정")
                .age(4)
                .phone("010-3333-4444")
                .address("경기도 수원시 팔달구")
                .id(12L)
                .build();

        //저장 할 때, 수정 할 때 -> save
        memberRepository.save(member);
    }
}