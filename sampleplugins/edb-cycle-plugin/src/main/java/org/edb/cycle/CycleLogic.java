package org.edb.cycle;

import org.edb.main.EDBPlugin;
import org.edb.main.PluginLogic;
import org.edb.main.PluginConfigConverter;
import org.edb.main.model.TargetProgram;
import org.edb.main.model.TargetWebsite;
import org.edb.main.util.DateFormatter;

import java.text.ParseException;
import java.util.*;

public class CycleLogic extends PluginLogic {
    private int focusCycle;
//    min단위
    private int restCycle;
    private int wildCardLimit;
    private Date finishingTime;
    private CycleMode curMode;
    private List<Integer> targetExternalServices;

    public CycleLogic() {
        this.targetExternalServices = new ArrayList<Integer>();
    }

    public void addSingleConfig(String attributeName, String attributeValue) {
//        TODO addSingleConfig 삭제 고려,
//        어차피 특정 pluginConfigController는 각 플러그인에 강한 결합되므로 getter, setter로 대체가능할듯.

    }

    public void decodeFromMap(Map<String, String> decodeConfig) {

        focusCycle = Integer.parseInt(decodeConfig.get("focusCycle"));
        restCycle = Integer.parseInt(decodeConfig.get("restCycle"));
        wildCardLimit = Integer.parseInt(decodeConfig.get("wildCardLimit"));

        try {
            finishingTime = DateFormatter.getSimpleFormattedDateFromString(decodeConfig.get("finishingTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        curMode = CycleMode.valueOf(decodeConfig.get("curMode"));

        List<String> strTargetIndexes = Arrays.asList(decodeConfig.get("tempConfigList").split(","));
        targetExternalServices = new ArrayList<Integer>();
        for (String strIndex:strTargetIndexes) {
            Integer tempInteger = new Integer(strIndex);
            targetExternalServices.add(tempInteger);
        }
    }

    public void removeSingleConfig(String singleConfig) {
//        TODO removeSingleConfig 삭제 고려
    }

    public void removeFromTargetExternalServices(int targetExternalIdx){
        targetExternalServices.remove(new Integer(targetExternalIdx));
    }

    public void extractConfig(PluginConfigConverter pluginConfigConverter) {
        Map<String,String> attributesMap = new HashMap<String,String>();

        attributesMap.put("focusCycle",Integer.toString(focusCycle));
        attributesMap.put("restCycle",Integer.toString(restCycle));
        attributesMap.put("wildCardLimit",Integer.toString(wildCardLimit));

        attributesMap.put("finishingTime",DateFormatter.getSimpleFormattedStringFromDate(finishingTime));
        attributesMap.put("curMode",curMode.toString());
        attributesMap.put("targetExternalServices",removeBracket(targetExternalServices.toString()));

        pluginConfigConverter.addSingleConfig("CycleLogic", attributesMap);
    }

    private String removeBracket(String string) {
        if(string.startsWith("[")){
            return string.substring(1,string.length()-1);
        }
        return string;
    }

    @Override
    public void checkForLogic(EDBPlugin plugin, List<String> curPrograms, List<String> curWebsites, Date curTime) {
        checkMode(curTime);
        checkForPrograms(plugin.getTargetPrograms(), curPrograms);
        checkForWebsites(plugin.getTargetWebsites(), curWebsites);
    }

    private void checkMode(Date curTime) {

        int nextCycle;
//        curTime이 Date보다 뒤라면 즉, curTime이 Date보다 크다면
        if(curTime.compareTo(finishingTime)==1){
            if(curMode==CycleMode.FOCUS){
                curMode = CycleMode.REST;
                nextCycle = restCycle;
                renewDate(nextCycle);
            }
            else{
                curMode = CycleMode.FOCUS;
                nextCycle = focusCycle;
                renewDate(nextCycle);
            }
        }
    }

    public void renewDate(int nextCycle) {
        Date curTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curTime);
        cal.add(Calendar.MINUTE,nextCycle);
        finishingTime = cal.getTime();
    }

    private void checkForPrograms(Map<String, TargetProgram> targetPrograms, List<String> curPrograms) {
        List<String> commonPrograms = new ArrayList(curPrograms);
        commonPrograms.retainAll(targetPrograms.keySet());
//        TODO OSNativeExecutor 개발 후 일치 프로그램 종료요청
//        OSNativeExecutor에게 일치하는 프로그램 종료 요청한다.(static?)
//        전달 매개변수는 targetProgram의 리스트
    }

    private void checkForWebsites(Map<String, TargetWebsite> targetWebsites, List<String> curWebsites) {
        List<String> commonPrograms = new ArrayList(curWebsites);
        commonPrograms.retainAll(targetWebsites.keySet());
//        TODO OSNativeExecutor 개발 후 일치 웹사이트 종료요청
//        OSNativeExecutor에게 일치하는 웹사이트 종료 요청한다.(static?)
//        전달 매개변수는 targetWebsite의 리스트 (나중에 정의 변경될 가능성 예방하여 String으로는 보내지 않는다)
    }

//
//    setter
//
    public void setFocusCycle(int focusCycle) {
        this.focusCycle = focusCycle;
    }

    public void setRestCycle(int restCycle) {
        this.restCycle = restCycle;
    }

    public void setWildCardLimit(int wildCardLimit) {
        this.wildCardLimit = wildCardLimit;
    }

    public void setFinishingTime(Date finishingTime) {
        this.finishingTime = finishingTime;
    }

    public void setCurMode(CycleMode curMode) {
        this.curMode = curMode;
    }

    public void addToTargetExternalServices(Integer targetExternalIdx) {
        targetExternalServices.add(targetExternalIdx);
    }

//
//    getter
//
    public int getFocusCycle() {
        return focusCycle;
    }

    public int getRestCycle() {
        return restCycle;
    }

    public int getWildCardLimit() {
        return wildCardLimit;
    }

    public Date getFinishingTime() {
        return finishingTime;
    }

    public CycleMode getCurMode() {
        return curMode;
    }

    public List<Integer> getTargetExternalServices() {
        return targetExternalServices;
    }


}
