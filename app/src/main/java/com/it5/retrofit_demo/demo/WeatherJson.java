package com.it5.retrofit_demo.demo;

/**
 * Created by IT5 on 2016/6/20.
 */
public class WeatherJson {
    //weatherinfo需要对应json数据的名称，我之前随便写了个，被坑很久
    private WeatherJson weatherJson;

    public WeatherJson getWeatherJson() {
        return weatherJson;
    }

    public void setWeatherJson(WeatherJson weatherJson) {
        this.weatherJson = weatherJson;
    }

    public class Weatherinfo{
        private String city;

        private String cityid;

        private String temp;

        private String WD;

        private String WS;

        private String SD;

        private String WSE;

        private String time;

        private String isRadar;

        private String Radar;

        private String njd;

        private String qy;

        public void setCity(String city){
            this.city = city;
        }
        public String getCity(){
            return this.city;
        }
        public void setCityid(String cityid){
            this.cityid = cityid;
        }
        public String getCityid(){
            return this.cityid;
        }
        public void setTemp(String temp){
            this.temp = temp;
        }
        public String getTemp(){
            return this.temp;
        }
        public void setWD(String WD){
            this.WD = WD;
        }
        public String getWD(){
            return this.WD;
        }
        public void setWS(String WS){
            this.WS = WS;
        }
        public String getWS(){
            return this.WS;
        }
        public void setSD(String SD){
            this.SD = SD;
        }
        public String getSD(){
            return this.SD;
        }
        public void setWSE(String WSE){
            this.WSE = WSE;
        }
        public String getWSE(){
            return this.WSE;
        }
        public void setTime(String time){
            this.time = time;
        }
        public String getTime(){
            return this.time;
        }
        public void setIsRadar(String isRadar){
            this.isRadar = isRadar;
        }
        public String getIsRadar(){
            return this.isRadar;
        }
        public void setRadar(String Radar){
            this.Radar = Radar;
        }
        public String getRadar(){
            return this.Radar;
        }
        public void setNjd(String njd){
            this.njd = njd;
        }
        public String getNjd(){
            return this.njd;
        }
        public void setQy(String qy){
            this.qy = qy;
        }
        public String getQy(){
            return this.qy;
        }
    }

}
