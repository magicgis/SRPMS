package ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by guofan on 2015/10/2.
 */
@Controller
@SuppressWarnings("unused")
@RequestMapping("/edit")
public class EditTest {


    @RequestMapping(value = {"/patent"}, method = RequestMethod.GET)
    public String magazineEdit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "patentEdit";
    }

}
