package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CarRepository carRepository;

    public void run(String...args){
        User user = new User("bart", "bart@domain.com", "bart", "Bart", "Simpson", true);
        Role userRole = new Role("bart", "ROLE_USER"); //specifies the role

        userRepository.save(user); //saving user object
        roleRepository.save(userRole); //saving userRole object

        User admin = new User("super", "super@domain.com", "super", "Super", "Hero", true);
        Role adminRole1 = new Role("super", "ROLE_ADMIN");
        Role adminRole2 = new Role("super", "ROLE_USER");

        userRepository.save(admin);
        roleRepository.save(adminRole1);
        roleRepository.save(adminRole2);

        Car prius = new Car("Toyota", "Prius", "A gas saving car", "https://res.cloudinary.com/dkim/image/upload/v1628533528/obgo6wkag41hsv7fvlrz.png");
        Car gtr = new Car("Nissan", "GTR", "A fast car", "https://res.cloudinary.com/dkim/image/upload/v1628533554/wkb5gunbedw4ia9knbjp.jpg");
        Car tesla = new Car("Tesla", "Model Y", "A fully electric car", "https://res.cloudinary.com/dkim/image/upload/v1628533482/mzyyics8gyr01226drrj.jpg");

        carRepository.save(prius);
        carRepository.save(gtr);
        carRepository.save(tesla);
    }
}
