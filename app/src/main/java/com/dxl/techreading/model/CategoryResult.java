package com.dxl.techreading.model;

import java.util.Date;
import java.util.List;

/**
 * @author dxl
 * @date 2018/11/12 8:17
 */
public class CategoryResult {


    private String _id;
    private Date createdAt;
    private String desc;
    private List<String> images;
    private Date publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    @Override
    public String toString() {
        return "CategoryResult{" +
                "_id='" + _id + '\'' +
                ", createdAt=" + createdAt +
                ", desc='" + desc + '\'' +
                ", images=" + images +
                ", publishedAt=" + publishedAt +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
