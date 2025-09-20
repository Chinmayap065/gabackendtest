package com.mtd.GamingArcade.service;

import com.mtd.GamingArcade.dto.CreateMemberRequest;
import com.mtd.GamingArcade.dto.MemberDetailsDto;
import com.mtd.GamingArcade.entity.*;
import com.mtd.GamingArcade.exception.ResourceNotFoundException;
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

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public MemberDetailsDto searchMemberByPhone(String phone) {
        Member member = memberRepository.findByPhone(phone)
            .orElseThrow(() -> new ResourceNotFoundException("Member not found with phone: " + phone));

        List<Recharge> recharges = rechargeRepository.findByMemberId(member.getId());
        List<Game> allGames = gameRepository.findAll();
        List<Transaction> transactions = transactionRepository.findByMemberIdOrderByDateTimeDesc(member.getId());

        return new MemberDetailsDto(member, recharges, allGames, transactions);
    }
    
    @Transactional
    public Member createMember(CreateMemberRequest request) {
        memberRepository.findByPhone(request.phone()).ifPresent(m -> {
            throw new IllegalStateException("Member with this phone number already exists.");
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
    
    // --- ADD THIS MISSING METHOD ---
    @Transactional
    public Member rechargeMember(String phone, double amount) {
        Member member = memberRepository.findByPhone(phone)
            .orElseThrow(() -> new ResourceNotFoundException("Member not found with phone: " + phone));

        // Add the recharge amount to the member's current balance
        member.setBalance(member.getBalance() + amount);
        Member updatedMember = memberRepository.save(member);

        // Create a new recharge record for history
        Recharge recharge = new Recharge();
        recharge.setMemberId(updatedMember.getId());
        recharge.setAmount(amount);
        rechargeRepository.save(recharge);

        return updatedMember;
    }
}