package jpabook.utilizejpa1.service;

import jpabook.utilizejpa1.domain.Member;
import jpabook.utilizejpa1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * - 중복회원 검증
     * @return Long id
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if(!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * @return List<Member>
     */
    public List<Member> findAllMember(){
        return memberRepository.findALl();
    }


    /**
     * 회원 id 조회
     * @return Member
     */
    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
