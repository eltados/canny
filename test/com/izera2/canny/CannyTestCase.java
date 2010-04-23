package com.izera2.canny;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mathieu
 * Date: 23-Apr-2010
 * Time: 14:17:16
 * To change this template use File | Settings | File Templates.
 */
public class CannyTestCase extends TestCase {

   protected static void assertContains(List expected , List actual ){
      assertTrue("we expected <"+expected+"> but was <"+actual+">", expected.containsAll(actual));

   }

   protected static void assertException(Class expectedException, AssertException block) {
      assertException(expectedException, null, block);
   }
   protected static void assertException(Class expectedException, String expectedMessage, AssertException block) {
      try {
         block.test();
         fail("exception should have been thrown");
      } catch (Exception e) {
         assertTrue("we expected a "+expectedException.getSimpleName()+" but got a "+e.getClass().getSimpleName(),expectedException.isInstance(e));
         if(expectedMessage!=null)
            assertEquals("exception message are differents",expectedMessage,e.getMessage());
      }
   }

   protected abstract class AssertException {
      public abstract void test() throws Exception;
   }
}
