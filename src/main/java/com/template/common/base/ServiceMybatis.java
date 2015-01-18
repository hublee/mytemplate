package com.template.common.base;

import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.constant.Constant;
import com.template.common.mybatis.mapper.BaseMapper;
import com.template.common.spring.utils.SpringContextHolder;
import com.template.common.utils.StringConvert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public abstract class ServiceMybatis<T extends BaseEntity> implements BaseService<T>{

	@Autowired
	protected Mapper<T> mapper;
	
	@Resource
	protected BaseMapper baseMapper;

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * 
	 * @param <T extend T>
	 */
	public List<T> select(T record) {
		record.setDelFlag(Constant.DEL_FLAG_NORMAL);
		return mapper.select(record);
	}

	/**
	 * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
	 * 
	 * @param <T extend T>
	 */
	public int selectCount(T record) {
		record.setDelFlag(Constant.DEL_FLAG_NORMAL);
		return mapper.selectCount(record);
	}

	/**
	 * 根据主键进行查询,必须保证结果唯一 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param <T extend T>
	 */
	public T selectByPrimaryKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * 插入一条数据 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 * 
	 * @param <T extend T>
	 */
	public int insert(T record) {
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		record.setDelFlag(Constant.DEL_FLAG_NORMAL);
		return mapper.insert(record);
	}

	/**
	 * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	 * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 * 
	 * @param <T extend T>
	 */
	public int insertSelective(T record) {
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		record.setDelFlag(Constant.DEL_FLAG_NORMAL);
		return mapper.insertSelective(record);
	}

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * 
	 * @param <T extend T>
	 */
	public int delete(T key) {
		return mapper.delete(key);
	}

	/**
	 * 通过主键进行删除,这里最多只会删除一条数据 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param <T extend T>
	 */
	public int deleteByPrimaryKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	/**
	 * 根据主键进行更新,这里最多只会更新一条数据 参数为实体类
	 * 
	 * @param <T extend T>
	 */
	public int updateByPrimaryKey(T record) {
		record.setUpdateDate(new Date());
		return mapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据主键进行更新 只会更新不是null的数据
	 * 
	 * @param <T extend T>
	 */
	public int updateByPrimaryKeySelective(T record) {
		record.setUpdateDate(new Date());
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 单表逻辑删除(需要有delFlag)
	* @param bean 删除的实体类型
	* @return 影响行数
	 */
	public <M extends BaseEntity> int updateDelFlagToDelStatusById(Class<M> bean,Long id){
		String mapperName = StringUtils.uncapitalize(bean.getSimpleName())+"Mapper"; 
		Mapper<M> mapper = SpringContextHolder.getBean(mapperName);
		M m = null;
		try {
			m = bean.newInstance();
			m.setId(id);
			m.setDelFlag(Constant.DEL_FLAG_DELETE);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return mapper.updateByPrimaryKeySelective(m);
	}

	/**
	 * 保存或者更新，根据传入id主键是不是null来确认
	 * 
	 * @param record
	 * @return 影响行数
	 */
	public int save(T record) {
		int count = 0;
		if (record.get("id") == null) {
			count = this.insertSelective(record);
		} else {
			count = this.updateByPrimaryKeySelective(record);
		}
		return count;
	}

	/**
	 * 单表分页
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            条数
	 * @param record
	 *            条件实体
	 * @return
	 */
	public PageInfo<T> selectPage(int pageNum, int pageSize, T record) {
		record.setDelFlag(Constant.DEL_FLAG_NORMAL);
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<T>(mapper.select(record));
	}

	/**
	 * 删除前验证是否有关联(仅限于单表)
	* @param bean 实体class
	* @param fields 检查的实体属性
	* @param values 属性值
	* @return -1有关联
	 */
	public <M extends BaseEntity> int beforeDelete(Class<M> bean,Map<String, Object> params){
		String mapperName = StringUtils.uncapitalize(bean.getSimpleName())+"Mapper"; 
		Mapper<M> mapper = SpringContextHolder.getBean(mapperName);
		M m = null;
		try {
			m = bean.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		m.setAll(params);
		int count = mapper.selectCount(m);
		return count>0 ? -1:count;
	}
	
	/**
	 * 有树结构的删除前验证(适应于两表)
	* @param id 删除的id
	* @param Field 验证的属性名称
	* @param beans class 第一个是要验证的class 第二个为删除的class
	* @return 未通过返回-1
	 */
	public int beforeDeleteTreeStructure(Object id,String Field,Class<?>... beans){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("checkField", StringConvert.camelhumpToUnderline(Field));
		for(int i=0;i<beans.length;i++ ){
			Class<?> cl = beans[i];
			String tableName = StringConvert.camelhumpToUnderline(cl.getSimpleName());
			map.put("t"+i, tableName);
		}
		
		int count = baseMapper.beforeDeleteTreeStructure(map);
		return  count>0?-1:count;
	}

}
