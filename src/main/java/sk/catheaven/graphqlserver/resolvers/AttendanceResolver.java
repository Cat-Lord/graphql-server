package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.attendance.NewCatch;
import sk.catheaven.graphqlserver.domain.persistence.Attendance;
import sk.catheaven.graphqlserver.domain.persistence.Catch;
import sk.catheaven.graphqlserver.domain.persistence.Fish;
import sk.catheaven.graphqlserver.domain.persistence.FishingGround;
import sk.catheaven.graphqlserver.repository.AttendanceRepository;
import sk.catheaven.graphqlserver.repository.CatchRepository;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceResolver implements GraphQLMutationResolver {
    private CatchRepository catchRepository;
    private AttendanceRepository attendanceRepository;

    public Long addAttendance(Long fishingGroundID, Integer totalVisits, NewCatch[] newCatches) {
        FishingGround fishingGround = FishingGround.builder().id(fishingGroundID).build();
        Attendance attendance = Attendance.builder()
                    .fishingGround(fishingGround)
                .numberOfVisits(totalVisits)
                .build();

        List<Catch> catches = Arrays
                .stream(newCatches)
                .map((newCatch) ->
                    Catch.builder()
                        .fish(
                            Fish.builder().id(newCatch.getFishID()).build()
                        )
                        .totalAmount(newCatch.getTotalAmount())
                        .totalWeight(newCatch.getTotalWeight())
                        .attendance(attendance)
                        .build())
                .toList();
        attendance.setCatches(catches);

        return attendanceRepository.save(attendance).getId();
    }


}
