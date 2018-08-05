package com.zwemmen.psv.event.competition;

import com.zwemmen.psv.generic.BaseIntegrationTest;
import com.zwemmen.psv.meet.BaseMeetService;
import com.zwemmen.psv.meet.Distance;
import com.zwemmen.psv.meet.Meet;
import com.zwemmen.psv.meet.Stroke;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * SwimmerService Integration Tests.
 *
 * @author afernandez
 */
public class CompetitionServiceIT extends BaseIntegrationTest {

    @Autowired
    private BaseCompetitionService competitionService;
    @Autowired
    private BaseMeetService meetService;

    private Competition testCompetition;
    private Meet testMeet;

    @Override
    public void init() throws Exception {
        testCompetition = competitionService.save(createCompetition());
        testMeet = meetService.save(createMeet());
    }

    @Override
    public void cleanUp() throws Exception {
        competitionService.delete(competitionService.findOne(testCompetition.getId()));
    }

    @Test
    public void testAddMeetToCompetition() throws Exception {
//        competitionService.addMeet(testMeet.getId(), testCompetition.getNumber());
//
//        validateCompetitionWithNewMeet();
    }

//    private void validateCompetitionWithNewMeet() {
//        final Competition competition = competitionService.findCompetitionWithMeets(testCompetition.getNumber());
//
//        assertEquals(1, competition.getMeets().size());
//        assertTrue(competition.getMeets().contains(testMeet));
//    }

    private Competition createCompetition() {
        return new Competition.Builder()
                .number(10)
                .course(RaceCourse.LONG)
                .place("Eindhoven")
                .startDate(LocalDate.of(2017, 7, 15))
                .endDate(LocalDate.of(2017, 7, 17))
                .build();
    }

    private Meet createMeet() {
        return new Meet.Builder()
                .programNumber(1)
                .distance(Distance.ONE_HUNDRED)
                .stroke(Stroke.BREASTSTROKE)
                .ageGroup("15-15")
                .build();
    }
}