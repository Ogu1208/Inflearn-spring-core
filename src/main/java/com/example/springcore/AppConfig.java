package com.example.springcore;

import com.example.springcore.discount.DiscountPolicy;
import com.example.springcore.discount.RateDiscountPolicy;
import com.example.springcore.member.MemberRepository;
import com.example.springcore.member.MemberService;
import com.example.springcore.member.MemberServiceImpl;
import com.example.springcore.member.MemoryMemberRepository;
import com.example.springcore.order.OrderService;
import com.example.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 1. call AppConfig.memberService (memberService() 호출)
    // call AppConfig.memberRepository
    // 2. call AppConfig.memberRepository (memberRepository() 호출)
    // 3. call AppConfig.orderService
    // call AppConfig.memberRepository
    // --------- 실제 호출 디버깅 : 메서드가 한번씩만 호출됨
    // 1. call AppConfig.memberService
    // 2. call AppConfig.memberRepository
    // 3. call AppConfig.orderService

    // 메서드 이름으로 스프링 빈의 이름을 설정한다.
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
