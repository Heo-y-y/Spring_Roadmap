package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberReposiotry;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberReposiotry memberRepository = ac.getBean("memberRepository", MemberReposiotry.class);

        MemberReposiotry memberRepository1 = memberService.getMemberReposiotry();
        MemberReposiotry memberRepository2 = orderService.getMemberReposiotry();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("memberService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberReposiotry()).isSameAs(memberRepository);
        assertThat(orderService.getMemberReposiotry()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());

    }
}
