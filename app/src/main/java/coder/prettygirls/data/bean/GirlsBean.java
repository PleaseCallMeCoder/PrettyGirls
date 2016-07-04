package coder.prettygirls.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by oracleen on 2016/6/29.
 */
public class GirlsBean {

    /**
     * error : false
     * results : [{"_id":"5771d5eb421aa931ddcc50d6","createdAt":"2016-06-28T09:42:03.761Z","desc":"Dagger2图文完全教程","publishedAt":"2016-06-28T11:33:25.276Z","source":"web","type":"Android","url":"https://github.com/luxiaoming/dagger2Demo","used":true,"who":"代码GG陆晓明"},{"_id":"5771c9ca421aa931ca5a7e59","createdAt":"2016-06-28T08:50:18.731Z","desc":"Android Design 设计模板","publishedAt":"2016-06-28T11:33:25.276Z","source":"chrome","type":"Android","url":"https://github.com/andreasschrade/android-design-template","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 5771d5eb421aa931ddcc50d6
     * createdAt : 2016-06-28T09:42:03.761Z
     * desc : Dagger2图文完全教程
     * publishedAt : 2016-06-28T11:33:25.276Z
     * source : web
     * type : Android
     * url : https://github.com/luxiaoming/dagger2Demo
     * used : true
     * who : 代码GG陆晓明
     */

    private List<ResultsEntity> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity implements Parcelable {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String get_id() {
            return _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getSource() {
            return source;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isUsed() {
            return used;
        }

        public String getWho() {
            return who;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }

        public ResultsEntity() {
        }

        protected ResultsEntity(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Parcelable.Creator<ResultsEntity> CREATOR = new Parcelable.Creator<ResultsEntity>() {
            @Override
            public ResultsEntity createFromParcel(Parcel source) {
                return new ResultsEntity(source);
            }

            @Override
            public ResultsEntity[] newArray(int size) {
                return new ResultsEntity[size];
            }
        };
    }
}
