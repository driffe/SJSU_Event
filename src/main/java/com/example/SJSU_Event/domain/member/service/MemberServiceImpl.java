package com.example.SJSU_Event.domain.member.service;

import com.example.SJSU_Event.domain.member.entity.Member;
import com.example.SJSU_Event.domain.member.entity.Role;
import com.example.SJSU_Event.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Transactional
    @Override
    public Long userRegister(String name) {
        Member member = Member.of(String.valueOf(UUID.randomUUID()), name, Role.USER);
        return memberRepository.save(member).getId();
    }
    @Transactional
    @Override
    public Long adminRegister(String name) {
        Member member = Member.of(String.valueOf(UUID.randomUUID()), name, Role.USER);
        return memberRepository.save(member).getId();
    }

    @Transactional
    @Override
    public Long updateInfo(Long memberId, String name) {
        Member member = Member.of(String.valueOf(UUID.randomUUID()), name, Role.USER);
        member.modifyInfo(name);
        return member.getId();
    }

    @Transactional
    @Override
    public void deleteMember(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        // 해당 username에 맞는 회원이 있으면 삭제
        if (memberOptional.isPresent()) {
            memberRepository.delete(memberId);
        } else {
            throw new RuntimeException("Member not found with username: " + memberId);
        }
    }

    // getByUsername: username을 기준으로 회원 조회
    @Transactional(readOnly = true)
    @Override
    public Member getByUsername(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with username: " + memberId));
    }
}