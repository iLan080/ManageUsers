package com.ManageUsers.ManageUsers.Business;

import com.ManageUsers.ManageUsers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class PerformanceTester {

    @Autowired
    private IUserService userManager;

    public void testDuplicateNameMethods() {
        long startTime1 = System.nanoTime();
       List<String> UniqueNames = userManager.getUniqueNamesDistinct();
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1);

        long startTime2 = System.nanoTime();
        Set<String> UniqueNames1 = userManager.getUniqueNamesSet();
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);


        long startTime3 = System.nanoTime();
        List<String> UniqueNames2 = userManager.getUniqueNamesGroupCon();
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3);

        long startTime4 = System.nanoTime();
        List<String> UniqueNames3 = userManager.getUniqueNamesGroup();
        long endTime4 = System.nanoTime();
        long duration4 = (endTime4 - startTime4);




        System.out.println("UniqueNamesDistinct süresi:       " + duration1 + " nanosaniye");
        System.out.println("UniqueNamesSet süresi:            " + duration2 + " nanosaniye");
        System.out.println("UniqueNamesGroupConcurent süresi: " + duration3 + " nanosaniye");
        System.out.println("UniqueNamesGroupBy süresi:        " + duration4 + " nanosaniye");



        System.out.println("UniqueNamesDistinct sonucu:       " + UniqueNames);
        System.out.println("UniqueNamesSet sonucu:            " + UniqueNames1);
        System.out.println("UniqueNamesGroupConcurent sonucu: " + UniqueNames2);
        System.out.println("UniqueNamesGroupBy sonucu:        " + UniqueNames3);


    }

    public void testDuplicateNameMethods1() {
        long startTime1 = System.nanoTime();
        Map<String, List<User>> DuplicateNamesG = userManager.getDuplicateNames();
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1);

        long startTime2 = System.nanoTime();
        Map<String, List<User>>  DuplicateNamesGC = userManager.getDuplicateNames1();
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);


        long startTime3 = System.nanoTime();
        Map<String, List<User>> DuplicateNamesGP = userManager.getDuplicateNamesP();
        long endTime3 = System.nanoTime();
         long duration3 = (endTime3 - startTime3);

         long startTime4 = System.nanoTime();
        Map<String, List<User>> DuplicateNamesGCP = userManager.getDuplicateNames1P();
        long endTime4 = System.nanoTime();
        long duration4 = (endTime4 - startTime4);




        System.out.println("DuplicateNamesG süresi:  " + duration1 + " nanosaniye");
        System.out.println("DuplicateNamesGC süresi: " + duration2 + " nanosaniye");
        System.out.println("Paralel ---------");
        System.out.println("DuplicateNamesGP süresi: " + duration3 + " nanosaniye");
        System.out.println("DuplicateNamesGCP        " + duration4 + " nanosaniye");



        System.out.println("DuplicateNamesG sonucu:       " + DuplicateNamesG);
        System.out.println("DuplicateNamesGC sonucu:            " + DuplicateNamesGC);
        System.out.println("UniqueNamesGroupConcurent sonucu: " + DuplicateNamesGP);
        System.out.println("UniqueNamesGroupBy sonucu:        " + DuplicateNamesGCP);


    }

    public void testDuplicateNameMethodsP() {
        long startTime1 = System.nanoTime();
        List<String> UniqueNamesP = userManager.getUniqueNamesDistinctP();
        long endTime1 = System.nanoTime();
        long duration1p = (endTime1 - startTime1);

        long startTime2 = System.nanoTime();
        Set<String> UniqueNames1P = userManager.getUniqueNamesSetP();
        long endTime2 = System.nanoTime();
        long duration2p = (endTime2 - startTime2);


        long startTime3 = System.nanoTime();
        List<String> UniqueNames2P = userManager.getUniqueNamesGroupConP();
        long endTime3 = System.nanoTime();
        long duration3p = (endTime3 - startTime3);

        long startTime4 = System.nanoTime();
        List<String> UniqueNames3P = userManager.getUniqueNamesGroupP();
        long endTime4 = System.nanoTime();
        long duration4p = (endTime4 - startTime4);

        long startTime5 = System.nanoTime();
        List<String> UniqueNames = userManager.getUniqueNamesDistinct();
        long endTime5 = System.nanoTime();
        long duration5 = (endTime5 - startTime5);

        long startTime6 = System.nanoTime();
        Set<String> UniqueNames1 = userManager.getUniqueNamesSet();
        long endTime6 = System.nanoTime();
        long duration6 = (endTime6 - startTime6);


        long startTime7 = System.nanoTime();
        List<String> UniqueNames2 = userManager.getUniqueNamesGroupCon();
        long endTime7 = System.nanoTime();
        long duration7 = (endTime7 - startTime7);

        long startTime8 = System.nanoTime();
        List<String> UniqueNames3 = userManager.getUniqueNamesGroup();
        long endTime8 = System.nanoTime();
        long duration8 = (endTime8 - startTime8);




        System.out.println("UniqueNamesDistinct süresi:       " + duration5 + " nanosaniye");
        System.out.println("UniqueNamesSet süresi:            " + duration6 + " nanosaniye");
        System.out.println("UniqueNamesGroupConcurent süresi: " + duration7 + " nanosaniye");
        System.out.println("UniqueNamesGroupBy süresi:        " + duration8 + " nanosaniye");
        System.out.println("Paralel------   ");
        System.out.println("UniqueNamesDistinctP süresi:       " + duration1p + " nanosaniye");
        System.out.println("UniqueNamesSetP süresi:            " + duration2p + " nanosaniye");
        System.out.println("UniqueNamesGroupConcurentP süresi: " + duration3p + " nanosaniye");
        System.out.println("UniqueNamesGroupByP süresi:        " + duration4p + " nanosaniye");






       // System.out.println("UniqueNamesDistinctP sonucu:       " + UniqueNamesP);
        //System.out.println("UniqueNamesSetP sonucu:            " + UniqueNames1P);
        //System.out.println("UniqueNamesGroupConcurentP sonucu: " + UniqueNames2P);
        //System.out.println("UniqueNamesGroupByP sonucu:        " + UniqueNames3P);


    }


}
