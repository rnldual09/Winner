package com.Winner.config;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.JdbcUtils;

@SuppressWarnings("serial")
public class CustomMap extends ListOrderedMap {

    public Object put(Object key, Object value) {
        // StringUtils.lowerCase 로 key값을 소문자로 변경 (USER_NAME => user_name)
        // JdbcUtils.convertUnderscoreNameToPropertyName 로 key값을 camelCase로 변경 (user_name => userName)
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(StringUtils.lowerCase((String) key)), value);
    }
}