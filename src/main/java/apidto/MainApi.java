package apidto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class MainApi {
    @SerializedName("list_total_count")
    private int totalcount;

    @SerializedName("RESULT")
    private ResultApi result;

    @SerializedName("row")
    private List<RowApi> rowApis;

    public List<RowApi> getRowApis() {
        return rowApis;
    }
}
