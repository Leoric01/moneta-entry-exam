package leoric.monetaentrytrial.services;

import leoric.monetaentrytrial.dtos.requests.TaskOneInput;
import leoric.monetaentrytrial.dtos.responses.ReversedText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class TransformTextServiceImpl implements TransformTextService {
    @Override
    public ReversedText reverseAndModify(TaskOneInput input) {

        // replace narusuje puvodni umisteni samohlasek ale na prikladu v zadani to vychazelo takto, bud je tedy spatne
        // priklad s resenim nebo formulace podminky(kde se původně vyskytovala písmena...). Konkretne:

        //Je     mi   fajn.
        //.NjaF iM ej

        // F je velke na pozici 4, coz vychazi na i v slove mi po redukci multiple whitespaces na jedno. Pokud bych
        // nebyl svazan zadanim, pridal bych jeste .trim()
        String stripedWhiteSpace = input.getInput().replaceAll(" {2,}", " ");

        // HashMap by mela byt rychlejsi nez List<> a if contains, ale nakladnejsi na pamet. Zalezi na ocekavani, pouziti,
        // infrastrukture a dle toho zvolit reseni. takto je celkova slozitost O(n), s Listem si myslim ze O(n²).
        HashMap<Integer, Boolean> upperCaseLettersMap = new HashMap<>();
        for (int i = 0; i < stripedWhiteSpace.length(); i++) {
            if (isVowel(stripedWhiteSpace.charAt(i))) {
                upperCaseLettersMap.put(i, true);
            }
        }
        StringBuilder reversed = new StringBuilder(stripedWhiteSpace).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            if (upperCaseLettersMap.getOrDefault(i, false)) {
                reversed.setCharAt(i, Character.toUpperCase(reversed.charAt(i)));
            } else {
                reversed.setCharAt(i, Character.toLowerCase(reversed.charAt(i)));
            }
        }
        ReversedText reversedText = new ReversedText();
        reversedText.setModifiedText(reversed.toString());
        return reversedText;
    }

    private boolean isVowel(char c) {
        // v zadani je uvedeno pouze aeiou, ale podpora cestiny -> proto vyjmenovano znovu s diakritikou.
        // Pokud by mělo platit i pro velká písmena, staci pridat Character.toLowerCase(c), popr dovyjmenovat natvrdo
        return "aeiouáéěíóúů".indexOf(c) != -1;
    }

}