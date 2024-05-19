package com.xml.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xml.entity.Leave;
import com.xml.mapper.LeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminLeaveController {

	@Autowired
	private LeaveMapper leaveMapper;
	
	@RequestMapping("/leave")
	public String leave(Integer pageNum,
			String userName,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		Leave leave = new Leave();
		leave.setUserName(userName);
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
		return "admin/leave/list";
	}

	@RequestMapping(value="/leaveDel")
	public ModelAndView deleteEmp( Integer id){
		leaveMapper.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/leave");
		return modelAndView;
	}

	@RequestMapping(value="/leaveApprove")
	public ModelAndView leaveApprove(Integer id){
		Leave leave = leaveMapper.queryById(id);
		leave.setStatus("审批通过");
		leaveMapper.update(leave);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/leave");
		return modelAndView;
	}

	@RequestMapping(value="/leaveReject")
	public ModelAndView leaveReject(Integer id){
		Leave leave = leaveMapper.queryById(id);
		leave.setStatus("审批拒绝");
		leaveMapper.update(leave);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/leave");
		return modelAndView;
	}

}