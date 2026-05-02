package com.secondhand.user.convert;

import com.secondhand.user.pojo.domain.BrowseHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-02T12:03:15+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
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
