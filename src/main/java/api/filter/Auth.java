package api.filter;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by guofan on 2015/5/7.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
}