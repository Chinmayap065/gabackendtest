package com.mtd.GamingArcade.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.GamingArcade.dto.CreateMemberRequest;
import com.mtd.GamingArcade.dto.MemberDetailsDto;
import com.mtd.GamingArcade.dto.RechargeRequestDto;
import com.mtd.GamingArcade.dto.SearchMemberRequest;
import com.mtd.GamingArcade.entity.Member;
import com.mtd.GamingArcade.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
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
        	MemberDetailsDto details = memberService.searchMemberByPhone(request.phone());
            return ResponseEntity.ok(details);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // This is the recharge endpoint
    @PostMapping("/{phone}/recharge")
    public ResponseEntity<?> rechargeMemberAccount(@PathVariable String phone, @RequestBody RechargeRequestDto request) {
        try {
            Member updatedMember = memberService.rechargeMember(phone, request.amount());
            return ResponseEntity.ok(updatedMember);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}