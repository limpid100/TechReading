package com.dxl.techreading.model;

import java.util.List;

/**
 * @author du_xi
 * @date 2018/11/14
 */
public class BingDailyPic {

    private List<Images> images;
    private Tooltips tooltips;

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setTooltips(Tooltips tooltips) {
        this.tooltips = tooltips;
    }

    public Tooltips getTooltips() {
        return tooltips;
    }

    public class Images {

        private String startdate;
        private String fullstartdate;
        private String enddate;
        private String url;
        private String urlbase;
        private String copyright;
        private String copyrightlink;
        private String title;
        private String quiz;
        private boolean wp;
        private String hsh;
        private int drk;
        private int top;
        private int bot;
        private List<String> hs;

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setWp(boolean wp) {
            this.wp = wp;
        }

        public boolean getWp() {
            return wp;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public String getHsh() {
            return hsh;
        }

        public void setDrk(int drk) {
            this.drk = drk;
        }

        public int getDrk() {
            return drk;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getTop() {
            return top;
        }

        public void setBot(int bot) {
            this.bot = bot;
        }

        public int getBot() {
            return bot;
        }

        public void setHs(List<String> hs) {
            this.hs = hs;
        }

        public List<String> getHs() {
            return hs;
        }

    }

    public class Tooltips {

        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getLoading() {
            return loading;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getPrevious() {
            return previous;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getNext() {
            return next;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }

        public String getWalls() {
            return walls;
        }

    }

}
