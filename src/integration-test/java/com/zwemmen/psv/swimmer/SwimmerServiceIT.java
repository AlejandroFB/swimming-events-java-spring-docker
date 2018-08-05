package com.zwemmen.psv.swimmer;

import com.zwemmen.psv.event.competition.BaseCompetitionService;
import com.zwemmen.psv.event.competition.Competition;
import com.zwemmen.psv.event.competition.RaceCourse;
import com.zwemmen.psv.generic.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * SwimmerService Integration Tests.
 *
 * @author afernandez
 */
public class SwimmerServiceIT extends BaseIntegrationTest {

    @Autowired
    private BaseCompetitionService competitionService;
    @Autowired
    private BaseSwimmerService swimmerService;

    private Swimmer testSwimmer;
    private Competition testCompetition;

    @Override
    public void init() throws Exception {
        final SwimmerProfile swimmerProfile = createSwimmerProfile();
        Swimmer swimmer = createSwimmer(swimmerProfile);
        Competition competition = createCompetition();

//        testSwimmer = swimmerService.save(swimmer);
//        testCompetition = competitionService.save(competition);
    }

    @Override
    public void cleanUp() throws Exception {
//        swimmerService.delete(swimmerService.findOne(testSwimmer.getId()));
//        competitionService.delete(competitionService.findOne(testCompetition.getId()));
    }

    @Test
    public void testRegisterAndRemoveOneCompetition() throws Exception {
//        swimmerService.registerCompetition(testSwimmer.getId(), testCompetition.getId());
//        validateRelationship();
//
//        swimmerService.deleteCompetition(testSwimmer.getId(), testCompetition.getId());
//        validateRemovedCompetition();
    }

//    private void validateRelationship() {
//        final Swimmer swimmer = swimmerService.findAllByCompetitionId(testSwimmer.getId());
//        final Competition competition = competitionService.findCompetitionWithSwimmers(testCompetition.getId());
//
//        // Check competition in swimmer
//        assertEquals(1, swimmer.getCompetitions().size());
//        assertTrue(swimmer.getCompetitions().contains(competition));
//
//        // Check swimmer in competition
//        assertEquals(1, competition.getSwimmers().size());
//        assertTrue(competition.getSwimmers().contains(swimmer));
//    }
//
//    private void validateRemovedCompetition() {
//        final Swimmer swimmer = swimmerService.findAllByCompetitionId(testSwimmer.getId());
//        final Competition competition = competitionService.findCompetitionWithSwimmers(testCompetition.getId());
//
//        // Check competition in swimmer
//        assertEquals(0, swimmer.getCompetitions().size());
//        assertFalse(swimmer.getCompetitions().contains(competition));
//
//        // Check swimmer in competition
//        assertEquals(0, competition.getSwimmers().size());
//        assertFalse(competition.getSwimmers().contains(swimmer));
//    }

    private SwimmerProfile createSwimmerProfile() {
        return new SwimmerProfile.Builder()
                .emailAddress("afernandez@gmail.com")
                .address("Hoge Tom 24, 5673LX Nuenen")
                .city("Nuenen")
                .phone("040 356835")
                .mobilePhone("0647709546")
                .name("Alejandro Fernandez")
                .birthday(LocalDate.of(1995, 4, 15))
                .build();
    }

    private Swimmer createSwimmer(SwimmerProfile swimmerProfile) {
        return new Swimmer.Builder()
                .number(1)
                .club("PSV")
                .registrationId(12345L)
                .userName("afernandez")
                .level(Level.B)
                .encryptedPassword("EncryptedPassword")
                .swimmerProfile(swimmerProfile)
                .build();
    }

    private Competition createCompetition() {
        return new Competition.Builder()
                .number(10)
                .course(RaceCourse.LONG)
                .place("Eindhoven")
                .startDate(LocalDate.of(2017, 7, 15))
                .endDate(LocalDate.of(2017, 7, 17))
                .build();
    }
}