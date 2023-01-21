package es.cifpcm.vidicdaliborkamiali.web;

import es.cifpcm.vidicdaliborkamiali.dao.UsersRepository;
import es.cifpcm.vidicdaliborkamiali.model.User;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class UsersController {
    @Autowired
    UsersRepository usersRepository;
    private static final AtomicInteger count = new AtomicInteger(600);
    @RequestMapping("/users")
    public String users(Model model){
        model.addAttribute("user", usersRepository.findAll());
        return "users/users";
    }

    @RequestMapping("/createUser")
    public String create(Model model) {
        return "users/createUser";
    }
    @RequestMapping("/saveUser")
    public String save(@RequestParam String name, @RequestParam String password) {
        User user = new User();
        user.setId((short)count.incrementAndGet());
        user.setUserName(name);
        user.setPassword(password);
        usersRepository.save(user);

        return "redirect: users/showUser/" + user.getId();
    }
    @RequestMapping("users/showUser/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("user", usersRepository.findById(id).orElse(null));
        return "users/showUser";
    }

    @RequestMapping("/deleteUser")
    public String delete(@RequestParam Integer id) {
        User user = usersRepository.findById(id).orElse(null);
        usersRepository.delete(user);

        return "redirect:/users";
    }

    @RequestMapping("users/showUser/editUser/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", usersRepository.findById(id).orElse(null));
        return "users/editUser";
    }

    @RequestMapping("/updateUser")
    public String update(@RequestParam Integer id, @RequestParam String userName, @RequestParam String password) {
        User user = usersRepository.findById(id).orElse(null);
        user.setUserName(userName);
        user.setPassword(password);
        usersRepository.save(user);
        return "redirect: users/showUser/" + user.getId();
    }

}
