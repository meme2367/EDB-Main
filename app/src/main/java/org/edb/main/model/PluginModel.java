package org.edb.main.model;

/*USER가 등록한 잠금정책 목록 조회(불러오기u13)
{
    "status": 200,
    "success": true,
    "message": "USER의 잠금 정책 조회 성공",
    "data": [
        {
            "lock_idx": 3,
            "name": "잠금정책2",
            "configuration": "{object:chrome}",
            "start_time": "08:19:00",
            "end_time": "08:19:00"
        },
        {
            "lock_idx": 5,
            "name": "잠금정책4",
            "configuration": "{object:game.exe}",
            "start_time": "11:19:00",
            "end_time": "11:19:00"
        }
    ]
}
* */
public class PluginModel {
    int idx;
    String configuration;
    String end_time;
    String start_time;
    String name;
    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String  getEnd_time() {

        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getConfiguration() {

        return configuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getIdx(){
        return idx;
    }


}

