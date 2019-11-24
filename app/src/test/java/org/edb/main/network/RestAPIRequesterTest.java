package org.edb.main.network;

import com.sun.security.ntlm.Server;
import org.edb.main.ServerResponseHandler;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class RestAPIRequesterTest {

    public static void main(String[] args) {
        requestLoginTest();
    }

    public static void requestLoginTest(){
        ServerResponseHandler serverResponseHandler=mock(ServerResponseHandler.class);

        doAnswer(new Answer<Void>(){

            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                System.out.println("id : " + (String)args[0]);
                System.out.println("token : " + (String)args[1]);
                return null;
            }
        }).when(serverResponseHandler).handleLoginResponse(anyBoolean(),anyString());

        RestAPIRequester restAPIRequester=new RestAPIRequester(serverResponseHandler);
        restAPIRequester.requestLogin("jooha208","123");
    }
}
