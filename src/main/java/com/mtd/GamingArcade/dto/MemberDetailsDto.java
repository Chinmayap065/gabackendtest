package com.mtd.GamingArcade.dto;

import com.mtd.GamingArcade.entity.Game;
import com.mtd.GamingArcade.entity.Member;
import com.mtd.GamingArcade.entity.Recharge;
import com.mtd.GamingArcade.entity.Transaction;
import java.util.List;

public record MemberDetailsDto(
    Member member,
    List<Recharge> rechargeHistory,
    List<Game> games,
    List<Transaction> playedHistory
) {}