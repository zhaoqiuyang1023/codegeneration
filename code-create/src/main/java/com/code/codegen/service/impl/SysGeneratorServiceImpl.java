package com.code.codegen.service.impl;

import cn.hutool.core.io.IoUtil;
import com.code.codegen.entity.GenConfig;
import com.code.codegen.mapper.SysGeneratorMapper;
import com.code.codegen.service.SysGeneratorService;
import com.code.codegen.util.CodegenUtils;
import com.code.codegen.util.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;


@Service
@AllArgsConstructor
public class SysGeneratorServiceImpl implements SysGeneratorService {
	private final SysGeneratorMapper sysGeneratorMapper;


	/**
	 * get tables info by pagination
	 *
	 * @param query search query
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryPage(Query query) {
		return sysGeneratorMapper.queryList(query);
	}

	/**
	 * generate code
	 *
	 * @param genConfig configuration to generate code
	 * @return
	 */
	@Override
	public byte[] generatorCode(GenConfig genConfig) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		Map<String, String> table = queryTable(genConfig.getTableName());
		List<Map<String, String>> columns = queryColumns(genConfig.getTableName());

		CodegenUtils.generateCode(genConfig, table, columns, zip);
		IoUtil.close(zip);
		return outputStream.toByteArray();
	}

	private Map<String, String> queryTable(String tableName) {
		return sysGeneratorMapper.queryTable(tableName);
	}

	private List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorMapper.queryColumns(tableName);
	}
}
