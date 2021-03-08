package com.meowlomo.ems.core.coordination;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ShareData {

    private static Map<UUID, HashMap<String, String>> shareDataMap = new ConcurrentHashMap<UUID, HashMap<String, String>>();

    public static Map<UUID, HashMap<String, String>> getShareDataMap() {
        return shareDataMap;
    }

    public static HashMap<String, String> getShareDataMapByUuid(UUID uuid) {
        return ShareData.shareDataMap.get(uuid);
    }

    public static String getValue(UUID uuid, String key) {
        if (ShareData.shareDataMap.containsKey(uuid)) {
            HashMap<String, String> map = ShareData.shareDataMap.get(uuid);
            return map.get(key);
        }
        else {
            return null;
        }
    }

    public static boolean deleteShareDataMapByUuid(UUID uuid) {
        ShareData.shareDataMap.remove(uuid);
        if (ShareData.shareDataMap.containsKey(uuid)) { return false; }
        return true;
    }

    public static boolean deleteValue(UUID uuid, String key) {
        if (ShareData.shareDataMap.containsKey(uuid)) {
            ShareData.shareDataMap.get(uuid).remove(key);
            if (ShareData.shareDataMap.get(uuid).containsKey(key)) {
                return false;
            }
            else {
                return true;
            }
        }
        return true;
    }

    public static boolean deleteShareDataMap() {
        ShareData.shareDataMap.clear();
        if (ShareData.shareDataMap.size() == 0) { return true; }
        return false;
    }

    public static void putValue(UUID uuid, String key, String value) {
        if (ShareData.shareDataMap.containsKey(uuid)) {
            HashMap<String, String> entryMap = ShareData.shareDataMap.get(uuid);
            entryMap.put(key, value);
            ShareData.shareDataMap.put(uuid, entryMap);
        }
        else {
            HashMap<String, String> entryMap = new HashMap<String, String>();
            entryMap.put(key, value);
            ShareData.shareDataMap.put(uuid, entryMap);
        }
    }

    public static void putMap(UUID uuid, Map<String, String> map) {
        if (ShareData.shareDataMap.containsKey(uuid)) {
            HashMap<String, String> entryMap = ShareData.shareDataMap.get(uuid);
            entryMap.putAll(map);
            ShareData.shareDataMap.put(uuid, entryMap);
        }
        else {
            ShareData.shareDataMap.put(uuid, new HashMap<String, String>(map));
        }
    }
}
