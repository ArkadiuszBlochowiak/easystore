package com.easybytes.easystore.controller;

import com.easybytes.easystore.dto.ProfileRequestDto;
import com.easybytes.easystore.dto.ProfileResponseDto;
import com.easybytes.easystore.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final IProfileService iProfileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile() {
        ProfileResponseDto responseDto = iProfileService.getProfile();
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<ProfileResponseDto> updateProfile(@Validated @RequestBody ProfileRequestDto profileRequestDto) {
        ProfileResponseDto profileResponseDto = iProfileService.updateProfile(profileRequestDto);
        return ResponseEntity.ok(profileResponseDto);
    }
}
