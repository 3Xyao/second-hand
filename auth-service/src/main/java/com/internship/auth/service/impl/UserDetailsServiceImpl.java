package com.internship.auth.service.impl;

import com.internship.api.client.AdminFeignClient;
import com.internship.api.domain.dto.SysUserDTO;
import com.internship.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminFeignClient adminFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Result<SysUserDTO> result=adminFeignClient.
    }
}
