package com.yjhh.ppwbusiness.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter;
import com.yjhh.ppwbusiness.adapter.ExpandableItemAdapter;

import java.util.List;

public class EvaluateManageItemBean extends AbstractExpandableItem<SubCommentsBean> implements MultiItemEntity {
    public String content;
    public int grade;
    public int id;
    public boolean ifFile;
    public boolean ifShop;
    public String nickName;
    public int time;
    public List<FilesBean> files;
    public List<SubCommentsBean> subComments;

    @Override
    public int getItemType() {
        return EvaluateManageAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }


    public static class FilesBean {
        /**
         * ext : .jpg
         * fileId :
         * fileName :
         * ifDoc : false
         * ifImage : false
         * ifMedia : false
         * url : http://192.168.2.200:8080/file/upload/jpg/20181116/dba9e8b58da3a250.jpg
         */

        public String ext;
        public String fileId;
        public String fileName;
        public boolean ifDoc;
        public boolean ifImage;
        public boolean ifMedia;
        public String url;
    }


    public static class SubCommentsBean implements MultiItemEntity {

        public String content;
        public boolean ifFile;
        public boolean ifShop;
        public String nickName;
        public int time;

        public SubCommentsBean(String content, boolean ifFile, boolean ifShop, String nickName, int time) {
            this.content = content;
            this.ifFile = ifFile;
            this.ifShop = ifShop;
            this.nickName = nickName;
            this.time = time;
        }

        @Override
        public int getItemType() {
            return ExpandableItemAdapter.TYPE_LEVEL_1;
        }
    }

}
