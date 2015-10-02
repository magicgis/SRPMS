package ctrl;

import engine.Engine;
import entity.User;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by guofan on 2015/9/18.
 */
@Controller
@SuppressWarnings("unused")
@RequestMapping(value = "/")
public class Index {

    @Autowired
    UserService userService;
    @Autowired
    Engine engine;

    @RequestMapping(value = {"login", ""}, method = RequestMethod.GET)
    public String index(Model model, RedirectAttributes redirectAttributes) {
        return "login";
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    public String login(@RequestParam("username") String staId, @RequestParam("password") String pwd,
                        HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User u = userService.getUser(staId, pwd);
        if (u == null || u.getStatus() == null || u.getStatus() == 0) {
            redirectAttributes.addFlashAttribute("error", "loginWrong");
            return "redirect:login";
        }
        else {
            request.getSession().setAttribute("user", u);
            request.getSession().setAttribute("level", u.getPrivilege());
            return "redirect:paper";
        }
    }

    @RequestMapping(value = {"paper"}, method = RequestMethod.GET)
    public String paper(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "paper";
    }

    @RequestMapping(value = {"magazine"}, method = RequestMethod.GET)
    public String magazine(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "magazine";
    }

    @RequestMapping(value = {"magazine/{orderId}"}, method = RequestMethod.GET)
    public String magazineEdit(Model model, @PathVariable("orderId") String orderId,
                               HttpServletRequest request, RedirectAttributes redirectAttributes) {
        List<Task> task = engine.getTaskByOrder(orderId);
        model.addAttribute(task.get(0));
//        task.get(0).getVariableMap().get("filesData")
        return "magazineEdit";
    }

    @RequestMapping(value = {"book"}, method = RequestMethod.GET)
    public String book(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "book";
    }

    @RequestMapping(value = {"userInfo"}, method = RequestMethod.GET)
    public String userInfo(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute(user);
        }
        return "userInfo";
    }

    @RequestMapping(value = {"patent"}, method = RequestMethod.GET)
    public String patent(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "patent";
    }

    @RequestMapping(value = {"patent/{orderId}"}, method = RequestMethod.GET)
    public String patentEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                             @PathVariable("orderId") String orderId) {
        List<Task> task = engine.getTaskByOrder(orderId);
        model.addAttribute(task.get(0));
        return "patentEdit";
    }

    @RequestMapping(value = {"project"}, method = RequestMethod.GET)
    public String project(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "project";
    }

    @RequestMapping(value = {"newOthers"}, method = RequestMethod.GET)
    public String newOthers(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "newOthers";
    }

    @RequestMapping(value = {"newMedicine"}, method = RequestMethod.GET)
    public String newMedicine(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "newMedicine";
    }

    @RequestMapping(value = {"newInstruments"}, method = RequestMethod.GET)
    public String newInstruments(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "newInstruments";
    }

    @RequestMapping(value = {"newFunctionFood"}, method = RequestMethod.GET)
    public String newFunctionFood(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "newFunctionFood";
    }

    @RequestMapping(value = {"award"}, method = RequestMethod.GET)
    public String award(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "award";
    }

    @RequestMapping(value = {"appraise"}, method = RequestMethod.GET)
    public String appraise(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "appraise";
    }

    @RequestMapping(value = {"change"}, method = RequestMethod.GET)
    public String change(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "change";
    }

    @RequestMapping(value = {"different"}, method = RequestMethod.GET)
    public String different(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "different";
    }

    @RequestMapping(value = {"sysBaseInfo"}, method = RequestMethod.GET)
    public String sysBaseInfo(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "sysBaseInfo";
    }

    @RequestMapping(value = {"allSRInfo"}, method = RequestMethod.GET)
    public String allSRinfo(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "allSRInfo";
    }

}
