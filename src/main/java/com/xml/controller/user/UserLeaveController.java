package com.xml.controller.user;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xml.entity.Leave;
import com.xml.entity.User;
import com.xml.mapper.LeaveMapper;
import com.xml.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserLeaveController {

	@Autowired
	private LeaveMapper leaveMapper;

	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/leave")
	public String leave(Integer pageNum,
			String name,
			Model model,HttpSession session){
		User user = (User) session.getAttribute("user");

		if(pageNum==null) {
			pageNum=1;
		}
		Leave leave = new Leave();
		leave.setUserId(user.getId());
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Leave> leaves = leaveMapper.queryAll(leave);
		System.out.println(leaves);
		PageInfo pageInfo = new PageInfo<Leave>(leaves, 5);

        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "user/leave/list";
	}


	@RequestMapping(value="/gotoAddLeave")
	public String gotoAddLeave(Model model,HttpServletRequest request){
		return "user/leave/add";
	}

	/*
	 * 上传
	 */
	@RequestMapping(value="/addLeave", method = {RequestMethod.POST})
	public String addLeave(Leave leave,HttpSession session) {
		User user = (User) session.getAttribute("user");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355

		leave.setUserId(user.getId());
		leave.setLaunchTime(formatStr);
		leave.setStatus("审批中");

		leaveMapper.insert(leave);

		return "redirect:/user/leave";
	}




}
