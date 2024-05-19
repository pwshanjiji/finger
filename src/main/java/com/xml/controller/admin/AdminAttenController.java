package com.xml.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xml.entity.Admin;
import com.xml.entity.Atten;
import com.xml.entity.User;
import com.xml.mapper.AttenMapper;
import com.xml.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminAttenController {

	@Autowired
	private AttenMapper attenMapper;


	@Autowired
	private UserMapper userMapper;


	@RequestMapping("/atten")
	public String atten(Integer pageNum,
			String userName,
			Model model, HttpSession session) {
		if(pageNum==null) {
			pageNum=1;
		}
		Atten atten = new Atten();
		atten.setUserName(userName);
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
		return "admin/atten/list";
	}
	
	
	@RequestMapping(value="/gotoAddAtten")
	public String gotoAddAtten(Model model){
		List<User> list = userMapper.findList(null);
		model.addAttribute("users", list);
		return "admin/atten/add";
	}

	@RequestMapping(value="/addAtten", method = {RequestMethod.POST})
	public ModelAndView addAtten(Atten atten, HttpSession session) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		atten.setTime(formatStr);
		attenMapper.insert(atten);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/atten");
		return modelAndView;
	}

	/**
	 */
	@RequestMapping(value="/attenDel")
	public ModelAndView deleteEmp( Integer id){
		attenMapper.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/atten");
		return modelAndView;
	}

	/**
	 * 去编辑页面
	 */
	@RequestMapping(value="/gotoEditAtten")
	public String gotoEditHotelType(Integer id,Map<String, Object> map){
		List<User> list = userMapper.findList(null);
		map.put("users", list);
		Atten atten =attenMapper.queryById(id);
		map.put("pa", atten);
		return "admin/atten/edit";
	}

	@RequestMapping(value="/editAtten", method = {RequestMethod.POST})
	public ModelAndView editAtten(Atten atten,HttpSession session) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		atten.setTime(formatStr);
		attenMapper.update(atten);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/atten");
		return modelAndView;
	}

}