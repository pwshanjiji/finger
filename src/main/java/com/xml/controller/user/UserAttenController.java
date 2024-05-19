package com.xml.controller.user;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xml.entity.Atten;
import com.xml.entity.User;
import com.xml.mapper.AttenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserAttenController {

	@Autowired
	private AttenMapper attenMapper;
	
	@RequestMapping("/atten")
	public String atten(Integer pageNum,
						String title,
						Model model, HttpSession session){
		User user = (User) session.getAttribute("user");

		if(pageNum==null) {
			pageNum=1;
		}
		Atten atten = new Atten();
		atten.setUserId(user.getId());
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Atten> attens = attenMapper.queryAll(atten);
		System.out.println(attens);
		PageInfo pageInfo = new PageInfo<Atten>(attens, 5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "user/atten/list";
	}
}