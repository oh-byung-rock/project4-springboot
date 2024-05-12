package jpacalendarchallenge.jpacacha.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import jakarta.persistence.*;

@Embeddable
@Getter
public class Address {

    private String userid;

    private String userpw;

    protected Address() {} // 설령 밑에 Address(1,2,3)만 사용해도 JPA에서 엔티티 상태 확인할때는 사용유무상관없이 기본생성자가 필수조건으로 선언해야함

    public Address(String userid, String userpw) {
        this.userid = userid;
        this.userpw = userpw;
    } // 처음 실행될떄 한번 값이 저장되고 수정(setter)은 안된다.
}
