package com.ManageUsers.ManageUsers.Business;

import com.ManageUsers.ManageUsers.DataAccess.IUserDal;
import com.ManageUsers.ManageUsers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserManager implements IUserService{


    @Autowired
    private IUserDal userDal;


    //stream kullanarak listenin ilk 100 elemanı ada göre sıralanmış DESC
    @Override
    @Transactional
    public List<User> get100Name() {
        List<User> userList = this.userDal.getAll();

               return userList.stream()
                .limit(100)
                .sorted(Comparator.comparing(User::getFirstname))
                .collect(Collectors.toList());
    }

    //stream kullanarak tüm listenin sadece isimleri
    @Override
    @Transactional
    public List<String> getAllUserNames() {
        List<User> userList = this.userDal.getAll();

        return userList.stream()
                .map(User::getFirstname)
                .collect(Collectors.toList());
    }


    //isimleri girince onların bilgilerini göster
    //"Nyaybv","Wlxtqemil"
    @Override
    @Transactional
    public List<User> getUsersByNames(String... names) {
        List<User> userList = this.userDal.getAll();

        return userList.stream()
            .filter(user -> Stream.of(names).anyMatch(name -> name.equals(user.getFirstname())))
                .collect(Collectors.toList());
    }



    //aynı isimleri bulup yazdır ve aynı zamanda bilgileride yazdır,sayııs
    @Override
    @Transactional
    public Map<String, List<User>> getDuplicateNames() {
        List<User> userList = this.userDal.getAll();

        return userList.stream()
              .collect(Collectors.groupingBy(User::getFirstname))
            .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    @Override
    @Transactional
    public Map<String, List<User>> getDuplicateNames1() {
        List<User> userList = this.userDal.getAll();

        return userList.stream()
                .collect(Collectors.groupingByConcurrent(User::getFirstname))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
//------------------
    @Override
    @Transactional
    public Map<String, List<User>> getDuplicateNamesP() {
        List<User> userList = this.userDal.getAll();

        return userList.parallelStream()
                .collect(Collectors.groupingBy(User::getFirstname))
                .entrySet().parallelStream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    @Override
    @Transactional
    public Map<String, List<User>> getDuplicateNames1P() {
        List<User> userList = this.userDal.getAll();

        return userList.parallelStream()
                .collect(Collectors.groupingByConcurrent(User::getFirstname))
                .entrySet().parallelStream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    @Transactional
    public Map<String, Map<Long, List<User>>> getDuplicateNames3() {
        List<User> userList = this.userDal.getAll();

        return userList.stream()
                .collect(Collectors.groupingBy(User::getFirstname))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> Map.of(
                                (long) entry.getValue().size(),
                                entry.getValue()
                        )));
    }


    //bütün isimler ancak tekrar eden isimler sadece bir kere gözüksün


    @Override
    @Transactional
    public List<String> getUniqueNamesDistinct() {
        List<User> userList = this.userDal.getAll();

        return userList.stream()
                .map(User::getFirstname)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Set<String> getUniqueNamesSet() {
        List<User> userList = this.userDal.getAll();

        return userList.stream()
                .map(User::getFirstname)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public List<String> getUniqueNamesGroupCon() {
        List<User> userList = this.userDal.getAll();


        Map<String, List<User>> groupedNames = userList.stream()
                .collect(Collectors.groupingByConcurrent(User::getFirstname));
        return groupedNames.keySet().stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> getUniqueNamesGroup() {
        List<User> userList = this.userDal.getAll();


        Map<String, List<User>> groupedNames = userList.stream()
                .collect(Collectors.groupingBy(User::getFirstname));
        return groupedNames.keySet().stream().collect(Collectors.toList());
    }


   //paraleelStream kısmı --------------

    @Override
    @Transactional
    public List<String> getUniqueNamesDistinctP() {
        List<User> userList = this.userDal.getAll();

        return userList.parallelStream()
                .map(User::getFirstname)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Set<String> getUniqueNamesSetP() {
        List<User> userList = this.userDal.getAll();

        return userList.parallelStream()
                .map(User::getFirstname)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public List<String> getUniqueNamesGroupConP() {
        List<User> userList = this.userDal.getAll();


        Map<String, List<User>> groupedNames = userList.parallelStream()
                .collect(Collectors.groupingByConcurrent(User::getFirstname));
        return groupedNames.keySet().stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> getUniqueNamesGroupP() {
        List<User> userList = this.userDal.getAll();

        Map<String, List<User>> groupedNames = userList.parallelStream()
                .collect(Collectors.groupingBy(User::getFirstname));
        return groupedNames.keySet().stream().collect(Collectors.toList());
    }




    @Override
    @Transactional
    public List<User> getAll() {
        List<User> userList = this.userDal.getAll();


        return userList;
    }

    @Override
    @Transactional
    public void add(User user) {

    this.userDal.add(user);

    }

    @Override
    @Transactional
    public void update(User user) {

    this.userDal.update(user);

    }

    @Override
    @Transactional
    public void delete(User user) {

    this.userDal.delete(user);

    }

    @Override
    @Transactional
    public User getUserById(int userId) {


        return this.userDal.getUserById(userId);
    }
}

