package org.edb.main;


import org.edb.main.network.RestApiConnector;

import java.util.ArrayList;

/**
 * User
 * can access common single TempUser instance
 */
public class User {
    private static User loggedInUser;

    private ArrayList<ExternalService> externalServices;
    private ArrayList<PluginConfig> pluginConfigs;
    private String id;
    private String token;

    public User(String id,String token){
        this.id = id;
        this.token = token;
    }

    /**
     * 공통적인 User 객체를 얻을 수 있다.
     *
     * 이 메서드를 호출하기 전에 login()메서드를 먼저 호출하는것이 권장된다.
     *
     * @return 현재 로그인된 유저의 정보를 담는 객체 반환
     *
     * @exception RuntimeException 로그인된 유저가 없을 경우 예외를 발생시킨다.
     * 이를 처리하는 쪽에서는 로그인을 요청하는 UI를 띄우거나, 잠금관련 기능을 일시정지한다.
     */
    public static User getUser() {
        if(loggedInUser==null){
            throw new RuntimeException();
            //exception 발생
        }
        return loggedInUser;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
    public static synchronized void login(String id, String token){
        if(loggedInUser!=null){
            if(id!=loggedInUser.getId())loggedInUser.logOut();
        }

        loggedInUser = new User(id,token);
        /*
            token=로그인 처리
            loadPluginConfig()
            loadExternalService()
            loggedinUser=new User(id, token);
         */
    }

    public void logOut(){
        loggedInUser=null;
        /*
            edbTimer.stop()?
         */
    }

    /*
        갱신부분 좀 난해한듯..
        플러그인, 외부서비스 불러오는 부분 일단 제외하고 나머지 작성.
     */
    public void loadPluginConfig(){
        /*
            잠금정책들 불러오기
            edbTimer에 전달하기
            만약 이러면 내부 메서드에 보관할 필요 있는가?
         */
    }

    public void loadExternalService(){
        /*
            외부서비스들 불러오기
            edbTimer에 전달하기
            너두
         */

    }
}
