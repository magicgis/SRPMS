package ctrl;

import engine.Engine;
import entity.*;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.*;

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
    @Autowired
    PatentService patentService;
    @Autowired
    ProjectService projectService;
    @Autowired
    BookService bookService;
    @Autowired
    AchAppraisalService achAppraisalService;
    @Autowired
    AchAwardService achAwardService;
    @Autowired
    PaperService paperService;

    @RequestMapping(value = {"index/**", "index"}, method = RequestMethod.GET)
    public String newindex(Model model, RedirectAttributes redirectAttributes) {
        return "index";
    }

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
            return "redirect:index#";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("level");
        return "redirect:login";
    }

    @RequestMapping(value = {"user/new"}, method = RequestMethod.GET)
    public String userInfo(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {
        return "userEdit";
    }

    @RequestMapping(value = {"user/{id}"}, method = RequestMethod.GET)
    public String userInfo(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session,
                           @PathVariable("id") String id) {
//        User user = (User) session.getAttribute("user");
        User user = userService.getById(id);
        if (user != null) {
            model.addAttribute(user);
        }
        return "userEdit";
    }

    @RequestMapping(value = {"magazine/{orderId}"}, method = RequestMethod.GET)
    public String magazineEdit(Model model, @PathVariable("orderId") String orderId,
                               HttpServletRequest request, RedirectAttributes redirectAttributes) {
        List<Task> task = engine.getTaskByOrder(orderId);
        model.addAttribute(task.get(0));
//        task.get(0).getVariableMap().get("filesData")
        return "magazineEdit";
    }

    @RequestMapping(value = {"paper/{paperId}"}, method = RequestMethod.GET)
    public String paperEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                            @PathVariable("paperId") String paperId) {
        Paper paper = paperService.getById(paperId);
        model.addAttribute(paper);
        return "paperEdit";
    }

    @RequestMapping(value = {"book/{bookId}"}, method = RequestMethod.GET)
    public String bookEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                           @PathVariable("bookId") String bookId) {
        Book book = bookService.getById(bookId);
        model.addAttribute(book);
        return "bookEdit";
    }

    @RequestMapping(value = {"patent/new"}, method = RequestMethod.GET)
    public String newPatent(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new Patent());
        return "patentEdit";
    }

    @RequestMapping(value = {"patent/{patentId}"}, method = RequestMethod.GET)
    public String patentEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                             @PathVariable("patentId") String patentId) {
        Patent patent = patentService.getById(patentId);
        model.addAttribute(patent);
        return "patentEdit";
    }

    @RequestMapping(value = {"project/new"}, method = RequestMethod.GET)
    public String newProject(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new Project());
        return "projectEdit";
    }

    @RequestMapping(value = {"project/{projectId}"}, method = RequestMethod.GET)
    public String projectEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                              @PathVariable("projectId") String projectId) {
        Project project = projectService.getById(projectId);
        model.addAttribute(project);
        return "projectEdit";
    }

    @RequestMapping(value = {"achAppraisal/new"}, method = RequestMethod.GET)
    public String newAchAppraisal(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new AchAppraisal());
        return "achAppraisalEdit";
    }

    @RequestMapping(value = {"achAppraisal/{achAppraisalId}"}, method = RequestMethod.GET)
    public String achAppraisalEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                                   @PathVariable("achAppraisalId") String achAppraisalId) {
        AchAppraisal achAppraisal = achAppraisalService.getById(achAppraisalId);
        model.addAttribute(achAppraisal);
        return "achAppraisalEdit";
    }

    @RequestMapping(value = {"achAward/new"}, method = RequestMethod.GET)
    public String newAchAward(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new AchAward());
        return "achAwardEdit";
    }

    @RequestMapping(value = {"achAward/{achAwardId}"}, method = RequestMethod.GET)
    public String achAwardEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                               @PathVariable("achAwardId") String achAwardId) {
        AchAward achAward = achAwardService.getById(achAwardId);
        model.addAttribute(achAward);
        return "achAwardEdit";
    }

    @RequestMapping(value = {"achTran/new"}, method = RequestMethod.GET)
    public String newachTran(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new AchAward());
        return "achTranEdit";
    }

    @RequestMapping(value = {"achTran/{achAwardId}"}, method = RequestMethod.GET)
    public String achTranEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                              @PathVariable("achAwardId") String achAwardId) {
        AchAward achAward = achAwardService.getById(achAwardId);
        model.addAttribute(achAward);
        return "achTranEdit";
    }


    @RequestMapping(value = {"sysBaseInfo"}, method = RequestMethod.GET)
    public String sysBaseInfo(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "sysBaseInfo";
    }

    @RequestMapping(value = {"order/{orderId}"}, method = RequestMethod.GET)
    public String OrderEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                            @PathVariable("orderId") String orderId) {
        Order order = engine.getOrder(orderId);
        Task task = engine.getTaskByOrder(orderId).get(0);
        String type = (String) order.getVariableMap().get("WF_Type");
        String entityId = (String) order.getVariableMap().get("WF_Entity");
        switch (type) {
            case "patent":
                Patent patent = patentService.getById(entityId);
                patent.setArgMap(order.getVariableMap());
                model.addAttribute(patent);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "patentEdit";
            case "project":
                Project project = projectService.getById(entityId);
                project.setArgMap(order.getVariableMap());
                model.addAttribute(project);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "projectEdit";
            case "achAward":
                AchAward achAward = achAwardService.getById(entityId);
                achAward.setArgMap(order.getVariableMap());
                model.addAttribute(achAward);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "achAwardEdit";
            case "achAppraisal":
                AchAppraisal achAppraisal = achAppraisalService.getById(entityId);
                achAppraisal.setArgMap(order.getVariableMap());
                model.addAttribute(achAppraisal);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "achAppraisalEdit";
            case "paper":
                model.addAttribute(order);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "paperEdit";
            case "book":
                model.addAttribute(order);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "bookEdit";
            default:
                return "redirect:/allSRInfo";
        }
    }

    @RequestMapping(value = {"task/{taskId}"}, method = RequestMethod.GET)
    public String taskEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                           @PathVariable("taskId") String taskId) {
        Task task = engine.getTask(taskId);
        Order order = engine.getOrder(task.getOrderId());
        String type = (String) order.getVariableMap().get("WF_Type");
        String entityId = (String) order.getVariableMap().get("WF_Entity");
        switch (type) {
            case "patent":
                Patent patent = patentService.getById(entityId);
                patent.setArgMap(order.getVariableMap());
                model.addAttribute(patent);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "patentEdit";
            case "project":
                Project project = projectService.getById(entityId);
                project.setArgMap(order.getVariableMap());
                model.addAttribute(project);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "projectEdit";
            case "achAward":
                AchAward achAward = achAwardService.getById(entityId);
                achAward.setArgMap(order.getVariableMap());
                model.addAttribute(achAward);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "achAwardEdit";
            case "achAppraisal":
                AchAppraisal achAppraisal = achAppraisalService.getById(entityId);
                achAppraisal.setArgMap(order.getVariableMap());
                model.addAttribute(achAppraisal);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "achAppraisalEdit";
            case "paper":
                model.addAttribute(order);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "paperEdit";
            case "book":
                model.addAttribute(order);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "bookEdit";

            default:
                return "redirect:/allSRInfo";
        }
    }

}
