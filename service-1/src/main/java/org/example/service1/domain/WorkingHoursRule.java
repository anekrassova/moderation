package org.example.service1.domain;

import org.example.service1.entity.RequestCategory;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Set;

public class WorkingHoursRule {
    private static final LocalTime WORK_START = LocalTime.of(9, 0);
    private static final LocalTime WORK_END = LocalTime.of(18, 0);

    private static final Set<RequestCategory> REQUIRES_WORKING_HOURS = Set.of(
            RequestCategory.DELIVERY,
            RequestCategory.ACCOUNT,
            RequestCategory.OTHER
    );

    private static boolean isWorkingTime(Instant createdAt){
        LocalTime time = createdAt.atZone(ZoneId.systemDefault()).toLocalTime();

        return !time.isBefore(WORK_START) && time.isBefore(WORK_END);
    }

    public static boolean isAllowed(RequestCategory category, Instant createdAt){
        if(!REQUIRES_WORKING_HOURS.contains(category)){
            return true;
        }

        return isWorkingTime(createdAt);
    }
}
