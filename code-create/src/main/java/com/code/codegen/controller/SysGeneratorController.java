package com.code.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.code.codegen.entity.GenConfig;
import com.code.codegen.service.SysGeneratorService;
import com.code.codegen.util.Query;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class SysGeneratorController {

	private final SysGeneratorService sysGeneratorService;

	/**
	 * list tables
	 *
	 * @param params parms
	 * @return tables
	 */
	@GetMapping("/page")
	public Page list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		query.setRecords(sysGeneratorService.queryPage(query));
		return query;
	}

	/**
	 * generate code
	 */
	@PostMapping("/code")
	public void code(@RequestBody GenConfig genConfig, HttpServletResponse response) throws IOException {
		byte[] data = sysGeneratorService.generatorCode(genConfig);

		response.reset();
		response.setHeader("Content-Disposition", String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}
}
