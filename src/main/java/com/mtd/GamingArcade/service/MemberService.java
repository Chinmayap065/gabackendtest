package com.mtd.GamingArcade.service;

import com.mtd.GamingArcade.dto.CreateMemberRequest;
import com.mtd.GamingArcade.dto.MemberDetailsResponse;
import com.mtd.GamingArcade.entity.*;
import com.mtd.GamingArcade.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final RechargeRepository rechargeRepository;
    private final GameRepository gameRepository;
    private final TransactionRepository transactionRepository;

    public MemberService(MemberRepository memberRepository, RechargeRepository rechargeRepository, GameRepository gameRepository, TransactionRepository transactionRepository) {
        this.memberRepository = memberRepository;
        this.rechargeRepository = rechargeRepository;
        this.gameRepository = gameRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Member createMember(CreateMemberRequest request) {
        memberRepository.findByPhone(request.phone()).ifPresent(m -> {
            throw new IllegalStateException("Member with phone number exists.");
        });
        Member member = new Member();
        member.setName(request.name());
        member.setPhone(request.phone());
        member.setBalance(request.fee());
        Member savedMember = memberRepository.save(member);
        Recharge initialRecharge = new Recharge();
        initialRecharge.setMemberId(savedMember.getId());
        initialRecharge.setAmount(request.fee());
        rechargeRepository.save(initialRecharge);
        return savedMember;
    }

    public MemberDetailsResponse searchMemberByPhone(String phone) {
        Member member = memberRepository.findByPhone(phone)
            .orElseThrow(() -> new RuntimeException("Member not found"));
        List<Recharge> recharges = rechargeRepository.findByMemberId(member.getId());
        List<Game> allGames = gameRepository.findAll();
        List<Transaction> transactions = transactionRepository.findByMemberIdOrderByDateTimeDesc(member.getId());
        return new MemberDetailsResponse(member, recharges, allGames, transactions);
    }
}