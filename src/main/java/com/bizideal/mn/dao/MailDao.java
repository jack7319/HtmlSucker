package com.bizideal.mn.dao;

import com.bizideal.mn.entity.Mail;

import java.util.List;

/**
 * @author : liulq
 * @date: 创建时间: 2018/2/1 10:12
 * @version: 1.0
 * @Description:
 */
public interface MailDao {

    /**
     * 插入一条邮箱信息
     */
    public long insertMail(Mail mail);

    /**
     * 删除一条邮箱信息
     */
    public int deleteMail(long id);

    /**
     * 更新一条邮箱信息
     */
    public int updateMail(Mail mail);

    /**
     * 查询邮箱列表
     */
    public List<Mail> selectMailList();

    /**
     * 根据主键id查询一条邮箱信息
     */
    public Mail selectMailById(long id);

}