package apidto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class ResultApi {

    @SerializedName("CODE")
    private String code;
    @SerializedName("MESSAGE")
    private String message;

}
