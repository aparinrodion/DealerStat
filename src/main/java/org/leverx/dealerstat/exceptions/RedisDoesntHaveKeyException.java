package org.leverx.dealerstat.exceptions;

public class RedisDoesntHaveKeyException extends RuntimeException {
    private final static String REDIS_DOESNT_HAVE_KEY_PATTERN =
            "Redis doesnt have key %s";

    public RedisDoesntHaveKeyException(String key) {
        super(String.format(REDIS_DOESNT_HAVE_KEY_PATTERN, key));
    }

}
