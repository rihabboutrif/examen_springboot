package com.example.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.HistoriqueAction;
import com.example.demo.models.User;
import com.example.demo.repositories.HistoriqueActionRepository;

@Service
public class LogService {
    @Autowired private HistoriqueActionRepository historiqueRepo;

    public void log(User user, String message) {
        HistoriqueAction h = new HistoriqueAction();
        h.setUtilisateur(user);
        h.setAction(message);
        h.setDate(LocalDateTime.now());
        historiqueRepo.save(h);
    }

    public List<Map<String, Object>> getActionCountsLast7Days() {
    List<Map<String, Object>> result = new ArrayList<>();
    LocalDate today = LocalDate.now();

    for (int i = 6; i >= 0; i--) {
        LocalDate date = today.minusDays(i);
        long count = historiqueRepo.countByDate(date);
        Map<String, Object> entry = new HashMap<>();
        entry.put("day", date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.FRENCH)); // eg. "Lun"
        entry.put("count", count);
        result.add(entry);
    }

    return result;
}

}