package top.felixu.annotation;

import java.lang.annotation.*;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Select {
    String value();
}
