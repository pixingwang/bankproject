package bank.spring.data.neo4j.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bank.spring.data.neo4j.importdata.DBfileService;


@RestController
public class DBController {
	private final DBfileService dbfileservice;
	
	@Autowired
	public DBController(DBfileService dbfileservice) {
		this.dbfileservice=dbfileservice;
	}	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)//上传文件
	@ResponseBody
	public String upload(@RequestParam("uploadfile") MultipartFile multipartfile) throws IllegalStateException, IOException  {

		if(multipartfile.isEmpty()) return "空文件";
		return dbfileservice.UpdateDB(multipartfile);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)//上传文件
	@ResponseBody
	public String delete() {

		return dbfileservice.DeleteDB();
	}
}
