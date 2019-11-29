package org.edb.main.network;

import com.google.gson.JsonObject;
import com.sun.security.ntlm.Server;
import javafx.application.Platform;
import org.edb.main.*;
import org.edb.main.UI.FXManipulator;
import org.edb.main.model.tempPlugin;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import retrofit2.Callback;
import org.edb.main.model.Time;
import org.edb.main.model.Object;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;


public class RestAPIRequesterTest {

    private static RestAPIRequester restAPIRequester;

    public static void main(String[] args) {

        //Mockito를 이용한 callback test
        ServerResponseHandler serverResponseHandler = mock(ServerResponseHandler.class);


        //callback boolean successful, String id
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                java.lang.Object[] args = invocationOnMock.getArguments();
                System.out.print("\nargs test33\n");
                System.out.print(args);
                System.out.print("\ntoken mock test33\n");
                System.out.print(User.getUser().getToken());//token

                //잠금정책목록
//                requestUserPlugins();

                // 2. 잠금정책등록 선택시 추가가능한 잠금정책목록보여주기
                //requestAvailablePlugins();

                //3.잠금정책목록에서 특정 잠금 정책 클릭 시 잠금정책설정 불러오기
//                requestUserDetailPlugin();
//'{"configuration":{"object":{"object_idx1":"Game.exe","object_idx2":"Chrome"}\n,"time":{"start_time":"09:20","end_time":"15:00"}}}'
                // 4. 잠금정책목록에서 특정 잠금 정책 화면에서 잠금정책설정 저장
                requestPostPluginDetail();

                return null;
            }


        }).when(serverResponseHandler).handleLoginResponse(anyBoolean(), anyString());

        restAPIRequester = new RestAPIRequester(serverResponseHandler);
        restAPIRequester.requestLogin("meme2367", "123");

    }

    // 1. 잠금정책목록
    public static void requestUserPlugins() {
        restAPIRequester.requestUserPlugins();
    }

    // 2. 잠금정책등록 선택시 추가가능한 잠금정책목록보여주기
    public static void requestAvailablePlugins(){ restAPIRequester.requestAvailablePlugins(); }

    // 3. 잠금정책목록에서 특정 잠금 정책 클릭 시 잠금정책설정 불러오기
    public static void requestUserDetailPlugin(){restAPIRequester.requestPluginDetails(3);}
    // 4. 잠금정책목록에서 특정 잠금 정책 화면에서 잠 금정책설정 저장
    public static void requestPostPluginDetail(){

        Object object = new Object("object1","Chrome");
        Object object2 = new Object("object2","game.exe");

        Time time = new Time("2019-11-19 12:00","2019-11-29 18:00");
        ArrayList<Object> objectArrayLists = new ArrayList<>();
        objectArrayLists.add(object);
        objectArrayLists.add(object2);

        JsonObject objecttmp = new JsonObject();

        for(int i = 0;i<objectArrayLists.size();i++){
            objecttmp.addProperty(objectArrayLists.get(i).getObject_name(),objectArrayLists.get(i).getObject_value());
        }

        JsonConverter jsonConverter = new JsonConverter();
        jsonConverter.makeStringObject(objecttmp);
        jsonConverter.setTime(time);

        restAPIRequester.requestPostUserPlugin(4,jsonConverter);

    }


}
