package leoric.monetaentrytrial;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ReversedText;
import leoric.monetaentrytrial.services.TransformTextServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransformTextServiceImplTest {

    @InjectMocks
    private TransformTextServiceImpl service;

    @Test
    void testBasicTransformation() {
        TaskOneInput input = new TaskOneInput("Ahoj, jak se máš?");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("?šÁm es kaj ,jOha", result.getModifiedText());
    }

    @Test
    void testMultipleSpaces() {
        TaskOneInput input = new TaskOneInput("Je     mi   fajn.");
        ReversedText result = service.reverseAndModify(input);
        assertEquals(".NjaF iM ej", result.getModifiedText());
    }

    @Test
    void testAllVowels() {
        TaskOneInput input = new TaskOneInput("aeiou AEIOU");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("UOIEA uoiea", result.getModifiedText());
    }

    @Test
    void testNoVowels() {
        TaskOneInput input = new TaskOneInput("qwrt");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("trwq", result.getModifiedText());
    }

    @Test
    void testEmptyString() {
        TaskOneInput input = new TaskOneInput("");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("", result.getModifiedText());
    }

    @Test
    void testOnlySpaces() {
        TaskOneInput input = new TaskOneInput("     ");
        ReversedText result = service.reverseAndModify(input);
        assertEquals(" ", result.getModifiedText());
    }

    @Test
    void testSingleCharacterVowel() {
        TaskOneInput input = new TaskOneInput("a");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("A", result.getModifiedText());
    }

    @Test
    void testSingleCharacterNotVowel() {
        TaskOneInput input = new TaskOneInput("c");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("c", result.getModifiedText());
    }

    @Test
    void testMixedCase() {
        TaskOneInput input = new TaskOneInput("MiXeD cAsE");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("eSaC dexim", result.getModifiedText());
    }

    @Test
    void testSpecialCharacters() {
        TaskOneInput input = new TaskOneInput("Hello, World!");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("!DlrOw ,Olleh", result.getModifiedText());
    }

    @Test
    void testSpecialCharacters2() {
        TaskOneInput input = new TaskOneInput("e!E!");
        ReversedText result = service.reverseAndModify(input);
        assertEquals("!e!e", result.getModifiedText());
    }

    @Test
    void testWhitespaceAtStartAndEnd() {
        TaskOneInput input = new TaskOneInput("  surrounding spaces  ");
        ReversedText result = service.reverseAndModify(input);
        assertEquals(" sEcaPS gNidnuoRrUs ", result.getModifiedText());
    }
}