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
            return "redirect:allSRInfo";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("level");
        return "redirect:login";
    }

    @RequestMapping(value = {"paper"}, method = RequestMethod.GET)
    public String paper(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "paper";
    }

    @RequestMapping(value = {"paper/new"}, method = RequestMethod.GET)
    public String newPaper(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new Paper());
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

    @RequestMapping(value = {"book"}, method = RequestMethod.GET)
    public String book(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "book";
    }

    @RequestMapping(value = {"book/new"}, method = RequestMethod.GET)
    public String newBook(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new Book());
        return "bookEdit";
    }

    @RequestMapping(value = {"book/{bookId}"}, method = RequestMethod.GET)
    public String bookEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                           @PathVariable("bookId") String bookId) {
        Book book = bookService.getById(bookId);
        model.addAttribute(book);
        return "bookEdit";
    }

    @RequestMapping(value = {"project"}, method = RequestMethod.GET)
    public String project(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "project";
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

    @RequestMapping(value = {"achAppraisal"}, method = RequestMethod.GET)
    public String achAppraisal(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "achAppraisal";
    }

    @RequestMapping(value = {"achAppraisal/new"}, method = RequestMethod.GET)
    public String newAchAppraisal(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        model.addAttribute(new AchAppraisal());
        return "achAppraisalEdit";
    }

    @RequestMapping(value = {"project/{achAppraisalId}"}, method = RequestMethod.GET)
    public String achAppraisalEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
                              @PathVariable("achAppraisalId") String achAppraisalId) {
        AchAppraisal achAppraisal = achAppraisalService.getById(achAppraisalId);
        model.addAttribute(achAppraisal);
        return "achAppraisalEdit";
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
            case "book":
                Book book = bookService.getById(entityId);
                book.setArgMap(order.getVariableMap());
                model.addAttribute(book);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "bookEdit";
            case "paper":
                model.addAttribute(order);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "paperEdit";
            default:
                return "redirect:allSRInfo";
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
            case "book":
                Book book = bookService.getById(entityId);
                book.setArgMap(order.getVariableMap());
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "bookEdit";
            case "paper":
                model.addAttribute(order);
                model.addAttribute("taskId", task.getId());
                model.addAttribute("taskName", task.getTaskName());
                return "paperEdit";
            default:
                return "redirect:allSRInfo";
        }
    }

}
