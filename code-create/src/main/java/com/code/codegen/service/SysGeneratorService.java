package com.code.codegen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.codegen.entity.GenConfig;


import java.util.List;
import java.util.Map;

/**
 * @author Alan
 */
public interface SysGeneratorService {
	/**
	 * generate code
	 *
	 * @param tableNames 表名称
	 * @return
	 */
	byte[] generatorCode(GenConfig tableNames);

	/**
	 * get tables info by pagination
	 * @param query 查询条件
	 * @return
	 */
	List<Map<String,Object>> queryPage(Page query, Map<String, Object> params);
}
