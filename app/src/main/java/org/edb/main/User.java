package org.edb.main;


/**
 * User
 * can access common single TempUser instance
 */
public class User {
    private static User loggedInUser;

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
    }

    public void logOut(){
        loggedInUser=null;
        /*
            edbTimer.stop()?
         */
    }
}
