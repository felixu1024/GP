package top.felixu.annotation;

import java.lang.annotation.*;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    Signature[] value();
}
