package com.bizideal.mn.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/1 10:11
 * @version: 1.0
 * @Description:
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = 2804715399245414521L;

    /**
     * 主键id
     */
    private long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 网站id，1表示新浪，2表示QQ，3表示搜狐，4表示火狐
     */
    private int webId;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用途
     */
    private String useFor;

    public Mail() {
    }

    public Mail(int webId, String mail, String useFor) {
        this.webId = webId;
        this.mail = mail;
        this.useFor = useFor;
    }

    public long getId() {
        return id;
    }

    public Mail setId(long id) {
        this.id = id;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Mail setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Mail setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public int getWebId() {
        return webId;
    }

    public Mail setWebId(int webId) {
        this.webId = webId;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Mail setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getUseFor() {
        return useFor;
    }

    public Mail setUseFor(String useFor) {
        this.useFor = useFor;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"modifyTime\":\"")
                .append(modifyTime).append('\"');
        sb.append(",\"webId\":")
                .append(webId);
        sb.append(",\"mail\":\"")
                .append(mail).append('\"');
        sb.append(",\"useFor\":\"")
                .append(useFor).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
