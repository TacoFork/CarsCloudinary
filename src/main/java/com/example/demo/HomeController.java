package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "carlist";
    }

    @GetMapping("addCar")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        return "addcar";
    }

    @PostMapping("/carAdded")
    public String carAdded(@ModelAttribute Car car, @RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return "redirect:/addCar";
        }
        try{
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            car.setPhoto(uploadResult.get("url").toString());
            carRepository.save(car);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/addCar";
        }
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout=true";
    }
}
