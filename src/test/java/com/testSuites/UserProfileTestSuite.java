package com.testSuites;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cqu.pages.UserProfilePage;

public class UserProfileTestSuite extends LoginToStudentPortalTestSuite {

    @Test
    public void checkGPA() {
        var userProfilePage = new UserProfilePage(driver).clickStudyMenuu();
        // var useP = open(UserProfilePage.class);

        assertEquals("5.875 ", userProfilePage.getGPA());

    }

    @Test
    public void checkSomething() {

    }
}
