package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.ProfileRequestDto;
import com.easybytes.easystore.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
