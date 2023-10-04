package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberReposiotry;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생")
    void findBeanByTypeDuplicate() {
//        MemberReposiotry bean = ac.getBean(MemberReposiotry.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberReposiotry.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanNyName() {
        MemberReposiotry memberReposiotry = ac.getBean("memberRepository1", MemberReposiotry.class);
        assertThat(memberReposiotry).isInstanceOf(MemberReposiotry.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회")
    void findAllBeanByType() {
        Map<String, MemberReposiotry> beansOfType = ac.getBeansOfType(MemberReposiotry.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    // static을 사용하는 것은 클래스안에다가 클래스를 쓴 것은 해당 클래스 안에서만 쓴다는 의미
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberReposiotry memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberReposiotry memberRepository2() {
            return new MemoryMemberRepository();
        }

    }
}
