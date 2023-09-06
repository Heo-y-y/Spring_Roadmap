package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("heo");
        // when
        repository.save(member);
        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("heo");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("heo1");
        repository.save(member1);

        Member result = repository.findByName("heo1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("heo1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("heo2");
        repository.save(member2);

        List <Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}