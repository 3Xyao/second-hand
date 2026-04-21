package com.secondhand.user.convert;

import com.secondhand.user.dto.UserDTO;
import com.secondhand.user.dto.UserForItemDetailRespDTO;
import com.secondhand.user.dto.UserInfoDTO;
import com.secondhand.user.dto.UserSyncEsDTO;
import com.secondhand.user.pojo.domain.UserInfo;
import com.secondhand.user.pojo.domain.UserStatistics;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T21:52:12+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserConvertImpl implements UserConvert {

    @Override
    public UserDTO toUserDTO(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUsrname( userInfo.getUsername() );
        userDTO.setId( userInfo.getId() );
        userDTO.setPassword( userInfo.getPassword() );

        return userDTO;
    }

    @Override
    public UserSyncEsDTO userInfotoUserSyncEsDTO(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        UserSyncEsDTO userSyncEsDTO = new UserSyncEsDTO();

        userSyncEsDTO.setNickname( userInfo.getNickname() );
        userSyncEsDTO.setAvatarUrl( userInfo.getAvatarUrl() );

        return userSyncEsDTO;
    }

    @Override
    public UserForItemDetailRespDTO toItemDetailRespDTO(UserInfo user, UserStatistics stats) {
        if ( user == null && stats == null ) {
            return null;
        }

        UserForItemDetailRespDTO userForItemDetailRespDTO = new UserForItemDetailRespDTO();

        if ( user != null ) {
            userForItemDetailRespDTO.setSellerId( user.getId() );
            userForItemDetailRespDTO.setSellerNickname( user.getNickname() );
            userForItemDetailRespDTO.setSellerAvatar( user.getAvatarUrl() );
            userForItemDetailRespDTO.setCreditLevelDesc( scoreToDesc( user.getCreditScore() ) );
        }
        if ( stats != null ) {
            if ( stats.getSoldCount() != null ) {
                userForItemDetailRespDTO.setSoldCount( stats.getSoldCount() );
            }
            else {
                userForItemDetailRespDTO.setSoldCount( 0 );
            }
            if ( stats.getOnSaleCount() != null ) {
                userForItemDetailRespDTO.setOnSaleCount( stats.getOnSaleCount() );
            }
            else {
                userForItemDetailRespDTO.setOnSaleCount( 0 );
            }
            userForItemDetailRespDTO.setPraiseRate( stats.getPraiseRate() );
        }
        userForItemDetailRespDTO.setFavorited( false );

        return userForItemDetailRespDTO;
    }

    @Override
    public UserInfoDTO toUserInfoDTO(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setAvatar( userInfo.getAvatarUrl() );
        userInfoDTO.setId( userInfo.getId() );
        userInfoDTO.setNickname( userInfo.getNickname() );

        return userInfoDTO;
    }

    @Override
    public List<UserInfoDTO> toUserInfoDTOList(List<UserInfo> userInfoList) {
        if ( userInfoList == null ) {
            return null;
        }

        List<UserInfoDTO> list = new ArrayList<UserInfoDTO>( userInfoList.size() );
        for ( UserInfo userInfo : userInfoList ) {
            list.add( toUserInfoDTO( userInfo ) );
        }

        return list;
    }
}
