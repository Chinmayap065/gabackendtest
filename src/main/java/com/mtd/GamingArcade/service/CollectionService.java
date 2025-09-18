package com.mtd.GamingArcade.service;

import com.mtd.GamingArcade.dto.CollectionResponseDto;
import com.mtd.GamingArcade.dto.DailyCollectionDto;
import com.mtd.GamingArcade.entity.Member;
import com.mtd.GamingArcade.entity.Recharge;
import com.mtd.GamingArcade.entity.Transaction;
import com.mtd.GamingArcade.repository.MemberRepository;
import com.mtd.GamingArcade.repository.RechargeRepository;
import com.mtd.GamingArcade.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
@Service
public class CollectionService {

    private final RechargeRepository rechargeRepository;
    private final TransactionRepository transactionRepository;
    private final MemberRepository memberRepository;

    public CollectionService(RechargeRepository rechargeRepository, TransactionRepository transactionRepository, MemberRepository memberRepository) {
        this.rechargeRepository = rechargeRepository;
        this.transactionRepository = transactionRepository;
        this.memberRepository = memberRepository;
    }

    public CollectionResponseDto getDailyCollection(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        // Fetch all recharges and transactions for the given day
        List<Recharge> recharges = rechargeRepository.findByDateTimeBetween(startOfDay, endOfDay);
        List<Transaction> transactions = transactionRepository.findByDateTimeBetween(startOfDay, endOfDay);

        // Fetch all members to easily get their names without multiple DB calls
        Map<String, String> memberIdToNameMap = memberRepository.findAll().stream()
                .collect(Collectors.toMap(Member::getId, Member::getName));
        
        List<DailyCollectionDto> activities = new ArrayList<>();

        // Process recharges
        recharges.forEach(recharge -> activities.add(new DailyCollectionDto(
                "RECHARGE",
                memberIdToNameMap.getOrDefault(recharge.getMemberId(), "Unknown Member"),
                recharge.getAmount(),
                recharge.getDateTime()
        )));

        // Process game plays (transactions)
        transactions.forEach(transaction -> activities.add(new DailyCollectionDto(
                "GAME_PLAY",
                memberIdToNameMap.getOrDefault(transaction.getMemberId(), "Unknown Member"),
                transaction.getAmount(),
                transaction.getDateTime()
        )));

        // Calculate total collection (sum of all recharges and game plays)
        double total = activities.stream().mapToDouble(DailyCollectionDto::amount).sum();

        return new CollectionResponseDto(date, total, activities);
    }
}