package com.example.SJSU_Event.domain.member.controller;

import com.example.SJSU_Event.domain.member.entity.Member;
import com.example.SJSU_Event.domain.member.service.MemberService;
import com.example.SJSU_Event.global.annotation.api.ApiErrorCodeExample;
import com.example.SJSU_Event.global.exception.code.ErrorStatus;
import com.example.SJSU_Event.global.exception.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member API", description = "Member API")
@ApiResponse(responseCode = "2000", description = "Success")
@RequestMapping("/api/members")
@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @Operation(summary = "Member User Registration 🔑", description = "Register user")
    @ApiErrorCodeExample
    @PostMapping("/register/user")
    public ApiResponseDto<Long> registerUser(@RequestParam String name) {
        return ApiResponseDto.onSuccess(memberService.userRegister(name));
    }
    @Operation(summary = "Member Admin Registration 🔑", description = "Register Admin")
    @ApiErrorCodeExample(value = {
            ErrorStatus.EVENT_NOT_FOUND,
    })
    @PostMapping("/register/admin")
    public ApiResponseDto<Long> registerAdmin(@RequestParam String name) {
        return ApiResponseDto.onSuccess(memberService.adminRegister(name));
    }

    @Operation(summary = "Update Information 🔑", description = "Update Information")
    @ApiErrorCodeExample(value = {
            ErrorStatus.EVENT_NOT_FOUND,
    })
    @PutMapping("/{memberId}")
    public ApiResponseDto<Long> updateInfo(
            @PathVariable Long memberId,
            @RequestParam String name) {
        return ApiResponseDto.onSuccess(memberService.updateInfo(memberId, name));
    }

    @Operation(summary = "Delete Member 🔑", description = "Delete Member")
    @ApiErrorCodeExample(value = {
            ErrorStatus.EVENT_NOT_FOUND,
    })
    @DeleteMapping("/{memberId}")
    public ApiResponseDto<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponseDto.onSuccess(null);
    }

    @Operation(summary = "Get Username 🔑", description = "Get Username")
    @ApiErrorCodeExample(value = {
            ErrorStatus.EVENT_NOT_FOUND,
    })
    @GetMapping("/{memberId}")
    public ApiResponseDto<Member> getByMemberId(@PathVariable Long memberId) {
        return ApiResponseDto.onSuccess(memberService.getByMemberId(memberId));
    }
}