package tech.getarays.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarays.backend.dto.MacBoardDto;
import tech.getarays.backend.model.Data;
import tech.getarays.backend.repository.DataRepository;

import java.util.List;

@Service
public class DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Data findNewest(String boardMac){
        return dataRepository.getNewest(boardMac);
    }
    public Data addData(Data data){
        return dataRepository.save(data);
    }
    public Data updateData(Data data){
        data.setId(dataRepository.getIdByMac(data.getBoard_mac()));
        return dataRepository.save(data);
    }

    public List<Data> dataFromTimeStamps(Long timeId, String boardMac){
        return dataRepository.dataFromTimeStamps(timeId, boardMac);
    }

    public MacBoardDto lastSelectedBoard(){
       return new MacBoardDto(dataRepository.lastSelectedBoard());
    }
}
