package com.woopaca.progether.member;

import com.woopaca.progether.entity.Member;
import com.woopaca.progether.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    public void join() {
        //given
        Member member = Member.builder()
                .id(1L)
                .email("test@gmail.com")
                .password("testtest")
                .name("testMember").build();

        //when
        Member savedMember = memberService.join(member);
        Optional<Member> optionalMember = memberService.findMember(savedMember.getId());
        Member findMember = null;
        if (optionalMember.isPresent()) {
            findMember = optionalMember.get();
        }

        //then
        Assertions.assertThat(findMember).isEqualTo(savedMember);

    }
}
