package com.maple.common.support;

import com.maple.common.exception.ErrorCode;
import com.maple.common.exception.MapleBossException;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableTypeAssert;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public final class MapleBossExceptionTest {

    private final ErrorCode errorCode;
    private final ThrowableTypeAssert<MapleBossException> throwableTypeAssert;

    public MapleBossExceptionTest(ErrorCode errorCode, ThrowableTypeAssert<MapleBossException> throwableTypeAssert) {
        this.errorCode = errorCode;
        this.throwableTypeAssert = throwableTypeAssert;
    }

    public static MapleBossExceptionTest assertThatMapleBossException(ErrorCode errorCode) {
        return new MapleBossExceptionTest(errorCode, assertThatExceptionOfType(MapleBossException.class));
    }

    public void isThrownBy(ThrowableAssert.ThrowingCallable throwingCallable) {
        throwableTypeAssert.isThrownBy(throwingCallable).withMessage(errorCode.getMessage());
    }
}
