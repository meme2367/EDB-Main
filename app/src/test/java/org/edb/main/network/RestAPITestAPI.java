package org.edb.main.network;

import org.edb.main.ServerResponseHandler;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import java.util.ArrayList;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/*
        RestAPIRequester의 반환내용 그대로가 아니라,
        ServerResponseHandler의 메소드를 호출할때의 매개변수를 중간에 취하는 방식
*/
public class RestAPITestAPI {

    /*
    사용방식은 RestAPIRequesterTest의 requestLoginTest 참고.
     */
    public static ServerResponseHandler getMockServerResponseHandler(){
        ServerResponseHandler serverResponseHandler=mock(ServerResponseHandler.class);

        return serverResponseHandler;
    }

    public static Stubber getStubber(ArrayList<String> responseNames){
        return doAnswer(new Answer<Void>(){
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                int size=responseNames.size();
                Object[] args = invocationOnMock.getArguments();
                for (int i = 0; i <size ; i++) {
                    System.out.println(responseNames.get(i) + args[i].toString());
                }
                return null;
            }
        });
    }
}