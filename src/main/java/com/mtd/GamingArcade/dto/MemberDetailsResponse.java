package com.mtd.GamingArcade.dto;

import com.mtd.GamingArcade.entity.Game;
import com.mtd.GamingArcade.entity.Recharge;
import com.mtd.GamingArcade.entity.Member;
import com.mtd.GamingArcade.entity.Transaction; // Assuming you have a Transaction entity
import java.util.List;

// This DTO combines all the information needed for the search result.
public record MemberDetailsResponse(
    Member member,
    List<Recharge> recharge_history,
    List<Game> games,
    List<Transaction> played_history
) {}