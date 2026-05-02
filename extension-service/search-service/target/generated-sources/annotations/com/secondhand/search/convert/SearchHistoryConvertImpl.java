package com.secondhand.search.convert;

import com.secondhand.search.pojo.domain.SearchHistory;
import com.secondhand.search.pojo.dto.req.SearchHistoryAddReqDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-02T12:02:56+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class SearchHistoryConvertImpl implements SearchHistoryConvert {

    @Override
    public SearchHistory toSearchHistory(Long userId, SearchHistoryAddReqDTO dto) {
        if ( userId == null && dto == null ) {
            return null;
        }

        SearchHistory searchHistory = new SearchHistory();

        if ( dto != null ) {
            searchHistory.setKeyword( dto.getKeyword() );
            searchHistory.setType( dto.getType() );
        }
        searchHistory.setUserId( userId );

        return searchHistory;
    }
}
