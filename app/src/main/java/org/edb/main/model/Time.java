package org.edb.main.model;

public class Time {
    String start_time;
    String end_time;
    boolean isStart = false;

    public Time(String start_time,String end_time){
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Time(){
    }
    public void setStartTime(String start_time) {
        this.start_time = start_time;
    }

    public void setTime(String start_or_end_time) {
        if (!isStart) {//false
            isStart = true;
            setStartTime(start_or_end_time);
            return;
        } else {//true
            setEndTime(start_or_end_time);
            isStart = false;

            return;
        }

    }

    public void setEndTime(String end_time) {

        this.end_time = end_time;
    }

    public String getStartTime() {

        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getStart_time() {
        return start_time;
    }
}

