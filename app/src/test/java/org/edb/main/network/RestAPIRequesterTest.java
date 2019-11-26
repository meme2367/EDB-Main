package org.edb.main.network;

import org.edb.main.ServerResponseHandler;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class RestAPIRequesterTest {

    public static void main(String[] args) {
        requestLoginTest();
    }

    public static void requestLoginTest(){
        /*
            response의 결과들의 이름을 ArrayList에 담는다.
            아무렇게나 지정해도 무관하지만, response 개수와는 일치시켜야함
         */
        ArrayList<String> responseNames=new ArrayList<String>();
        responseNames.add("로그인성공여부");
        responseNames.add("id");
        responseNames.add("token");

        /*
            RestAPIRequester의 반환내용 그대로가 아니라,
            ServerResponseHandler의 메소드를 호출할때의 매개변수를 중간에 취하는 방식.
            ServerResponseHandler 객체를 받아온다.
        */
        ServerResponseHandler serverResponseHandler=RestAPITestAPI.getMockServerResponseHandler();

        /*
            when(serverResponseHandler)까지는 그대로 사용해도 되며,
            그 이후는 response가 도착했을때 호출되는 ServerResponseHandler메소드를 호출.
            매개변수는 anyType()의 형태로 매개변수의 개수와 맞춰준다.
         */
        RestAPITestAPI.getStubber(responseNames).when(serverResponseHandler).handleLoginResponse(anyBoolean(),anyString(),anyString());

        RestAPIRequester restAPIRequester=new RestAPIRequester(serverResponseHandler);
        restAPIRequester.requestLogin("jooha208","123");
    }
}
