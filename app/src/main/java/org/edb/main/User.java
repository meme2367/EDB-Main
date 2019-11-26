package org.edb.main;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * User
 * can access common single TempUser instance
 */
public class User {
    private static User loggedInUser;

    private HashMap<Integer, ExternalService> externalServices;

    private String id;
    private String token;

    public User(String id,String token){
        this.id = id;
        this.token = token;
        externalServices=new HashMap<Integer, ExternalService>();
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
            loadPluginConfig()
            loadExternalService()
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

    public void loadExternalService(ArrayList<ExternalService> data){
        for (ExternalService externalService:data) {
            externalServices.put(externalService.getExternal_service_idx(),externalService);
        }
        /*
            외부서비스들 불러오기
            edbTimer에 전달하기
            너두
         */

    }

    public synchronized void loadExternalServicedDetails(ArrayList<ExternalServiceDetail>data){

//        이게 아니라 externalServiceManager를 활용할까?
//        그게 하는 역할이 뭐지? 아니 근데 EDBPluginManager에서 관리하는게 편하지 않나?
//        필요할때마다 계속 get할거여? 근데 PluginManager에 넣기에는 그걸 UI에서도 활용할텐데.


    }


}
