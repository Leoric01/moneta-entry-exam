package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.repositories.ActionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionHistoryServiceImpl implements ActionHistoryService {
    private final ActionHistoryRepository actionHistoryRepository;

}