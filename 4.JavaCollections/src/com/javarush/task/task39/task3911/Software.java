package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {

    private int currentVersion;


    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }


    public boolean rollback(int rollbackVersion) {
        Set<Integer> integers = versionHistoryMap.keySet();
        List<Integer> list = new ArrayList<>();


        if (integers.contains(rollbackVersion)){
            for (Integer temp : integers){
                if (temp > rollbackVersion){
                   list.add(temp);
                }
            }

            for (Integer temp : list){
                versionHistoryMap.remove(temp);
            }

            this.currentVersion = rollbackVersion;
            return true;
        }else {
            return false;
        }


    }


    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }



}
