package pl.tsmiatacz.mockapi.service;

import org.springframework.stereotype.Component;
import pl.tsmiatacz.mockapi.data.HistoryData;

import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryService {

    private final List<HistoryData> historyData = new ArrayList<>();

    public void add(HistoryData data) {
        historyData.add(data);
    }

    public List<HistoryData> list() {
        return historyData;
    }
}
