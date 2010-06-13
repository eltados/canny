package com.izera2.canny.utils;

import com.izera2.canny.interfaces.User;
import com.izera2.canny.rule.Rule;

public class RulesUtils {
    public static Rule Not(final Rule rule) {
        return new Rule(rule.getNotErrorMessage(), rule.getErrorMessage()) {
            public boolean can(User user, Object object) {
                return !rule.can(user, object);
            }
        };
    }

    public static Rule Combine(final Rule... rules) {
        return new Rule("Combined " + rules.toString()) {
            public boolean can(User user, Object object) {
                for (Rule rule : rules) {
                    if (!rule.can(user, object))
                        return false;
                }
                return true;
            }
        };
    }
}
