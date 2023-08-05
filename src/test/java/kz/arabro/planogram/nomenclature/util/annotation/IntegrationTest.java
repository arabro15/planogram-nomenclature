package kz.arabro.planogram.nomenclature.util.annotation;

import kz.arabro.planogram.nomenclature.util.extention.PostgresExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ActiveProfiles("test")
@ExtendWith({ SpringExtension.class, PostgresExtension.class })
@SpringBootTest
public @interface IntegrationTest {
}