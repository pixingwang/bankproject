package bank.spring.data.neo4j.controller;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class PageController {
	
	@RequestMapping(value="/db")//担保环界面
	public String dbPage() throws IOException  {

		return "page/db.html";
	}
	
	@RequestMapping(value="/admin")//更新数据库界面
	public String adminPage()  {
		
		return "page/admin.html";
	}
	
	@RequestMapping(value="/searchAccount")//账户搜索界面
	public String searchAcc() {
		return "page/searchAccount.html";
	}
	
	@RequestMapping(value="/searchCompany")//公司搜索界面
	public String searchCom() {
		return "page/searchCompany.html";
	}
	@RequestMapping(value="/index")//公司搜索界面
	public String index() {
		return "page/index.html";
	}
}
