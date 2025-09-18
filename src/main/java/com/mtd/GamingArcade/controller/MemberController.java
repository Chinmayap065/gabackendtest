package com.mtd.GamingArcade.controller;

import com.mtd.GamingArcade.dto.CreateMemberRequest;
import com.mtd.GamingArcade.dto.MemberDetailsResponse;
import com.mtd.GamingArcade.dto.SearchMemberRequest;
import com.mtd.GamingArcade.entity.Member;
import com.mtd.GamingArcade.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> createNewMembership(@RequestBody CreateMemberRequest request) {
        try {
            Member newMember = memberService.createMember(request);
            return new ResponseEntity<>(newMember, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchMember(@RequestBody SearchMemberRequest request) {
        try {
            MemberDetailsResponse details = memberService.searchMemberByPhone(request.phone());
            return ResponseEntity.ok(details);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}