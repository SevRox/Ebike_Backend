package tech.getarays.Ebike.Backend.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    private final DataRepo dataRepo;

    @Autowired
    public DataService(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    public Data findNewest(String boardMac){
        return dataRepo.getNewest(boardMac);
    }
    public Data addData(Data data){
        return dataRepo.save(data);
    }
    public Data updateData(Data data){
        data.setId(dataRepo.getIdByMac(data.getBoard_mac()));
        return dataRepo.save(data);
    }

    public List<Data> dataFromTimeStamps(Long timeId, String boardMac){
        return dataRepo.dataFromTimeStamps(timeId, boardMac);
    }
}
