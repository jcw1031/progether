package com.woopaca.progether.service;

import com.woopaca.progether.entity.Member;
import com.woopaca.progether.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(Member member) {
        String message = checkDuplicate(member);
        if (message == null) {
            log.info("회원가입 성공");
            return memberRepository.save(member);
        }
        throw new IllegalArgumentException(message);
    }

    String checkDuplicate(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            return "중복된 이메일입니다.";
        }
        if (memberRepository.existsByName(member.getName())) {
            return "중복된 닉네임입니다.";
        }
        return null;
    }

    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
}
