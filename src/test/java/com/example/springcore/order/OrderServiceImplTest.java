package com.example.springcore.order;

import com.example.springcore.discount.FixDiscountPolicy;
import com.example.springcore.member.Grade;
import com.example.springcore.member.Member;
import com.example.springcore.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    // 수정자 주입을 사용하면, 순수 자바 코드로 테스트 시에 직접 의존성을 주입해야 한다.
    @Test
    void createOrder() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "memberA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}