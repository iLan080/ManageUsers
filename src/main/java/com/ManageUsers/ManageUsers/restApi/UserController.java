package com.ManageUsers.ManageUsers.restApi;


import com.ManageUsers.ManageUsers.Business.IUserService;
import com.ManageUsers.ManageUsers.Business.PerformanceTester;
import com.ManageUsers.ManageUsers.entity.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> get() {

        return userService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userService.add(user);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }

    @GetMapping("/users/{userId}")
    public User getById(@PathVariable int userId) {

        return userService.getUserById(userId);

    }

    @GetMapping("/users100")
    public List<User> getName100() {
        return userService.get100Name();
    }

    @GetMapping("/names")
    public List<String> getAllUserNames() {
        return userService.getAllUserNames();
    }

    @GetMapping("/sameName/{names}")
    public List<User> getUserByNames(@PathVariable List<String> names) {

        return userService.getUsersByNames(names.toArray(new String[0]));
    }

    @GetMapping("/duplicate")
    public Map<String, List<User>> getDuplicateNames() {
        return userService.getDuplicateNames();
    }

    @GetMapping("/duplicate1")
    public Map<String, List<User>> getDuplicateNames1() {
        return userService.getDuplicateNames1();
    }

    @GetMapping("/duplicate3")
    public Map<String, Map<Long, List<User>>> getDuplicateNames3() {
        return userService.getDuplicateNames3();
    }

    @GetMapping("uniqeNames")
    public List<String> getUniqeNamesDistinct() {
        return userService.getUniqueNamesDistinct();
    }

    @GetMapping("uniqeNames1")
    public Set<String> getUniqeNamesSet() {
        return userService.getUniqueNamesSet();
    }

    @GetMapping("uniqeNames2")
    public List<String> getUniqeNamesGroupCon() {
        return userService.getUniqueNamesGroupCon();
    }

    @GetMapping("uniqeNames3")
    public List<String> getUniqeNamesGroup() {
        return userService.getUniqueNamesGroup();
    }

    @GetMapping("uniqeNamesP")
    public List<String> getUniqeNamesDistinctP() {
        return userService.getUniqueNamesDistinct();
    }

    @GetMapping("uniqeNames1P")
    public Set<String> getUniqeNamesSetP() {
        return userService.getUniqueNamesSet();
    }

    @GetMapping("uniqeNames2P")
    public List<String> getUniqeNamesGroupConP() {
        return userService.getUniqueNamesGroupCon();
    }

    @GetMapping("uniqeNames3P")
    public List<String> getUniqeNamesGroupP() {
        return userService.getUniqueNamesGroup();
    }


    @Autowired
    private PerformanceTester performanceTester;


@GetMapping("/test")
    public void runPerformanceTests() {
        performanceTester.testDuplicateNameMethods();
    }

    @GetMapping("/test1")
    public void runPerformanceTests1() {
        performanceTester.testDuplicateNameMethods1();
    }
    @GetMapping("/testP")
    public void runPerformanceTestsP() {
        performanceTester.testDuplicateNameMethodsP();
    }

}

