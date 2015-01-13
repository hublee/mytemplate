package com.template.common.base;

import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public abstract class ServiceMybatis<T extends BaseEntity> implements BaseService<T> {
	
	@Autowired
	protected Mapper<T> mapper;

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	* @param <T extend T>
	 */
    public List<T> select(T record){
    	return mapper.select(record);
    }

    /**
	 * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
	* @param <T extend T>
	 */
    public int selectCount(T record){
    	return mapper.selectCount(record);
    }

    /**
	 * 根据主键进行查询,必须保证结果唯一
	*  单个字段做主键时,可以直接写主键的值
	*  联合主键时,key可以是实体类,也可以是Map
	* @param <T extend T>
	 */
    public T selectByPrimaryKey(Object key){
    	return mapper.selectByPrimaryKey(key);
    }

    /**
	 *  插入一条数据
	*	支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	*	优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	* @param <T extend T>
	 */
    public int insert(T record){
    	record.set("createDate", new Date());
    	record.set("updateDate", new Date());
    	record.set("delFlag", "0");
    	return mapper.insert(record);
    }

    /**
	 * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	*支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	*优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	* @param <T extend T>
	 */
    public int insertSelective(T record){
    	record.set("createDate", new Date());
    	record.set("updateDate", new Date());
    	record.set("delFlag", "0");
    	return mapper.insertSelective(record);
    }

    /**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	* @param <T extend T>
	 */
    public int delete(T key){
    	return mapper.delete(key);
    }

    /**
	 * 通过主键进行删除,这里最多只会删除一条数据
	*单个字段做主键时,可以直接写主键的值
	*联合主键时,key可以是实体类,也可以是Map
	* @param <T extend T>
	 */
    public int deleteByPrimaryKey(Object key){
    	return mapper.deleteByPrimaryKey(key);
    }

    /**
	*根据主键进行更新,这里最多只会更新一条数据
	*参数为实体类
	* @param <T extend T>
	 */
    public int updateByPrimaryKey(T record){
    	record.set("updateDate", new Date());
    	return mapper.updateByPrimaryKey(record);
    }

    /**
	 *根据主键进行更新
	*只会更新不是null的数据
	* @param <T extend T>
	 */
    public int updateByPrimaryKeySelective(T record){
    	record.set("updateDate", new Date());
    	return mapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 保存或者更新，根据传入id主键是不是null来确认
    * @param record
    * @return 影响行数
     */
    public int save(T record){
		int count = 0;
		if(record.get("id") == null){
			count = this.insertSelective(record);
		}else{
			count = this.updateByPrimaryKeySelective(record);
		}
		return count;
	}
    
    /**
     * 单表分页
    * @param pageNum 页码
    * @param pageSize 条数
    * @param record 条件实体
    * @return
     */
	public PageInfo<T> selectPage(int pageNum,int pageSize,T record){
    	PageHelper.startPage(pageNum, pageSize);
    	return new PageInfo<T>(mapper.select(record));
    }

}
