package com.izera2.canny;

import com.izera2.canny.exception.AccessDeniedException;

import static com.izera2.canny.impl.ActionImpl.*;
import static com.izera2.canny.impl.RuleImpl.ALL;
import static com.izera2.canny.impl.RuleImpl.NONE;
import static com.izera2.canny.impl.UserImpl.A_USER;

import com.izera2.canny.rule.Definition;
import com.izera2.canny.rule.Engine;
import org.junit.Test;

import java.util.Arrays;

public class TestAuthorization extends CannyTestCase {
    @Test
    public void testAllowTrue() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(CREATE)
                            .allow(ALL);
                }}
        ));
        assertTrue(Authorization.can(A_USER, CREATE));
    }

    @Test
    public void testAllowFalse() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(CREATE)
                            .allow(NONE);
                }}
        ));
        assertFalse(Authorization.can(A_USER, CREATE));
    }

    public void testAllowOrAnd() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(CREATE)
                            .allow(NONE)
                            .allow(ALL);
                    forAction(READ)
                            .allow(NONE, ALL);
                }}
        ));
        assertTrue(Authorization.can(A_USER, CREATE));
        assertFalse(Authorization.can(A_USER, READ));

    }

    public void testAllowMoreComplexAnd() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(CREATE)
                            .allow(NONE, ALL, ALL, ALL);
                    forAction(DELETE)
                            .allow(ALL, ALL, ALL);
                }}
        ));
        assertEquals(false, Authorization.can(A_USER, CREATE));
        assertEquals(true, Authorization.can(A_USER, DELETE));
    }

    public void testDeny() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(READ)
                            .deny(NONE)
                            .allow(ALL);

                    forAction(CREATE)
                            .allow(ALL)
                            .deny(NONE);

                    forAction(UPDATE)
                            .allow(ALL)
                            .deny(ALL);

                    forAction(DELETE)
                            .deny(ALL)
                            .allow(ALL);


                }}
        ));
        assertEquals(true, Authorization.can(A_USER, READ));
        assertEquals(true, Authorization.can(A_USER, CREATE));
        assertEquals(false, Authorization.can(A_USER, UPDATE));
        assertEquals(false, Authorization.can(A_USER, DELETE));
    }


    public void testDenyOrAnd() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(READ)
                            .deny(NONE)
                            .deny(ALL)
                            .allow(ALL);

                    forAction(CREATE)
                            .deny(ALL, NONE)
                            .allow(ALL);

                }}
        ));
        assertEquals(false, Authorization.can(A_USER, READ));
        assertEquals(true, Authorization.can(A_USER, CREATE));
    }

    public void testIsAuthorized() throws Exception {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(READ)
                            .allow(ALL);

                    forAction(CREATE)
                            .allow(NONE);

                }}
        ));

        assertEquals(true, Authorization.isAutorized(A_USER, READ));

        assertException(AccessDeniedException.class, new AssertException() {
            public void test() throws Exception {
                Authorization.isAutorized(A_USER, CREATE);
            }
        });

    }

    public void testGetActions() throws Exception {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(READ)
                            .allow(ALL);

                    forAction(CREATE)
                            .allow(ALL);

                    forAction(UPDATE)
                            .allow(NONE);

                    forAction(DELETE)
                            .allow(NONE);

                }}
        ));

        assertContains(Arrays.asList(READ, CREATE, UPDATE, DELETE), Authorization.getAllActions());
        assertContains(Arrays.asList(READ, CREATE), Authorization.getAvailableActions(A_USER));
        assertContains(Arrays.asList(UPDATE, DELETE), Authorization.getUnavailableActions(A_USER));

    }


    public void testRuleNot() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(READ)
                            .allow(NONE.not());


                    forAction(CREATE)
                            .deny(ALL.not());

                    forAction(UPDATE)
                            .allow(ALL.not())
                            .allow(NONE.not());

                    forAction(DELETE)
                            .deny(ALL)
                            .deny(NONE.not())
                            .allow(ALL.not(), NONE.not());

                }}
        ));
        assertEquals(true, Authorization.can(A_USER, READ));
        assertEquals(false, Authorization.can(A_USER, CREATE));
        assertEquals(true, Authorization.can(A_USER, UPDATE));
        assertEquals(false, Authorization.can(A_USER, DELETE));

    }

    public void testWhy() {
        Authorization.load(new Engine(
                new Definition() {{
                    forAction(READ, CREATE)
                            .allow(NONE, ALL, NONE, ALL)
                            .allow(ALL.not())
                            .deny(NONE.not(), ALL.not().not(), NONE);
                }}
        ));
        assertEquals("Always rejected AND Always valid* AND Always rejected AND Always valid* => FAILED\n" +
                "Always rejected => FAILED\n", Authorization.why(A_USER, READ));
        assertEquals("Always rejected AND Always valid* AND Always rejected AND Always valid* => FAILED\n" +
                "Always rejected => FAILED\n", Authorization.why(A_USER, CREATE));
    }

    public void testMultipleActions() {
        Authorization.load(new Engine(
                new Definition() {{

                    forAction(READ, CREATE)
                            .allow(ALL);

                    forAction(UPDATE, DELETE)
                            .allow(ALL, NONE.not())
                            .deny(NONE)
                            .deny(ALL);
                }}
        ));

        assertEquals(true, Authorization.can(A_USER, READ));
        assertEquals(true, Authorization.can(A_USER, CREATE));
        assertEquals(false, Authorization.can(A_USER, UPDATE));
        assertEquals(false, Authorization.can(A_USER, DELETE));
        assertEquals(Authorization.getEngine().getLaw(READ), Authorization.getEngine().getLaw(CREATE));
        assertEquals(Authorization.getEngine().getLaw(UPDATE), Authorization.getEngine().getLaw(DELETE));
        assertNotSame(Authorization.getEngine().getLaw(READ), Authorization.getEngine().getLaw(DELETE));
        
    }


}
