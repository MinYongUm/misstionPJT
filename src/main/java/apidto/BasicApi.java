package apidto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BasicApi {

    @SerializedName("TbPublicWifiInfo")
    private MainApi tbPublicWifiInfo;
}
