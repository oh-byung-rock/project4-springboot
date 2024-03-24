package jpacalendarchallenge.jpacacha.service;

import jakarta.persistence.EntityManager;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest // 얘랑 위에꺼랑 묶어서 선언해야 스프링 부트 테스트 가능
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void joinmember() throws Exception{
        // 이런 경우에
        Member member = new Member(); // 새로운 Member.java 를 생성후 member라고 이름을 지어줍니다.
        member.setName("kim"); // member에게 'kim'이란 이름을 지어줍니다.

        // 이렇게 한다면
        Long saveId = memberService.join(member); // member 친구를 memberservice에 가입시킵니다. 가입이 되면 saveid라는 정수(Long)인 고유번호를 받게됩니다.

        // 이런 결과가 나오게해라
        assertEquals(member,memberRepository.findOne(saveId)); // memberrepository에서 saveid를 찾아서 우리가 가입시킨 member와 같은지 확인합니다.
    }
    @Test(expected = IllegalStateException.class) // IllegalStateException : try - catch 기능
    public void duplmember() throws Exception {
        // 이런 경우에
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        // 이렇게 한다면
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다

        // 이런 결과가 나오게해라

    }
}