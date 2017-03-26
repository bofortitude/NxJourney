package AssistanTFx;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by bofei on 1/20/2017.
 */
public class DefaultVariable {
    public String currentJarPath;

    public DefaultVariable(){
        String tmpCurrentJarPath = DefaultVariable.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            this.currentJarPath = URLDecoder.decode(tmpCurrentJarPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            this.currentJarPath = tmpCurrentJarPath;
            e.printStackTrace();
        }

    }
}
