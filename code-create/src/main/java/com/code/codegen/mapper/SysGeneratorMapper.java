package com.code.codegen.mapper;
import com.code.codegen.util.Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface SysGeneratorMapper{

	/**
	 * get tables info by pagination
	 *
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryList(Query params);



	/**
	 * get table infos
	 * @param tableName table name
	 * @return
	 */
	@Select(value = "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) and table_name = #{tableName}")
	Map<String, String> queryTable(String tableName);

	/**
	 * Get columns infos of table
	 * @param tableName 表名称
	 * @return
	 */
	@Select(value = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
	List<Map<String, String>> queryColumns(String tableName);
}
