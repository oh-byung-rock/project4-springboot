package jpacalendarchallenge.jpacacha.api;

import jakarta.validation.Valid;
import jpacalendarchallenge.jpacacha.domain.Address;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody로서
// Controller는 데스크직원(대면)입니다. 주로 '웹 페이지를 반환'에 사용됩니다.
// RestController는 콜센터직원(비대면)입니다. 주로 ' HTTP 요청을 처리하고, 결과를 JSON이나 XML 같은 형태로 반환'에 사용됩니다.
@RequiredArgsConstructor
public class MemberApiController {


    private final MemberService memberService;
    // 회원가입 관련 시작
    @PostMapping("api/ver1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
//        ' @RequestBody @Valid Member member ' 설명 :
//        1. @RequestBody : 사용자가 입력한 JSON데이터를 클래스의 인스턴스로 변환
//        2. @Valid : 인스턴스로 변환후 각 요소들의 제약 조건(예를들어 id는 @nonNull)을 만족하는지 체크합니다.
//        3. 1.과 2.가 성공적으로 통과되면 최종 member 클래스의 인스턴스가 나오게됩니다.
        Long id = memberService.join(member);
        String name = member.getName();
        return new CreateMemberResponse(id,name);
    }

    @PostMapping("api/ver2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setAddress(request.getAddress());
        String name = member.getName();
        Long id = memberService.join(member); // @GeneratedValue에 의해 member에서 id가 고유식별자라 '자동'으로 Long id 값에 id가 할당되는것
        return new CreateMemberResponse(id,name);
    }

    @Data // return에대한 값 ("api/ver1/members" 와 "api/ver2/members")
    static class CreateMemberResponse {
        private Long id;
        private String name;
        public CreateMemberResponse(Long id, String name) {
            this.id = id; // this.id 가 return new CreateMemberResponse(id)로 부터 받은 회원가입한 member의 고유한 id 값
            this.name = name;
        }
    }

    @Data // "api/ver2/members" 관련 요청
    static class CreateMemberRequest {
        private String name;
        private Address address;
    }

//    정리 :
//    - ver1 : 손님이 마트 창고에서 바로 필요한 물건을 꺼내는 행위 , 만약 물건 중에서 팔면 안되는 물품을 고객이 꺼낼수있어서 보안이 취약함
//    - ver2 : 손님이 데스크에서 원하는 물품을 요청하면, 직원이 창고에서 판매가능 물건만 꺼내서 전달해줍니다. 보안측면에서 강화됩니다.
    // 회원가입 관련 끝

    // 상품 수정 시작

}

