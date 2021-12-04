package org.leverx.dealerstat.exceptions;

public class RedisAlreadyHasKeyException extends RuntimeException {
    private final static String REDIS_ALREADY_HAS_KEY_PATTERN =
            "Redis already contains key";

    public RedisAlreadyHasKeyException() {
        super(REDIS_ALREADY_HAS_KEY_PATTERN);
    }
}
