package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping(path = "/user")
    public User getUser(@RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response
            , HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null)
            user = userDao.findById(id).get();
        session.setAttribute("user",User.builder().name("Session").build());
        System.out.println(user);
        return user;
    }

    @RequestMapping("/exception")
    public void exception() {
        throw new RuntimeException("어머나!");
    }

    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    public void upload() {

    }

    @RequestMapping(path="/upload" , method= RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/")+"/WEB-INF/static/" + file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url","/images/"+ file.getOriginalFilename());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e",e);
        return modelAndView;
    }
}
