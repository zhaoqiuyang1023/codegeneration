package com.code.codegen.mapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface SysGeneratorMapper{

	/**
	 * get tables info by pagination
	 *
	 * @param m
	 * @return
	 */
	List<Map<String, Object>> queryList(Page query,@Param("m") Map<String, Object> m);



	/**
	 * get table infos
	 * @param tableName table name
	 * @return
	 */
	//SELECT name as tableName  FROM sqlite_master WHERE type='table' ORDER BY name;
	//@Select(value = "select name tableName, engine, table_comment tableComment, create_time createTime from schema.tables where table_schema = (select database()) and table_name = #{tableName}")
	@Select(value = "SELECT name as tableName  FROM sqlite_master WHERE type='table' and name = #{tableName}  ORDER BY name")

	Map<String, String> queryTable(String tableName);

	/**
	 * Get columns infos of table
	 * @param tableName 表名称
	 * @return
	 */
	@Select(value = " PRAGMA table_info ([${tableName}]) ")
	List<Map<String, String>> queryColumns(@Param("tableName") String tableName);
}
