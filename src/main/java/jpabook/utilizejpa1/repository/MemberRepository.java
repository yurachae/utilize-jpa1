package jpabook.utilizejpa1.repository;

import jpabook.utilizejpa1.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //스프링에서 EntityManger 주입시켜 주는 역할
    private final EntityManager em;

    //등록
    public void save(Member member){
        em.persist(member);
    }

    //단건 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //전체 조회
    public List<Member> findALl(){
        return em.createQuery("select m from Member m ", Member.class)
                .getResultList();
    }

    //이름으로 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}
