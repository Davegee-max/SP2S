package Data;

import java.sql.Timestamp;
import java.util.List;

public interface EKGDAO {
    void save(EKGDTO ekgdto);

    List<EKGDTO> load(Timestamp time);

}
