package leoric.monetaentrytrial;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ModifiedText;
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
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("?šÁm es kaj ,jOha", result.getModifiedText());
    }

    @Test
    void testMultipleSpaces() {
        TaskOneInput input = new TaskOneInput("Je     mi   fajn.");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals(".NjaF iM ej", result.getModifiedText());
    }

    @Test
    void testAllVowels() {
        TaskOneInput input = new TaskOneInput("aeiou AEIOU");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("UOIEA uoiea", result.getModifiedText());
    }

    @Test
    void testNoVowels() {
        TaskOneInput input = new TaskOneInput("qwrt");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("trwq", result.getModifiedText());
    }

    @Test
    void testEmptyString() {
        TaskOneInput input = new TaskOneInput("");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("", result.getModifiedText());
    }

    @Test
    void testOnlySpaces() {
        TaskOneInput input = new TaskOneInput("     ");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals(" ", result.getModifiedText());
    }

    @Test
    void testSingleCharacterVowel() {
        TaskOneInput input = new TaskOneInput("a");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("A", result.getModifiedText());
    }

    @Test
    void testSingleCharacterNotVowel() {
        TaskOneInput input = new TaskOneInput("c");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("c", result.getModifiedText());
    }

    @Test
    void testMixedCase() {
        TaskOneInput input = new TaskOneInput("MiXeD cAsE");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("eSaC dexim", result.getModifiedText());
    }

    @Test
    void testSpecialCharacters() {
        TaskOneInput input = new TaskOneInput("Hello, World!");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("!DlrOw ,Olleh", result.getModifiedText());
    }

    @Test
    void testSpecialCharacters2() {
        TaskOneInput input = new TaskOneInput("e!E!");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals("!e!e", result.getModifiedText());
    }

    @Test
    void testWhitespaceAtStartAndEnd() {
        TaskOneInput input = new TaskOneInput("  surrounding spaces  ");
        ModifiedText result = service.reverseAndModify(input);
        assertEquals(" sEcaPS gNidnuoRrUs ", result.getModifiedText());
    }
}