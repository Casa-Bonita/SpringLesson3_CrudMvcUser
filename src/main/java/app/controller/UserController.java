package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class UserController {


    @GetMapping("/allUser")
    public String allUserIndex(Model model){
        model.addAttribute("userList", UserService.userList);
        return "allUser";
    }

    @GetMapping("/addUser")
    public String addUserIndex(){
        return "addUser";
    }

    @PostMapping("/addUser")
//    public String addUser(User user ){
    public String addUser(@RequestParam(name = "id") int id, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ){
        User user = new User(id, login, password);
        UserService.userList.add(user);
        return "addUser";
    }


    @GetMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable(name = "userId") int userId, Model model){
        Optional<User> first = UserService.userList.stream().filter(user -> user.getId() == userId).findFirst();
        first.ifPresent(user->{
            model.addAttribute("user", user);
        });
        return "userCard";
    }

    @PostMapping("/updateUser")
    public String updateUser(User newUser, Model model){
        Optional<User> first = UserService.userList.stream().filter(user -> user.getId() == newUser.getId()).findFirst();
        first.ifPresent(user-> {
            user.setLogin(newUser.getLogin());
            user.setPassword(newUser.getPassword());
            model.addAttribute("user", user);
        });

        return "userCard";
    }

    @GetMapping("/removeUser/{userId}")
    public String updateUser(@PathVariable(name = "userId") int userId){
        Optional<User> first = UserService.userList.stream().filter(user -> user.getId() == userId).findFirst();
        first.ifPresent(user->{
            UserService.userList.remove(user);
        });
        return "redirect:/allUser";
    }


}
