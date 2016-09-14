package tiy.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by RdDvls on 9/12/16.
 */
@Controller
public class SampleSpringAppController {
    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public String person(Model model, String name, String city, int age) {
        Person p = new Person(name, city, age);
        model.addAttribute("person", p);
        return "person";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("userName"));
        return "home";
    }
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("userName");
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public String chat(Model model, HttpSession session, String message) {
       return "chat";
    }

    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public String send(Model model, String message){
        WebChatClient client = new WebChatClient();
        String serverResponse = client.sendMessageToUser(message);
            System.out.println(message);
        return "redirect:/chat";
        }


}


