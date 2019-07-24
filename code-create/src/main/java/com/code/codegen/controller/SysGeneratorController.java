package com.code.codegen.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.code.codegen.entity.GenConfig;
import com.code.codegen.service.SysGeneratorService;
import com.code.codegen.util.Query;
import com.code.codegen.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
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
	@PostMapping("/page")
	@ResponseBody
	public R<Page> list(@RequestParam Map<String, Object> params) {
		Page<Map<String,Object>> page = new Page<>(1,10);
		page.setRecords(sysGeneratorService.queryPage(page,params));
		return new R<>(page);
	}

	@RequestMapping("/index")
	public String list() {
		return "view/index";
	}

	/**
	 * generate code
	 */
	@RequestMapping("/code")
	public void code(@RequestBody GenConfig genConfig, HttpServletResponse response) throws IOException {
		System.out.println(genConfig);
		initCode(genConfig, response);
	}

	private void initCode(@RequestBody GenConfig genConfig, HttpServletResponse response) throws IOException {
		byte[] data = sysGeneratorService.generatorCode(genConfig);

		String zipName = "code";
		response.reset();
		response.setHeader("Content-Disposition", String.format("attachment; filename=%s.zip", zipName));
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}

	@RequestMapping("/code/{json}")
	public void code1(@PathVariable("json") String genConfig, HttpServletResponse response) throws IOException {
		System.out.println(genConfig);
		GenConfig genConfig1=JSONUtil.toBean(JSONUtil.parseObj(genConfig),GenConfig.class);
		initCode(genConfig1, response);
	}
}
