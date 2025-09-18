package com.mtd.GamingArcade.service;

import com.mtd.GamingArcade.entity.Game;
import com.mtd.GamingArcade.entity.Member;
import com.mtd.GamingArcade.entity.Transaction;
import com.mtd.GamingArcade.repository.GameRepository;
import com.mtd.GamingArcade.repository.MemberRepository;
import com.mtd.GamingArcade.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(MemberRepository memberRepository, GameRepository gameRepository, TransactionRepository transactionRepository) {
        this.memberRepository = memberRepository;
        this.gameRepository = gameRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public String playGame(String memberId, String gameId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));

        if (member.getBalance() < game.getPrice()) {
            return "Insufficient balance!";
        }

        // Deduct balance and save
        member.setBalance(member.getBalance() - game.getPrice());
        memberRepository.save(member);

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setMemberId(memberId);
        transaction.setGameId(gameId);
        transaction.setGameName(game.getName());
        transaction.setAmount(game.getPrice());
        transactionRepository.save(transaction);

        return "Game played successfully!";
    }
}