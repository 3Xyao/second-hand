package com.secondhand.user.convert;

import com.secondhand.user.pojo.domain.BrowseHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T19:19:32+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class BrowseHistoryConvertImpl implements BrowseHistoryConvert {

    @Override
    public BrowseHistory toBrowseHistory(Long userId, Long spuId) {
        if ( userId == null && spuId == null ) {
            return null;
        }

        BrowseHistory browseHistory = new BrowseHistory();

        browseHistory.setUserId( userId );
        browseHistory.setSpuId( spuId );

        return browseHistory;
    }
}
