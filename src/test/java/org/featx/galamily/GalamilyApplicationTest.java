package org.featx.galamily;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GalamilyApplicationTest {

    GalamilyApplication application;

    GalamilyApplicationTest() {
        application = new GalamilyApplication();
    }

    @Test
    void testRun() {
        List<String> commandList = new ArrayList<>();
        commandList.add("glob is I");
        commandList.add("prok is V");
        commandList.add("pish is X");
        commandList.add("tegj is L");
        commandList.add("glob glob Silver is 34 Credits");
        commandList.add("glob prok Gold is 57800 Credits");
        commandList.add("pish pish Iron is 3910 Credits");
        commandList.add("how much is pish tegj glob glob ?");
        commandList.add("how many Credits is glob prok Silver ?");
        commandList.add("how many Credits is glob prok Gold ?");
        commandList.add("how many Credits is glob prok Iron ?");
        commandList.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        List<String> result = application.run(commandList);
        List<String> expectedList = new ArrayList<>();
        for(int i =0; i < 7; i++) {
            expectedList.add("");
        }
        expectedList.add("pish tegj glob glob is 42");
        expectedList.add("68.0 Credits is glob prok Silver ");
        expectedList.add("57800.0 Credits is glob prok Gold ");
        expectedList.add("782.0 Credits is glob prok Iron ");
        expectedList.add("I have no idea what you are talking about");
        assertEquals(expectedList, result);
    }

    @Test
    void testRun2() {
        List<String> commandList = new ArrayList<>();
        commandList.add("I is zncj");
        commandList.add("prok is V");
        commandList.add("pish is X");
        commandList.add("tegj is L");
        commandList.add("qcki is C");
        commandList.add("D is mert");
        commandList.add("M is youm");
        commandList.add("zncj zncj Bean is qcki pish CNY");
        commandList.add("zncj prok Salt is tegj prok USD");
        commandList.add("pish pish Oil is 3910 JPY");
        commandList.add("qcki youm tegj USD is prok JPY");
        commandList.add("prok zncj USD is youm CNY");
        commandList.add("how much is youm qcki youm prok ?");
        commandList.add("how many CNY is zncj prok Salt ?");
        commandList.add("how many USD is zncj prok Oil ?");
        commandList.add("how many JPY is zncj prok Bean ?");
        commandList.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        List<String> result = application.run(commandList);
        List<String> expectedList = new ArrayList<>();
        for(int i =0; i < 12; i++) {
            expectedList.add("");
        }
        expectedList.add("youm qcki youm prok is 1,905");
        expectedList.add("9166.666666666666 CNY is zncj prok Salt ");
        expectedList.add("148580.0 USD is zncj prok Oil ");
        expectedList.add("0.006947368421052632 JPY is zncj prok Bean ");
        expectedList.add("I have no idea what you are talking about");
        assertEquals(expectedList, result);
    }
}
