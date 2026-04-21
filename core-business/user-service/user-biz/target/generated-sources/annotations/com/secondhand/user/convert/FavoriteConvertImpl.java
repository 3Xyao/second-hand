package com.secondhand.user.convert;

import com.secondhand.user.pojo.domain.Favorite;
import com.secondhand.user.pojo.vo.UserActionRespDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T19:19:32+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class FavoriteConvertImpl implements FavoriteConvert {

    @Override
    public Favorite toFavorite(Long userId, Long spuId) {
        if ( userId == null && spuId == null ) {
            return null;
        }

        Favorite favorite = new Favorite();

        favorite.setUserId( userId );
        favorite.setSpuId( spuId );

        return favorite;
    }

    @Override
    public UserActionRespDTO toUserActionRespDTO(Favorite favorite) {
        if ( favorite == null ) {
            return null;
        }

        UserActionRespDTO userActionRespDTO = new UserActionRespDTO();

        userActionRespDTO.setActionTime( favorite.getUpdateTime() );
        userActionRespDTO.setId( favorite.getId() );
        userActionRespDTO.setSpuId( favorite.getSpuId() );

        return userActionRespDTO;
    }
}
