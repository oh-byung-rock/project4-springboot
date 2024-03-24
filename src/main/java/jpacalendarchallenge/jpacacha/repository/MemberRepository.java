package jpacalendarchallenge.jpacacha.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jpacalendarchallenge.jpacacha.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // DB 관련 작업클래스임을 선언
@RequiredArgsConstructor // lombok 기능 : 공책에 답을 적어서 제출하는거(기존)랑 스마트폰으로 사진찍어서 제출하는거(lombok)
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em; // DB에 접근하는 작업담당자
    // JPA : Java Persistence Query , Persistence 란 영속성이란 의미를 가진다.
    // 영속성이란? DB에 데이터를 사용하는것을 의미
    public void save(Member member) {
        em.persist(member);
    }
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    // query문 : Member 객체 m 을 모두 선택하여 조회
    // Member.class : 조회한 데이터를 어떤 타입으로 변환할지, 즉 조회결과를 Member 객체로 변환
    // getResultList() : 조회결과를 리스트 형태로 반환
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
    // 특정 이름을 가진 Member 객체를 조회
    // ' :name ' 이란 파라미터를 의미
    // 파라미터는 setParameter 가 해당하며, 여기선 (String name)이 해당

    // MemberRepository.java

    public Member findByUseridAndUserpw(String userid, String userpw) {
        try {
            return em.createQuery("select m from Member m where m.address.userid = :userid and m.address.userpw = :userpw", Member.class)
                    .setParameter("userid", userid)
                    .setParameter("userpw", userpw)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // 일치하는 회원이 없으면 null 반환
        }
    }

}

