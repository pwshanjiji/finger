package com.xml.mapper;


import com.xml.entity.Leave;

import java.awt.print.Pageable;
import java.util.List;

/**
 * (Leave)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-18 20:27:58
 */
public interface LeaveMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Leave queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param leave 查询条件
     * @return 对象列表
     */
    List<Leave> queryAll(Leave leave);

    /**
     * 新增数据
     *
     * @param leave 实例对象
     * @return 影响行数
     */
    int insert(Leave leave);

    /**
     * 修改数据
     *
     * @param leave 实例对象
     * @return 影响行数
     */
    int update(Leave leave);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

