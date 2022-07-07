package jpabook.utilizejpa1.service;

import jpabook.utilizejpa1.domain.Member;
import jpabook.utilizejpa1.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired    MemberService memberService;
    @Autowired    MemberRepository memberRepository;
    //@Autowired    EntityManager em;


    @Test
    @DisplayName("회원가입")
    public void MemberServiceTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("member1");

        //when
        Long saveId = memberService.join(member);

        //em.flush();   //insert문 확인하고 싶을 때 사용, 또는 rollback(false)추가하기
        //then
        //Assertions.assertThat(member.getId()).isEqualTo(saveId);
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("중복확인")
    public void MemberServiceTest2() throws Exception {
        //given
        Member member1= new Member();
        member1.setName("member1");

        Member member2 = new Member();
        member2.setName("member1");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, ()->{
            memberService.join(member2);
        });

    }
}