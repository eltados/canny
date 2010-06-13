package com.izera2.canny.rule;

import com.izera2.canny.Authorization;
import com.izera2.canny.CannyTestCase;
import com.izera2.canny.exception.AccessDeniedException;
import com.izera2.canny.interfaces.User;
import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import java.util.Arrays;

import static com.izera2.canny.impl.ActionImpl.*;
import static com.izera2.canny.impl.RuleImpl.ALL;
import static com.izera2.canny.impl.RuleImpl.NONE;
import static com.izera2.canny.impl.UserImpl.A_USER;

public class TestRule extends CannyTestCase {

    private Rule rule;

    @Before()
    public void setUp() {
        rule = new Rule("errorMessage", "notErrorMessage") {
            public boolean can(User user, Object object) {
                return true;
            }
        };
    }

    @Test
    public void testRule() {
        assertEquals("errorMessage", rule.getErrorMessage());
        assertEquals("notErrorMessage", rule.getNotErrorMessage());
        assertEquals(rule.getErrorMessage(), rule.not().getNotErrorMessage());
        assertEquals(rule.not().getErrorMessage(), rule.getNotErrorMessage());


    }

    


}