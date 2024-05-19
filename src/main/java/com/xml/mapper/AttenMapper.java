package com.xml.mapper;

import com.xml.entity.Atten;

import java.util.List;

/**
 * (Atten)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 10:20:40
 */
public interface AttenMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Atten queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param atten 查询条件
     * @return 对象列表
     */
    List<Atten> queryAll(Atten atten);

    /**
     * 新增数据
     *
     * @param atten 实例对象
     * @return 影响行数
     */
    int insert(Atten atten);

    /**
     * 修改数据
     *
     * @param atten 实例对象
     * @return 影响行数
     */
    int update(Atten atten);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

