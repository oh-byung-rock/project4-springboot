package jpacalendarchallenge.jpacacha.service;

import jpacalendarchallenge.jpacacha.domain.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 회원가입, 로그인 같은 서비스 기능
@Transactional(readOnly = true) // MemberSerive 내 모든 private에 readonly적용, 단, join에는 또 트랜잭션 걸어서 예외시킴
@RequiredArgsConstructor // final로 선언되거나 @NonNull에만 생성자(this.)를 생성
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional // join에서는 '중복회원검증 + 회원정보저장'을 Transactional으로 하나로 묶어서 처리
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증

        if ("456".equals(member.getAddress().getUserid())) {
            member.setRole(MemberStatus.ADMIN); // 특정 userid에만 ADMIN 권한 부여
        } else {
            member.setRole(MemberStatus.USER); // 나머지는 USER 권한
        }
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 단일 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

// MemberService.java

    public Member validateMember(String userid, String userpw) {
        Member member = memberRepository.findByUseridAndUserpw(userid, userpw);
        return member; // 일치하는 회원 객체 반환, 일치하는 회원이 없으면 null 반환
    }

}
