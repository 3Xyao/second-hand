package com.secondhand.search.convert;

import com.secondhand.search.pojo.domain.SearchHistory;
import com.secondhand.search.pojo.dto.req.SearchHistoryAddReqDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T19:50:59+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
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
