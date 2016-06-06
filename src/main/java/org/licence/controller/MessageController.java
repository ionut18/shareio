package org.licence.controller;

import org.licence.model.MessageModel;
import org.licence.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ionut on 04.06.2016.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{

    @Autowired
    MessageService messageService;

    @RequestMapping("/")
    public String getMessages(Model model) {

        if(messageService.getAllMessagesForUser(getUserName()) != null) {
            model.addAttribute("messages", messageService.getAllMessagesForUser(getUserName()));
            return "message/list";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public String handleMessage(@ModelAttribute("messageModel") MessageModel messageModel) {
        messageService.handleResponse(messageModel);
        return "redirect:/";
    }

}
