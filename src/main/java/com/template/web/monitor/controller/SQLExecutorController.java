package com.template.web.monitor.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.template.common.constant.Constant;

@Controller
@RequestMapping("${adminPath}/monitor/db")
public class SQLExecutorController {

	@RequestMapping(value = "sql", method = RequestMethod.GET)
	public String showSQLForm() {
		return "sys/monitor/db/sqlForm";
	}

	@RequestMapping(value = "sql", method = RequestMethod.POST)
	public String executeSQL(String sql, final Model model) {
		try {
			if (sql != null) {
				String lowerCaseSQL = sql.trim().toLowerCase();
				final boolean isDML = lowerCaseSQL.startsWith("insert")
						|| lowerCaseSQL.startsWith("update")
						|| lowerCaseSQL.startsWith("delete");
				final boolean isDQL = lowerCaseSQL.startsWith("select");
				if (!isDML && !isDQL) {
					model.addAttribute(Constant.ERROR,
							"您执行的SQL不允许，只允许insert、update、delete、select");
				} else if (isDML) {

				} else {

				}
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			model.addAttribute(Constant.ERROR, sw.toString());
		}
		return "sys/monitor/db/result";
	}

}
