package com.satyam.contacts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.satyam.contacts.dao.ContactDAO;
import com.satyam.contacts.model.Contacts;

@Controller
public class MainController {
	
	@Autowired
	private ContactDAO contactdao;
	
	@RequestMapping(value="/")
	public ModelAndView listcontact(ModelAndView model){
		List<Contacts> listContact= contactdao.list();
		model.addObject("listContact", listContact);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public ModelAndView newContacts(ModelAndView model){
		Contacts newContacts=new Contacts();
		model.addObject("Contact", newContacts);
		model.setViewName("contact_form");
		return model;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveContacts(@ModelAttribute Contacts Contact){
		
		if(Contact.getId()==0){
			contactdao.save(Contact);
		}else{
			contactdao.update(Contact);
		}
		
		
		return new ModelAndView("redirect:/");	
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView editContacts(HttpServletRequest request){
		Integer id=Integer.parseInt(request.getParameter("id"));
		Contacts contacts=contactdao.get(id);
		
		ModelAndView model=new ModelAndView("contact_form");
		model.addObject("Contact", contacts);
		
		return  model;	
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public ModelAndView deleteContacts(@RequestParam Integer id){
		contactdao.delete(id);
		return new ModelAndView("redirect:/");
		
	}

}
