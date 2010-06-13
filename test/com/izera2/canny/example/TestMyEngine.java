package com.izera2.canny.example;

import com.izera2.canny.example.vo.PostVO;
import com.izera2.canny.example.vo.UserVO;
import com.izera2.canny.rule.Engine;
import junit.framework.TestCase;
import org.junit.Test;

public class TestMyEngine extends TestCase {

    private Engine engine = MyEngine.getInstance();
    private UserVO admin = new UserVO(true, false, false);
    private UserVO author1 = new UserVO(false, true, false);
    private UserVO author2 = new UserVO(false, true, false);
    private UserVO spammer = new UserVO(false, false, true);
    private UserVO guest = new UserVO(false, false, false);
    private PostVO post;


    @Test
    public void testCreatePostRule() {
        assertEquals(true, engine.can(admin, MyActions.CREATE_POST));
        assertEquals(true, engine.can(author1, MyActions.CREATE_POST));
        assertEquals(true, engine.can(author2, MyActions.CREATE_POST));
        assertEquals(false, engine.can(guest, MyActions.CREATE_POST));
        assertEquals(false, engine.can(spammer, MyActions.CREATE_POST));
    }

    @Test
    public void testDeletePostRule() {
        post = new PostVO();
        post.setAuthor(author1);

        assertEquals(true, engine.can(admin, MyActions.DELETE_POST, post));
        assertEquals(true, engine.can(author1, MyActions.DELETE_POST, post));
        assertEquals(false, engine.can(author2, MyActions.DELETE_POST, post));
        assertEquals(false, engine.can(guest, MyActions.DELETE_POST, post));
        assertEquals(false, engine.can(spammer, MyActions.DELETE_POST, post));
    }

    @Test
    public void testViewPostRule() {
        assertEquals(true, engine.can(admin, MyActions.VIEW_POST, post));
        assertEquals(true, engine.can(author1, MyActions.VIEW_POST, post));
        assertEquals(true, engine.can(author2, MyActions.VIEW_POST, post));
        assertEquals(true, engine.can(guest, MyActions.VIEW_POST, post));
        assertEquals(true, engine.can(spammer, MyActions.VIEW_POST, post));
    }

    @Test
    public void testCommentPostRule() {
        post = new PostVO();
        post.setAuthor(author1);

        assertEquals(true, engine.can(admin, MyActions.COMMENT_POST, post));
        assertEquals(true, engine.can(author1, MyActions.COMMENT_POST, post));
        assertEquals(true, engine.can(author2, MyActions.COMMENT_POST, post));
        assertEquals(true, engine.can(guest, MyActions.COMMENT_POST, post));
        assertEquals(false, engine.can(spammer, MyActions.COMMENT_POST, post));
    }

    @Test
    public void testPromotePostRule() {
        post = new PostVO();
        post.setAuthor(author1);

        assertEquals(false, engine.can(admin, MyActions.PROMOTE_POST, post));
        assertEquals(false, engine.can(author1, MyActions.PROMOTE_POST, post));
        assertEquals(true, engine.can(author2, MyActions.PROMOTE_POST, post));
        assertEquals(false, engine.can(guest, MyActions.PROMOTE_POST, post));
        assertEquals(false, engine.can(spammer, MyActions.PROMOTE_POST, post));
    }
}