package org.edb.main;

import java.util.ArrayList;

public class TestPluginConfig extends PluginConfig {
    private int tempConfigInt;
    private String tempConfigStr;
    private ArrayList<String> tempConfigList;

    public TestPluginConfig(){
        tempConfigList = new ArrayList<String>();
    }

//    TODO 하드코딩 컨피그에서 변경필요
    @Override
    public void addSingleConfig(String singleConfig) {
        if(singleConfig.startsWith("tempConfigInt : ")){
            tempConfigInt=3;
        }
        if(singleConfig.startsWith("tempConfigStr: ")){
            tempConfigStr = "tempStr";
        }
    }

//  TODO 테스트용 말고 제대로된 decode 작성 필요
    @Override
    public void decode(String decodeConfig) {
        System.out.println("decode with some Logics");
        System.out.println(decodeConfig);
    }

    @Override
    public void removeSingleConfig(String singleConfig) {

    }

    @Override
    public void extractConfig(PluginConfigConverter pluginConfigConverter) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("tempConfigInt : 3, tempConfigStr : tempStr");
        pluginConfigConverter.addSingleConfig("TestPluginConfig",stringBuffer.toString());
    }
}
