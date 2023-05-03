/**
 * @author JOJO
 * @class ETLUtil
 * @date 2023/4/22
 * @apiNote
 */

package com.depsystem.app.systemServer.util.securityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ETLUtil {

    public static List<Map<String, Object>> parseToList(Map<String, Object> resultMap, String idKey, String listKey) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Object[] ids = (Object[]) resultMap.get(idKey);
        Object[] lists = (Object[]) resultMap.get(listKey);
        if (ids != null && lists != null && ids.length == lists.length) {
            for (int i = 0; i < ids.length; i++) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put(idKey, ids[i]);
                itemMap.put(listKey, parseArrayToList(lists[i]));
                resultList.add(itemMap);
            }
        }
        return resultList;
    }

    /**
     * 转List object
     * @param arrayObj String 字符串
     * @return List object
     */
    public static List<String> parseArrayToList(Object arrayObj) {
        List<String> itemList = new ArrayList<>();
        if (arrayObj instanceof String) {
            itemList.add((String) arrayObj);
        } else if (arrayObj instanceof Object[]) {
            for (Object item : (Object[]) arrayObj) {
                if (item != null) {
                    itemList.add(item.toString());
                }
            }
        }
        return itemList;
    }
     static Map<String, Object> toMap(String result) {
        Map<String, Object> map = new HashMap<>();
        String[] keyValuePairs = result.substring(1, result.length() - 1).split(", ");
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            String key = entry[0];
            Object value = entry[1];
            if (value.toString().startsWith("[") && value.toString().endsWith("]")) {
                value = value.toString().substring(1, value.toString().length() - 1).split(", ");
            }
            map.put(key, value);
        }
        return map;
    }
}
