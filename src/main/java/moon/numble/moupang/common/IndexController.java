package moon.numble.moupang.common;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.annotation.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String main(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("user", user);
        }
        return "index.html";
    }
}
